package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverHelper {

    //Singleton Pattern Design --> private constructor
    //It is a way to centralize your driver

    private static WebDriver driver;

    private DriverHelper(){} // I do not want anyone to create an object from this class
    private  static ChromeOptions options;


    public static  WebDriver getDriver(){
        if(driver == null || ((RemoteWebDriver)driver).getSessionId() == null){

            switch (ConfigReader.readProperty("browser")){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    //driver= new ChromeDriver();
                    options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                    break;

                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver =  new FirefoxDriver();
                    break;

                default:
                    WebDriverManager.chromedriver().setup();
                    //driver = new ChromeDriver();
                    options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);

            }
        }
       return driver;
}

    }
