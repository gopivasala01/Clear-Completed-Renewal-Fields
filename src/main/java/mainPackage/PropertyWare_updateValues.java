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

import DataReader.ReadingLeaseAgreements;

public class PropertyWare_updateValues 
{
	
	private static ThreadLocal<String> oldLeaseStartDate_ProrateRentThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> oldLeaseEndDate_ProrateRentThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> newLeaseEndDate_ProrateRentThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> prorateResidentBenefitPackageThreadLocal = new ThreadLocal<>();  //For other portfolios, it should be added as second full month in Auto Charges 
	private static ThreadLocal<String> prorateMonthlyRentThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> renewalStatusValueThreadLocal = new ThreadLocal<>();

	
	
	public static String getOldLeaseStartDate_ProrateRent() {
		if(oldLeaseStartDate_ProrateRentThreadLocal.get()==null)
			return "Error";
		else
		 return oldLeaseStartDate_ProrateRentThreadLocal.get();
	}

	public static void setOldLeaseStartDate_ProrateRent(String startDate) {
		oldLeaseStartDate_ProrateRentThreadLocal.set(startDate);
	}
	
	public static String getOldLeaseEndDate_ProrateRent() {
		if(oldLeaseEndDate_ProrateRentThreadLocal.get()==null)
			return "Error";
		else
		 return oldLeaseEndDate_ProrateRentThreadLocal.get();
	}

	public static void setOldLeaseEndDate_ProrateRent(String endDate) {
		oldLeaseEndDate_ProrateRentThreadLocal.set(endDate);
	}
	
	public static String getNewLeaseEndDate_ProrateRent() {
		if(newLeaseEndDate_ProrateRentThreadLocal.get()==null)
			return "Error";
		else
		 return newLeaseEndDate_ProrateRentThreadLocal.get();
	}

	public static void setNewLeaseEndDate_ProrateRent(String endDate) {
		newLeaseEndDate_ProrateRentThreadLocal.set(endDate);
	}
	
	public static String getProrateResidentBenefitPackage() {
		if(prorateResidentBenefitPackageThreadLocal.get()==null)
			return "Error";
		else
		 return prorateResidentBenefitPackageThreadLocal.get();
	}

	public static void setProrateResidentBenefitPackage(String ProrateResidentBenefitPackage) {
		prorateResidentBenefitPackageThreadLocal.set(ProrateResidentBenefitPackage);
	}
	public static String getProrateMonthlyRent() {
		if(prorateMonthlyRentThreadLocal.get()==null)
			return "Error";
		else
		 return prorateMonthlyRentThreadLocal.get();
	}

	public static void setProrateMonthlyRent(String prorateMonthlyRent) {
		prorateMonthlyRentThreadLocal.set(prorateMonthlyRent);
	}
	public static String getRenewalStatusValue() {
		if(renewalStatusValueThreadLocal.get()==null)
			return "Error";
		else
		 return renewalStatusValueThreadLocal.get();
	}

	public static void setRenewalStatusValue(String renewalStatusValue) {
		renewalStatusValueThreadLocal.set(renewalStatusValue);
	}

	
	
	
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
			if(company.equals("Arizona"))
			PropertyWare_updateValues.getRentCodeForArizona(driver);
			
			//Create table for the lease with it's SNO
			try {
				String query = "Select * into automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" from automation.LeaseReneWalsAutoChargesConfiguration";
				DataBase.updateTable(query);
			}
			catch(Exception e) {}
			
			//Clear all values Configuration table first 
			String query1 = "update  automation.LeaseReneWalsAutoChargesConfiguration_"+SNo+" Set set Amount = Null, StartDate= Null, EndDate= Null, Flag = Null";
			DataBase.updateTable(query1);
			
	
					
			
			//Compare Start and end Dates in PW with Lease Agreement
			try
			{
				if(RunnerClass.getStartDate().trim().equals(RunnerClass.getStartDateInPW().trim()))
				System.out.println("Start is matched");
				else 
				{
					System.out.println("Start is not matched");
					failedReason = failedReason+",Start is not matched";
					RunnerClass.setFailedReason(failedReason);
				}
				
				if(RunnerClass.getEndDate().trim().equals(RunnerClass.getEndDateInPW().trim()))
					System.out.println("End is matched");
					else 
					{
						System.out.println("End is not matched");
						failedReason = failedReason+",End is not matched";
						RunnerClass.setFailedReason(failedReason);
					}
			}
			catch(Exception e)
			{}
			
