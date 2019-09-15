package Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PizzaHutTASK {
	ChromeDriver d1;
	WebElement w2;

	@Test
	public void Pizza() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverManager.chromedriver().setup();
		d1 = new ChromeDriver();
		d1.manage().window().maximize();
		d1.get("https://www.pizzahut.co.in/customer-feedback.php");
		d1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		d1.findElement(By.xpath("(//a[@class='curve8px'])[1]")).click();
		d1.findElement(By.xpath("//input[@value='1']")).click();
		Select city = new Select(d1.findElement(By.xpath("//select[@id='city_id']")));
		city.selectByVisibleText("Hyderabad");
		Select city1 = new Select(d1.findElement(By.xpath("//select[@id='storeId']")));
		city1.selectByIndex(3);
		d1.findElement(By.xpath("//input[@value='Next']")).click();
		d1.findElement(By.xpath("//input[@name='name']")).sendKeys(Test(0));
		d1.findElement(By.xpath("//input[@name='email']")).sendKeys(Test(1));
		d1.findElement(By.xpath("//input[@name='phone']")).sendKeys(Test(2));
		d1.findElement(By.xpath("//textarea[@name='address']")).sendKeys(Test(3));
		d1.findElement(By.xpath("//textarea[@name='your_feedback']")).sendKeys(Test(4));
		Select g1 = new Select(d1.findElement(By.xpath("//select[@id='typeselector']")));
		g1.selectByIndex(1);
		Select g2 = new Select(d1.findElement(By.xpath("//select[@id='preferred_time']")));
		g2.selectByIndex(2);
		WebElement w1 = d1.findElement(By.xpath("//input[@id='image1']"));
		w1.sendKeys("E:\\Screenshot123.png");
		d1.findElement(By.xpath("//input[@name='date_of_visit']")).click();
		Calender();
		//System.exit(0);
		MonthSelection();
		//System.exit(0);
		d1.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr[4]/td[3]")).click();
		
		// d1.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//a[@class='ui-state-default']")).click();
		d1.findElement(By.xpath("//input[@name='math_captcha']")).sendKeys(Capcha());
		d1.findElement(By.xpath("//input[@name='submit']")).click();
	}

	public void Calender() {
		String inputYear = "2017";
		int year = Integer.parseInt(inputYear);

		String yearText = d1.findElement(By.xpath("//span[contains(@class,'year')]")).getText();
		System.out.println(yearText);
		int yearExist = Integer.parseInt(yearText);
		System.out.println(yearExist);
		while (true) {
			if (yearExist == year) {
				break;
			} else {
				d1.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Prev']")).click();
				yearExist =Integer.parseInt(d1.findElement(By.xpath("//span[contains(@class,'year')]")).getText());
				System.out.println("inside loop" + yearText);
			}
		}
	}
	
	public void MonthSelection()
	{
		String InputMonth = "April";
		//int Month = Integer.valueOf(InputMonth);
		String ActulMonth=(d1.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
		System.out.println(ActulMonth);
		while(true)
		{
			if(InputMonth.equals(ActulMonth))
			{
				break;
			}
			else
			{
				d1.findElement(By.xpath("//div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']/a[@title='Prev']")).click();
				ActulMonth=(d1.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
			}
		}
		
		
	}

	public String Capcha() {
		String w4 = d1.findElement(By.xpath("//td[@valign='middle']/font[1]")).getText();
		String Parts[] = w4.split(" ");
		String Part1 = Parts[0];
		String Part2 = Parts[1];
		String Part3 = Parts[2];
		int An1 = 0;
		// Integer.toString(An1);
		if (Part2.equals("+"))

		{

			An1 = Integer.parseInt(Part1) + Integer.parseInt(Part3);
		}
		if (Part2.equals("-"))

		{

			An1 = Integer.parseInt(Part1) - Integer.parseInt(Part3);
		}
		if (Part2.equals("*"))

		{

			An1 = Integer.parseInt(Part1) * Integer.parseInt(Part3);
		}
		if (Part2.equals("÷"))

		{

			An1 = Integer.parseInt(Part1) / Integer.parseInt(Part3);
		}
		return An1 + "";
	}

	public static String Test(int i) throws EncryptedDocumentException, InvalidFormatException, IOException {
		DataFormatter d1 = new DataFormatter();
		File f1 = new File("E:\\userdata\\Task.xlsx");
		FileInputStream fis = new FileInputStream(f1);
		Workbook w1 = WorkbookFactory.create(fis);
		Cell c1 = w1.getSheet("Task").getRow(1).getCell(i);
		return d1.formatCellValue(c1);
	}

}
