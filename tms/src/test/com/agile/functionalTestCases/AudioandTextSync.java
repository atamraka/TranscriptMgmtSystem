//Functional Test Case for Audio and Text Synchronization and then play the audio-@Mahaveer Jhaver
//package automationframework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AudioandTextSync {
	public static WebDriver fd = new FirefoxDriver();

	@Test
	public static void Play() throws InterruptedException {
		fd.manage().window().maximize();
		fd.get("file:///G:/webapp/webapp/Home.html");

		new Select(fd.findElement(By.xpath(".//*[@id='selectAudioBox']")))
				.selectByVisibleText("Audio 1");
		new Select(fd.findElement(By.xpath(".//*[@id='selecttextbox']")))
				.selectByVisibleText("Text 1");
		fd.findElement(By.xpath(".//*[@id='MainPage']")).click();
Thread.sleep(3000);
		fd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		fd.findElement(By.xpath(".//*[@id='playWithBookmark']")).click();// click
																			// on
																			// play
		for (int j = 0; j < 3; j++) {
			fd.findElement(By.xpath(".//*[@id='passage-audio']")).sendKeys(
					Keys.ARROW_DOWN);
		}
		List<WebElement> ls = fd.findElements(By
				.id("passage-text"));
		for (int i = 0; i < ls.size(); i++) {
			List<WebElement> lst = ls.get(i).findElements(By.tagName("span"));
			for (WebElement d : lst) {
				System.out.println(d.getText());
				d.click();
			}
		}
		fd.findElement(By.xpath("//button[@id='EndBookmark']")).click();// /click
																		// on
																		// pause

	}
	
	
}
