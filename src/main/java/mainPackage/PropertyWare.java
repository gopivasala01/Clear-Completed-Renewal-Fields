package mainPackage;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import DataReader.ReadingLeaseAgreements;
import io.github.bonigarcia.wdm.WebDriverManager;

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

					return true;
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

	public static boolean downloadLeaseAgreement(WebDriver driver, String building, String ownerName, String company)
			throws Exception {
		String failedReason = "";
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		if (company.equalsIgnoreCase("Arizona")) {
			// City from Building Address for Arizona rent code
			try {
				driver.findElement(Locators.buildingLinkInLeasePage).click();
				PropertyWare.intermittentPopUp(driver);
				String buildingAddress = driver.findElement(Locators.buildingAddress).getText();
				String[] lines = buildingAddress.split("\\n");
				String city = lines[1].split(" ")[0].trim();
				GetterAndSetterClass.setArizonaCityFromBuildingAddress(city);
				System.out.println("Building Address = " + buildingAddress);
				System.out.println("Building City = " + city);
				driver.navigate().back();
			} catch (Exception e) {
			}
		}

		try {

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

			// Start and End Dates in Property Ware
			try {
				GetterAndSetterClass.setStartDateInPW(driver.findElement(Locators.leaseStartDate_PW).getText());
				System.out.println("Lease Start Date in PW = " + GetterAndSetterClass.getStartDateInPW());
				GetterAndSetterClass.setEndDateInPW(driver.findElement(Locators.leaseEndDate_PW).getText());
				System.out.println("Lease End Date in PW = " + GetterAndSetterClass.getEndDateInPW());
			} catch (Exception e) {
			}

			driver.findElement(Locators.notesAndDocs).click();
			int k = 0;
			while (k < 2) {
				try {
					List<WebElement> documents = driver.findElements(Locators.documentsList);
					boolean checkLeaseAgreementAvailable = false;
					String filename = null;
					for (int i = 0; i < documents.size(); i++) {
						for (int j = 0; j < AppConfig.LeaseAgreementFileNames.length; j++) {
							if ((documents.get(i).getText().trim().startsWith(AppConfig.LeaseAgreementFileNames[j]) || documents.get(i).getText().trim().contains("Renewal_Lease") || documents.get(i).getText().trim().contains("Renewal Lease"))
									&& !documents.get(i).getText().trim().contains("Termination")
									&& !documents.get(i).getText().trim().contains("_Mod")
									&& !documents.get(i).getText().trim().contains("_MOD")
									&& !documents.get(i).getText().trim().contains("Renewal_Offer")&&!documents.get(i).getText().trim().contains("Renewal Offer"))// &&documents.get(i).getText().contains(AppConfig.getCompanyCode(RunnerClass.company)))
							{
								documents.get(i).click();
								filename = documents.get(i).getText();
								GetterAndSetterClass.setFileName(filename);
								checkLeaseAgreementAvailable = true;
								PropertyWare.waitUntilFileIsDownloaded(filename);
								break;
							}
						}
						if (checkLeaseAgreementAvailable == true)
							break;
					}

					if (checkLeaseAgreementAvailable == false) {
						System.out.println("Lease Agreement is not available");
						failedReason = failedReason + "," + "Lease Agreement is not available";
						GetterAndSetterClass.setFailedReason(failedReason);
						return false;
					}
					Thread.sleep(2000);
					if (ReadingLeaseAgreements.dataRead(GetterAndSetterClass.getFileName()) == false) {
						return false;
					}

					return true;
				} catch (Exception e) {
					driver.navigate().refresh();
					continue;
				}

			}
		} catch (Exception e) {
			System.out.println("Unable to download Lease Agreement");
			failedReason = failedReason + "," + "Unable to download Lease Agreement";
			GetterAndSetterClass.setFailedReason(failedReason);
			return false;
		}
		return true;

	}

	public static void waitUntilFileIsDownloaded(String filename) throws Exception {
		try {
			Thread.sleep(10000);
			if (RunnerClass.getLastModified(filename) != null) {
				while (true) {
					File file = RunnerClass.getLastModified(filename);
					if (file.getName().endsWith(".crdownload")) {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e1) {

							e1.printStackTrace();
							// Handle the InterruptedException if needed
						}
					} else {
						// Break the loop if the file name does not end with ".crdownload"
						break;
					}
				}
			}
			File file = RunnerClass.getLastModified(filename);
		} catch (Exception e) {
			e.printStackTrace();
			Thread.sleep(10000);
		}
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

			return true;
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

			boolean popupCheck = false;
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
