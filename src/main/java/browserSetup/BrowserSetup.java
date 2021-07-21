package browserSetup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetup {
    public static WebDriver driver;
    // ChromeDriver Setup Method
    public static WebDriver setChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }
    //Firefox Setup Method
    public static WebDriver setFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }
    // Edge Driver Setup Method
    public static WebDriver setEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        return driver;
    }

}
