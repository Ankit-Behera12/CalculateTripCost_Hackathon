package Cruise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import Utils.ReadCruiseDetailsExcel;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetCruiseDetails {
    Properties prop =new Properties();
    ReadCruiseDetailsExcel r= new ReadCruiseDetailsExcel();
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

    public void SetCruiseLine(WebDriver driver, String CruiseLine) throws IOException {
        //Importing the config file
        InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configuration.properties");
        //Loading the config file
        prop.load(input);
        driver.get(prop.getProperty("Cruise_Url"));
        //Clicking on SetCruiseLine button
        driver.findElement(By.xpath(prop.getProperty("Cruise_line"))).click();
        //Waiting until Listbox is generated
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-item-17391487")));
        String cruiseLine = r.ReadExcelWords().get(1);
        for (int i = 1; i < 133; i++) {
            int max = 133;
            WebElement all = driver.findElement(By.xpath("//ul[@role='listbox']/li[" + i + "]"));

            String find = all.getText();
            if (cruiseLine.equals(find)) {
                all.click();
                i = max;
            }
        }
    }




    public void SetCruiseShip(WebDriver driver, String CruiseShip) {

        WebDriverWait wait=new WebDriverWait(driver,50);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(prop.getProperty("Cruise_ship"))).click();
        //Waiting until Ship Name listBox is generator.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='listbox']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@role='listbox']")));

        //List<WebElement> Options=new ArrayList<WebElement>();
        driver.findElements(By.xpath("//*[@id=\'menu-item-17735350\']")).get(0).click();
		/*WebElement Parent = driver.findElement(By.xpath("//*[@id=\"component_1\"]/div/div[3]/div/div[2]/div/div/ul"));


		Options = Parent.findElements(By.tagName("div"));
		for(int i = 0 ; i < Options.size(); i++) {
			if(Options.get(i).getText().equalsIgnoreCase(CruiseShip)) {

				driver.findElement(By.xpath("//*[@id=\"component_1\"]/div/div[3]/div/div[2]/div/div/ul/li[+temp+]")).click();
				break;
			}

		}*/

    }



    public void SearchCruise(WebDriver driver) throws InterruptedException {

        Thread.sleep(1000);
        WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("Cruise_search"))));
        driver.findElement(By.xpath(prop.getProperty("Cruise_search"))).click();

    }


}
