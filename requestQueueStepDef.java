package StepDefinition;

import dev.failsafe.internal.util.Assert;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
//import org.junit.jupiter.api.Assertions;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import Pages.pageFactory;


public class testAutomationStepDef {
	
	WebDriver driver = null;
	
	pageFactory page;
	
	
//	public testAutomationStepDef(WebDriver driver) {
//		this.driver = driver;
//		StepDefinition.initElements(driver, this);
//	}]
//	@FindAll(
//	{
//		@FindBy(className = "navigationItem")
//	})
//	List<WebElement> mainMenuItems;
	

	
	@Given("user navigates to STAR QA platform")
	public void user_is_on_login_page() {
	
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("remote-allow-origins=*");
	    options.addArguments("--start-maximized");
	    
	    driver = new ChromeDriver(options);
	    
	    page = new pageFactory(driver);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://ui-us-east-1.systemsreporting-qa.aws.gwl.com/platform-react-app-ui/feature-sr-4328--snapshot/index.html#/");
	
		
	}
	
	@Then("page URL is accurate")
	public void validate_page_url() {
		
		try
		{
			page.check__sprint_metrics_page_url();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("all main menu items are displayed")
	public void all_main_menu_items_are_displayed() {
	    
		try
		{
			page.main_menu_items_displayed();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@When("reporting is opened")
	public void reporting_is_opened() {
		
		//find reporting menu
		List<WebElement> mainMenuItems = driver.findElements(By.className("navigationItem"));	     
	    //click reporting menu
		mainMenuItems.get(1).click();
	     
	     
	}

	@Then("all reporting sub menu items are displayed correctly")
	public void all_reporting_sub_menu_items_are_displayed_correctly() {
		
		//find reporting menu
		List<WebElement> mainMenuItems = driver.findElements(By.className("navigationItem"));
		WebElement reportingMenu = mainMenuItems.get(1);
		
		List<WebElement> reportingSubMenuItems = reportingMenu.findElements(By.className("subNavigationItem"));
	     
	     String[] expectedReportingSubMenuItems = {"Application development", "Project management office", 
	    		 "Quality center of excellence", "Canada life" ,"Operational health", "Ad-hoc", "Contractors",
	    		 "Finance" };
	     
	     for(int i = 0; i < reportingSubMenuItems.size(); i++)
			{
	    	 	reportingSubMenuItems.get(i).isDisplayed();
	    	 	
				try
				{
				assertEquals(expectedReportingSubMenuItems[i].toLowerCase(), reportingSubMenuItems.get(i).getText().toLowerCase());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
	}

	@When("Application development sub menu is opened")
	public void application_development_sub_menu_is_opened() {
		//find reporting menu
		List<WebElement> mainMenuItems = driver.findElements(By.className("navigationItem"));
		WebElement reportingMenu = mainMenuItems.get(1);
	     
		List<WebElement> reportingSubMenuItems = reportingMenu.findElements(By.className("subNavigationItem"));
	     
		reportingSubMenuItems.get(0).click();
		
	}

	@Then("application development mega menu items will be displayed correctly")
	public void application_development_mega_menu_items_will_be_displayed_correctly() {
	    
		
		List<WebElement> mainMenuItems = driver.findElements(By.className("navigationItem"));
		WebElement reportingMenu = mainMenuItems.get(1);
	     
	     List<WebElement> reportingSubMenuItems = reportingMenu.findElements(By.className("subNavigationItem"));
	     
	     //should give us release epic delivery, sprint metrics data
	     List<WebElement> reportingMegaMenuItemsClass = reportingSubMenuItems.get(0).findElements(By.className("wBH430x0pI9TurqmpQdB"));
	
	     List<WebElement> reportingMegaMenuItems = reportingMegaMenuItemsClass.get(0).findElements(By.tagName("p"));
	     
	     String[] expectedMegaMenu = {"Release epic delivery", "Sprint metrics data"};
	     
	     
	     for(int i = 0; i < reportingMegaMenuItems.size(); i++)
			{
	    	 reportingMegaMenuItems.get(i).isDisplayed();
			System.out.println(expectedMegaMenu[i] +  ">>>> " + reportingMegaMenuItems.get(i).getText());
			System.out.println(expectedMegaMenu[i] +  ">>>> " + reportingMegaMenuItems.get(i+1).getText());
	
				try
				{
				assertEquals(expectedMegaMenu[i].toLowerCase(), reportingMegaMenuItems.get(i+1).getText().toLowerCase());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
	
	}

	@When("Sprint metrics data is clicked")
	public void sprint_metrics_data_is_clicked() {
	    
		//find reporting menu
		List<WebElement> mainMenuItems = driver.findElements(By.className("navigationItem"));
		WebElement reportingMenu = mainMenuItems.get(1);
	     
	     List<WebElement> reportingSubMenuItems = reportingMenu.findElements(By.className("subNavigationItem"));
	     
	     //should give us release epic delivery, sprint metrics data
	     List<WebElement> reportingMegaMenuItems = reportingSubMenuItems.get(0).findElements(By.tagName("p"));
	
	     reportingMegaMenuItems.get(1).click();
	}

	@Then("user is on Sprint metrics data page")
	public void user_is_on_sprint_metrics_data_page() {
	    
		driver.navigate().refresh();
		
		try {
			Thread.sleep(3000);
			String expectedUrl = "https://ui-us-east-1.systemsreporting-qa.aws.gwl.com/platform-react-app-ui/feature-sr-4228--snapshot/index.html#/reporting/sprint-metrics-data";
			String currentUrl = driver.getCurrentUrl();
		assertEquals(expectedUrl, currentUrl);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Given("page breadcrumb is Home- Reporting- Application development- Sprint metrics data")
	public void page_breadcrumb_is_home_reporting_application_development_sprint_metrics_data() throws Exception{
	   
		//find all webelements with classname breadcrumb-item
		List<WebElement> breadcrumbItems = driver.findElements(By.className("breadcrumb-item"));
		
		//create string list of expected breadcrumb text
		String[] expectedBreadcrumbItems = {"Home", "Reporting", "Application development", "Sprint metrics data"};
		
		for(int i = 0; i < breadcrumbItems.size(); i++) {
			
			//check the element is displayed
			assertTrue(breadcrumbItems.get(i).isDisplayed());
			
			//check element text matches
			assertEquals(expectedBreadcrumbItems[i], breadcrumbItems.get(i).getText());
			System.out.println("Asserted successfully. ");
		}
		
	}

	@Then("page title is Sprint metrics data")
	public void page_title_is_sprint_metrics_data() {
	    try
	    {
	    	page.check_sprint_metrics_page_title();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}

	@Then("page drescription is correct")
	public void page_drescription_is_correct() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("some other action")
	public void some_other_action() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Sprint metrics data report table is displayed")
	public void sprint_metrics_data_report_table_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("table contains all required sprint metrics data report files")
	public void table_contains_all_required_sprint_metrics_data_report_files() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	
	/////request queue
	
	@When("user goes to contractor request queue page")
	public void navigate_to_request_queue() {
		
		try
		{
			//driver.get("https://ui-us-east-1.systemsreporting-qa.aws.gwl.com/platform-react-app-ui/feature-sr-4328--snapshot/index.html#/vm/contractor-request-queue");
			page.go_to_request_queue_page();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("user is on contractor request queue page")
	public void check_on_request_queue_page() {
		
		String expectedURL = "https://ui-us-east-1.systemsreporting-qa.aws.gwl.com/platform-react-app-ui/feature-sr-4328--snapshot/index.html#/vm/contractor-request-queue";
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals(expectedURL, currentUrl);
	
	}
	
	@When("user updates status for request")
	public void update_status() {
		
		try
		{
			
			page.update_requests_status();
//			page.validate_current_request_status();
//			page.update_request_status();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Then("request status is updated")
	public void check_status_updated() {
		
	}

	
	
}
