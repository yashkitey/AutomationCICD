package FileUploading;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

public class fileUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// Get the path of project run time - dynamically
		String downloadPath = System.getProperty("user.dir");
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("profile.default_content_settings.popups", 0);
	    chromePrefs.put("download.default_directory", downloadPath);   //document will download to user.dir
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);  //pref is property/key
		
		
        WebDriver driver = new ChromeDriver(options); //chrome invoke
		
        driver.get("https://www.ilovepdf.com/pdf_to_jpg");
        driver.findElement(By.linkText("Select PDF files")).click();
        Thread.sleep(3000);
        //Call .exe file of code to give path of upload file and uplaod
        Runtime.getRuntime().exec("C:\\Users\\HP\\Documents\\FileUploadingAutoITscripts\\fileupload.exe");   //java code use to execute .exe file
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//convert to jpg btn click once visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#processTask")));		
		driver.findElement(By.cssSelector("#processTask")).click();
		//click download btn once visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download JPG images")));
		driver.findElement(By.linkText("Download JPG images")).click();
		//driver.findElement(By.xpath(".downloader__btn")).click();
		
		
		//to check downloaded file in folder
		Thread.sleep(20000); 
//		File f = new File("C:\\Users\\HP\\Downloads\\ilovepdf_pages-to-jpg.zip");   //this will work in system where this code developed
//		if(f.exists())
//		{
//			System.out.println("File Downloaded and present in expected folder");
//		}
//		else
//		{
//			System.out.println("File not found");
//		}
		
		//Get path of project dynamically
		File f2 = new File(downloadPath+"/ilovepdf_pages-to-jpg.zip");   //this will work in system where this code developed
		if(f2.exists())
		{
			//Assert.assertTrue(f2.exists());
			System.out.println("File Downloaded and present in folder:" +downloadPath);
			if(f2.delete())
			{
				System.out.println("file deleted");
			}
		}
		else
		{
			System.out.println("File not found");
		}
		
		
		
	}

}
