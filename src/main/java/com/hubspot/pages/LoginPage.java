package com.hubspot.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil; //element util obje olusturduk.
	
	
	//Non-page factory TestNg de By kullaniyoruz.
	//Locator
	By emailId = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	
	
	//Constructor
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver); //Constructor altinda tekrar yazmak gerekiyor.
	}
	
	
	//Page actions
	public String getLoginPagetitle(){
		//elementutil den cagirdik
		return elementUtil.waitForGetPageTitle(Constants.LOGIN_PAGE_TITLE);
		//return driver.getTitle();
	}

	
	public HomePages doLogin(String username, String pwd){
		
		//method icinde olusturdumuz parametreleri veriyoruz.
		elementUtil.doSendKeys(emailId, username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn); //loginbtn click
		
		
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		
		return new HomePages(driver);
		
	}
	
	
}
