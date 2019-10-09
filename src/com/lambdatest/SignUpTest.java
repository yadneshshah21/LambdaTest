package com.lambdatest;


//TestNG Todo : Sample App
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
 
public class SignUpTest{
    public String username = "yadneshshah91";
    public String accesskey = "iD4oCZKyC8R2kwH1kLhTxAGgXZFANGoyzGDzY9RTqDTx9jskZo";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
  
    @BeforeClass
    	public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
               
    }
    
    
    @BeforeMethod
    public void openBrowser()
    {
    	driver.manage().deleteAllCookies();
    	
		driver.get("https://www.lambdatest.com/");
        
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        
        WebElement signUpButton = driver.findElement(By.xpath("//a[contains(text(),'Free Signup')]"));
        signUpButton.click(); 
        
    }
    
    @Test
    public void verifyElemntsOnPageTest()
    {
    	WebElement lambdaTestLogo = driver.findElement(By.xpath("//p[@class='signup-titel']"));
    	lambdaTestLogo.isDisplayed();
    	
    	WebElement signUpTitle = driver.findElement(By.xpath("//p[@class='signup-titel']"));
    	signUpTitle.isDisplayed();
    	
    	WebElement termsText = driver.findElement(By.xpath("//label[@class='woo']"));
    	termsText.isDisplayed();
    	
    	WebElement loginLinkText = driver.findElement(By.xpath("//p[@class='login-in-link test-left']"));
    	loginLinkText.isDisplayed();
    	
    }
    
    @Test
    public void termsRedirectionTest()
    {
    	WebElement termsLink = driver.findElement(By.xpath("//a[contains(text(),'Terms')]"));
    	termsLink.click();    	
    	
    	Set <String> allWindows = driver.getWindowHandles();
    	
    	for(String handle : allWindows)
    	{
    		driver.switchTo().window(handle);
    	}    	        	
    	  	
    	String expectedURL = "https://www.lambdatest.com/terms-of-service";
    	String actualURL = driver.getCurrentUrl();
    	//System.out.println(actualURL);
    	Assert.assertEquals(actualURL, expectedURL);
    	
    	String expectedTitle = "Terms of Service - LambdaTest";
    	String actualTitle = driver.getTitle();
    	//System.out.println(actualTitle);
    	Assert.assertEquals(actualTitle, expectedTitle);
    	
    }
    
    @Test
    public void privacyPolicyRedirectionTest()
    {
    	WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[contains(text(),'Privacy')]"));
    	privacyPolicyLink.click();
    	
    	Set <String> allWindows = driver.getWindowHandles();
    	
    	for(String handle : allWindows)
    	{
    		driver.switchTo().window(handle);
    	}    	        	
    	  	
    	String expectedURL = "https://www.lambdatest.com/privacy";
    	String actualURL = driver.getCurrentUrl();
    	//System.out.println(actualURL);
    	Assert.assertEquals(actualURL, expectedURL);
    	
    	String expectedTitle = "Privacy Policy | LambdaTest";
    	String actualTitle = driver.getTitle();
    	//System.out.println(actualTitle);
    	Assert.assertEquals(actualTitle, expectedTitle);
    }
    
    @Test
    public void loginRedirectionTest()
    {
    	WebElement loginLink = driver.findElement(By.xpath("//a[contains(text(),'Login')]"));
    	loginLink.click();
    	
    	String expectedURL = "https://accounts.lambdatest.com/login";
    	String actualURL = driver.getCurrentUrl();
    	//System.out.println(actualURL);
    	Assert.assertEquals(actualURL, expectedURL);
    	
    	String expectedTitle = "Login - LambdaTest";
    	String actualTitle = driver.getTitle();
    	//System.out.println(actualTitle);
    	Assert.assertEquals(actualTitle, expectedTitle);
    	
    }
  
    @Test
    public void validRegistrationTest(){            
              
        WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
             
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("TestName");
             
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test3.lambdatest@gmail.com");
         
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@12345");
 
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("9876543210");
            
        WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        termsOfServices.click();
              
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedURL = "https://accounts.lambdatest.com/email/verify";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
        
        String expectedTitle = "Verify Your Email Address - LambdaTest";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        
        
    }
    
    @Test
    public void emptyCompanyNameTest()
    {
    	 WebElement companyName = driver.findElement(By.name("organization_name"));
         companyName.sendKeys("");
              
         WebElement fullName = driver.findElement(By.name("name"));
         fullName.sendKeys("TestName");
              
         WebElement email = driver.findElement(By.name("email"));
         email.sendKeys("test2.lambdatest@gmail.com");
          
         WebElement password = driver.findElement(By.name("password"));
         password.sendKeys("Test@12345");
  
         WebElement phone = driver.findElement(By.name("phone"));
         phone.sendKeys("9876543210");
             
         WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
         termsOfServices.click();
               
         WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
         signUp.click();
         
         
		/*
		 * Set <String> allWindows = driver.getWindowHandles();
		 * 
		 * for(String handle : allWindows) { driver.switchTo().window(handle); }
		 */ 
         
         String expectedURL = "https://accounts.lambdatest.com/email/verify";
         String actualURL = driver.getCurrentUrl();
         Assert.assertEquals(actualURL, expectedURL);
         
         String expectedTitle = "Verify Your Email Address - LambdaTest";
         String actualTitle = driver.getTitle();
         Assert.assertEquals(actualTitle, expectedTitle);
    }
    
    @Test
    public void emptyNameTest()
    {
        WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
          
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("");
         
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test@test.com");
                 
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@123");
          
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("9876543210");
            
        WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        termsOfServices.click();
         
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedErrorMsg = "Please enter your Name";
        
        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Please enter your Name')]"));
        String actualErrorMsg = exp.getText();
        		
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
        
    }
    
    @Test
    public void emptyEmailTest()
    {
    	WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
          
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("test");
         
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("");
                 
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@123");
          
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("9876543210");
            
        WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        termsOfServices.click();
         
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedErrorMsg = "Please enter your Email Address";
        
        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Please enter your Email Address')]"));
        String actualErrorMsg = exp.getText();
        		
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
    
    @Test
    public void invalidEmailTest()
    {
        
        WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
          
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("TestName");
         
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test@test.com");
                 
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@123");
          
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("9876543210");
            
        WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        termsOfServices.click();
         
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedErrorMsg = "This email is already registered";
        
        WebElement exp = driver.findElement(By.xpath("//p[@class='error-mass']"));
        String actualErrorMsg = exp.getText();
        		
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
    
    @Test
    public void emptyPasswordTest()
    {
    	WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
          
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("TestName");
         
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test@test.com");
                 
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("");
          
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("9876543210");
            
        WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        termsOfServices.click();
         
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedErrorMsg = "Please enter a desired password";
        
        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Please enter a desired password')]"));
        String actualErrorMsg = exp.getText();
        		
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
    
    @Test
    public void inValidPasswordTest()
    {
        WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
          
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("TestName");
         
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test@test.com");
                 
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("T");
          
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("9876543210");
            
        WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        termsOfServices.click();
         
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedErrorMsg = "Password should be at least 8 characters long";
        
        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Password should be at least 8 characters long')]"));
        String actualErrorMsg = exp.getText();
        		
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    	//Password should be at least 8 characters long
    }
  
    @Test
    public void emptyPhoneTest()
    {
    	WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
          
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("TestName");
         
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test@test.com");
                 
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@123");
          
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("");
            
        WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        termsOfServices.click();
         
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedErrorMsg = "The phone field is required.";
        
        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'The phone field is required.')]"));
        String actualErrorMsg = exp.getText();
        		
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
    
    
    @Test
    public void inValidPhoneTest()
    {
        WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
          
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("TestName");
         
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test@test.com");
                 
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@123");
          
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("98");
            
        WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        termsOfServices.click();
         
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedErrorMsg = "Please enter a valid Phone number";
        
        WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Please enter a valid Phone number')]"));
        String actualErrorMsg = exp.getText();
        		
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
        
    	//Please enter a valid Phone number
    }
    
    @Test
    public void uncheckedTerms()
    {
        WebElement companyName = driver.findElement(By.name("organization_name"));
        companyName.sendKeys("TestCompany");
          
        WebElement fullName = driver.findElement(By.name("name"));
        fullName.sendKeys("TestName");
         
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test@test.com");
                 
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Test@123");
          
        WebElement phone = driver.findElement(By.name("phone"));
        phone.sendKeys("9876543210");
            
        //WebElement termsOfServices = driver.findElement(By.id("terms_of_service"));
        //termsOfServices.click();
         
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedTermsErrorMessage = "To proceed further you must agree to our Terms of Service and Privacy Policy";
        WebElement uncheckedTermCheckbox = driver.findElement(By.xpath("//p[@class='error-mass mt-2']"));
    	String actualTermsErrorMessage = uncheckedTermCheckbox.getText();
        //To proceed further you must agree to our Terms of Service and Privacy Policy
    	
    	Assert.assertEquals(actualTermsErrorMessage, expectedTermsErrorMessage);
    }
    
    @AfterClass
    public void tearDown() throws Exception {
       if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}