package Task;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {
	@Test
	public static void BrokenLinks1() throws MalformedURLException, IOException
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver d1 = new ChromeDriver();
		d1.manage().window().maximize();
		d1.manage().deleteAllCookies();
		d1.get("https://www.biba.in/");
		d1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> List=d1.findElements(By.tagName("a"));
		List.addAll(d1.findElements(By.tagName("img")));
		System.out.println("Total Elements" + List.size());
		List<WebElement> activelink = new ArrayList<WebElement>();
		try
		{
		for(int i=0; i<List.size();i++)
		{
		//System.out.println(List.get(i).getAttribute("href"));
			if(List.get(i).getAttribute("href")!=null)
			{
				activelink.add(List.get(i));
			}
		}
		}catch (StaleElementReferenceException e){}
			System.out.println("The active elements in the site" + activelink.size());
	try
	{
	for(int j = 0; j<activelink.size();j++)
		
	{
		HttpsURLConnection connection=(HttpsURLConnection)new URL(activelink.get(j).getAttribute("href")).openConnection();
		connection.connect();
		String Message=connection.getResponseMessage();
		connection.disconnect();
		System.out.println(activelink.get(j).getAttribute("href")+".....>"+""+Message);
	}
	  } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
		
	}
	}
	}
