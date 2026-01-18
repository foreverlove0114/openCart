package testBase;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j


public class BaseClass {
    public static WebDriver driver;
    // 虽然 static 带来了便利，但它有一个致命的缺点：不支持真正的并行执行（Parallel Execution）。
    //
    //线程冲突：static 变量在 JVM 中只有一份。如果你配置 TestNG 同时运行 5 个测试用例，这 5 个线程会试图同时操控同一个 static driver。
    //
    //结果：浏览器会乱跳、报错，或者一个线程把另一个线程的浏览器关掉了。
    public Logger logger; //Log4j
    public Properties p;

    @Parameters({"os","browser"})
    @BeforeMethod(groups = { "Master", "Sanity", "Regression" })
    public void setup(String os,String br) throws IOException {

        //Loading properties files
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        //Loading Log4j file
        logger = LogManager.getLogger(this.getClass()); //Log4j

        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //os
            if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN11);
            }else if(os.equalsIgnoreCase("mac")){
                capabilities.setPlatform(Platform.MAC);
            }else if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No matching os");
                return;
            }

            //browser
            switch (br.toLowerCase()){
                case "chrome":capabilities.setBrowserName("chrome");break;
                case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
                case "firefox":capabilities.setBrowserName("firefox");break;
                default:System.out.println("No matching browser");return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }

        if(p.getProperty("execution_env").equalsIgnoreCase("local")){
            //launching browser based on condition
            switch (br.toLowerCase()){
                case "chrome": driver = new ChromeDriver();break;
                case "edge": driver = new EdgeDriver(); break;
                case "firefox": driver = new FirefoxDriver();break;
                default:System.out.println("No matching browser...");return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber(){
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric(){
        String str = RandomStringUtils.randomAlphabetic(3);
        String num = RandomStringUtils.randomNumeric(3);
        return (str + "@" + num);
    }

    public String captureScreen(String tname)throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
