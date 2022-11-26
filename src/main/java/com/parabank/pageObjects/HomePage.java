package com.parabank.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    @FindBy(css = "a[href='/parabank/openaccount.htm']") public WebElement openNewAccount;

    @FindBy(css = "select[id='type']") public WebElement accountTypeDropDown;

    @FindBy(css = "#rightPanel form input") public WebElement openNewAccountButton;

    @FindBy(css = "div[id='rightPanel'] div h1") public WebElement accountOpenTitle;

    @FindBy(css = "a[href='/parabank/transfer.htm']") public WebElement transferFunds;

    @FindBy(css = "a[href='/parabank/logout.htm']") public WebElement logOutLink;

    @FindBy(css = "a[href='/parabank/overview.htm']") public WebElement accoutnOverviewLink;

    @FindBy(css = "div[id='rightPanel'] div div form p input") public WebElement amountFiled;

    @FindBy(css = "select[id='fromAccountId']") public WebElement fromAccountID;

    @FindBy(css = "select[id='toAccountId']") public WebElement toAccountID;

    @FindBy(css = "div[id='rightPanel'] div div form div:nth-child(4) input") public WebElement transferButton;

    @FindBy(css = "div[id='rightPanel'] div div h1") public WebElement transferSuccessMessage;

    @FindBy(css = "#accountTable tbody tr:nth-child(1) td:nth-child(2)") public WebElement mainAccountBalance;

    @FindBy(css = "#accountTable tbody tr:nth-child(2) td:nth-child(2)") public WebElement newAccountBalance;

    @FindBy(css = "a[href='/parabank/logout.htm']") public WebElement logout;

    public void waitForVisibility(WebElement element) throws Error{
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void clickOpenNewAccountLink() {
        openNewAccount.click();
    }

    public void selectSavingsAccountType() {
        Select select = new Select(accountTypeDropDown);
        select.selectByValue("1");
    }


    public void clickOpenNewAccountButton() {
        waitForVisibility(openNewAccountButton);
        openNewAccountButton.click();
    }

    public String getAccountOpenTitle(){
        waitForVisibility(accountOpenTitle);
        return accountOpenTitle.getText();
    }


    public void clickTransferFunds(){
        transferFunds.click();
    }

    public void enterAmount(String amount){
        amountFiled.sendKeys(amount);
    }

    public void selectFromAccount(){
        Select select = new Select(fromAccountID);
        select.selectByIndex(0);
    }
    public void selectToAccount(){
        Select select = new Select(toAccountID);
        select.selectByIndex(1);
    }


    public void clickTransferButton(){
        transferButton.click();
    }

    public String getTransferSuccessMessage(){
        return transferSuccessMessage.getText();
    }

    public String getAccountBalance(){
        return mainAccountBalance.getText();
    }


    public void clickAccountOverview(){
        accoutnOverviewLink.click();
    }

    public String getNewAccountBalance(){
        return newAccountBalance.getText();
    }

    public void clickLogoutButton(){
        logOutLink.click();
    }

}
