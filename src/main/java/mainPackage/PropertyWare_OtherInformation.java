package mainPackage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Joiner;


public class PropertyWare_OtherInformation 
{
	
	private static ThreadLocal<String> type1ThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> type2ThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> type3ThreadLocal = new ThreadLocal<>();
	
	//Breed
	private static ThreadLocal<String> breed1ThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> breed2ThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> breed3ThreadLocal = new ThreadLocal<>();
	
	//Weight
	private static ThreadLocal<String> weight1ThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> weight2ThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> weight3ThreadLocal = new ThreadLocal<>();
	
	public static void setType1(String type1) {
		type1ThreadLocal.set(type1);
	}
	
	public static String getType1() {
		 return type1ThreadLocal.get();
	}
	
	public static void setType2(String type2) {
		type2ThreadLocal.set(type2);
	}
	
	public static String getType2() {
		 return type2ThreadLocal.get();
	}
	
	public static void setType3(String type3) {
		type3ThreadLocal.set(type3);
	}
	
	public static String getType3() {
		 return type3ThreadLocal.get();
	}
	
	//Breed
	public static void setBreed1(String breed1) {
		breed1ThreadLocal.set(breed1);
	}
	
	public static String getBreed1() {
		 return breed1ThreadLocal.get();
	}
	
	public static void setBreed2(String breed2) {
		breed2ThreadLocal.set(breed2);
	}
	
	public static String getBreed2() {
		 return breed2ThreadLocal.get();
	}
	
	public static void setBreed3(String breed3) {
		breed3ThreadLocal.set(breed3);
	}
	
	public static String getBreed3() {
		 return breed3ThreadLocal.get();
	}
	
	//Weight
	public static void setWeight1(String weight1) {
		weight1ThreadLocal.set(weight1);
	}
	
	public static String getWeight1() {
		 return weight1ThreadLocal.get();
	}
	
	public static void setWeight2(String weight2) {
		weight2ThreadLocal.set(weight2);
	}
	
	public static String getWeight2() {
		 return weight2ThreadLocal.get();
	}
	
	public static void setWeight3(String weight3) {
		weight3ThreadLocal.set(weight3);
	}
	
	public static String getWeight3() {
		 return weight3ThreadLocal.get();
	}
	
	 
	public static boolean addOtherInformation(WebDriver driver,String company,String buildingAbbreviation) throws Exception
	{
		
		String type1,type2,type3,weight1,weight2,weight3,breed1,breed2,breed3;
		String failedReason="";
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		type1=type2=type3=weight1=weight2=weight3=breed1=breed2=breed3 ="";
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.navigate().refresh();
		//Pop up after clicking Lease Name
		PropertyWare.intermittentPopUp(driver);
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		driver.findElement(Locators.summaryEditButton).click();
		
		try
		{
		//Other Fields
        Thread.sleep(2000);
	 
        //Base Rent
        try
        {
        	
        	if(RunnerClass.getMonthlyRent().equalsIgnoreCase("Error"))
			{
				failedReason = failedReason+",Base Rent";
				RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "Intial Monthly Rent"+'\n');
				//temp=1;
			}
			else
			{
			actions.moveToElement(driver.findElement(Locators.baseRent)).build().perform();
			//driver.findElement(Locators.initialMonthlyRent).clear();
			driver.findElement(Locators.baseRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			driver.findElement(Locators.baseRent).sendKeys(RunnerClass.getMonthlyRent());
			
			}
		}
		catch(Exception e)
		{
			DataBase.notAutomatedFields(buildingAbbreviation, "Base Rent"+'\n');
			failedReason = failedReason+",Base Rent";
			RunnerClass.setFailedReason(failedReason);
			//temp=1;
		}
        
		// RC Field
		try
		{
			if(PDFReader.getRCDetails().equalsIgnoreCase("Error"))
			{
				failedReason = failedReason+",RC Details";
				RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "RC Details"+'\n');
				//temp=1;
			}
			else
			{
			actions.moveToElement(driver.findElement(Locators.RCDetails)).build().perform();
			driver.findElement(Locators.rcField).clear();
			Thread.sleep(1000);
			driver.findElement(Locators.rcField).sendKeys(PDFReader.getRCDetails());
			}
		}
		catch(Exception e)
		{
			try
			{
				actions.moveToElement(driver.findElement(Locators.APMField)).build().perform();
				driver.findElement(Locators.APMField).clear();
				Thread.sleep(1000);
				driver.findElement(Locators.APMField).sendKeys(PDFReader.getRCDetails());
			}
			catch(Exception e2)
			{
				try
				{
					actions.moveToElement(driver.findElement(Locators.RC)).build().perform();
					driver.findElement(Locators.RC).clear();
					Thread.sleep(1000);
					driver.findElement(Locators.RC).sendKeys(PDFReader.getRCDetails());
				}
				catch(Exception e3)
				{
					failedReason = failedReason+",RC Details";
					RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "RC Details"+'\n');
				//temp=1;
				}
			}
		}
		
