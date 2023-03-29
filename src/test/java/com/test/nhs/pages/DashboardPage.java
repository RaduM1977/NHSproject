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
import java.util.*;

public class DashboardPage {

    public DashboardPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".row div.huge+div")
    private List<WebElement> cards;

    @FindBy(css = ".panel-heading")
    private List<WebElement> cardsColor;
    @FindBy(css = ".row div.huge")
    private List<WebElement> cardsDisplayNumbers;

    @FindBy(xpath = "//div[contains(@class,'dataTables_wrapper')]") //css =".dataTables_scroll "
    private List<WebElement> tableCards;

    @FindBy(xpath = "//th[@tabindex='0' and @aria-controls='patients-in-hospital']")
    private List<WebElement> patientsWithRoomsHeader;

    @FindBy(xpath = "//th[text()='Score' and @aria-controls='patients-in-hospital']")
    private WebElement score;

    @FindBy(xpath = "//table[@id='patients-in-hospital']//td[@class='sorting_1']")
    private List<WebElement> scoreColumValues;


    // ========= tables bodies list =========
    @FindBy(xpath = "//div[@id='patients-waiting_wrapper']//tr[@role='row']")
    private List<WebElement> patientsWaitingTable;
    @FindBy(xpath = "//table[@id='patients-waiting']/tbody/tr[@role='row']")
    List<WebElement> patientsWaitingBodyList;
    @FindBy(xpath = "//table[@id='patients-in-hospital']//tbody/tr")
    private List<WebElement> patientsWithRoomList;

    @FindBy(xpath = "//table[@id='patients-in-hospital']//tr[@role='row'][1]")
    List<WebElement> listOfPatientsWithRooms;

    @FindBy(xpath = "//table[@id='free-rooms']//tbody/tr")
    private List<WebElement> freeRoomsList;


    @FindBy(xpath = "//div[contains(@id,'wrapper') and contains(@class,'dataTables')]//thead/tr")
    private List<WebElement> tablesHeadersList;


    // ======== search fields =========
    @FindBy(xpath = "//input[@aria-controls='patients-waiting']")
    private  WebElement searchPatientWaiting;

    @FindBy(xpath = "//input[@aria-controls='free-rooms']")
    private  WebElement searchFreeRooms;

    @FindBy(xpath = "//input[@aria-controls='patients-in-hospital']")
    private  WebElement searchPatientWithRoom;

    @FindBy(tagName = "input")
    List<WebElement> searchBoxFieldsList;


    // =========== buttons ==============
    @FindBy(xpath = "//a[contains(text(),'Add patient')]")
    private WebElement addPatientButton;

    @FindBy(partialLinkText = "System settings")
    private WebElement addSystemSettingsButton;

    @FindBy(partialLinkText = "Dashboard")
    private WebElement dashboardButton;


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
        wait.until(ExpectedConditions.elementToBeClickable(addSystemSettingsButton)).click();
    }
    public void doDashboardButton(){
        dashboardButton.click();
    }

    //
    public String isPatientAdded(WebDriver driver, String tableHeader,String userInfo){

        Actions action = new Actions(driver);
        boolean isAdded = false;
        boolean isPatientWaitingTable = false;
        for(WebElement patient:patientsWaitingTable){
            String text = patient.getText();

            if(text.contains(tableHeader)) {
                isPatientWaitingTable = true;
            }
            if (text.contains(userInfo)) {
                    action.doubleClick(patient).build().perform();
                    //System.out.println("patient " + text);
                    isAdded = true;
                    break;
            }

        }

//        System.out.println(isAdded + " : " + isPatientWaitingTable);
//        System.out.println(AddPatientPage.count);
        return  isAdded && isPatientWaitingTable && AddPatientPage.count<2 ? "added":"not added";
    }


    public boolean searchBoxAttributeValue(String attributeValue){
            boolean isValue = false;
        for(WebElement element:searchBoxFieldsList){
            String value = element.getAttribute("aria-controls").replace("-"," ");
            if(attributeValue.equalsIgnoreCase(value)){
                isValue = true;
                break;
            }
        }
        return isValue;
    }

    public boolean doSearch(WebDriver driver, String tableHeader, String searchMessage){
        AddPatientPage.count = 1;
        boolean isFound = false;

        if(tableHeader.equals("Patients waiting")) {
                searchPatientWaiting.sendKeys(searchMessage);
                String actualPatientInfo = isPatientAdded(driver, tableHeader, searchMessage);
                System.out.println("actual added patient" + actualPatientInfo);

                System.out.println(actualPatientInfo);
                 isFound =  actualPatientInfo.equals("added");

            }
            if(tableHeader.equals("Patients with rooms")){
               searchPatientWithRoom.sendKeys(searchMessage);
               int patientsWithRoomSize = patientsWithRoomList.size();
                System.out.println(patientsWithRoomSize);
                isFound = patientsWithRoomSize>0;
            }

            if(tableHeader.equals("Free rooms")){
                searchFreeRooms.sendKeys(searchMessage);
                int freeRoomsSize = freeRoomsList.size();
                System.out.println(freeRoomsSize);
                isFound = freeRoomsSize>0;
            }
            return isFound;
        }

        //maps of freeRooms,rooms with patients and patient info
        private Map<String,Boolean> getRoomWithPatients(){
        Map<String,Boolean> roomsWithPatientsMap = new HashMap<>();
            for(WebElement element:patientsWithRoomList){
                String text = BrowserUtils.getText(element);
                String[] patientInfo = text.split(" ");
                String room = "Room "+patientInfo[patientInfo.length-2];
                roomsWithPatientsMap.put(room,true);
            }
            return roomsWithPatientsMap;
        }

        private Map<String,Boolean> getFreeRooms(){
            Map<String,Boolean> freeRoomsMap = new HashMap<>();
            for(WebElement element:freeRoomsList){
                String text = BrowserUtils.getText(element);
                freeRoomsMap.put(text,false);
            }
            return freeRoomsMap;
        }
        public Map<String, Object> roomsMap(){
        Map<String,Object> roomMap = new HashMap<>();
        roomMap.putAll(getRoomWithPatients());
        roomMap.putAll(getFreeRooms());
        return roomMap;
    }


    private List<Map<String, String>> infoListOfPatientsWithRoom(){

        List<Map<String,String>> patientsInfoList = new ArrayList<>();
        for(WebElement element: patientsWithRoomList){
            Map<String,String> patientInfoMap = new LinkedHashMap<>();
            String[] patientInfoArray = BrowserUtils.getText(element).split(" ");
                patientInfoMap.put("hospitalNumber",patientInfoArray[0]);
                patientInfoMap.put("firstName",patientInfoArray[1]);
                patientInfoMap.put("lastName",patientInfoArray[2]);
                patientInfoMap.put("room",patientInfoArray[3]+" "+patientInfoArray[4]);
                patientInfoMap.put("score",patientInfoArray[patientInfoArray.length-1]);

                patientsInfoList.add(patientInfoMap);
        }
        return patientsInfoList;
    }
    private List<Map<String,String>> infoPatientsWithNoRooms(){
        List<Map<String,String>> patientsInfoList = new ArrayList<>();

        for(WebElement element: patientsWaitingBodyList){
            Map<String,String> patientInfoMap = new LinkedHashMap<>();
            String[] patientInfoArray = BrowserUtils.getText(element).split(" ");
            //System.out.println(Arrays.toString(patientInfoArray));

            patientInfoMap.put("hospitalNumber",patientInfoArray[0]);
            patientInfoMap.put("firstName",patientInfoArray[1]);
            patientInfoMap.put("lastName",patientInfoArray[2]);
            patientInfoMap.put("room","noroom");
            patientInfoMap.put("score",patientInfoArray[patientInfoArray.length-1]);

            patientsInfoList.add(patientInfoMap);
        }
        return patientsInfoList;
    }

   public List<Map<String, String>> patientsInfo(){
        List<Map<String,String>> patientInfoList= new ArrayList<>();
        patientInfoList.addAll(infoListOfPatientsWithRoom());
        patientInfoList.addAll(infoPatientsWithNoRooms());

        patientInfoList.sort(Comparator.comparing(map-> map.get("hospitalNumber")));
        return patientInfoList;

   }

    }
