package Demo;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class CaptureScreenshot {
	
	

	public void screenShot() throws Exception
	{

// This code will capture screenshot of current screen		
BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	    
// This will store screenshot on Specific location
ImageIO.write(image, "png", new File("C:\\selenium\\CurrentScreenshot.png")); 

	}

		
	public void captureScreenShot(){
		// Take screenshot and store as a file format 
		WebDriver driver = new ChromeDriver();
		
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
			
		// now copy the  screenshot to desired location using copyFile method

		try {
			FileHandler.copy(src, new File("C:\\selenium\\screen1"+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
			
	}	
}

