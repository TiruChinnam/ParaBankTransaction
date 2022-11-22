package com.parabank.tests;

import com.parabank.pageObjects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.parabank.pageObjects.LoginPage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParaBankTests {

        WebDriver driver;
        String username;

    @BeforeMethod
    public void openUrl(){
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://parabank.parasoft.com/parabank/index.htm");
       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test(groups = {"Sanity"}, priority = 1)
    public void registerNewAccount() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
       String actualTitle = loginPage.getRegistrationTitle();
        Assert.assertEquals(actualTitle, "Signing up is easy!");
        List<WebElement> selectedList=loginPage.form2Fields();

        List<String> lstSelectedItem=new ArrayList<String>();
        for(int i=0;i<selectedList.size();i++){
            System.out.println(selectedList.get(i).getText());
            lstSelectedItem.add(selectedList.get(i).getText());
        }


        List<String> expectedList = new ArrayList<String>();
        expectedList.add("First Name:");
        expectedList.add("Last Name:");
        expectedList.add("Address:");
        expectedList.add("City:");
        expectedList.add("State:");
        expectedList.add("Zip Code:");
        expectedList.add("Phone #:");
        expectedList.add("SSN:");
        expectedList.add("Username:");
        expectedList.add("Password:");
        expectedList.add("Confirm:");
        Assert.assertEquals(lstSelectedItem, expectedList);
       username = RandomStringUtils.randomAlphabetic(5);
        System.out.println("Generated username is : "+username);
        loginPage.fillForm(username);
        loginPage.clickRegisterButton();
        String successMessage = loginPage.getRegistrationSuccessMessage();
        Assert.assertEquals(successMessage, "Your account was created successfully. You are now logged in.");
    }
    @Test(groups = {"Regression"}, dependsOnGroups = {"Sanity"}, priority = 2)
    public void registerNewAccountExistingUser() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        String actualTitle = loginPage.getRegistrationTitle();
        Assert.assertEquals(actualTitle,"Signing up is easy!");
        loginPage.fillForm(username);
        loginPage.clickRegisterButton();
        String errorMessage = loginPage.getRegistrationErrorMessage();

        Assert.assertEquals(errorMessage, "This username already exists.");
    }

    @Test(groups = {"Regression"}, dependsOnGroups = {"Sanity"}, priority = 3)
    public void registerNewAccountEmptyData() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        String actualTitle = loginPage.getRegistrationTitle();
        Assert.assertEquals(actualTitle,"Signing up is easy!");
        loginPage.fillFormEmpty();
        loginPage.clickRegisterButton();
        List<WebElement> errorMessage = loginPage.getRegistrationErrorMessages();

        List<String> lstSelectedItem=new ArrayList<String>();
        for(int i=0;i<errorMessage.size();i++){
            System.out.println(errorMessage.get(i).getText());
            lstSelectedItem.add(errorMessage.get(i).getText());
        }
        List<String> expectedList = new ArrayList<String>();
        expectedList.add("First name is required.");
        expectedList.add("Last name is required.");
        expectedList.add("Address is required.");
        expectedList.add("City is required.");
        expectedList.add("State is required.");
        expectedList.add("Zip Code is required.");
        expectedList.add("Phone is required.");
        expectedList.add("Social Security Number is required.");
        expectedList.add("Username is required.");
        expectedList.add("Password is required.");
        expectedList.add("Password confirmation is required.");
        Assert.assertEquals(lstSelectedItem, expectedList);
    }

    @Test(groups = {"Sanity"}, priority = 4)
    public void openNewSavingsAccount() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(username);
        loginPage.enterPassword("account2");
        loginPage.clickLoginButton();
        HomePage homePage = new HomePage(driver);
        homePage.clickAccountOverview();
        Thread.sleep(5000);
        String accountBal = homePage.getAccountBalance().substring(1);
        System.out.println(" account balance : "+accountBal);
        homePage.clickOpenNewAccountLink();
        homePage.selectSavingsAccountType();
        homePage.clickOpenNewAccountButton();
        String accountOpenTitle = homePage.getAccountOpenTitle();
        Assert.assertEquals(accountOpenTitle, "Open New Account");
//		Assert.assertEquals(accountOpenTitle, "Account Opened!");
        homePage.clickAccountOverview();
        Thread.sleep(5000);
        String accountBalAfterNewAccount = homePage.getAccountBalance().substring(1);
        Assert.assertEquals(Float.parseFloat(accountBal)-Float.parseFloat(accountBalAfterNewAccount), Float.parseFloat("100.00"));
        String newAccountBal = homePage.getNewAccountBalance().substring(1);
        Assert.assertEquals(Float.parseFloat(newAccountBal), Float.parseFloat(("100.00")));
    }
    @Test(groups = {"Sanity"}, priority = 5)
    public void transferFunds() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(username);
        loginPage.enterPassword("account2");
        loginPage.clickLoginButton();
        HomePage homePage = new HomePage(driver);
        Thread.sleep(5000);
        String mainAccountBal = homePage.getAccountBalance().substring(1);
        String newAccountBalance = homePage.getNewAccountBalance().substring(1);
        System.out.println("main account balance : "+mainAccountBal);
        System.out.println("new account balance : "+newAccountBalance);
        homePage.clickTransferFunds();
        homePage.selectFromAccount();
        Thread.sleep(5000);
        homePage.selectToAccount();
        homePage.enterAmount("100");
        homePage.clickTransferButton();
        String transferMessage = homePage.getTransferSuccessMessage();
        Assert.assertEquals(transferMessage, "Transfer Complete!");
        homePage.clickAccountOverview();
        String mainActBalAfterTransfer = homePage.getAccountBalance().substring(1);
        String newActBalAfterTransfer = homePage.getNewAccountBalance().substring(1);
        System.out.println("main account bal after transfer : "+mainActBalAfterTransfer);
        System.out.println("new account bal after transfer : "+newActBalAfterTransfer);
        Assert.assertEquals(Float.parseFloat(mainAccountBal)-100.00, Float.parseFloat(mainActBalAfterTransfer));
        Assert.assertEquals(Float.parseFloat(newAccountBalance)+100.00, Float.parseFloat(newActBalAfterTransfer));
    }

    @AfterMethod
    public void afterClass(){
        driver.close();
    }

}