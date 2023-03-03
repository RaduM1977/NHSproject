package com.test.nhs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

public class LoginPage {

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    //elements

    @FindBy(id = "inputEmail")
    WebElement username;

    @FindBy(id = "inputPassword")
    WebElement password;

    @FindBy(xpath = "//button[.='Sign in']")
    WebElement signInButton;



    //methods

    public void doLogin(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        signInButton.click();
    }

    public boolean isUserAttributeRequired(){

        return Boolean.parseBoolean(username.getAttribute("required"));
    }

    public boolean isPasswordAttributeRequired(){

        return Boolean.parseBoolean(password.getAttribute("required"));
    }
}