			//Update dates as per Move and Auto Charges
			PropertyWare_updateValues.updateDates(driver,company);
			PropertyWare_updateValues.decideMoveInAndAutoCharges(company,buildingAbbreviation,SNo);
			PropertyWare_updateValues.addingValuesToTable(company,buildingAbbreviation,SNo);
			return true;
			}

		public static void updateDates(WebDriver driver,String company) throws Exception
		{
			
			
			String priorMonthlyRent = "";
			String failedReason = "";
			Actions actions = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			
			//Get all Required dates converted
			String lastDayOfTheStartDate = RunnerClass.lastDateOfTheMonth(RunnerClass.getStartDate());
			String firstFullMonth = RunnerClass.firstDayOfMonth(RunnerClass.getStartDate(),1);
			String secondFullMonth = RunnerClass.firstDayOfMonth(RunnerClass.getStartDate(),2);
			
			try {
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
					setRenewalStatusValue(seletedOption);
					System.out.println("Renewal Status = "+getRenewalStatusValue());
				}
				catch(Exception e) {
					setRenewalStatusValue("Error");
					//GenericMethods.logger.error("Issue in getting Renewal Status");
					 failedReason = failedReason+","+ "Issue in getting Renewal Status";
					 RunnerClass.setFailedReason(failedReason);
				   
				     
				}
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
				 RunnerClass.setFailedReason(failedReason);
			    // return false;
			} 
		

			try {
				
				setOldLeaseStartDate_ProrateRent(RunnerClass.firstDayOfMonth(RunnerClass.getStartDate(), 0));
				setOldLeaseEndDate_ProrateRent(RunnerClass.dateMinusOneDay(RunnerClass.getStartDate()));
				setNewLeaseEndDate_ProrateRent(RunnerClass.lastDateOfTheMonth(RunnerClass.getStartDate()));
		    	
				if(!RunnerClass.getresidentBenefitsPackage().equalsIgnoreCase("Error")) {
					setProrateResidentBenefitPackage(ProrateAmountCalculator.prorateAmountOld(RunnerClass.getresidentBenefitsPackage()));
				}
				if(!priorMonthlyRent.equalsIgnoreCase("") ||!priorMonthlyRent.isEmpty() ||!priorMonthlyRent.equalsIgnoreCase("Error")) {
					setProrateMonthlyRent(ProrateAmountCalculator.prorateAmountOld(priorMonthlyRent));  
				}
				
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
	
	
		}
		
		public static boolean addingValuesToTable(String company,String buildingAbbreviation,String SNo)
		{
			
			return true;
			
		}
		
		public static void decideMoveInAndAutoCharges(String company,String buildingAbbreviation,String SNo)
		{
			
		}
		
		

		
		
		public static void getRentCodeForArizona(WebDriver driver) throws Exception
		{
			Actions actions = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			driver.findElement(Locators.ledgerTab).click();
			Thread.sleep(2000);
			actions.sendKeys(Keys.ESCAPE).build().perform();
			driver.findElement(Locators.newCharge).click();
			Thread.sleep(2000);
			//Account code
			driver.findElement(Locators.accountDropdown).click();
			List<WebElement> chargeCodes = driver.findElements(Locators.chargeCodesList);
			for(int i=0;i<chargeCodes.size();i++)
			{
				String code = chargeCodes.get(i).getText();
				if(code.contains(RunnerClass.getArizonaCityFromBuildingAddress()))
				{
					RunnerClass.setArizonaRentCode(code);
					RunnerClass.setArizonaCodeAvailable(true);
					break;
					
				}
				
			}
			driver.findElement(Locators.moveInChargeCancel).click();
			
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
