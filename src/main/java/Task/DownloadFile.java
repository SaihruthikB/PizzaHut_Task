package Task;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadFile {
	@Test
	public static void download() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver d1 = new ChromeDriver();
		d1.manage().window().maximize();
		d1.get("http://demo.guru99.com/test/yahoo.html");
		d1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement button=d1.findElement(By.id("messenger-download"));
		String source=button.getAttribute("href");
		String cmd = "cmd/E wget -p E:" +source;
		try
		{
			Process p1 = Runtime.getRuntime().exec(cmd);
			int wait = p1.waitFor();
			System.out.println("Exit value:" +wait);
			
		}catch(IOException ex)
		{
			System.out.println(ex.toString());
		}
	}

}
