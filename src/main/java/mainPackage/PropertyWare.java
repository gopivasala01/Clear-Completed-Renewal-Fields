package mainPackage;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PropertyWare {

	public static boolean searchBuilding(WebDriver driver, String company, String building,
			String completeBuildingAbbreviation) {
		String failedReason = "";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			// driver.findElement(Locators.dashboardsTab).click();
			driver.findElement(Locators.searchbox).clear();
			driver.findElement(Locators.searchbox).sendKeys(building);
			try {
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(Locators.searchingLoader)));
			} catch (Exception e) {
				try {
					driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
					driver.navigate().refresh();
					actions.sendKeys(Keys.ESCAPE).build().perform();
					driver.findElement(Locators.dashboardsTab).click();
					driver.findElement(Locators.searchbox).clear();
					driver.findElement(Locators.searchbox).sendKeys(building);
					wait.until(ExpectedConditions.invisibilityOf(driver.findElement(Locators.searchingLoader)));
				} catch (Exception e2) {
				}
			}
			try {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				if (driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed()) {
					long count = building.chars().filter(ch -> ch == '.').count();
					if (building.chars().filter(ch -> ch == '.').count() >= 2) {
						building = building.substring(building.indexOf(".") + 1, building.length());
						driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
						driver.navigate().refresh();
						actions.sendKeys(Keys.ESCAPE).build().perform();
						driver.findElement(Locators.dashboardsTab).click();
						driver.findElement(Locators.searchbox).clear();
						driver.findElement(Locators.searchbox).sendKeys(building);
						wait.until(ExpectedConditions.invisibilityOf(driver.findElement(Locators.searchingLoader)));
						try {
							driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
							if (driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed()) {
								System.out.println("Building Not Found");
								failedReason = failedReason + "," + "Building Not Found";
								GetterAndSetterClass.setFailedReason(failedReason);
								return false;
							}
						} catch (Exception e3) {
						}
					} else {
						try {
							building = building.split("_")[1];
							driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
							driver.navigate().refresh();
							actions.sendKeys(Keys.ESCAPE).build().perform();
							driver.findElement(Locators.dashboardsTab).click();
							driver.findElement(Locators.searchbox).clear();
							driver.findElement(Locators.searchbox).sendKeys(building);
							wait.until(ExpectedConditions.invisibilityOf(driver.findElement(Locators.searchingLoader)));
							try {
								driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
								if (driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed()) {
									System.out.println("Building Not Found");
									failedReason = failedReason + "," + "Building Not Found";
									GetterAndSetterClass.setFailedReason(failedReason);
									return false;
								}
							} catch (Exception e3) {
							}
						} catch (Exception e) {
							System.out.println("Building Not Found");
							failedReason = failedReason + "," + "Building Not Found";
							GetterAndSetterClass.setFailedReason(failedReason);
							return false;
						}
					}
				}
			} catch (Exception e2) {
			}
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			Thread.sleep(1000);
			System.out.println(building);
			// Select Lease from multiple leases
			List<WebElement> displayedCompanies = null;
			try {
				displayedCompanies = driver.findElements(Locators.searchedLeaseCompanyHeadings);
			} catch (Exception e) {

			}
			boolean leaseSelected = false;
			for (int i = 0; i < displayedCompanies.size(); i++) {
				String companyName = displayedCompanies.get(i).getText();
				if (companyName.toLowerCase().contains(company.toLowerCase()) && !companyName.contains("Legacy")) {

					List<WebElement> leaseList = driver
							.findElements(By.xpath("(//*[@class='section'])[" + (i + 1) + "]/ul/li/a"));
					// System.out.println(leaseList.size());
					// Check if displayed leases list has the building name completely first
					for (int j = 0; j < leaseList.size(); j++) {
						String lease = leaseList.get(j).getText();
						if (lease.toLowerCase().contains(completeBuildingAbbreviation.toLowerCase())) {

							driver.findElement(
									By.xpath("(//*[@class='section'])[" + (i + 1) + "]/ul/li[" + (j + 1) + "]/a"))
									.click();
							leaseSelected = true;
							break;
						}
					}
					if (leaseSelected == false) {
						for (int j = 0; j < leaseList.size(); j++) {
							String lease = leaseList.get(j).getText();
							if (lease.toLowerCase().contains(building.toLowerCase()) && lease.contains(":")) {

								driver.findElement(
										By.xpath("(//*[@class='section'])[" + (i + 1) + "]/ul/li[" + (j + 1) + "]/a"))
										.click();
								leaseSelected = true;
								break;
							}
						}
					}
				}
				if (leaseSelected == true) {
					String status = driver.findElement(Locators.status).getText();
					if(status.equalsIgnoreCase("ACTIVE") || status.equalsIgnoreCase("Active - Month to Month") || status.equalsIgnoreCase("Active - TTO") || status.equalsIgnoreCase("Active - Notice Given")){
						GetterAndSetterClass.setleaseStatus(true);
			        	System.out.println("Status = " + status);
			        	//RunnerClass.failedReason = "Lease is Active";
			        	return true;
			        }
			        else {
			        	GetterAndSetterClass.setleaseStatus(false);
			        	System.out.println("Status = " + status);
			        	failedReason = "Lease is not Active";
			        	GetterAndSetterClass.setFailedReason(failedReason);
			        	return false;
			        }

					
				}
			}
			if (leaseSelected == false) {
				failedReason = failedReason + "," + "Building Not Found";
				GetterAndSetterClass.setFailedReason(failedReason);
				return false;
			}
		} catch (Exception e) {
			failedReason = failedReason + "," + "Issue in selecting Building";
			GetterAndSetterClass.setFailedReason(failedReason);
			return false;
		}
		return true;
	}



	public static boolean selectBuilding(WebDriver driver, String company, String ownerName, String LeaseEntityID) {
		String failedReason = "";
		Actions actions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try {
			// Get BuildingEntityID from LeaseFact_Dashboard table
			// buildingEntityID = DataBase.getBuildingEntityID(company,ownerName);

			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Duration.ofSeconds(100));
			driver.navigate().refresh();
			actions.sendKeys(Keys.ESCAPE).build().perform();
			PropertyWare.intermittentPopUp(driver);
			// if(PropertyWare.checkIfBuildingIsDeactivated()==true)
			// return false;
			if (company.equals("California PFW"))
				company = "California pfw";
			driver.findElement(Locators.marketDropdown).click();
			String marketName = "HomeRiver Group - " + company;
			Select marketDropdownList = new Select(driver.findElement(Locators.marketDropdown));
			marketDropdownList.selectByVisibleText(marketName);
			String buildingPageURL = AppConfig.leasePageURL + LeaseEntityID;
			driver.navigate().to(buildingPageURL);
			PropertyWare.intermittentPopUp(driver);
			 String status = driver.findElement(Locators.status).getText();
			if(status.equalsIgnoreCase("ACTIVE") || status.equalsIgnoreCase("Active - Month to Month") || status.equalsIgnoreCase("Active - TTO") || status.equalsIgnoreCase("Active - Notice Given")){
				GetterAndSetterClass.setleaseStatus(true);
	        	System.out.println("Status = " + status);
	        	//RunnerClass.failedReason = "Lease is Active";
	        	return true;
	        }
	        else {
	        	GetterAndSetterClass.setleaseStatus(false);
	        	System.out.println("Status = " + status);
	        	failedReason = "Lease is not Active";
	        	GetterAndSetterClass.setFailedReason(failedReason);
	        	return false;
	        }
			
		}

		catch (Exception e) {
			System.out.println("Lease Not Found");
			failedReason = failedReason + "," + "Lease Not Found";
			return false;
		}

	}

	public static void intermittentPopUp(WebDriver driver) throws Exception {
		Thread.sleep(2000);
		// Pop up after clicking lease name
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

			
			try {
				driver.switchTo().frame(driver.findElement(Locators.scheduleMaintananceIFrame));
				if (driver.findElement(Locators.scheduleMaintanancePopUp2).isDisplayed()) {
					driver.findElement(Locators.maintananceCloseButton).click();
				}
				driver.switchTo().defaultContent();
			} catch (Exception e) {
			}
			try {
				if (driver.findElement(Locators.popUpAfterClickingLeaseName).isDisplayed()) {
					driver.findElement(Locators.popupClose).click();
				}
			} catch (Exception e) {
			}
			try {
				if (driver.findElement(Locators.scheduledMaintanancePopUp).isDisplayed()) {
					driver.findElement(Locators.scheduledMaintanancePopUpOkButton).click();
				}
			} catch (Exception e) {
			}
			try {
				if (driver.findElement(Locators.scheduledMaintanancePopUpOkButton).isDisplayed())
					driver.findElement(Locators.scheduledMaintanancePopUpOkButton).click();
			} catch (Exception e) {
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		} catch (Exception e) {
		}

	}
	
	

}
