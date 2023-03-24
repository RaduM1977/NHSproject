package com.test.nhs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtils;

import java.time.Duration;

public class PatientPage {

    public PatientPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "delete-button")
    private WebElement deleteButton;

    //========= methods ========

    public void deletePatient(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        deleteButton   = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        BrowserUtils.clickWithJS(driver,deleteButton);
        //deleteButton.click();
    }
}
