//package automationframework;

//Test case-2 Play with Bookmark-@Nitesh Kumar Agarwal 
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaywithBookmark {
	public WebDriver driver;

	@BeforeClass(enabled = false)
	public void Setup() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("file:///G:/webapp/webapp/Home.html");//path of home page of the project in local machine
	}
	//Test Case created using Selenium Testing tool
	//These are Functional Test case.
	//Test case Created for Play with Bookmark and executed using  "Test-GN" Tool and result is shown in Firefox Browser
	@Test(enabled = false)
	public void f() throws InterruptedException {

		List<WebElement> Paragraph = driver.findElements(By.xpath("//div/p"));
		for (int i = 0; i < Paragraph.size(); i++) {
			List<WebElement> listwords = Paragraph.get(i).findElements(By.tagName("span"));
			for (int j = 0; j < listwords.size(); j++) {
				if (listwords.get(j).getText().equalsIgnoreCase("because")) {
					System.out.println(listwords.get(j).getText());
					Actions act = new Actions(driver);

					System.out.println("Before click on Element of Cssvalue :...."
							+ listwords.get(j).getCssValue("background-color"));
					listwords.get(j).click();
					System.out.println("After click on Element of Cssvalue :...."
							+ listwords.get(j).getCssValue("background-color"));
				}

			}

			/*
			 * Actions act = new Actions(driver);
			 * act.moveToElement(listwords.get(i)).perform();
			 * System.out.println(listwords.get(i).getCssValue("box-shadow"));
			 */

		}

	}

	@Test(enabled = false)
	public void Setup1() {
		FirefoxDriver fd = new FirefoxDriver();
		fd.get("file:///G:/webapp/webapp/Home.html");
		fd.manage().window().maximize();
		fd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		fd.findElement(By.xpath(".//*[@id='playWithBookmark']")).click();
		for (int j = 0; j < 3; j++) {
			fd.findElement(By.xpath(".//*[@id='passage-audio']")).sendKeys(Keys.ARROW_DOWN);
		}
		List<WebElement> ls = fd.findElements(By.xpath(".//*[@id='passage-text']/p"));
		for (int i = 0; i < ls.size(); i++) {
			List<WebElement> lst = ls.get(i).findElements(By.tagName("span"));
			for (WebElement d : lst) {
				System.out.println(d.getText());
				d.click();
			}
		}
		fd.findElement(By.xpath("//button[@id='EndBookmark']")).click();
	}

	@Test
	public void Setup2() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("file:///G:/webapp/webapp/Home.html");
		WebElement element = driver.findElement(By.xpath("//select[@id='selectAudioBox']"));
		Select ddaudiolist = new Select(element);
		ddaudiolist.selectByVisibleText("Audio 1");
		;
		Select ddtextlist = new Select(driver.findElement(By.xpath(".//*[@id='selecttextbox']")));
		ddtextlist.selectByVisibleText("Text 1");
		driver.findElement(By.xpath("//button[@id='MainPage']")).click();
		driver.findElement(By.xpath("//button[@id='playWithBookmark']")).click();
		Thread.sleep(3000);

		List<WebElement> list = driver.findElements(By.xpath(".//*[@id='passage-text']"));
		for (int i = 0; i < list.size(); i++) {
			List<WebElement> listwords = list.get(i).findElements(By.xpath("span"));
			for (int j = 0; j < listwords.size(); j++) {
				if (listwords.get(j).getText().equalsIgnoreCase("some")) {

					System.out.println("Before click on Element of Cssvalue of " + listwords.get(j).getText() + " :...."
							+ listwords.get(j).getCssValue("background-color"));
					listwords.get(j).click();
					System.out.println("After click on Element of Cssvalue of " + listwords.get(j).getText() + ":...."
							+ listwords.get(j).getCssValue("background-color"));
					break;

				}

			}
			break;
			// i=list.size();
			// break;
		}
		driver.findElement(By.xpath("//button[@id='Bookmark']")).click();
		driver.findElement(By.xpath("//button[@id='EndBookmark']")).click();
		driver.findElement(By.xpath("//button[@id='playWithBookmark']")).click();
		List<WebElement> list1 = driver.findElements(By.xpath(".//*[@id='passage-text']"));
		for (int i = 0; i < list.size(); i++) {
			List<WebElement> listwords = list1.get(i).findElements(By.xpath("span"));
			for (int j = 0; j < listwords.size(); j++) {
				if (listwords.get(j).getText().equalsIgnoreCase("some")) {

					System.out.println("Before click on Element of Cssvalue of " + listwords.get(j).getText() + " :...."
							+ listwords.get(j).getCssValue("background-color"));
					listwords.get(j).click();
					System.out.println("After click on Element of Cssvalue of " + listwords.get(j).getText() + ":...."
							+ listwords.get(j).getCssValue("background-color"));
					break;

				}

			}
			break;
			// i=list.size();
			// break;
		}
		
	}

}
