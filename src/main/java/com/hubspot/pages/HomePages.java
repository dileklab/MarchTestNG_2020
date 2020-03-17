package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;

public class HomePages extends BasePage{
	
	WebDriver driver; 
	ElementUtil elementUtil; //obje olusturduk
	
	//h1[@class='private-page__title'] ===header
	
	//span[@class='account-name '] === account
	
	By header = By.xpath("//h1[@class='private-page__title']");
	By accountName = By.xpath("//span[@class='account-name ']");
	By contactMainTab = By.id("nav-primary-contacts-branch");
	By contatcChildTab = By.id("nav-secondary-contacts");
	
	
	//Constructor
	public HomePages(WebDriver driver){
		this.driver = driver; //initilaiz yaptik.
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle(){ //Title aldim
		return elementUtil.waitForGetPageTitle(Constants.HOME_PAGE_TITLE);
		//return driver.getTitle();
	}
	
	public String getHomePageHeader(){
		return elementUtil.doGetText(header); //gettext kulandik
		//return driver.findElement(header).getText();
	}

	public String verifyLopggedInAccountName(){
		return elementUtil.doGetText(accountName);
		//return driver.findElement(header).getText();
	}

	//helper method
	private void clickOnContacts(){
		elementUtil.doClick(contactMainTab);
		elementUtil.doClick(contatcChildTab);
	}
	public ContactsPage gotoContactsPage(){
		clickOnContacts();
		return new ContactsPage(driver);
	}		


}
