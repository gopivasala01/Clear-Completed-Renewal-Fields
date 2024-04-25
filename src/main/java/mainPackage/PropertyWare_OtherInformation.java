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
		if(RunnerClass.getpetFlag()==true||RunnerClass.getserviceAnimalFlag()==true)
		{
		//pet information
			//Thread.sleep(2000);
			String petType = "";
			String petBreed = "";
			String petWeight = "";
			if(RunnerClass.getPetTypes().isEmpty()) {
				petType = "";
				petBreed = "";
				petWeight = "";
			}
			else {
				//Pet Type
				petType = String.join(",", RunnerClass.getPetTypes());
				petBreed = String.join(",", RunnerClass.getPetBreeds());
				petWeight = String.join(",", RunnerClass.getPetWeights());
			}
			
			petInfoDistribution(petType,petWeight,petBreed);
			
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet1Type)).build().perform();
				driver.findElement(Locators.pet1Type).clear();
				Thread.sleep(1000);
				driver.findElement(Locators.pet1Type).sendKeys(getType1());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Types"+'\n');
				failedReason = failedReason+",Pet Types";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			//Thread.sleep(2000);
			//Pet Breed
			//String petBreed = String.join(",", PDFReader.petBreed);
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet1Breed)).build().perform();
				driver.findElement(Locators.pet1Breed).clear();
				Thread.sleep(1000);
				driver.findElement(Locators.pet1Breed).sendKeys(getBreed1());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Breed"+'\n');
				failedReason = failedReason+",Pet Breed";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			
			//Pet Weight
			//String petWeight = String.join(",", PDFReader.petWeight);
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet1Weight)).build().perform();
				driver.findElement(Locators.pet1Weight).clear();
				Thread.sleep(1000);
				driver.findElement(Locators.pet1Weight).sendKeys(getWeight1());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Weight"+'\n');
				failedReason = failedReason+",Pet Weight";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			//Pet 2 Info
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet2Type)).build().perform();
				driver.findElement(Locators.pet2Type).clear();
				Thread.sleep(1000);
				if(getType2() == null) {
					setType2("");
				}
				driver.findElement(Locators.pet2Type).sendKeys(getType2());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Types"+'\n');
				failedReason = failedReason+",Pet Types";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet2Breed)).build().perform();
				driver.findElement(Locators.pet2Breed).clear();
				Thread.sleep(1000);
				if(getBreed2() == null) {
					setBreed2("");
				}
				driver.findElement(Locators.pet2Breed).sendKeys(getBreed2());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Breed"+'\n');
				failedReason = failedReason+",Pet Breed";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet2Weight)).build().perform();
				driver.findElement(Locators.pet2Weight).clear();
				Thread.sleep(1000);
				if(getWeight2() == null) {
					setWeight2("");
				}
				driver.findElement(Locators.pet2Weight).sendKeys(getWeight2());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Weight"+'\n');
				failedReason = failedReason+",Pet Weight";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			
			//Pet 3 Info
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet3Type)).build().perform();
				driver.findElement(Locators.pet3Type).clear();
				Thread.sleep(1000);
				if(getType3() == null) {
					setType3("");
				}
				driver.findElement(Locators.pet3Type).sendKeys(getType3());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Types"+'\n');
				failedReason = failedReason+",Pet Types";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet3Breed)).build().perform();
				driver.findElement(Locators.pet3Breed).clear();
				Thread.sleep(1000);
				if(getBreed3() == null) {
					setBreed3("");
				}
				driver.findElement(Locators.pet3Breed).sendKeys(getBreed3());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Breed"+'\n');
				failedReason = failedReason+",Pet Breed";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			try
			{
				actions.moveToElement(driver.findElement(Locators.pet3Weight)).build().perform();
				driver.findElement(Locators.pet3Weight).clear();
				Thread.sleep(1000);
				if(getWeight3() == null) {
					setWeight3("");
				}
				driver.findElement(Locators.pet3Weight).sendKeys(getWeight3());
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Weight"+'\n');
				failedReason = failedReason+",Pet Weight";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			
			
			
			//Pet Rent
			//Thread.sleep(2000);
			try
			{
				if(RunnerClass.getPetRent().equalsIgnoreCase("Error"))
				{
					//DataBase.notAutomatedFields(buildingAbbreviation, "pet Rent"+'\n');
					failedReason = failedReason+",Pet Rent";
					RunnerClass.setFailedReason(failedReason);
					//temp=1;
				}
				else
				{
					try
					{
				actions.moveToElement(driver.findElement(Locators.petAmount)).build().perform();
				//driver.findElement(Locators.petAmount).clear();
				driver.findElement(Locators.petAmount).click();
				//OKC_PropertyWare.clearTextField();
				driver.findElement(Locators.petAmount).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
				Thread.sleep(1000);
				//actions.click(driver.findElement(Locators.petAmount)).sendKeys(Keys.SHIFT).sendKeys(Keys.HOME).sendKeys(Keys.BACK_SPACE).build().perform();
				driver.findElement(Locators.petAmount).sendKeys(RunnerClass.getPetRent());
					}
					catch(Exception e)
					{
						actions.moveToElement(driver.findElement(Locators.petAmount2)).build().perform();
						//driver.findElement(Locators.petAmount).clear();
						driver.findElement(Locators.petAmount2).click();
						//OKC_PropertyWare.clearTextField();
						driver.findElement(Locators.petAmount2).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
						Thread.sleep(1000);
						//actions.click(driver.findElement(Locators.petAmount)).sendKeys(Keys.SHIFT).sendKeys(Keys.HOME).sendKeys(Keys.BACK_SPACE).build().perform();
						driver.findElement(Locators.petAmount2).sendKeys(RunnerClass.getPetRent());
					}
				}
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "pet Rent"+'\n');
				failedReason = failedReason+",Pet Rent";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			try
			{
				if(RunnerClass.getPetOneTimeNonRefundableFee().equalsIgnoreCase("Error"))
				{
					//DataBase.notAutomatedFields(buildingAbbreviation, "Pet One Time Non-Refundable Fee"+'\n');
					failedReason = failedReason+",Pet One Time Non-Refundable Fee";
					RunnerClass.setFailedReason(failedReason);
					//temp=1;
				}
				else
				{
					try
					{
				actions.moveToElement(driver.findElement(Locators.tenantOneTimePetFee)).build().perform();
				driver.findElement(Locators.tenantOneTimePetFee).click();
				Thread.sleep(1000);
				driver.findElement(Locators.tenantOneTimePetFee).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
				//OKC_PropertyWare.clearTextField();
				//actions.click(driver.findElement(Locators.tenantOneTimePetFee)).sendKeys(Keys.SHIFT).sendKeys(Keys.HOME).sendKeys(Keys.BACK_SPACE).build().perform();
				driver.findElement(Locators.tenantOneTimePetFee).sendKeys(RunnerClass.getPetOneTimeNonRefundableFee());
					}
					catch(Exception e)
					{
						actions.moveToElement(driver.findElement(Locators.petDepositAmount)).build().perform();
						driver.findElement(Locators.petDepositAmount).click();
						Thread.sleep(1000);
						driver.findElement(Locators.petDepositAmount).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
						//OKC_PropertyWare.clearTextField();
						//actions.click(driver.findElement(Locators.tenantOneTimePetFee)).sendKeys(Keys.SHIFT).sendKeys(Keys.HOME).sendKeys(Keys.BACK_SPACE).build().perform();
						driver.findElement(Locators.petDepositAmount).sendKeys(RunnerClass.getPetOneTimeNonRefundableFee());
					}
				}
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "pet One Time Non-Refundable Fee"+'\n');
				failedReason = failedReason+",Pet One Time Non-Refundable Fee";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			
			//Initial Pet Rent Amount
			try
			{
				if(RunnerClass.getPetRent().equalsIgnoreCase("Error"))
				{
					failedReason = failedReason+",Intial Pet Rent";
					RunnerClass.setFailedReason(failedReason);
					//DataBase.notAutomatedFields(buildingAbbreviation, "Intial Monthly Rent"+'\n');
					//temp=1;
				}
				else
				{
				actions.moveToElement(driver.findElement(Locators.initialPetRentAmount)).build().perform();
				driver.findElement(Locators.initialPetRentAmount).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
				driver.findElement(Locators.initialPetRentAmount).sendKeys(RunnerClass.getPetRent());
				
				}
			}
			catch(Exception e)
			{
				DataBase.notAutomatedFields(buildingAbbreviation, "Intial Pet Monthly Rent"+'\n');
				failedReason = failedReason+",Intial Pet Rent";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			
			//Pet Rent Amount
			try
			{
				if(RunnerClass.getPetRent().equalsIgnoreCase("Error"))
				{
					failedReason = failedReason+",Pet Rent";
					RunnerClass.setFailedReason(failedReason);
					//DataBase.notAutomatedFields(buildingAbbreviation, "Intial Monthly Rent"+'\n');
					//temp=1;
				}
				else
				{
				actions.moveToElement(driver.findElement(Locators.petRentAmount)).build().perform();
				driver.findElement(Locators.petRentAmount).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
				driver.findElement(Locators.petRentAmount).sendKeys(RunnerClass.getPetRent());
				
				}
			}
			catch(Exception e)
			{
				DataBase.notAutomatedFields(buildingAbbreviation, "Pet Rent Amount"+'\n');
				failedReason = failedReason+",Pet Rent";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
			
		}
			//Service Animal Information
			if(RunnerClass.getserviceAnimalFlag()==true)
			{
			
				//Pet Special Provisions
				try
				{
					actions.moveToElement(driver.findElement(Locators.petSpecialProvisions)).build().perform();
					driver.findElement(Locators.petSpecialProvisions).clear();
					Thread.sleep(1000);
					driver.findElement(Locators.petSpecialProvisions).sendKeys("Service animals, no deposit required");
				}
				catch(Exception e)
				{
					//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Special Provisions"+'\n');
					failedReason = failedReason+",Pet Special Provisions";
					RunnerClass.setFailedReason(failedReason);
					//temp=1;
				}
				
			} //Service Animal block
			else
			{
			//Pet Security Deposit
			//Thread.sleep(2000);
			try
			{
				//if(!OKC_PropertyWare.petSecurityDeposit.equalsIgnoreCase("Error")||!OKC_PropertyWare.petSecurityDeposit.trim().equalsIgnoreCase(" ")||!OKC_PropertyWare.petSecurityDeposit.trim().equalsIgnoreCase(""))
				if(RunnerClass.onlyDigits(RunnerClass.getPetSecurityDeposit().trim())==true)
				{
				actions.moveToElement(driver.findElement(Locators.petDepositAmount)).build().perform();
				//driver.findElement(Locators.petAmount).clear();
				driver.findElement(Locators.petDepositAmount).click();
				driver.findElement(Locators.petDepositAmount).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
				//OKC_PropertyWare.clearTextField();
				Thread.sleep(1000);
				//actions.click(driver.findElement(Locators.petAmount)).sendKeys(Keys.SHIFT).sendKeys(Keys.HOME).sendKeys(Keys.BACK_SPACE).build().perform();
				driver.findElement(Locators.petDepositAmount).sendKeys(RunnerClass.getPetSecurityDeposit());
				}
			}
			catch(Exception e)
			{
				//DataBase.notAutomatedFields(buildingAbbreviation, "Pet Security Deposit"+'\n');
				failedReason = failedReason+",Pet Security Deposit";
				RunnerClass.setFailedReason(failedReason);
				//temp=1;
			}
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
	
	public static void petInfoDistribution(String petType, String petWeight, String petBreed)
	{
	 //String  type1,type2,type3,weight1,weight2,weight3,breed1,breed2,breed3;
	 //type1=type2=type3=weight1=weight2=weight3=breed1=breed2=breed3 ="";
		if(RunnerClass.getpetFlag()==false&&RunnerClass.getserviceAnimalFlag()==true)
		{
			 if(String.join(",",RunnerClass.getServiceAnimalPetType()).contains(","))
			 {
				 RunnerClass.getServiceAnimalPetType().replaceAll(s->"Service "+s);
				 setType1( String.join(",",RunnerClass.getServiceAnimalPetType()).split(",",2)[0]);
				 setType2(String.join(",",RunnerClass.getServiceAnimalPetType()).split(",",2)[1]);
				 
				 setWeight1(String.join(",",RunnerClass.getServiceAnimalPetWeights()).split(",",2)[0]);
				 setWeight2(String.join(",",RunnerClass.getServiceAnimalPetWeights()).split(",",2)[1]);
				 
				 setBreed1(String.join(",",RunnerClass.getServiceAnimalPetBreeds()).split(",",2)[0]);
				 setBreed2(String.join(",",RunnerClass.getServiceAnimalPetBreeds()).split(",",2)[1]);
			 }
			 else
			 {
				 RunnerClass.getServiceAnimalPetType().replaceAll(s->"Service "+s);
				 setType1( String.join(",",RunnerClass.getServiceAnimalPetType()));
				 setWeight1( String.join(",",RunnerClass.getServiceAnimalPetWeights()));
				 setBreed1 ( String.join(",",RunnerClass.getServiceAnimalPetBreeds()));
			 }
		}
		else
	 if(RunnerClass.getserviceAnimalFlag()==true)
	 {
		 RunnerClass.getServiceAnimalPetType().replaceAll(s->"Service "+s);
		 setType3(String.join(",",RunnerClass.getServiceAnimalPetType()));
		 setWeight3(String.join(",",RunnerClass.getServiceAnimalPetWeights()));
		 setBreed3(String.join(",",RunnerClass.getServiceAnimalPetBreeds()));
		 
		 if(petType.contains(","))
		 {
			 setType1(petType.split(",",2)[0]);
			 setType2(petType.split(",",2)[1]);
			 
			 setWeight1( petWeight.split(",",2)[0]);
			 setWeight2( petWeight.split(",",2)[1]);
			 
			 setBreed1(petBreed.split(",",2)[0]);
			 setBreed2( petBreed.split(",",2)[1]);
		 }
		 else
		 {
			 setType1(petType);
			 setWeight1(petWeight);
			 setBreed1(petBreed);
		 }
	 }
	 else
	 {
		 int count =(int)petType.chars().filter(ch -> ch == ',').count();
		 if(count==0)
		 {
			 setType1(petType);
			 
			 setWeight1(petWeight);
			 
			 setBreed1(petBreed);
		 }
		 else  if(count==1)
		 {
			 setType1(petType.split(",",2)[0]);
			 setType2(petType.split(",",2)[1]);
			 
			 setWeight1(petWeight.split(",",2)[0]);
			 setWeight2(petWeight.split(",",2)[1]);
			 
			 setBreed1(petBreed.split(",",2)[0]);
			 setBreed2(petBreed.split(",",2)[1]); 
		 }
		 else if(count>1)
		 {
			 
			 setType1(petType.split(",",3)[0]);
			 setType2(petType.split(",",3)[1]);
			 setType3(petType.split(",",3)[2]);
			 
			 setWeight1(petWeight.split(",",3)[0]);
			 setWeight2(petWeight.split(",",3)[1]);
			 setWeight3( petWeight.split(",",3)[2]);
			 
			 setBreed1(petBreed.split(",",3)[0]);
			 setBreed2(petBreed.split(",",3)[1]); 
			 setBreed3(petBreed.split(",",3)[2]);
		 }			
		 }
		
		
	 
	 System.out.println("Pet Type = "+getType1()+ "  |  "+getType2()+"   |  "+getType3());
	 System.out.println("Pet Weight = "+getWeight1()+ "  |  "+getWeight2()+"   |  "+getWeight3());
	 System.out.println("Pet Breed = "+getBreed1()+ "  |  "+getBreed2()+"   |  "+getBreed3());
	 }
	 
		
}
