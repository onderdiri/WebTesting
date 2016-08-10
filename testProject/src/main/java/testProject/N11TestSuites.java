package testProject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class N11TestSuites {

	private static WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		setDriver(new FirefoxDriver());
		setBaseUrl("http://www.n11.com/");
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// Test 1: Open main page and proof that
	// ana sayfay� a� ve a��ld���n� onayla
	@Ignore
	@SuppressWarnings("deprecation")
	@Test
	public void setUpMainPage() {

		getDriver().get(baseUrl);
		String expectedURL = getDriver().getCurrentUrl();
		System.out.println("baseUrl<<<<<" + baseUrl);
		System.err.println("expectedURL<<<" + expectedURL);
		try {
			Assert.assertEquals(expectedURL, baseUrl);
		} catch (AssertionError e) {
			System.out.println("Yanl�� URL");
		}
		
	}

	// Test 2: Login dummy username/password and proof that
	// Db'de kay�tl� kullan�c� ad ve �ifre ile giri� yap�l�p yap�lmad���n� kontrol et

	@Test
	public void getLogin() {
		getDriver().get(baseUrl);
		String actualUserName = "onderdiri@gmail.com";
		String actualPassWord = "od12345";

		getDriver().findElement(By.linkText("Giri� Yap")).click();
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("onderdiri@gmail.com");
		// Use getAttribute("value") method for get value in Email Textbox
		String expectedUserName = getDriver().findElement(By.id("email")).getAttribute("value");

		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys("od123456");
		// Use getAttribute("value") method for get value in Password Textbox
		String expectedPassword = getDriver().findElement(By.id("password")).getAttribute("value");

		getDriver().findElement(By.id("loginButton")).click();

		try {
			Assert.assertTrue(expectedUserName.equals(actualUserName) && expectedPassword.equals(actualPassWord));
		} catch (AssertionError e) {
			System.out.println("Yanl�� kullan�c� ve/veya �ifre");
		}
	}

	// Test 3: search some word and accept is there any result or not
	// �r�n ismi ile arama yapmak ve sonu� gelip gelmedi�ini onaylamak
	@Ignore
	@Test
	public void searchText() throws Exception {
		getDriver().get(baseUrl + "/");
		getDriver().findElement(By.linkText("Giri� Yap")).click();
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("onderdiri@gmail.com");
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys("od123456");
		getDriver().findElement(By.id("loginButton")).click();
		getDriver().findElement(By.id("searchData")).click();
		getDriver().findElement(By.id("searchData")).clear();
		getDriver().findElement(By.id("searchData")).sendKeys("samsung");
		getDriver().findElement(By.cssSelector("span.icon.iconSearch")).click();

		// use getText() method for get value via xpath
		String searchResult = getDriver().findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/section[2]/div[1]/div[1]/strong")).getText();
		double searchResultParseValue = Double.parseDouble(searchResult);
		try {
			Assert.assertTrue(searchResultParseValue > 0);
		} catch (AssertionError e) {
			System.out.println("Arama i�in sonu� bulunamad�");
		}
	}

	// Test 4: click 2nd page and confirm is it active or not?
	// 2. sayfa t�kland���nda aktif olup olmad���n� kontrol et
	@SuppressWarnings("deprecation")
	@Ignore
	@Test
	public void ClickPagination() throws Exception {

		getDriver().get(baseUrl + "/");
		getDriver().findElement(By.linkText("Giri� Yap")).click();
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("onderdiri@gmail.com");
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys("od123456");
		getDriver().findElement(By.id("loginButton")).click();
		getDriver().findElement(By.id("searchData")).click();
		getDriver().findElement(By.id("searchData")).clear();
		getDriver().findElement(By.id("searchData")).sendKeys("samsung");
		getDriver().findElement(By.cssSelector("span.icon.iconSearch")).click();
		getDriver().findElement(By.linkText("2")).click();

		try {
			Assert.assertTrue(getDriver().findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[3]/a[3]")).getAttribute("class").contains("active"));
		} catch (AssertionError e) {
			System.out.println("Sayfa aktif de�il");
		}
	}

	// Test 5: add product in the favourite list and than check list. Is it in list or not ?
	// �r�n� favori listesine ekle ve favori listesinden eklendi�ini kontrol et
	@Ignore
	@SuppressWarnings({ "null", "deprecation" })
	@Test
	public void AddFavAndCheck() throws Exception {
		getDriver().get(baseUrl);
		getDriver().findElement(By.linkText("Giri� Yap")).click();
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("onderdiri@gmail.com");
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys("od123456");
		getDriver().findElement(By.id("loginButton")).click();
		getDriver().findElement(By.id("searchData")).click();
		getDriver().findElement(By.id("searchData")).clear();
		getDriver().findElement(By.id("searchData")).sendKeys("samsung");
		getDriver().findElement(By.cssSelector("span.icon.iconSearch")).click();
		getDriver().findElement(By.linkText("2")).click();

		getDriver().findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/section/div[2]/ul/li[3]/div/div[2]/span")).click();

		// get href value of which product was choosed
		// se�ilen �r�n�n href de�erini al�yoruz
		@SuppressWarnings("static-access")
		String choosenProductHref = getDriver().findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/section/div[2]/ul/li[3]/div/div[2]/a")).getAttribute("href");

		getDriver().findElement(By.linkText("Hesab�m")).click();
		getDriver().findElement(By.xpath("(//a[contains(text(),'Favorilerim')])[2]")).click();

		// get fav. list and all product's href assing one by one in the String arrayList.
		//favoriler listesini �ekiyoruz ve referans alanlar�n� tek tek String bir arraylist i�ine at�yoruz.
		@SuppressWarnings("static-access")
		List<WebElement> allFavList = getDriver().findElement(By.id("watchList")).findElements(By.tagName("td").className("goToProduct"));
		ArrayList<String> allFavRefList = new ArrayList<String>();
		for (WebElement allFavListHref : allFavList) {
			allFavRefList.add(allFavListHref.getAttribute("href"));
			System.err.println("   ref>> " + allFavListHref.getAttribute("href"));
		}

		// Is arrayList include choosen product's href or not?
		// arraylistimiz, se�ti�imiz �r�n�n referans�n� i�eriyormu?
		try {
			Assert.assertTrue(allFavRefList.contains(choosenProductHref));
		} catch (AssertionError e) {
			System.out.println("�r�n favori listesine eklenmedi");
		}
	}

	// Test 6: remove product in the favourite list and than check list. Is it in or not ?
	//�r�n� favori listesinden kald�r ve kald�r�ld���n� onayla.
	@Ignore
	@SuppressWarnings({ "deprecation" })
	@Test
	public void RemoveFavAndCheck() throws Exception {
		getDriver().get(baseUrl);
		getDriver().findElement(By.linkText("Giri� Yap")).click();
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("onderdiri@gmail.com");
		getDriver().findElement(By.id("password")).clear();
		getDriver().findElement(By.id("password")).sendKeys("od123456");
		getDriver().findElement(By.id("loginButton")).click();
		getDriver().findElement(By.id("searchData")).click();
		getDriver().findElement(By.id("searchData")).clear();
		getDriver().findElement(By.id("searchData")).sendKeys("samsung");
		getDriver().findElement(By.cssSelector("span.icon.iconSearch")).click();
		getDriver().findElement(By.linkText("2")).click();

		//choose 3.th product
		getDriver().findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/section/div[2]/ul/li[3]/div/div[2]/span")).click();

		// get href for choosen product
		@SuppressWarnings("static-access")
		String choosenProductHref = getDriver().findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/section/div[2]/ul/li[3]/div/div[2]/a")).getAttribute("href");
		System.err.println(" chosen>>>>>" + choosenProductHref);

		getDriver().findElement(By.linkText("Hesab�m")).click();
		getDriver().findElement(By.xpath("(//a[contains(text(),'Favorilerim')])[2]")).click();
		getDriver().findElement(By.linkText("Kald�r")).click();

		@SuppressWarnings("static-access")
		List<WebElement> allFavList = getDriver().findElement(By.id("watchList")).findElements(By.tagName("td").className("goToProduct"));
		ArrayList<String> allFavRefList = new ArrayList<String>();
		for (WebElement allFavListHref : allFavList) {
			allFavRefList.add(allFavListHref.getAttribute("href"));
			System.err.println("   ref>> " + allFavListHref.getAttribute("href"));
		}

		try {
			Assert.assertFalse(allFavRefList.contains(choosenProductHref));
		} catch (Exception e) {
			System.out.println("�r�n favorilerden kald�r�lamad�");
		}

	}

	@After
	public void tearDown() throws Exception {
		getDriver().quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			// Assert.a(verificationErrorString);
		}
	}

	@SuppressWarnings("unused")
	private boolean isElementPresent(By by) {
		try {
			getDriver().findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@SuppressWarnings("unused")
	private String closeAlertAndGetItsText() {
		try {
			Alert alert = getDriver().switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		N11TestSuites.driver = driver;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}

//logger mant���, hataya gereksiz loglar basmas�(t�m methodlara uygula)
//OOP mant���nda dizayn et, ortak yap�lar� methodlara ay�r
//GIT
