package com.test.nhs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


}
