import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigateTest {

  @Test
  public void openNewTab(){

    WebDriverManager.chromedriver().setup();

    WebDriver driver = new ChromeDriver();
    driver.navigate().to("https://www.google.com");
    driver.switchTo().newWindow(WindowType.TAB);
    driver.navigate().to("https://www.swtestacademy.com");
  }


  @Test
  public void openNewWindow(){

    WebDriverManager.chromedriver().setup();

    WebDriver driver = new ChromeDriver();
    driver.navigate().to("https://www.google.com");
    driver.switchTo().newWindow(WindowType.WINDOW);
    driver.navigate().to("https://www.swtestacademy.com");
  }
}
