package com.hubspot.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;


@Listeners(com.hubspot.listeners.TestAllureListener.class) //we add listener
public class LoginPageTest {

	Logger log = Logger.getLogger("LoginPageTest"); // class name copy paste
	WebDriver driver;
	Properties prop; //java util import yapiyoruz.
	BasePage basePage;
	LoginPage loginPage;
	
	
	
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		log.info("Browser is launching");
		prop = basePage.initialize_properties(); //Base page Prop a esitliyoruz.
		driver = basePage.initialize_driver(prop); 
		loginPage = new LoginPage(driver);
	}
	
	
	@Test(priority=2, enabled=true, description="Login test using corret username and password")
	public void loginTest1(){
		log.info("logintest1 is starting...");
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		log.info("Logintest1 is ending...");
		
		
	}
	@Test(priority=3, enabled=true, description="Login test using corret username and inpassword")
	public void loginTest2(){
		log.info("Test 2 is starting...");
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("incorrectpass"));
		log.info("test 2 is ending....");
	}
	@Test(priority=4, enabled=true, description="Login test using incorret username and password")
	public void loginTest3(){
		loginPage.doLogin(prop.getProperty("incorrectuser"), prop.getProperty("password"));
	}
	
	
	//enable=true yaparsak test calisir
	//       false calismaz
	//Bircok test case imiz oldugunda kullaniriz istedigimiz test caselerini calistitmak icin.
	
	@Test(priority=1,enabled=true, description="Hubspot Login get title")
	public void getTitle(){
		String title = loginPage.getLoginPagetitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "title is incorrect");
	}
	
	@AfterMethod
	public void tearDown(){
		basePage.quitBrowser();
	}
	







}


