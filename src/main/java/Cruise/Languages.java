package Cruise;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Languages {
    public void Languages(WebDriver driver) throws InterruptedException, IOException {
        Properties prop = new Properties();
        //Importing the config file
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration.properties");
        //Loading the config file
        prop.load(input);
        String firstPage = driver.getWindowHandle();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


      // driver.findElement(By.xpath("//*[@id=\"component_1\"]/div/div[3]/div/div[3]/span/button")).click();

        Set<String> oneId = driver.getWindowHandles();
        Iterator<String> itr = oneId.iterator();

        String mainId = itr.next();
        String newpage = itr.next();
        //Switching to new tab
        driver.switchTo().window(newpage);

        Thread.sleep(4000);
       //Clicking on the language selector button
        driver.findElement(By.xpath(prop.getProperty("Languages"))).click();
        // Waiting until the language listbox is formulated
        WebDriverWait wait = new WebDriverWait(driver, 20);
       Thread.sleep(1000);
       System.out.println("Languages offered are: ");
        //Displaying all the languages.
        for (int i = 1; i < 50; i++) {

            WebElement a = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/footer[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[" + i + "]/a[1]"));
            String str = a.getText();

            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

            String convert = new String(bytes);

            System.out.println(convert);

        }
    }
}
