package env;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ErrorHandler;

public class DriverUtil {
	public static long DEFAULT_WAIT = 20;
	protected static WebDriver driver = null;
	static String currentPath = System.getProperty("user.dir");
	static Properties prop = new Properties();
	static DesiredCapabilities capability = null;

	

	public static WebDriver getDefaultDriver() {
		if (driver != null) {
			return driver;
		}
		DesiredCapabilities capabilities = null;
		capabilities = DesiredCapabilities.chrome();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("takesScreenshot", true);
		driver = chooseDriver(capabilities);
		driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}

	private static WebDriver chooseDriver(DesiredCapabilities capabilities) {
		final ChromeOptions chromeOptions = new ChromeOptions();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		try {
			driver = new ChromeDriver(chromeOptions);
			ErrorHandler handler = new ErrorHandler();
			handler.setIncludeServerErrors(false);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return driver;
	}

	public static void closeDriver() {
		if (driver != null) {
			try {
				driver.close();
				driver.quit(); 
			} catch (NoSuchMethodError nsme) { // in case quit fails
			} catch (NoSuchSessionException nsse) { // in case close fails
			} catch (SessionNotCreatedException snce) {
			} // in case close fails
			driver = null;
		}
	}
}
