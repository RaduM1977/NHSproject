package com.test.nhs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage {
    private static int count;
    public DashboardPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".row div.huge+div")
    List<WebElement> cards;

    @FindBy(css = ".panel-heading")
    List<WebElement> cardsColor;
    @FindBy(css = ".row div.huge")
    List<WebElement> cardsDisplayNumbers;

    @FindBy(xpath = "//div[contains(@class,'dataTables_wrapper')]") //css =".dataTables_scroll "
    List<WebElement> tableCards;

    @FindBy(xpath = "//th[@tabindex='0' and @aria-controls='patients-in-hospital']")
    List<WebElement> patientsWithRoomsHeader;

    @FindBy(xpath = "//th[text()='Score' and @aria-controls='patients-in-hospital']")
    WebElement score;

    @FindBy(xpath = "//table[@id='patients-in-hospital']//td[@class='sorting_1']")
    List<WebElement> scoreColumValues;

    @FindBy(xpath = "//a[contains(text(),'Add patient')]")
    WebElement addPatientButton;

    @FindBy(xpath = "//table//tr[@role='row']")
    List<WebElement> patientsWaitingTable;

    @FindBy(partialLinkText = "System settings")
    private WebElement addSystemSettings;


    //  ======== methods ================
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

    public List<WebElement> isTablesDisplayed(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         return wait.until(ExpectedConditions.visibilityOfAllElements(tableCards));
    }

    public List<String> getPatientsWithRoomsHeaderText(){
        List<String> actualHeaderText = new ArrayList<>();
        for(WebElement header: patientsWithRoomsHeader){
            actualHeaderText.add(BrowserUtils.getText(header));
        }
        return actualHeaderText;
    }

    public String getScoreDefaultValue(){
        return score.getAttribute("aria-sort");
    }

    public List<String> getValuesOfScoreColumn(){
        List<String> actualScoreColumValues = new ArrayList<>();
        for(WebElement value: scoreColumValues){
            actualScoreColumValues.add(BrowserUtils.getText(value));
        }
        return actualScoreColumValues;
    }

// ======= buttons methods ======
    public void doAddPatientButton(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(addPatientButton)).click();
    }

    public void doSystemSettingsButton(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(addSystemSettings)).click();
    }

    //
    public String isPatientAdded(WebDriver driver, String tableHeader){
        Actions action = new Actions(driver);
        boolean isAdded = false;
        boolean isPatientWaitingTable = false;

        for(WebElement patient:patientsWaitingTable){
            String text = patient.getText();
            if(text.contains(tableHeader)) {
                isPatientWaitingTable = true;
            }
            if (text.contains("101 John Doe 2")) {
                    action.doubleClick(patient).build().perform();
                    System.out.println(text);

                    isAdded = true;
                    break;
            }


        }
        count ++;
        return  isAdded && isPatientWaitingTable && count<2 ? "added":"not added";

    }
}
