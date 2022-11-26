package com.parabank.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdminPage {

    WebDriver driver;

    @FindBy(css = "input[value='restjson']") public WebElement restJson;

    @FindBy(css = "input[value='Submit']") public WebElement submitButton;


    public void waitForVisibility(WebElement element) throws Error{
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.visibilityOf(element));
    }
    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setDataAccessModeToRest(){
        restJson.click();
        submitButton.click();
    }

}
