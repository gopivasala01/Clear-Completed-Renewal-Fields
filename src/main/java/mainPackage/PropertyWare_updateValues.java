package mainPackage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PropertyWare_updateValues 
{
	
	
	//ConfigureValues
		public static boolean configureValues(WebDriver driver,String company,String buildingAbbreviation,String SNo) throws Exception
		{
			//Drop the table if exists
			try
			{
				String tableName = "automation.LeaseReneWalsAutoChargesConfiguration_"+SNo;
				String query = "Drop table if exists "+tableName;
				DataBase.updateTable(query);
			}
			catch(Exception e)
			{}
			
			String failedReason ="";
			//For Arizona - To get Rent Charge codes
			try {
				if(company.equals("Arizona"))
					PropertyWare_updateValues.getRentCodeForArizona(driver);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			//Create table for the lease with it's SNO
			try {
				String query = "Select * into automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" from automation.LeaseReneWalsAutoChargesConfiguration";
				DataBase.updateTable(query);
			}
			catch(Exception e) {}
			
			//Clear all values Configuration table first 
			String query1 = "update  automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set Amount = Null, StartDate= Null, EndDate= Null, Flag = Null";
			DataBase.updateTable(query1);
			
	
					
			
			//Compare Start and end Dates in PW with Lease Agreement
			try
			{
				if(GetterAndSetterClass.getStartDate().trim().equals(GetterAndSetterClass.getStartDateInPW().trim()))
				System.out.println("Start is matched");
				else 
				{
					System.out.println("Start is not matched");
					failedReason = failedReason+",Start is not matched";
					GetterAndSetterClass.setFailedReason(failedReason);
				}
				
				if(GetterAndSetterClass.getEndDate().trim().equals(GetterAndSetterClass.getEndDateInPW().trim()))
					System.out.println("End is matched");
					else 
					{
						System.out.println("End is not matched");
						failedReason = failedReason+",End is not matched";
						GetterAndSetterClass.setFailedReason(failedReason);
					}
			}
			catch(Exception e)
			{}
			
			//Update dates as per Move and Auto Charges
			if(PropertyWare_updateValues.updateDates(driver,company) == false) {
				return false;
			}
			if(PropertyWare_updateValues.addingValuesToTable(company,buildingAbbreviation,SNo)==false) {
				return false;
			}
			if(PropertyWare_updateValues.decideMoveInAndAutoCharges(driver,company,buildingAbbreviation,SNo) == false) {
				return false;
			}
				
			
			return true;
			}

		public static boolean updateDates(WebDriver driver,String company) throws Exception
		{
			
		
			String priorMonthlyRent = "";
			String failedReason = "";
			Actions actions = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			
			//Get all Required dates converted
		/*	String lastDayOfTheStartDate = RunnerClass.lastDateOfTheMonth(GetterAndSetterClass.getStartDate());
			String firstFullMonth = RunnerClass.firstDayOfMonth(GetterAndSetterClass.getStartDate(),1);
			String secondFullMonth = RunnerClass.firstDayOfMonth(GetterAndSetterClass.getStartDate(),2);
		*/	
				String lastDayOfTheStartDate = RunnerClass.lastDateOfTheMonth(GetterAndSetterClass.getStartDate());
				GetterAndSetterClass.setlastDayOfTheStartDate(lastDayOfTheStartDate);
				String firstFullMonth = RunnerClass.firstDayOfMonth(GetterAndSetterClass.getStartDate(),1);
				GetterAndSetterClass.setFirstFullMonth(firstFullMonth);
				driver.navigate().refresh();
				PropertyWare.intermittentPopUp(driver);
				driver.findElement(Locators.summaryEditButton).click();
				Thread.sleep(2000);
				js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
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
					 failedReason = failedReason+","+ "Issue in getting Renewal Status";
					 GetterAndSetterClass.setFailedReason(failedReason);
				}
				try {
					actions.moveToElement(driver.findElement(Locators.priorMonthlyRent)).build().perform();
					String priorAmount = driver.findElement(Locators.priorMonthlyRent).getAttribute("value");
					priorMonthlyRent = priorAmount.replace("$", "").replace(",", "");
					System.out.println("Prior Montly Rent = "+priorMonthlyRent);
					actions.moveToElement(driver.findElement(Locators.cancelLease)).click(driver.findElement(Locators.cancelLease)).build().perform();
				}
				catch(Exception e) {
					priorMonthlyRent = "Error";
					//GenericMethods.logger.error("Issue in getting prior monthly rent");
					 failedReason = failedReason+","+ "Issue in getting prior monthly rent";
					 GetterAndSetterClass.setFailedReason(failedReason);
				    // return false;
				} 
			
		
			try {
				if(GetterAndSetterClass.getStartDate().split("/")[1].equals("1")||GetterAndSetterClass.getStartDate().split("/")[1].equals("01"))
			    {
					return false;
			    }
				else
				{
					if(GetterAndSetterClass.getIncrementRentFlag() == true) {
						GetterAndSetterClass.setOldLeaseStartDate_ProrateRent(RunnerClass.firstDayOfMonth(GetterAndSetterClass.getStartDate(), 0));
						GetterAndSetterClass.setOldLeaseEndDate_ProrateRent(RunnerClass.dateMinusOneDay(GetterAndSetterClass.getStartDate()));
						GetterAndSetterClass.setNewLeaseEndDate_ProrateRent(RunnerClass.lastDateOfTheMonth(GetterAndSetterClass.getStartDate()));
						GetterAndSetterClass.setprorateEscalationStartDate(GetterAndSetterClass.getIncreasedRentDates().get(2));
						GetterAndSetterClass.setprorateEscalationEndDate(GetterAndSetterClass.getIncreasedRentDates().get(3));
				    	
						if(!GetterAndSetterClass.getresidentBenefitsPackage().equalsIgnoreCase("Error")) {
							GetterAndSetterClass.setProrateResidentBenefitPackage(ProrateAmountCalculator.prorateAmountOld(GetterAndSetterClass.getresidentBenefitsPackage()));
						}
						if(!priorMonthlyRent.equalsIgnoreCase("") ||!priorMonthlyRent.isEmpty() ||!priorMonthlyRent.equalsIgnoreCase("Error")) {
							GetterAndSetterClass.setProrateMonthlyRent(ProrateAmountCalculator.prorateAmountOld(priorMonthlyRent));  
						}
						if(!(GetterAndSetterClass.getIncreasedRentAmounts().get(1)).equalsIgnoreCase("") || !(GetterAndSetterClass.getIncreasedRentAmounts().get(1)).equalsIgnoreCase("Error")) {
							GetterAndSetterClass.setprorateEscalationAmount(GetterAndSetterClass.getIncreasedRentAmounts().get(1));  
						}
					}
					else {
						GetterAndSetterClass.setOldLeaseStartDate_ProrateRent(RunnerClass.firstDayOfMonth(GetterAndSetterClass.getStartDate(), 0));
						GetterAndSetterClass.setOldLeaseEndDate_ProrateRent(RunnerClass.dateMinusOneDay(GetterAndSetterClass.getStartDate()));
						GetterAndSetterClass.setNewLeaseEndDate_ProrateRent(RunnerClass.lastDateOfTheMonth(GetterAndSetterClass.getStartDate()));
				    	
						if(!GetterAndSetterClass.getresidentBenefitsPackage().equalsIgnoreCase("Error")) {
							GetterAndSetterClass.setProrateResidentBenefitPackage(ProrateAmountCalculator.prorateAmountNew(GetterAndSetterClass.getresidentBenefitsPackage()));
						}
						if(!priorMonthlyRent.equalsIgnoreCase("") ||!priorMonthlyRent.isEmpty() ||!priorMonthlyRent.equalsIgnoreCase("Error")) {
							GetterAndSetterClass.setProrateMonthlyRent(ProrateAmountCalculator.prorateAmountOld(priorMonthlyRent));  
						}
					}
				
				}
				try {
					if(getRBPISChanged(driver)== true) {
						if(!GetterAndSetterClass.getOldRBPAmount().equalsIgnoreCase("Error")) {
							GetterAndSetterClass.setOldProrateResidentBenefitPackage(ProrateAmountCalculator.prorateAmountOld(GetterAndSetterClass.getOldRBPAmount()));
						}
						
					}
					Thread.sleep(1000);
				}
				catch(Exception e) {
					//e.printStackTrace();
					 failedReason = failedReason+","+ "Issue in getting old RBP Value";
					 GetterAndSetterClass.setFailedReason(failedReason);
					 return false;
				}
				
			}
			catch(Exception e) {
				//e.printStackTrace();
				 failedReason = failedReason+","+ "Issue in getting prorate dates";
				 GetterAndSetterClass.setFailedReason(failedReason);
				 return false;
			}
			
			return true;
	
		}
		
		public static boolean addingValuesToTable(String company,String buildingAbbreviation,String SNo)
		{
			String failedReason ="";
			try {
				
				String query ="";
				for(int i=1;i<=5;i++)
				{
					switch(i)
					{
					case 1:
						query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set ChargeCode = '"+AppConfig.getResidentBenefitsPackageChargeCode(company)+"',Amount = '"+GetterAndSetterClass.getOldProrateResidentBenefitPackage()+"',StartDate='"+GetterAndSetterClass.getOldLeaseStartDate_ProrateRent()+"',EndDate='"+GetterAndSetterClass.getOldLeaseEndDate_ProrateRent()+"',Flag = '' where ID=14";
					    break;
					case 2:
						query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set ChargeCode = '"+AppConfig.getResidentBenefitsPackageChargeCode(company)+"',Amount = '"+GetterAndSetterClass.getProrateResidentBenefitPackage()+"',StartDate='"+GetterAndSetterClass.getStartDate()+"',EndDate='"+GetterAndSetterClass.getNewLeaseEndDate_ProrateRent()+"',Flag = '' where ID=18";
					    break;
					case 3: 
						query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set ChargeCode = '"+AppConfig.getProrateRentChargeCode(company)+"',Amount = '"+GetterAndSetterClass.getProrateRent()+"',StartDate='"+GetterAndSetterClass.getStartDate()+"',EndDate='"+GetterAndSetterClass.getNewLeaseEndDate_ProrateRent()+"',Flag = '' where ID=15";
					    break;
					case 4: 
						query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set ChargeCode = '"+AppConfig.getMonthlyRentChargeCode(company)+"',Amount = '"+GetterAndSetterClass.getProrateMonthlyRent()+"',StartDate='"+GetterAndSetterClass.getOldLeaseStartDate_ProrateRent()+"',EndDate='"+GetterAndSetterClass.getOldLeaseEndDate_ProrateRent()+"',Flag = '' where ID=16";
					    break;
					case 5: 
						query = query+"\n Update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set ChargeCode = '"+AppConfig.getMonthlyRentChargeCode(company)+"',Amount = '"+GetterAndSetterClass.getprorateEscalationAmount()+"',StartDate='"+GetterAndSetterClass.getprorateEscalationStartDate()+"',EndDate='"+GetterAndSetterClass.getprorateEscalationEndDate()+"',Flag = '' where ID=17";
					    break;  
					}
					
				}
				DataBase.updateTable(query);
				return true;
			}
			
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Issue in adding values to Auto charges table");
				failedReason =  failedReason+","+"Internal Error - consolidating auto charges";
				GetterAndSetterClass.setFailedReason(failedReason);
				return false;

			}
			
			
		}
		
		public static boolean decideMoveInAndAutoCharges(WebDriver driver,String company,String buildingAbbreviation,String SNo) throws Exception
		{
			Actions actions = new Actions(driver);
			String failedReason ="";
			String query1="";
			if(GetterAndSetterClass.getRenewalStatusValue().toLowerCase().contains("close out coordinator review")) {
				if(GetterAndSetterClass.getProrateRent().equalsIgnoreCase("Error")||GetterAndSetterClass.getProrateRent().equalsIgnoreCase("0.00")) {
						
					if(GetterAndSetterClass.getOldRBPAmount().equalsIgnoreCase("Error")) {
						actions.moveToElement(driver.findElement(Locators.renewalStatus)).build().perform();
						Select select = new Select(driver.findElement(Locators.renewalStatus));
						Thread.sleep(500);
						select.selectByVisibleText("RW-4a: CHARGE RENEWAL FEE - ANNUAL");
						actions.moveToElement(driver.findElement(Locators.saveLease)).click(driver.findElement(Locators.saveLease)).build().perform();
						System.out.println("Prorate Rent and RBP Error or 0");
						failedReason =  failedReason+","+"Prorate Rent/RBP Error";
						GetterAndSetterClass.setFailedReason(failedReason);
						return false;
					}
					else {
						query1 = "update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set Flag = 1 where ID in (14,18)";
						DataBase.updateTable(query1);
					}
					//actions.moveToElement(driver.findElement(Locators.cancelLease)).click(driver.findElement(Locators.cancelLease)).build().perform();
					
				}
				else {
					if(GetterAndSetterClass.getresidentBenefitsPackageAvailabilityCheckFlag()==true) {
						if(!GetterAndSetterClass.getOldRBPAmount().equalsIgnoreCase(GetterAndSetterClass.getresidentBenefitsPackage())) {
							if(GetterAndSetterClass.getOldRBPAmount().equalsIgnoreCase("Error")) {
								query1 = "update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set Flag = 1 where ID in (15,16,18)";
							}
							else {
								query1 = "update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set Flag = 1 where ID in (14,15,16,18)";
							}
						}
						else {
							query1 = "update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set Flag = 1 where ID in (15,16)";
						}
						
					}
					else {
						query1 = "update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set Flag = 1 where ID in (15,16)";
					}
					DataBase.updateTable(query1);
					
					if(GetterAndSetterClass.getIncrementRentFlag() == true) {
						query1 = "update automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set Flag = 1 where ID in (17)";
						DataBase.updateTable(query1);
					} 
					
					
				}
			}
			else {
				System.out.println("Renewal Status is not close out coordinator review");
				failedReason =  failedReason+","+"Renewal Status is "+GetterAndSetterClass.getRenewalStatusValue();
				GetterAndSetterClass.setFailedReason(failedReason);
				return false;
			} 
			return true;
		} 
		
		public static boolean getRBPISChanged(WebDriver driver) {
			Actions actions = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			try {
				driver.navigate().refresh();
				PropertyWare.intermittentPopUp(driver);
				driver.findElement(Locators.summaryEditButton).click();
				Thread.sleep(2000);
				//js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
 				actions.moveToElement(driver.findElement(Locators.newAutoCharge)).build().perform();
				boolean newChargeChanges = false;
				List<WebElement> existingAutoCharges = driver.findElements(Locators.autoCharge_List);
				List<WebElement> startDates = driver.findElements(Locators.autoCharge_List_StartDates);
				List<WebElement> endDates = driver.findElements(Locators.autoCharge_List_EndDates);
				List<WebElement> existingAmounts = driver.findElements(Locators.autoCharge_List_Amounts);
				for (int i = 0; i < existingAutoCharges.size(); i++) {
				    String autoChargeText = existingAutoCharges.get(i).getText().replaceAll("[.]", "");
				    String startDateText = startDates.get(i).getText(); 
				    String endDateText = endDates.get(i).getText(); 
				    String amount = existingAmounts.get(i).getText();
				    if (autoChargeText.contains("43070 - Resident Benefit") && endDateText.trim().isEmpty() && GetterAndSetterClass.getFirstFullMonth().equalsIgnoreCase(startDateText) && amount.replace("$", "").equalsIgnoreCase(GetterAndSetterClass.getresidentBenefitsPackage()) ) {
				    	//GetterAndSetterClass.setRBPNoChangeRequired(true);
				    	newChargeChanges = true;
				        continue; // No need to continue once the condition is met
				    }
				    if(newChargeChanges == true && autoChargeText.contains("43070 - Resident Benefit") && ( GetterAndSetterClass.getOldLeaseEndDate_ProrateRent().equalsIgnoreCase(endDateText.trim()))) { //GetterAndSetterClass.getlastDayOfTheStartDate().trim().equalsIgnoreCase(endDateText.trim()) ||
				    	GetterAndSetterClass.setOldRBPAmount(amount.replace("$", ""));
				    	//GetterAndSetterClass.setOldRBPAmount("44.95");
				    	break;
				    }
				}
			}
			catch(Exception e) {
				return false;
			}
			actions.moveToElement(driver.findElement(Locators.cancelLease)).click(driver.findElement(Locators.cancelLease)).build().perform();
			return true;
		}
		
		
		
		
		public static void getRentCodeForArizona(WebDriver driver) throws Exception
		{
			try {
				Actions actions = new Actions(driver);
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				driver.findElement(Locators.ledgerTab).click();
				Thread.sleep(2000);
				actions.sendKeys(Keys.ESCAPE).build().perform();
				driver.findElement(Locators.newCharge).click();
				Thread.sleep(2000);
				//Account code
				driver.findElement(Locators.arizonaAccountDropdown).click();
				List<WebElement> chargeCodes = driver.findElements(Locators.chargeCodesList);
				for(int i=0;i<chargeCodes.size();i++)
				{
					String code = chargeCodes.get(i).getText();
					if(code.contains(GetterAndSetterClass.getArizonaCityFromBuildingAddress()))
					{
						GetterAndSetterClass.setArizonaRentCode(code);
						GetterAndSetterClass.setArizonaCodeAvailable(true);
						break;
						
					}
					
				}
				actions.sendKeys(Keys.ESCAPE).build().perform();
				driver.findElement(Locators.moveInChargeCancel).click();
				Thread.sleep(1000);
				driver.findElement(Locators.summaryTab).click();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		 public static String replaceNumbers(String input, Map<String, String> replacements) 
		    {
		        // Split the input string by commas to get individual numbers
		        String[] numbers = input.split(",");
		        
		        // Initialize a StringBuilder to build the replaced string
		        StringBuilder replacedString = new StringBuilder();
		        
		        // Iterate through the numbers and replace them
		        for (String number : numbers) {
		            // Check if the number has a replacement in the map
		            if (replacements.containsKey(number)) {
		                replacedString.append(replacements.get(number));
		            } else {
		                // If no replacement is found, keep the original number
		                replacedString.append(number);
		            }
		            
		            // Append a comma to separate numbers
		            replacedString.append(",");
		        }
		        
		        // Remove the trailing comma
		        if (replacedString.length() > 0) {
		            replacedString.deleteCharAt(replacedString.length() - 1);
		        }
		        
		        return replacedString.toString();
		    }

		
}