        //Early Termination
		try
		{
			if(RunnerClass.getEarlyTermination().equalsIgnoreCase("Error"))
			{
				failedReason = failedReason+",Early Termination";
				RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "Early Termination"+'\n');
				//temp=1;
			}
			else
			{
			if(RunnerClass.getEarlyTermination().contains("2")||RunnerClass.getFloridaLiquidizedAddendumOption1Check()==true)
			{
				if(company.equals("San Antonio"))
				{
					actions.moveToElement(driver.findElement(Locators.earlyTermFee2x_textbox1)).build().perform();
					driver.findElement(Locators.earlyTermFee2x_textbox1).clear();
					driver.findElement(Locators.earlyTermFee2x_textbox1).sendKeys(AppConfig.getEarlyTermination(company));
				}
				else
				{
				actions.moveToElement(driver.findElement(Locators.earlyTermFee2x)).build().perform();
				driver.findElement(Locators.earlyTermFee2x).click();
				Select earlyTermination_List = new Select(driver.findElement(Locators.earlyTermination_List));
				try
				{
				earlyTermination_List.selectByVisibleText(AppConfig.getEarlyTermination(company));
				}
				catch(Exception e)
				{
					try
					{
						earlyTermination_List.selectByVisibleText("YES");
					}
					catch(Exception e2)
					{
						try
						{
							earlyTermination_List.selectByVisibleText("Yes");
						}
						catch(Exception e3)
						{
							failedReason = failedReason+",Early Termination";
							RunnerClass.setFailedReason(failedReason);
							//DataBase.notAutomatedFields(buildingAbbreviation, "Early Termination"+'\n');
							e2.printStackTrace();
							//temp=1;
						}
					}
				}
				}
			}
			else
			{
				failedReason = failedReason+",Early Termination";
				RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "Early Termination"+'\n');
				//temp=1;
			}
			}
		}
		catch(Exception e)
		{
			try
			{
				if(RunnerClass.getEarlyTermination().contains("2"))
				{
					if(company.equals("San Antonio"))
					{
						actions.moveToElement(driver.findElement(Locators.earlyTermFee2x_textbox2)).build().perform();
						driver.findElement(Locators.earlyTermFee2x_textbox2).clear();
						driver.findElement(Locators.earlyTermFee2x_textbox2).sendKeys(AppConfig.getEarlyTermination(company));
					}
					else
					{
					actions.moveToElement(driver.findElement(Locators.earlyTermFee2x_2)).build().perform();
					driver.findElement(Locators.earlyTermFee2x_2).click();
					Select earlyTermination_List = new Select(driver.findElement(Locators.earlyTermination_List_2));
					try
					{
					earlyTermination_List.selectByVisibleText(AppConfig.getEarlyTermination(company));
					}
					catch(Exception ee)
					{
						try
						{
							earlyTermination_List.selectByVisibleText("YES");
						}
						catch(Exception e2)
						{
							try
							{
								earlyTermination_List.selectByVisibleText("Yes");
							}
							catch(Exception e3)
							{
								failedReason = failedReason+",Early Termination";
								RunnerClass.setFailedReason(failedReason);
								//DataBase.notAutomatedFields(buildingAbbreviation, "Early Termination"+'\n');
								e2.printStackTrace();
								//temp=1;
							}
						}
					}
					}
				}
				else
				{
					failedReason = failedReason+",Early Termination";
					RunnerClass.setFailedReason(failedReason);
					//DataBase.notAutomatedFields(buildingAbbreviation, "Early Termination"+'\n');
					//temp=1;
				}
			}
			catch(Exception e2)
			{
			failedReason = failedReason+",Early Termination";
			RunnerClass.setFailedReason(failedReason);
			//DataBase.notAutomatedFields(buildingAbbreviation, "Early Termination"+'\n');
			e2.printStackTrace();
			//temp=1;
			}
		}
		
		if(RunnerClass.getresidentBenefitsPackageAvailabilityCheckFlag()==true)
		{
			if(RunnerClass.getresidentBenefitsPackage()!="Error")
			{
			//Thread.sleep(2000);
			try
			{
			actions.moveToElement(driver.findElement(Locators.residentBenefitsPackage)).build().perform();
			driver.findElement(Locators.residentBenefitsPackage).click();
			Select residentBenefitsPackageList = new Select(driver.findElement(Locators.residentBenefitsPackage));
			//if(OKC_PropertyWare.HVACFilterFlag==false)
			residentBenefitsPackageList.selectByVisibleText("YES");
			//else enrolledInFilterEasyList.selectByVisibleText("NO");
			}
			catch(Exception e)
			{
				failedReason = failedReason+",Resident Benefits Package";
				RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "Resident Benefits Package"+'\n');
				//temp=1;
				e.printStackTrace();
			}
			}
		}
		else
		{
			if(company.equals("Chicago"))
			{
			//Enrolled in FilterEasy
			if(RunnerClass.getairFilterFee()!="Error")
			{
			//Thread.sleep(2000);
			try
			{
			actions.moveToElement(driver.findElement(Locators.enrolledInFilterEasy)).build().perform();
			driver.findElement(Locators.enrolledInFilterEasy).click();
			Select enrolledInFilterEasyList = new Select(driver.findElement(Locators.enrolledInFilterEasy_List));
			if(RunnerClass.getHVACFilterFlag()==false||RunnerClass.getHVACFilterOptOutAddendum()==true)
			enrolledInFilterEasyList.selectByVisibleText("YES");
			else enrolledInFilterEasyList.selectByVisibleText("NO");
			}
			catch(Exception e)
			{
				try
				{
				actions.moveToElement(driver.findElement(Locators.enrolledInFilterEasy)).build().perform();
				driver.findElement(Locators.enrolledInFilterEasy).click();
				Select enrolledInFilterEasyList = new Select(driver.findElement(Locators.enrolledInFilterEasy_List));
				if(RunnerClass.getHVACFilterFlag()==false||RunnerClass.getHVACFilterOptOutAddendum()==true)
				enrolledInFilterEasyList.selectByVisibleText("Yes");
				else enrolledInFilterEasyList.selectByVisibleText("No");
				}
				catch(Exception e2)
				{
					failedReason = failedReason+",Enrolled in FilterEasy";
					RunnerClass.setFailedReason(failedReason);
					//DataBase.notAutomatedFields(buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
					//temp=1;
					e.printStackTrace();
				}
			}
		}
		}
		}
		
		//RBP opt out
				try
				{
					if( RunnerClass.getRBPOptOutAddendumCheck()==true)
					{
						//Enrolled in RBP For PM Use
						try
						{
						actions.moveToElement(driver.findElement(Locators.enrolledInRBPForPMUse)).build().perform();
						driver.findElement(Locators.enrolledInRBPForPMUse).click();
						Select RBPOptOut = new Select(driver.findElement(Locators.enrolledInRBPForPMUse));
						try
						{
						RBPOptOut.selectByVisibleText("No");
						}
						catch(Exception e2)
						{
							try
							{
								RBPOptOut.selectByVisibleText("NO");
							}
							catch(Exception e3)
							{
								failedReason = failedReason+",Enrolled in RBP For PM Use";
								RunnerClass.setFailedReason(failedReason);
								//DataBase.notAutomatedFields(RunnerClass.buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
								//temp=1;
								e3.printStackTrace();
							}
						}
						}
						catch(Exception O) {
							failedReason = failedReason+",Enrolled in RBP For PM Use";
							RunnerClass.setFailedReason(failedReason);
							//DataBase.notAutomatedFields(RunnerClass.buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
							//temp=1;
							O.printStackTrace();
						}
						
						
						//RBP Opt Out Options
						
						try
						{
						actions.moveToElement(driver.findElement(Locators.RBPOptOut)).build().perform();
						driver.findElement(Locators.RBPOptOut).click();
						Select RBPOptOut = new Select(driver.findElement(Locators.RBPOptOut));
						try
						{
						RBPOptOut.selectByVisibleText("Yes");
						}
						catch(Exception e2)
						{
							try
							{
								RBPOptOut.selectByVisibleText("YES");
							}
							catch(Exception e3)
							{
								failedReason = failedReason+",RBP Opt Out Options";
								RunnerClass.setFailedReason(failedReason);
								//DataBase.notAutomatedFields(RunnerClass.buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
								//temp=1;
								e3.printStackTrace();
							}
						}
						}
						catch(Exception O) {
							failedReason = failedReason+",RBP Opt Out Options";
							RunnerClass.setFailedReason(failedReason);
							//DataBase.notAutomatedFields(RunnerClass.buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
							//temp=1;
							O.printStackTrace();
						}
					}
				}
				catch(Exception e)
				{
					failedReason = failedReason+",RBP Opt Out";
					RunnerClass.setFailedReason(failedReason);
					//DataBase.notAutomatedFields(RunnerClass.buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
					//temp=1;
					e.printStackTrace();
				}
		
		//Client Type
		try
		{
			actions.moveToElement(driver.findElement(Locators.clientType)).build().perform();
			driver.findElement(Locators.clientType).click();
			Select clientType = new Select(driver.findElement(Locators.clientType));
			if(RunnerClass.getPortfolioTypeForClientType().equals("MCH"))
			clientType.selectByVisibleText("Institutional");
			else clientType.selectByVisibleText("Retail");
		}
		catch(Exception e)
		{
			failedReason = failedReason+",Client Type";
			RunnerClass.setFailedReason(failedReason);
			//DataBase.notAutomatedFields(buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
			//temp=1;
			e.printStackTrace();
		}
		
		//Captive Insurence
		//if(PDFReader.captiveInsurenceATXFlag==true) 
		//{
			try
			{
			actions.moveToElement(driver.findElement(Locators.captiveInsurence)).build().perform();
			driver.findElement(Locators.captiveInsurence).click();
			Select captiveInsurenceList = new Select(driver.findElement(Locators.captiveInsurence));
			try
			{
				if(RunnerClass.getCaptiveInsurenceATXFlag()==true)
				captiveInsurenceList.selectByVisibleText("Yes");
				else
					captiveInsurenceList.selectByVisibleText("No");
			}
			catch(Exception e)
			{
				try
				{
					if(RunnerClass.getCaptiveInsurenceATXFlag()==true)
						captiveInsurenceList.selectByVisibleText("YES");
						else
							captiveInsurenceList.selectByVisibleText("NO");
				}
				catch(Exception e2)
				{
					failedReason = failedReason+",Captive Insurence";
					RunnerClass.setFailedReason(failedReason);
					//DataBase.notAutomatedFields(buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
					//temp=1;
					//e.printStackTrace();
				}
			}
			}
			catch(Exception e)
			{
				failedReason = failedReason+",Captive Insurence";
				RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "Enrolled in FilterEasy"+'\n');
				//temp=1;
				//e.printStackTrace();
			}
		//}
		
		//Needs New Lease - No by default
		//Thread.sleep(2000);
		try
		{
		actions.moveToElement(driver.findElement(Locators.needsNewLease)).build().perform();
		driver.findElement(Locators.needsNewLease).click();
		Select needsNewLease_List = new Select(driver.findElement(Locators.needsNewLease_List));
		try {
			needsNewLease_List.selectByVisibleText("NO");
		}
		catch(Exception e){
			needsNewLease_List.selectByVisibleText("No");	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			failedReason = failedReason+",Needs New Lease";
			RunnerClass.setFailedReason(failedReason);
			//DataBase.notAutomatedFields(buildingAbbreviation, "Needs New Lease"+'\n');
			//temp=1;
		}
		//Lease Occupants
		//Thread.sleep(2000);
		try
		{
			if(RunnerClass.getOccupants().equalsIgnoreCase("Error"))
			{
				failedReason = failedReason+",Lease Occupants";
				RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "Lease Occupants"+'\n');
				//temp=1;
			}
			else
			{
			actions.moveToElement(driver.findElement(Locators.leaseOccupants)).build().perform();
			driver.findElement(Locators.leaseOccupants).clear();
			Thread.sleep(1000);
			driver.findElement(Locators.leaseOccupants).sendKeys(RunnerClass.getOccupants().trim());
			Thread.sleep(1000);
			}
		}
		catch(Exception e)
		{
			try
			{
				actions.moveToElement(driver.findElement(Locators.otherOccupants)).build().perform();
				driver.findElement(Locators.otherOccupants).clear();
				Thread.sleep(1000);
				driver.findElement(Locators.otherOccupants).sendKeys(RunnerClass.getOccupants());
			}
			catch(Exception e2)
			{
			//DataBase.notAutomatedFields(buildingAbbreviation, "Lease Occupants"+'\n');
			failedReason = failedReason+",Lease Occupants";
			RunnerClass.setFailedReason(failedReason);
			}
			//temp=1;
		}
	
		
			
			

		//Initial Monthly Payment
		try
		{
			if(RunnerClass.getMonthlyRent().equalsIgnoreCase("Error"))
			{
				failedReason = failedReason+",Intial Monthly Rent";
				RunnerClass.setFailedReason(failedReason);
				//DataBase.notAutomatedFields(buildingAbbreviation, "Intial Monthly Rent"+'\n');
				//temp=1;
			}
			else
			{
				if(company.equals("Boise")||company.equals("Idaho Falls")||company.equals("Utah"))
				{
					actions.moveToElement(driver.findElement(Locators.monthlyRentAmount)).build().perform();
					driver.findElement(Locators.monthlyRentAmount).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					driver.findElement(Locators.monthlyRentAmount).sendKeys(RunnerClass.getMonthlyRent());
				}
				else
				{
			        actions.moveToElement(driver.findElement(Locators.initialMonthlyRent)).build().perform();
			        driver.findElement(Locators.initialMonthlyRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			        driver.findElement(Locators.initialMonthlyRent).sendKeys(RunnerClass.getMonthlyRent());
				}
			
			}
		}
		catch(Exception e)
		{
			DataBase.notAutomatedFields(buildingAbbreviation, "Intial Monthly Rent"+'\n');
			failedReason = failedReason+",Intial Monthly Rent";
			RunnerClass.setFailedReason(failedReason);
			//temp=1;
		}
		
		//Current Monthly Rent
				try
				{
					if(RunnerClass.getMonthlyRent().equalsIgnoreCase("Error"))
					{
						failedReason = failedReason+",Current Monthly Rent";
						RunnerClass.setFailedReason(failedReason);
						//DataBase.notAutomatedFields(buildingAbbreviation, "Intial Monthly Rent"+'\n');
						//temp=1;
					}
					else
					{
					actions.moveToElement(driver.findElement(Locators.currentMonthlyRent)).build().perform();
					//driver.findElement(Locators.initialMonthlyRent).clear();
					driver.findElement(Locators.currentMonthlyRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
					driver.findElement(Locators.currentMonthlyRent).sendKeys(RunnerClass.getMonthlyRent());
					
					}
				}
				catch(Exception e)
				{
					DataBase.notAutomatedFields(buildingAbbreviation, "Current Monthly Rent"+'\n');
					failedReason = failedReason+",Current Monthly Rent";
					RunnerClass.setFailedReason(failedReason);
					//temp=1;
				}
		
		
		//Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		try
		{
			Thread.sleep(2000);
			if(AppConfig.saveButtonOnAndOff==true)
			{
			actions.moveToElement(driver.findElement(Locators.saveLease)).click(driver.findElement(Locators.saveLease)).build().perform();
			Thread.sleep(2000);
			try
			{
				wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(Locators.saveLease)));
			}
			catch(Exception e) {}
			if(driver.findElement(Locators.saveLease).isDisplayed())
			{
				actions.moveToElement(driver.findElement(Locators.leaseOccupants)).build().perform();
				driver.findElement(Locators.leaseOccupants).clear();
				actions.moveToElement(driver.findElement(Locators.saveLease)).click(driver.findElement(Locators.saveLease)).build().perform();
				Thread.sleep(2000);
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Thread.sleep(2000);
		/*
		if(temp==0)
		RunnerClass.leaseCompletedStatus = 1;
		else RunnerClass.leaseCompletedStatus = 3;
		*/
		return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		//RunnerClass.leaseCompletedStatus = 2;
		Thread.sleep(2000);
		if(AppConfig.saveButtonOnAndOff==true)
		{
		actions.moveToElement(driver.findElement(Locators.saveLease)).click(driver.findElement(Locators.saveLease)).build().perform();
		if(driver.findElement(Locators.saveLease).isDisplayed())
		{
			actions.moveToElement(driver.findElement(Locators.leaseOccupants)).build().perform();
			driver.findElement(Locators.leaseOccupants).clear();
			actions.moveToElement(driver.findElement(Locators.saveLease)).click(driver.findElement(Locators.saveLease)).build().perform();
			//Thread.sleep(2000);
		}
		}
		return false;
		}
	}
	

	 
		
}
