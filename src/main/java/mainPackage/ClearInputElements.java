package mainPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.util.List;

public class ClearInputElements {

	public static boolean clearAllFields(WebDriver driver,String company) {
		String failedReason = "";
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String followupNotes="";
		try {
			LocalDate currentDay = LocalDate.now();
	        String currentYear = String.valueOf(currentDay.getYear());
	        System.out.println("Current Year - " +currentYear);
			//Get Today's Date
	        String currentDate = RunnerClass.getCurrentDate();
			 
			driver.navigate().refresh();
			// Pop up after clicking Lease Name
			PropertyWare.intermittentPopUp(driver);
			driver.findElement(Locators.summaryTab).click();
			driver.findElement(Locators.summaryEditButton).click();
			Thread.sleep(2000);
			try {
				actions.moveToElement(driver.findElement(Locators.renewalStatus)).build().perform();
				Select select = new Select(driver.findElement(Locators.renewalStatus));
			    // get selected option with getFirstSelectedOption() with its text
				String seletedOption =select.getFirstSelectedOption().getText();
				GetterAndSetterClass.setRenewalStatusValue(seletedOption);
				System.out.println("Renewal Status = "+GetterAndSetterClass.getRenewalStatusValue());
			}
			catch(Exception e) {
				GetterAndSetterClass.setRenewalStatusValue("Error");
				//GenericMethods.logger.error("Issue in getting Renewal Status");
				 failedReason = failedReason+","+ "Issue in Getting Renewal Status";
				 GetterAndSetterClass.setFailedReason(failedReason);
			}
			
			
			// Find the starting and ending elements
		/*	List<WebElement> Elements = driver.findElements(By.xpath("//tr[td[@class='pmRoleTitle' and contains(text(), '5. Renewals - Accounting')]]/following-sibling::tr[following-sibling::tr[td[@class='pmRoleTitle' and contains(text(),'5. Renewals - Property Manager') and not(contains(text(), 'z.5. Renewals - Property Manager'))]]]"));
			// Loop through each tr element
			for (WebElement tr : Elements) {
				// Find the <th> element and get its text (name)
				actions.moveToElement(tr).build().perform();
				try {
					WebElement thElement = tr.findElement(By.tagName("th"));
					Thread.sleep(500);
					String fieldName = thElement.getText().trim();
					Thread.sleep(500);
					// Print <th> name and count of <input> and <select> elements
					System.out.println("Name of the Field : " + fieldName);
					WebElement inputSelectElement = tr.findElement(By.xpath(".//td//*[self::input or self::select or self::textarea]"));
					Thread.sleep(500);
					String fieldType = inputSelectElement.getTagName();
					System.out.println("TagName: " + fieldType);
					// Find all <input> and <select> elements within <td>
					if (fieldType.equalsIgnoreCase("input")) {
						// inputSelectElement.clear();
						System.out.println("Inside input field");
					} else if (fieldType.equalsIgnoreCase("select")) {
						System.out.println("Inside select field");
					} else if (fieldType.equalsIgnoreCase("textarea")) {
						System.out.println("Inside textarea field");
					}
				} catch (Exception e) {
					continue;
				}

				// System.out.println("Value/Text: " +getInputOrSelectValue(inputSelectElement));
				System.out.println("--------------------");

			} */
			if(GetterAndSetterClass.getRenewalStatusValue().contains("RENEWAL/NTV COMPLETED")) {
				try {
					actions.moveToElement(driver.findElement(Locators.futureStartDate)).build().perform();
					driver.findElement(Locators.futureStartDate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					driver.findElement(Locators.futureStartDateText).click();
					Thread.sleep(500);
				}
				catch(Exception e) {
					System.out.println("Issue in Clearing Future Start Date");
					failedReason =  failedReason+","+"Issue in Clearing Future Start Date";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					actions.moveToElement(driver.findElement(Locators.futureRentAmountOption1)).build().perform();
					driver.findElement(Locators.futureRentAmountOption1).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					Thread.sleep(500);
				}
				catch(Exception e) {
					System.out.println("Issue in Clearing Future Rent Amount Option 1");
					failedReason =  failedReason+","+"Issue in Clearing Future Rent Amount Option 1";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					actions.moveToElement(driver.findElement(Locators.futureLeaseTermOption1)).build().perform();
					driver.findElement(Locators.futureLeaseTermOption1).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					Thread.sleep(500);
				}
				catch(Exception e) {
					System.out.println("Issue in Clearing Future Lease Term Option 1");
					failedReason =  failedReason+","+"Issue in Clearing Future Lease Term Option 1";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					actions.moveToElement(driver.findElement(Locators.futureEndDateOption1)).build().perform();
					driver.findElement(Locators.futureEndDateOption1).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					driver.findElement(Locators.futureEndDateOption1Text).click();
					Thread.sleep(500);
				}
				catch(Exception e) {
					System.out.println("Issue in Clearing Future End Date Option 1");
					failedReason =  failedReason+","+"Issue in Clearing Future End Date Option 1";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					actions.moveToElement(driver.findElement(Locators.futureMonthToMonthRate)).build().perform();
					driver.findElement(Locators.futureMonthToMonthRate).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					Thread.sleep(500);
				}
				catch(Exception e) {
					System.out.println("Issue in Clearing Future Month To Month Rate");
					failedReason =  failedReason+","+"Issue in Clearing Future Month To Month Rate";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					actions.moveToElement(driver.findElement(Locators.futureRentTaxAmountOption1)).build().perform();
					driver.findElement(Locators.futureRentTaxAmountOption1).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					Thread.sleep(500);
				}
				catch(Exception e) {
					if(company.equalsIgnoreCase("Arizona")) {
						System.out.println("Issue in Clearing Future Rent Tax Amount Option 1");
						failedReason =  failedReason+","+"Issue in Clearing Future Rent Tax Amount Option 1";
						GetterAndSetterClass.setFailedReason(failedReason);
					}
				}
				try {
					actions.moveToElement(driver.findElement(Locators.futureAdminCostOption1)).build().perform();
					driver.findElement(Locators.futureAdminCostOption1).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					Thread.sleep(500);
				}
				catch(Exception e) {
					if(company.equalsIgnoreCase("Arizona")) {
						System.out.println("Issue in Clearing Future Admin Cost Option 1");
						failedReason =  failedReason+","+"Issue in Clearing Future Admin Cost Option 1";
						GetterAndSetterClass.setFailedReason(failedReason);
					}
				}
				try {
					actions.moveToElement(driver.findElement(Locators.renewalFollowUpNotes)).build().perform();
					followupNotes = driver.findElement(Locators.renewalFollowUpNotes).getText().trim();
					System.out.println("Follow Up Notes : \n"+followupNotes);
					Thread.sleep(500);
					driver.findElement(Locators.renewalFollowUpNotes).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					Thread.sleep(500);
				}
				catch(Exception e) {
					System.out.println("Issue in Getting Notes/Clearing Renewal Follow Up Notes");
					failedReason =  failedReason+","+"Issue in Getting Notes/Clearing Renewal Follow Up Notes";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					actions.moveToElement(driver.findElement(Locators.renewalStatus)).build().perform();
					Select select = new Select(driver.findElement(Locators.renewalStatus));
					select.selectByVisibleText("PM-1: PREP WORK");
				}
				catch(Exception e) {
					try {
							Select select = new Select(driver.findElement(Locators.renewalStatus));
							select.selectByValue("PM-1%3A+PREP+WORK");
					}
					catch(Exception e1) {
						System.out.println("Issue in Changing Renewal Status");
						failedReason = failedReason+","+"Issue in Changing Renewal Status";
						GetterAndSetterClass.setFailedReason(failedReason);
						e1.printStackTrace();
					}
				}
				
				try {
					if(AppConfig.saveButtonOnAndOff == false) {
						actions.moveToElement(driver.findElement(Locators.cancelLease))
						.click(driver.findElement(Locators.cancelLease)).build().perform();
					}
					else {
						actions.moveToElement(driver.findElement(Locators.saveLease))
						.click(driver.findElement(Locators.saveLease)).build().perform();
					}
				}
				catch(Exception e) {
					System.out.println("Issue in Saving lease");
					failedReason =  failedReason+","+"Issue in Saving lease";
					GetterAndSetterClass.setFailedReason(failedReason);
					return false;
				}
				Thread.sleep(2000);
				PropertyWare.intermittentPopUp(driver);
				actions.moveToElement(driver.findElement(Locators.notesAndDocs)).build().perform();
				driver.findElement(Locators.notesAndDocs).click();
				Thread.sleep(500);
				try {
					actions.moveToElement(driver.findElement(Locators.attachNote)).build().perform();
					driver.findElement(Locators.attachNote).click();
					Thread.sleep(2000);
				}
				catch(Exception e) {
					System.out.println("Issue in Clicking Attach Note");
					failedReason =  failedReason+","+"Issue in Clicking Attach Note";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					if(!followupNotes.isEmpty()) {
						driver.findElement(Locators.noteBody).sendKeys(followupNotes);
						Thread.sleep(500);
					}
					else {
						driver.findElement(Locators.noteBody).sendKeys("No follow-up notes captured in this renewal period.");
						System.out.println("No follow-up notes captured in this renewal period.");
						Thread.sleep(500);
					}
					
				}
				catch(Exception e) {
					System.out.println("Issue in Adding Notes Body");
					failedReason =  failedReason+","+"Issue in Adding Notes Body";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					driver.findElement(Locators.noteDate).sendKeys(currentDate);
					Thread.sleep(500);
				}
				catch(Exception e) {
					System.out.println("Issue in Adding Notes Date");
					failedReason =  failedReason+","+"Issue in Adding Notes Date";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					driver.findElement(Locators.noteSubject).sendKeys(currentYear+" Renewal Follow-up Notes");
					Thread.sleep(500);
				}
				catch(Exception e) {
					System.out.println("Issue in Adding Notes Subject");
					failedReason =  failedReason+","+"Issue in Adding Notes Subject";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
			/*	try {
					driver.findElement(Locators.notePrivate).click();
					Thread.sleep(500);
				}
				catch(Exception e) {
					
				} */
				try {
					if(AppConfig.saveButtonOnAndOff == false) {
						actions.moveToElement(driver.findElement(Locators.noteCancel))
						.click(driver.findElement(Locators.noteCancel)).build().perform();
					}
					else {
						actions.moveToElement(driver.findElement(Locators.noteSave))
						.click(driver.findElement(Locators.noteSave)).build().perform();
					}
				}
				catch(Exception e) {
					System.out.println("Issue in Saving Notes");
					failedReason =  failedReason+","+"Issue in Saving Notes";
					GetterAndSetterClass.setFailedReason(failedReason);
					//return false;
				}
				
			}
			else {
				System.out.println("Renewal Status is not  Renewal/NTV Completed");
				failedReason =  failedReason+","+"Renewal Status is "+GetterAndSetterClass.getRenewalStatusValue();
				GetterAndSetterClass.setFailedReason(failedReason);
				return false;
			} 
			

		} catch (Exception e) {
			
			System.out.println("Issue in clearing few fields");
			failedReason =  failedReason+","+"Issue in clearing few fields";
			GetterAndSetterClass.setFailedReason(failedReason);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Helper method to get input/select value or text
	private static String getInputOrSelectValue(WebElement element) {
		String tagName = element.getTagName();
		if ("input".equalsIgnoreCase(tagName)) {
			return element.getAttribute("value");
		} else if ("select".equalsIgnoreCase(tagName)) {
			return element.getAttribute("value");
			// You can handle selected options or text based on your specific case
		}
		return "";
	}  
}