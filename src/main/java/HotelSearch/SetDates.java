package HotelSearch;

import java.io.File;
import java.io.IOException;

import browserSetup.BrowserSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetDates {

 public static WebDriver driver= BrowserSetup.setChromeDriver();
    public void TakeScreenShot(WebDriver driver, String Name) {

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        String path = System.getProperty("user.dir");
        File DestFile = new File(System.getProperty("user.dir")+"/Screenshots/" + Name +".png");
        //Copy file at destination
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //This function is used to parse the data from Excel File
    public static String parseDate(String dates) {
        //Replacing all hiphens with space
        String date = dates.replaceAll("-", " ");

        return date;
    }

    //This function is used to set the Check-in Date
    public void SetCheckInDate(WebDriver driver, String check_in) throws InterruptedException {
        //Getting the parsed string and storing it
        String parsed_Check_in = parseDate(check_in);
        //Clicking on the Check-in calendar
        driver.findElements(By.className("lRYY2wxe")).get(0).click();
        //To take a screenshot
        Thread.sleep(500);
        TakeScreenShot(driver ,"Check_in");
        //Waiting for the the elements to be visible
        WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@aria-label,'"+parsed_Check_in+"')]")));
        //Setting the check in Date

        driver.findElement(By.xpath("//div[contains(@aria-label,'"+parsed_Check_in+"')]")).click();

    }

    //This function is used to set the Check-out Date
    public void SetCheckOutDate(WebDriver driver, String check_out) throws InterruptedException {
        //To take a screenshot
        Thread.sleep(500);
        TakeScreenShot(driver ,"Check_out");
        //Getting the parsed string and storing it
        String parsed_Check_out = parseDate(check_out);
        //Waiting for the the elements to be visible
        WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@aria-label,'"+parsed_Check_out+"')]")));
        //Setting the check out Date
        driver.findElement(By.xpath("//div[contains(@aria-label,'"+parsed_Check_out+"')]")).click();

    }


}
