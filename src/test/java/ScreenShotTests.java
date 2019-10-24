import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScreenShotTests {

  @Test
  public void screenshotTest(){

    WebDriverManager.chromedriver().setup();

    WebDriver driver = new ChromeDriver();
    driver.navigate().to("https://www.swtestacademy.com");

    WebElement logo = driver.findElement(By.className("fusion-logo-link"));

    File file = logo.getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(file,new File("logo.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
