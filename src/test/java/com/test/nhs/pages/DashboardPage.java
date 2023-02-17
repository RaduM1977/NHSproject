package com.test.nhs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage {

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".row div.huge+div")
    List<WebElement> cards;

    @FindBy(css = ".panel-heading div.row")
    List<WebElement> cardsColor;
    @FindBy(css = ".row div.huge")
    List<WebElement> cardsDisplayNumbers;

    public int getNumberOfCards(){
        return cards.size();
    }

    public List<Integer> getCardsDisplayedNumbers(){
        List<Integer> actualNumbers = new ArrayList<>();
        for(WebElement card:cardsDisplayNumbers){
            int cardNumber= Integer.parseInt(BrowserUtils.getText(card));
            actualNumbers.add(cardNumber);
        }
        return actualNumbers;
    }

    public List<String> getCardsDisplayedMessage(){
        List<String> actualCardsMessages = new ArrayList<>();
        for(WebElement card:cards){
            actualCardsMessages.add(BrowserUtils.getText(card));
        }
        return actualCardsMessages;
    }

    public List<String> getCardsColors(){
        List<String> actualCardsColors = new ArrayList<>();
        for(WebElement card:cardsColor){
            actualCardsColors.add(card.getCssValue("border-color"));
        }
        return actualCardsColors;
    }

}
