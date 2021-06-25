package browser;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class Browser {
    public static WebDriver driver;
    public static Actions act;

    //WebDriver Setup Selection Method

    public static WebDriver setDriver(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Which browser would you like to execute the script on? \r\n 1. Google Chrome \r\n 2. Internet Explorer");

        int browserChoice = scan.nextInt();
        scan.close();

        if(browserChoice == 1)
        {
            driver = setChromeDriver();
        }
        else if(browserChoice == 2)
        {
            driver = setIEDriver();
        }
        else{
            System.out.println("Invalid Data...");
        }
        act = new Actions(driver);
        return driver;

    }

    //ChromeDriver Setup Method
    
    public static WebDriver setChromeDriver(){
        String driverPath = "/Users/priyansh/Desktop/Mini-Project/driverss/chromedriver";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        return driver;
    }

    //Internet Explorer Setup Method
    
    public static WebDriver setIEDriver(){
    	String driverPath = "/Users/priyansh/Desktop/Mini-Project/driverss/IEDriverServer.exe";
//      String driverPath = System.getProperty("user.dir")+ "\\driverss\\IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", driverPath);

        driver = new InternetExplorerDriver();
        return driver;
    }

    //URL Setup Method
    
    public static void getUrl() throws Exception{

    	String filelocation = "/Users/priyansh/Desktop/Mini-Project/Applicationdata/Configure.properties";
        File file = new File(filelocation);
        FileInputStream fileinput = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(fileinput);

        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //Driver Close Method
    
    public static void closeBrowser(){
        driver.quit();
    }

}
