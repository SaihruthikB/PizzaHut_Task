package Task;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Calender {
	static ChromeDriver d1;
	//String inputyear;
	public static void main(String args[])
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver d1 = new ChromeDriver();
		d1.manage().window().maximize();
		//d1.manage().deleteAllCookies();
		d1.get("http://crm.pizzahut.co.in/index.php/feedback/dinein");
		d1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//d1.findElement(By.xpath("(//a[@class='curve8px'])[1]")).click();
		d1.findElement(By.xpath("//input[@value='1']")).click();
		Select city = new Select(d1.findElement(By.xpath("//select[@id='city_id']")));
		city.selectByVisibleText("Hyderabad");
		Select city1 = new Select(d1.findElement(By.xpath("//select[@id='storeId']")));
		city1.selectByIndex(3);
		d1.findElement(By.xpath("//input[@value='Next']")).click();
		
		
		d1.findElement(By.xpath("//input[@name='date_of_visit']")).click();
		getYear();
		Monthselection();
		
	}
	public static void getYear()
	{
		String inputyear= "2017";
		int getYear = Integer.parseInt(inputyear);
		String actaulyear=d1.findElement(By.xpath("//span[contains(@class,'year')]")).getText();
		System.out.println(actaulyear);
		int actaulyear1 = Integer.parseInt(actaulyear);
		while(true)
		{
		if(getYear == actaulyear1)
		{
			break;
		}else
		{
			d1.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Prev']")).click();
			actaulyear1=Integer.parseInt(d1.findElement(By.xpath("//span[text()=2019]")).getText());
			
		}
		
		
	}
	}
	public static void Monthselection()
	{
		String ExpMonth = "June";
		String ActMonth=d1.findElement(By.xpath("//span[contains(@class,'ui-datepicker-month')]")).getText();
		while(true)
		{
			if(ExpMonth.equals(ActMonth))
			{
			break;
		}else
		{
			d1.findElement(By.xpath("//span[contains(@class,'ui-icon ui-icon-circle-triangle-w')]")).clear();
			ActMonth=d1.findElement(By.xpath("//span[contains(@class,'ui-datepicker-month')]")).getText();
			
		}
	}
}
	public String Capcha()
	{
		int a1=0;
		String value=d1.findElementByXPath("//td[@valign='middle']/font[1]").getText();
		String Parts[]=value.split(value);
		String part1=Parts[0];
		String part2=Parts[1];
		String part3=Parts[2];
		if (part2.equals("+"))
		{
			a1=Integer.parseInt(part1) + Integer.parseInt(part3);
			
		}
		return a1+ " ";
	}
}
