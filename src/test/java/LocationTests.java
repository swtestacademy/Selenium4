import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocationTests {

  @Test
  public void location(){

    WebDriverManager.chromedriver().setup();

    WebDriver driver = new ChromeDriver();
    driver.navigate().to("https://www.swtestacademy.com");

    WebElement logo = driver.findElement(By.className("fusion-logo-link"));

    System.out.println("Height is "+logo.getRect().getDimension().getHeight());
    System.out.println("Width is "+logo.getRect().getDimension().getWidth());
    System.out.println("Location X is "+logo.getRect().getX());
    System.out.println("Location Y is "+logo.getRect().getY());
  }

}
