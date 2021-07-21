package Cruise;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import Utils.WriteCruiseDetails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class PassengersAndCrew {

    public static WebDriver driver;


    public PassengersAndCrew(WebDriver d)
    {
        driver=d;
    }

    //To take screenshot
    public void TakeScreenShot(String Name) {

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        String path = System.getProperty("user.dir");
        File DestFile = new File(System.getProperty("user.dir")+"\\ScreenShots\\" + Name +".png");
        //Copy file at destination
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // To write Passengers and Crew data in excel file
    public void passengersAndCrew(String parent, WriteCruiseDetails excel)throws IOException, InterruptedException {
        Properties prop = new Properties();
        //Importing the config file
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration.properties");
        //Loading the config file
        prop.load(input);
        JavascriptExecutor jse=(JavascriptExecutor)driver;



        Set<String> s = driver.getWindowHandles();
        for (String element : s) {
            if (!parent.equals(element)) {
                driver.switchTo().window(element);
            }
        }
        //To take a screenshot
        Thread.sleep(300);
        TakeScreenShot("Cruise Page");

        jse.executeScript("window.scrollBy(0,350)");

        excel.writeToExcel(driver.findElement(By.cssSelector(prop.getProperty("Passengers_Crew"))).getText());
    }
}
