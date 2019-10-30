import static org.openqa.selenium.devtools.network.Network.emulateNetworkConditions;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.ConnectionType;
import org.openqa.selenium.devtools.security.Security;

public class DevToolsTest {

  private DevTools chromeDevTools;
  private ChromeDriver driver;

  @Before
  public void before(){
    WebDriverManager.chromedriver().setup();

    driver = new ChromeDriver();
    chromeDevTools = driver.getDevTools();
    chromeDevTools.createSession();
  }

  @Test
  public  void emulateNetworkConditionTest(){

    chromeDevTools.send(Network.enable(Optional.of(1000000), Optional.empty(), Optional.empty()));
    chromeDevTools.send(
        emulateNetworkConditions(false,100,200000,100000, Optional.of(ConnectionType.cellular4g)));

    long startTime = System.currentTimeMillis();
    driver.navigate().to("https://www.swtestacademy.com");

    long endTime = System.currentTimeMillis();

    System.out.println("Load time is "+ (endTime-startTime));
  }

  @Test
  public void userAgentOverride(){

    String fakeAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36";

    driver.navigate().to("https://www.whoishostingthis.com/tools/user-agent/");

    Assert.assertNotEquals(driver.findElement(By.cssSelector(".info-box.user-agent")).getText(),fakeAgent);

    chromeDevTools.send(Network.setUserAgentOverride(fakeAgent,
        Optional.empty(), Optional.empty()));

    driver.navigate().to("https://www.whoishostingthis.com/tools/user-agent/");

    Assert.assertEquals(driver.findElement(By.cssSelector(".info-box.user-agent")).getText(),fakeAgent);
  }

  @Test
  public void webToMobileView(){

    String fakeAgent = "Mozilla/5.0 (Linux; Android 7.0; SM-G930V Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.125 Mobile Safari/537.36";

    chromeDevTools.send(Network.setUserAgentOverride(fakeAgent,
        Optional.empty(), Optional.empty()));

    driver.navigate().to("https://www.amazon.com");

  }


  @Test
  public void loadInsecureWebsite() {

    chromeDevTools.send(Security.enable());

    chromeDevTools.send(Security.setIgnoreCertificateErrors(true));

    driver.get("https://expired.badssl.com/");

  }

}
