package info.seleniumcucumber.stepdefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import env.DriverUtil;

public class PredefinedStepDefinitions {
	protected WebDriver driver = DriverUtil.getDefaultDriver();
	// Navigation Steps

	// Step to navigate to specified URL
	@Then("^I navigate to \"([^\"]*)\"$")
	public void navigate_to(String link) {
		driver.get(link);
	}

	// Step to close the browser
	@Then("^I close browser$")
	public void close_browser() {
		driver.quit();
	}

	@Then("^I should see page title starts with \"([^\"]*)\"$")
	public void check_title(String title) throws Throwable {
		Assert.assertTrue(driver.getTitle().startsWith(title), "Title of the page not started with :" + title);
		System.out.println("Title of the page started with :" + title);
	}

	@Given("^I want food in \"([^\"]*)\"$")
	// @Given("^I want food in (\\d+)$")
	public void i_want_food_in(String postalCode) throws Throwable {
		WebElement txtPostalCode = driver.findElement(By.name("postcode"));
		txtPostalCode.clear();
		txtPostalCode.sendKeys(postalCode);
	}

	@When("^I search for restaurants$")
	public void i_search_for_restaurants() throws Throwable {
		WebElement btnSearchRestaurents = driver
				.findElement(By.xpath("//button[@type='submit']//span[text()='Search']"));
		Assert.assertTrue(btnSearchRestaurents.isDisplayed());
		btnSearchRestaurents.submit();
	}

	@Then("^I should see some restaurants in \"([^\"]*)\"$")
	public void i_should_see_some_restaurants_in(String postalCode) throws Throwable {
		List<WebElement> lblResultscount = driver
				.findElements(By.xpath("//div[@data-test-id='openrestaurants']/section"));
		String lblLocationDisplayed = driver
				.findElement(By.xpath("//div[@class='c-dishSearch-locationHeader-container']/h1")).getText();

		Assert.assertTrue(lblLocationDisplayed.startsWith(postalCode),
				"Results page for the postal code : " + postalCode + " is not displayed");
		Assert.assertTrue(lblResultscount.size() >= 1,
				"Not Even one Resturant(s) is displayed in search results for postal code : " + postalCode);
	}

	@Then("^I should see an error message \"([^\"]*)\"$")
	public void i_should_see_an_error_message(String erroMsg) throws Throwable {
		String msgSearchError = driver.findElement(By.id("errorMessage")).getText();
		Assert.assertTrue(msgSearchError.equals(erroMsg),
				"Message displayed is not same as the error message.Error Message Expected is :" + erroMsg
						+ " , Error Message displayed is :" + msgSearchError);
	}

	@Given("^I land on homepage$")
	public void i_land_on_homepage() throws Throwable {
		driver.findElement(By.xpath("//a[@aria-label='Go to Just Eat homepage']")).click();
	}

	@When("^user is not logged in$")
	public void user_is_not_logged_in() throws Throwable {
		
			List <WebElement> linkLogin = driver.findElements(By.xpath("//a[text()='Logout']"));
			if(linkLogin.size()>0)	{
				linkLogin.get(0).click();
			}
	}

	@Then("^I should see Login option$")
	public void i_should_see_Login_option() throws Throwable {
		WebElement linkLogin = driver.findElement(By.xpath("//a[text()='Login']"));
		Assert.assertTrue(linkLogin.isDisplayed());

	}

	@Then("^I should see Help option$")
	public void i_should_see_Help_option() throws Throwable {
		WebElement linkHelp = driver.findElement(By.xpath("//a[text()='Help']"));
		Assert.assertTrue(linkHelp.isDisplayed());

	}
}