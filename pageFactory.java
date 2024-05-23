package Pages;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class pageFactory {

	@FindAll
		({
		@FindBy(className = "navigationItem")
		})
	List<WebElement> menuItems;
	
	@FindAll
	({
		@FindBy(className = "breadcrumb-item")
	})
	List<WebElement> pageBreadcrumb;
	
	@FindBy(className = "headingText")
	WebElement pageTitle;
	
	
	WebDriver driver;
	
	public pageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void check__sprint_metrics_page_url() {
		
		try {
			Thread.sleep(3000);
			String expectedUrl = "https://ui-us-east-1.systemsreporting-qa.aws.gwl.com/platform-react-app-ui/feature-sr-4228--snapshot/index.html#/";
			String currentUrl = driver.getCurrentUrl();
		assertEquals(expectedUrl, currentUrl);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void main_menu_items_displayed() {
		
		//main menu items will be a list of elements with the navigationItem class name
				List<WebElement> mainMenuItems = driver.findElements(By.className("navigationItem"));
				
				//list of expected main menu items text
				String[] expectedMainMenuItems = {"Dashboards", "Reporting" ,"People management" , "Vendor management" , "Training", "Forms" , "Contact us", "Admin"};
				
				//loop through the menu elements and verify they are displayed and have the correct text
				
				for(int i = 0; i < mainMenuItems.size(); i++)
				{
					mainMenuItems.get(i).isDisplayed();
					try
					{
					assertEquals(expectedMainMenuItems[i].toLowerCase(), mainMenuItems.get(i).getText().toLowerCase());
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
	}
	
	public void check_sprint_metrics_page_title() throws Exception {
		
		assertTrue(pageTitle.isDisplayed());
		
		String sprintMetricsExpectedTitleText = "Sprint metrics2 data";
		if(sprintMetricsExpectedTitleText.replace(" ", "").equals(pageTitle.getText().replace(" ", "")))
		{
			System.out.println("Page title verified");
		}
		else if(!sprintMetricsExpectedTitleText.replace(" ", "").equals(pageTitle.getText().replace(" ", "")))
		{
			throw new Exception("Page title incorrect. Expected title: " + sprintMetricsExpectedTitleText + "Displayed: " + pageTitle.getText());
		}
		
	}
	
	public void go_to_request_queue_page() {
		
		try
		{
			Thread.sleep(3000);
			WebElement vmMenu = menuItems.get(3);
//			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
//			wait.until(ExpectedConditions.visibilityOf(vmMenu));
			vmMenu.click();
			
			List<WebElement> vmSubMenuItems = vmMenu.findElements(By.className("subNavigationItem"));
			vmSubMenuItems.get(1).click();
			driver.navigate().refresh();
			
			try 
			{
				Thread.sleep(5000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		
	}
	
	public void update_requests_status() {
		
		List<WebElement> tableRows = driver.findElements(By.className("ydLIFREGYMRZVJe2G54A"));
		
		
		for(int i = 0; i < tableRows.size(); i++)
		{
			
		List<WebElement> columnValues = tableRows.get(i).findElements(By.className("NfIlypsiikJ4GqfATy1s"));
			
		List<WebElement> actionButtons = columnValues.get(0).findElements(By.tagName("a"));
			
		WebElement editStatusButton = actionButtons.get(0);
		
		editStatusButton.click();
		
		
		//String expectedStatus = "Awaiting VP response on extension request";
		
//		Select s = new Select(driver.findElement(By.id("contractorStatusId")));
//		String displayedStatus = s.getFirstSelectedOption().getText();
//		System.out.println("Displayed status: " + displayedStatus);
		
		//assertEquals(expectedStatus, displayedStatus);
		
		
		Select s2 = new Select(driver.findElement(By.id("contractorStatusId")));
		s2.selectByIndex(2);
		
		WebElement saveButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
		
		saveButton.click();
		tableRows = driver.findElements(By.className("ydLIFREGYMRZVJe2G54A"));
		
		try
		{
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
//		editStatusButton.click();
		}
		
		
		
//		WebElement saveButton = driver.findElement(By.xpath("//button[type = 'submit']"));
//		
//		editStatusButton.click();
	}
	
	/**
	 * 
	 */
	public void validate_current_request_status() {
		
		String expectedStatus = "Awaiting VP response on extension request";
		
		Select s2 = new Select(driver.findElement(By.id("contractorStatusId")));
		String displayedStatus = s2.getFirstSelectedOption().getText();
		System.out.println("Displayed status: " + displayedStatus);
		
		assertEquals(expectedStatus, displayedStatus);
		
	}
	
	public void update_request_status() {
		
		
		
		Select s = new Select(driver.findElement(By.id("contractorStatusId")));
		s.selectByValue("15");
		
		WebElement saveButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
		
		saveButton.click();
		
		
		
		
	}
	
	
}
