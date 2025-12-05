package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pages.LandingPage;

public class BaseClass {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initilizeDriver() throws IOException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-gpu");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--start-maximized"); // ensures visibility

		
		Properties prop = new Properties();
		
		 try {
		        prop.load(getClass().getClassLoader().getResourceAsStream("GlobalData.properties"));
		    } catch (Exception e) {
		        throw new RuntimeException("❌ ERROR: Unable to load GlobalData.properties", e);
		    }

		    String browserName = System.getProperty("browser") != null
		            ? System.getProperty("browser")
		            : prop.getProperty("browser");

		    if (browserName.equalsIgnoreCase("chrome")) {
		        driver = new ChromeDriver(options);
		    } else if (browserName.equalsIgnoreCase("fireFox")) {
		        driver = new FirefoxDriver();
		    } else if (browserName.equalsIgnoreCase("safari")) {
		        driver = new SafariDriver();
		    } else if (browserName.equalsIgnoreCase("edge")) {
		        driver = new EdgeDriver();
		    }


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		return driver;
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		File to = new File(System.getProperty("user.dir")+"//Screenshots//"+ testCaseName + ".png");
		FileUtils.copyFile(from, to);
		return System.getProperty("user.dir")+"//Screenshots//"+ testCaseName + ".png";
	}

	public List<HashMap<String, String>> getDataFromMap(String filePath) throws IOException {

		// Read JSON file content into a string
		String jsonContent = FileUtils.readFileToString(new File(filePath), "UTF-8");

		// Convert JSON string to List of HashMaps
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchApplication() throws IOException {
		driver = initilizeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
		System.out.println("Successfully Close");
	}

}
