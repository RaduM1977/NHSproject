package com.test.nhs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientPage {

    public PatientPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "delete-button")
    private WebElement deleteButton;

    //========= methods ========

    public void deletePatient(){
        deleteButton.click();
    }
}
