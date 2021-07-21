package Cruise;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import Utils.WriteCruiseDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LaunchedYear {

    public static WebDriver driver;

    public LaunchedYear(WebDriver d) {
        driver = d;
    }

    // To write Launched year in excel file
    public void launchedYear(String parent, WriteCruiseDetails excel) throws IOException {
        Properties prop =  new Properties();
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


        jse.executeScript("window.scrollBy(0,350)");


        excel.writeToExcel(driver.findElement(By.xpath(prop.getProperty("Launched_Year"))).getText());
    }
}