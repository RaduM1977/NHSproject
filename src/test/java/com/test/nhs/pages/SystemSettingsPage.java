package com.test.nhs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtils;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SystemSettingsPage {
        static String roomNo;
    public SystemSettingsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    // ======== elements of the rooms ==========
    @FindBy(xpath = "//input[@name='roomName']")
    private WebElement enterRoom;

    @FindBy(xpath = "//input[@value='Add room']")
    private WebElement addRoomButton;

    @FindBy(xpath = "//table[@id='rooms-table']//td")
    private List<WebElement> roomsList;

    @FindBy(xpath = "//div[@id='rooms-table_filter']//input[@type='search']")
    private WebElement searchRoom;

    @FindBy(xpath = "//table[@id='rooms-table']//th[@class='sorting_asc']")
    private WebElement roomsTableOrder;

    @FindBy(xpath = "//table[@id='rooms-table']//input")
    private List<WebElement> roomsListSelect;

    @FindBy(xpath = "//input[@value='Delete rooms']")
    private WebElement deleteRoomsBtn;


    // ===== elements of the diseases ====

    @FindBy(xpath = "//table[@id='diseases-table']//th[@aria-sort='ascending' and .='Disease']")
    private WebElement diseaseSorting;

    @FindBy(xpath = "//table[@id='diseases-table']//tr/td[contains(@class,'sorting')]")
    private List<WebElement> listOfDiseaseNames;

    // =============== elements of user =========
    @FindBy(css = "input[name= 'username']")
    WebElement username;
    @FindBy(css = "input[name= 'password']")
    WebElement password;
    @FindBy(xpath = "//button[.='Add user']")
    WebElement addUserBtn;

    @FindBy(css = "div .alert-success")
    WebElement addedUserMessage;

    // =========== methods ============

    private String createRoom(){
        Random random = new Random();
        String roomNo = "Room " +  random.nextInt(100);
        System.out.println(roomNo);
        return roomNo;
    }

    public boolean addRoom(WebDriver driver){
         this.roomNo = createRoom();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(enterRoom)).sendKeys(roomNo);

       addRoomButton.click();
        boolean isAdded = false;
        for(WebElement element : roomsList){
            String actualRoom = BrowserUtils.getText(element);
            if(actualRoom.equals(roomNo)){
                isAdded = true;
                break;
            }
        }
        return isAdded;
    }

    public List<String> orderOfRooms(String roomsOrder){
        List<String> actualRoomsList = new LinkedList<>();
        String actualOrder = roomsTableOrder.getAttribute("aria-sort");
        if(actualOrder.equals(roomsOrder)) {
            for (WebElement element : roomsList) {
                actualRoomsList.add(BrowserUtils.getText(element));

            }
        }
        return actualRoomsList;
    }

    public boolean deleteRoom(){
        searchRoom.sendKeys(roomNo);

        for(WebElement element:roomsListSelect){
            String roomAttributeValue = element.getAttribute("value");
            if(roomAttributeValue.contains(roomNo)){
                element.click();
            }
        }
        deleteRoomsBtn.click();

        boolean isDeleted = true;

        for(WebElement element : roomsList){
            String actualRoom = BrowserUtils.getText(element);
            if(actualRoom.equals(roomNo)){
                isDeleted = false;
                break;
            }
        }
        return isDeleted;
    }

    public void addUser(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        addUserBtn.click();
    }

    public String addUserMessage(){
        String actualSuccessMessage = BrowserUtils.getText(addedUserMessage);
        return actualSuccessMessage;
    }




//    public List<String> diseaseOrdered(String sortingAttribute){
//            List<String> actualDiseaseList = new LinkedList<>();
//
//            String actualAttribute = diseaseSorting.getAttribute("aria-sorting").trim();
//
//            if(actualAttribute.equals(sortingAttribute)){
//                for(WebElement element:listOfDiseaseNames){
//                    actualDiseaseList.add(BrowserUtils.getText(element));
//                }
//            }
//            return actualDiseaseList;
//    }





}
