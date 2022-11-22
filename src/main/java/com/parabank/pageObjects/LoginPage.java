package com.parabank.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage {

        WebDriver driver;
    @FindBy(css = "input[name='username']") public WebElement loginUsername;
    @FindBy(css = "input[name='password']") public WebElement loginPassword;

    @FindBy(css = "input[value='Log In']") public WebElement loginButton;
    @FindBy(css = "div[id='loginPanel'] p:nth-child(3) a") public WebElement registerLink;
    @FindBy(css = "div[id='rightPanel'] h1") public WebElement title;
    @FindBy(css = "table[class='form2'] tr b") public List<WebElement> form2;
    @FindBy(css = ".form2 tbody tr td:nth-child(3)") public List<WebElement> form2ErrorMessages;

    @FindBy(id = "customer.firstName") public WebElement firstName;
    @FindBy(id = "customer.lastName") public WebElement lastName;
    @FindBy(id = "customer.address.street") public WebElement address;
    @FindBy(id = "customer.address.city") public WebElement city;
    @FindBy(id = "customer.address.state") public WebElement state;
    @FindBy(id = "customer.address.zipCode") public WebElement zipCode;
    @FindBy(name = "customer.phoneNumber") public WebElement phone;
    @FindBy(name = "customer.ssn") public WebElement ssn;

    @FindBy(css = "input[name='customer.username']") public WebElement userName;
    @FindBy(css = "input[name='customer.password']") public WebElement password;
    @FindBy(css = "input[name='repeatedPassword']")  public WebElement confirm;
    @FindBy(css = "input[value='Register']") public WebElement registerButton;
    @FindBy(css = "div[id='rightPanel'] p") public WebElement successfulRegistrationMessage;
    @FindBy(css = "span[id='customer.username.errors']") public WebElement registrationErrorMessage;   // Locator to be verify to correct




    private void waitForVisibility(WebElement element) throws Error{
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element));
    }

    public void clickRegisterLink() throws InterruptedException {
        waitForVisibility(registerLink);
        registerLink.click();
    }
    public String getRegistrationTitle() {
        return title.getText();
    }
    public List<WebElement> form2Fields() {
        return form2;
    }

    public void fillForm(String uname){
        firstName.sendKeys("fname");
        lastName.sendKeys("lname");
        address.sendKeys("add");
        city.sendKeys("addcity");
        state.sendKeys("addstate");
        zipCode.sendKeys("123456");
        phone.sendKeys("1234567890");
        ssn.sendKeys("7896543210");
        userName.sendKeys(uname);
        password.sendKeys("account2");
        confirm.sendKeys("account2");
    }
    public void clickRegisterButton(){
        registerButton.click();
    }

    public String getRegistrationSuccessMessage(){
        return successfulRegistrationMessage.getText();
    }

    public String getRegistrationErrorMessage(){
        return registrationErrorMessage.getText();
    }



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String uname){
        loginUsername.sendKeys(uname);
    }

    public void enterPassword(String pwd){
        loginPassword.sendKeys(pwd);
    }

    public void clickLoginButton(){
        loginButton.click();
    }


    public void fillFormEmpty() {
        firstName.sendKeys("");
        lastName.sendKeys("");
        address.sendKeys("");
        city.sendKeys("");
        state.sendKeys("");
        zipCode.sendKeys("");
        phone.sendKeys("");
        ssn.sendKeys("");
        userName.sendKeys("");
        password.sendKeys("");
        confirm.sendKeys("");
    }

    public List<WebElement> getRegistrationErrorMessages() {
        return form2ErrorMessages;
    }
}
