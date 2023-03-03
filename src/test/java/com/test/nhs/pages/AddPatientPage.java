package com.test.nhs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddPatientPage {

    public AddPatientPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(name = "lastName")
    WebElement lastName;

    @FindBy(name = "hospitalNumber")
    WebElement hospitalNo;

    @FindBy(name = "dateOfBirth")
    WebElement datOfBirth;

    @FindBy(xpath = "//input[@name='sex']/..")
    List<WebElement> genders;

    @FindBy(xpath = "//input[@type='search']")
    WebElement searchDisease;

    @FindBy(xpath = "//table[@id='add-new-patient']//td[starts-with(@class,'sorting')]")
    List<WebElement> diseaseColumList;

    @FindBy(css = "td[class^='sorting']+td")
    List<WebElement> scoreColumList;

    @FindBy(css = "td[class^='sorting']~td>input")
    List<WebElement> diagnosisColumList;

    @FindBy(xpath = "//input[@name='Submit']")
    WebElement addButton;

    @FindBy(xpath = "//form//input[@type='text']")
    List<WebElement> requiredFieldList;

    @FindBy(xpath = "//form//label[@class]")
    List<WebElement> displayedFieldList;

    @FindBy(xpath = "//div[contains(text(),'The date is not valid.')]")
    WebElement invalidDateMessage;

    //====== methods =======

//    public void addPatient(String firstname,
//                           String lastname,
//                           String hospitalNo,
//                           String dateOfBirth,
//                           String gender,
//                           String disease){
    public void addPatient(Map<String,String> inputFields){

        String firstname = inputFields.get("First Name");
        String lastname =  inputFields.get("Last Name");
        String hospitalNo = inputFields.get("Hospital No.");
        String dateOfBirth=  inputFields.get("Date of Birth");
        String gender = inputFields.get("Gender");
        String disease =  inputFields.get("Disease");


        firstName.sendKeys(firstname);
        lastName.sendKeys(lastname);
        this.hospitalNo.sendKeys(hospitalNo);
        this.datOfBirth.sendKeys(dateOfBirth);
        selectGender(gender);
        selectDiseases(disease);
        addButton.click();

    }

    //select the gender
    public void selectGender(String givenGender){

      for(int i = 0;i<genders.size();i++){
          String text = BrowserUtils.getText(genders.get(i));
          if(givenGender.equalsIgnoreCase(text)){
              genders.get(i).click();
              break;
          }
      }

    }

    public void selectDiseases(String disease){
        searchDisease.sendKeys(disease);

        if(diseaseColumList.size()!=0) {
            for (int i = 0; i < diseaseColumList.size(); i++) {
                String diseaseName = BrowserUtils.getText(diseaseColumList.get(i));
                String scoreValue = BrowserUtils.getText(scoreColumList.get(i));
                if (disease.equalsIgnoreCase(diseaseName) || disease.equalsIgnoreCase(scoreValue)) {
                    diagnosisColumList.get(i).click();
                }
            }
        }
    }

    public boolean isRequiredAttributeCheck(){
        boolean isRequired = true;
        for (WebElement field:requiredFieldList){
            if(!Boolean.parseBoolean(field.getAttribute("required"))){
                isRequired =false;
                break;
            }
        }
       return isRequired;
    }

    public boolean isDisplayed(){
        boolean isDisplayed = true;
        for (WebElement field:displayedFieldList){
            if(!field.isDisplayed()){
                System.out.println(field);
                isDisplayed = false;
                break;
            }
        }
        return isDisplayed;
    }

    public List<String> addPatientDisplayedList(){
        List<String> actualDisplayedFieldsText = new ArrayList<>();
        for(WebElement field: displayedFieldList){
            actualDisplayedFieldsText.add(BrowserUtils.getText(field));
        }
            return actualDisplayedFieldsText;
    }
    public String getInvalidDataMessage(){
        return BrowserUtils.getText(invalidDateMessage);
    }
}
