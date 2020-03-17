package com.hubspot.tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.ContactsPage;
import com.hubspot.pages.HomePages;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;
import com.hubspot.util.ExcelUtil;

public class ContactsPageTest extends BasePage {
	
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePages homePage;
	ContactsPage contactsPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.gotoContactsPage();
	}
	
	@Test(priority=1)
	public void verifyContactPageTitle(){
		String title = contactsPage.getConctactsPageTitle();
		System.out.println("contact page title: "+ title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	
	// TestNG icin de hey word var @DataProvider kullanicaz.
	@DataProvider()
	public Object[][] getContactData(){
		Object data[][] = ExcelUtil.getTestData("contact"); //Sheet name
		return data;
	}
	
	@Test(priority=2, dataProvider="getContactData") // Method name
	
	public void createNewContactTest(String email, String firstname, String lastname, String jobtitle) throws InterruptedException{
		contactsPage.createNewContact(email, firstname, lastname, jobtitle);
	}
	
	@AfterMethod
	public void tearDown(){
		basePage.quitBrowser();
	}







}
