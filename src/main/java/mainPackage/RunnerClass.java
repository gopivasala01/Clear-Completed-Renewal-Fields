package mainPackage;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RunnerClass {
	public static String[][] pendingRenewalLeases;
	public static Alert alert;

	public static ChromeOptions options;
	public static String[][] pendingBuildingList;
	public static int updateStatus;
	public static String[] statusList;
	public static String currentDate = "";
	public static String downloadFilePath;
	public static String currentTime;




	
	private static ThreadLocal<String> portfolioNameThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> portfolioTypeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<ChromeDriver> driverThreadLocal = new ThreadLocal<ChromeDriver>();
	private static ThreadLocal<String> failedReasonThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> fileNameThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> startDateThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> endDateThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> monthlyRentThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> monthlyRentTaxAmountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> monthlyRentTaxFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> prorateRentThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> prorateRentDateThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> adminFeeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> occupantsThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> residentBenefitsPackageAvailabilityCheckThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> HVACFilterFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> petFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> serviceAnimalFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> concessionAddendumFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> airFilterFeeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> prepaymentChargeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> residentBenefitsPackageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> proratedPetRentThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> petRentThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> totalPetRentWithTaxThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> petOneTimeNonRefundableFeeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> smartHomeAgreementCheckThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> smartHomeAgreementFeeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> earlyTerminationThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> totalMonthlyRentWithTaxThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> OnePercentOfRentAmountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> OnePercentOfProrateRentAmountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> OnePercentOfPetRentAmountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> OnePercentOfProratePetRentAmountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> residentBenefitsPackageTaxAvailabilityCheckThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> residentBenefitsPackageTaxAmountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> incrementRentFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> increasedRent_amountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> increasedRent_newStartDateThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> increasedRent_previousRentEndDateThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> petRentTaxFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> prorateRUBSThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> rUBSThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> residentUtilityBillFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> captiveInsurenceATXFeeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> captiveInsurenceATXFlagThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> dueDay_GreaterOfThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> percentageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> flatFeeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> initialFeeAmountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> perDayFeeAmountThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> additionalLateChargesLimitThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> dueDay_initialFeeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> startDateInPWThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> endDateInPWThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> petSecurityDepositThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> HVACFilterOptOutAddendumThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> RBPOptOutAddendumCheckThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> floridaLiquidizedAddendumOption1CheckThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> prorateResidentBenefitPackageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> arizonaCityFromBuildingAddressThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> arizonaRentCodeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Boolean> arizonaCodeAvailableThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> PDFFormatTypeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<Integer> statusIDThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> portfolioTypeForClientTypeThreadLocal   = new ThreadLocal<>();
	
	
	private static ThreadLocal<ArrayList<String>> petTypeThreadLocal = ThreadLocal.withInitial(ArrayList::new);
	private static ThreadLocal<ArrayList<String>> petBreedThreadLocal = ThreadLocal.withInitial(ArrayList::new);
	private static ThreadLocal<ArrayList<String>> petWeightThreadLocal = ThreadLocal.withInitial(ArrayList::new);
	
	private static ThreadLocal<ArrayList<String>> serviceAnimalPetTypeThreadLocal = ThreadLocal.withInitial(ArrayList::new);
	private static ThreadLocal<ArrayList<String>> serviceAnimalPetBreedThreadLocal = ThreadLocal.withInitial(ArrayList::new);
	private static ThreadLocal<ArrayList<String>> serviceAnimalPetWeightThreadLocal = ThreadLocal.withInitial(ArrayList::new);

	
	private static ThreadLocal<String[][]> moveInChargesThreadLocal = ThreadLocal.withInitial(() -> new String[0][0]);
	private static ThreadLocal<String[][]> autoChargesThreadLocal = ThreadLocal.withInitial(() -> new String[0][0]);
	
	@BeforeMethod
	public boolean setUp() {
		// Set up WebDriverManager to automatically download and set up ChromeDriver
		// System.setProperty("webdriver.http.factory", "jdk-http-client");
		try {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			// WebDriverManager.chromedriver().setup();
			RunnerClass.downloadFilePath = AppConfig.downloadFilePath;
			Map<String, Object> prefs = new HashMap<String, Object>();
			// Use File.separator as it will work on any OS
			prefs.put("download.default_directory", RunnerClass.downloadFilePath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--remote-allow-origins=*");
			//options.addArguments("--headless");
			options.addArguments("--disable-gpu"); // GPU hardware acceleration isn't needed for headless
			options.addArguments("--no-sandbox"); // Disable the sandbox for all software features
			options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
			options.addArguments("--disable-extensions"); // Disabling extensions can save resources
			options.addArguments("--disable-plugins");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			// Create a new ChromeDriver instance for each thread
			ChromeDriver driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			// test = extent.createTest("Login Page");
			// Store the ChromeDriver instance in ThreadLocal
			driverThreadLocal.set(driver);
			driver.get(AppConfig.URL);
			driver.findElement(Locators.userName).sendKeys(AppConfig.username);
			driver.findElement(Locators.password).sendKeys(AppConfig.password);
			Thread.sleep(2000);
			driver.findElement(Locators.signMeIn).click();
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

			try {
				if (driver.findElement(Locators.loginError).isDisplayed()) {
					System.out.println("Login failed");
					return false;
				}
			} catch (Exception e) {
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Test(dataProvider = "testData")
	public void testMethod(String SNo,String company, String buildingAbbreviation, String ownerName) throws Exception {
		System.out.println(" Building -- " + buildingAbbreviation + "Company -- "+ company);
		int statusID = 0;
		setStatusID(statusID);
		String failedReason = "";
		String completeBuildingAbbreviation="";
		ChromeDriver driver = driverThreadLocal.get();

		try {
			FileUtils.cleanDirectory(new File(AppConfig.downloadFilePath));
		} catch (Exception e) {
		} 

		if (company.equals("Chicago PFW"))
			company = "Chicago";
		if (company.contains("Austin") || company.contains("California") || company.contains("Chattanooga")
				|| company.contains("Chicago") || company.contains("Colorado") || company.contains("Kansas City")
				|| company.contains("Houston") || company.contains("Maine") || company.contains("Savannah")
				|| company.contains("North Carolina") || company.contains("Alabama") || company.contains("Arkansas")
				|| company.contains("Dallas/Fort Worth") || company.contains("Indiana")
				|| company.contains("Little Rock") || company.contains("San Antonio") || company.contains("Tulsa")
				|| company.contains("Georgia") || company.contains("OKC") || company.contains("South Carolina")
				|| company.contains("Tennessee") || company.contains("Florida") || company.contains("New Mexico")
				|| company.contains("Ohio") || company.contains("Pennsylvania") || company.contains("Lake Havasu")
				|| company.contains("Columbia - St Louis") || company.contains("Maryland")
				|| company.contains("Virginia") || company.contains("Boise") || company.contains("Idaho Falls")
				|| company.contains("Utah") || company.contains("Spokane") || company.contains("Washington DC")
				|| company.contains("Hawaii") || company.contains("Arizona") || company.contains("New Jersey")
				|| company.contains("Montana") || company.contains("Delaware")) {
			// Change the Status of the Lease to Started so that it won't run again in the
			// Jenkins scheduling Process
			DataBase.insertData(buildingAbbreviation, "Started", 6);
			completeBuildingAbbreviation = buildingAbbreviation; // This will be used when Building not found in first
																	// attempt
			try {
				String a = buildingAbbreviation;
				a = a.replace(" ", "");
				int b = a.length() - 1;
				char c = a.charAt(a.indexOf('-') + 1);
				if (a.indexOf('-') >= 1 && a.indexOf('-') == (b - 1))
					buildingAbbreviation = buildingAbbreviation;
				else if (a.indexOf('-') >= 1 && a.charAt(a.indexOf('-') + 1) == '(')
					buildingAbbreviation = buildingAbbreviation.split("-")[0].trim();
				else
					buildingAbbreviation = buildingAbbreviation;
			} catch (Exception e) {
			}
			// Login to the PropertyWare
			try {
				// Search building in property Ware
				if (PropertyWare.selectBuilding(driver,company, ownerName) == true) {
					RunnerClass.processAfterBuildingIsSelected(driver,SNo,company, buildingAbbreviation, ownerName,failedReason);
				} else if ((PropertyWare.searchBuilding(driver,company, buildingAbbreviation,completeBuildingAbbreviation) == true)) {
					if (PropertyWare.downloadLeaseAgreement(driver,buildingAbbreviation, ownerName) == true) {

						if (PDFReader.readPDFPerMarket(company,SNo) == true) {
							PropertyWare_updateValues.configureValues(driver,company,buildingAbbreviation,SNo);
							PropertyWare_AutoCharges.addingAutoCharges(driver,buildingAbbreviation,SNo);
							PropertyWare_OtherInformation.addOtherInformation(driver,company,buildingAbbreviation);

							// Update Completed Status
							failedReason = getFailedReason();
							if (failedReason == null || failedReason.equalsIgnoreCase(""))
								failedReason = "";
							else if (failedReason.charAt(0) == ',')
								failedReason = failedReason.substring(1);
							String updateSuccessStatus = "";
							if (statusID == 0)
								updateSuccessStatus = "Update [Automation].LeaseInfo Set Status ='Completed', StatusID=4,NotAutomatedFields='"
										+ failedReason + "',LeaseCompletionDate= getDate() where BuildingName like '%"
										+ buildingAbbreviation + "%'";
							else
								updateSuccessStatus = "Update [Automation].LeaseInfo Set Status ='Review', StatusID=5,NotAutomatedFields='"
										+ failedReason + "',LeaseCompletionDate= getDate() where BuildingName like '%"
										+ buildingAbbreviation + "%'";
							DataBase.updateTable(updateSuccessStatus);

						} else {
							failedReason = getFailedReason();
							if (failedReason == null || failedReason.equalsIgnoreCase(""))
								failedReason = "";
							else if (failedReason.charAt(0) == ',')
								failedReason = failedReason.substring(1);
							String updateSuccessStatus = "Update [Automation].LeaseInfo Set Status ='Failed', StatusID=3,NotAutomatedFields='"
									+ failedReason + "',LeaseCompletionDate= getDate() where BuildingName like '%"
									+ buildingAbbreviation + "%'";
							DataBase.updateTable(updateSuccessStatus);

						}

					} else {
						if (PropertyWare.selectBuilding(driver,company, completeBuildingAbbreviation) == true) {
							RunnerClass.processAfterBuildingIsSelected(driver,SNo,company, buildingAbbreviation, ownerName,failedReason);
						} else {
							failedReason = getFailedReason();
							if (failedReason == null || failedReason.equalsIgnoreCase(""))
								failedReason = "";
							else if (failedReason.charAt(0) == ',')
								failedReason = failedReason.substring(1);
							String updateSuccessStatus = "Update [Automation].LeaseInfo Set Status ='Failed', StatusID=3,NotAutomatedFields='"
									+ failedReason + "',LeaseCompletionDate= getDate() where BuildingName like '%"
									+ buildingAbbreviation + "%'";
							DataBase.updateTable(updateSuccessStatus);
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				setFailedReason(null);
				setFileName(null);
				setStartDate(null);
				setEndDate(null);
				setMonthlyRent(null);
				setMonthlyRentTaxAmount(null);
				setOccupants(null);
				setMonthlyRentTaxFlag(false);
				setresidentBenefitsPackageAvailabilityCheckFlag(false);
				setHVACFilterFlag(false);
				setpetFlag(false);
				setserviceAnimalFlag(false);
				setconcessionAddendumFlag(false);
				setAdminFee(null);
				setProrateRent(null);
				setProrateRentDate(null);
				setairFilterFee(null);
				setprepaymentCharge(null);
				setresidentBenefitsPackage(null);
				setproratedPetRent(null);
				setPetRent(null);
				setTotalPetRentWithTax(null);
				setPetOneTimeNonRefundableFee(null);
				setSmartHomeAgreementCheck(false);
				setSmartHomeAgreementFee(null);
				setEarlyTermination(null);
				setTotalMonthlyRentWithTax(null);
				setOnePercentOfRentAmount(null);
				setOnePercentOfProrateRentAmount(null);
				setOnePercentOfPetRentAmount(null);
				setOnePercentOfProratePetRentAmount(null);
				setResidentBenefitsPackageTaxAvailabilityCheck(false);
				setResidentBenefitsPackageTaxAmount(null);
				setPortfolioName(null);
				setPortfolioType(null);
				setIncrementRentFlag(false);
				setIncreasedRent_amount(null);
				setIncreasedRent_newStartDate(null);
				setPetRentTaxFlag(false);
				setProrateRUBS(null);
				setResidentUtilityBillFlag(false);
				setRUBS(null);
				setIncreasedRent_previousRentEndDate(null);
				setCaptiveInsurenceATXFee(null);
				setCaptiveInsurenceATXFlag(false);
				
				setPercentage(null);
				setFlatFee(null);
				setInitialFeeAmount(null);
				setPerDayFeeAmount(null);
				setAdditionalLateChargesLimit(null);
				setDueDay_initialFee(null);
				setStartDateInPW(null);
				setEndDateInPW(null);
				setPetSecurityDeposit(null);
				setHVACFilterOptOutAddendum(false);
				setRBPOptOutAddendumCheck(false);
				setFloridaLiquidizedAddendumOption1Check(false);
				setProrateResidentBenefitPackage(null);
				setArizonaCityFromBuildingAddress(null);
				setArizonaRentCode(null);
				setArizonaCodeAvailable(false);
				setPDFFormatType(null);
				setStatusID(0);
				setPortfolioTypeForClientType(null);
				PropertyWare_updateValues.setStartDate_MoveInCharge(null);
				PropertyWare_updateValues.setEndDate_ProrateRent(null);
				PropertyWare_updateValues.setstartDate_AutoCharge(null);
				PropertyWare_updateValues.setautoCharge_startDate_MonthlyRent(null);  //For other portfolios, it should be added as second full month in Auto Charges 
				PropertyWare_updateValues.setendDate_MonthlyRent_WhenIncreasedRentAvailable(null);
				PropertyWare_updateValues.setCheckIfMoveInDateIsLessThan5DaysToEOM(false);
				PropertyWare_updateValues.setPetInspectionFeeFlag(false);
				PropertyWare_updateValues.setPetSecurityDepositFlag(false);
				PDFReader.setProrateRentGET(null);
				PDFReader.setLateFeeRuleType(null);
				PDFReader.setLateChargeDay(null);
				PDFReader.setLateFeePercentage(null);
				PDFReader.setLateFeeType(null);
				PDFReader.setLateChargeFee(null);
				PDFReader.setLateFeeChargePerDay(null);
				setAdditionalLateChargesLimit(null);
				PDFReader.setAdditionalLateCharges(null);
				PDFReader.setRCDetails(null);
				PropertyWare_OtherInformation.setType1(null);
				PropertyWare_OtherInformation.setType2(null);
				PropertyWare_OtherInformation.setType3(null);
				//Breed
				PropertyWare_OtherInformation.setBreed1(null);
				PropertyWare_OtherInformation.setBreed2(null);
				PropertyWare_OtherInformation.setBreed3(null);
				//Weight
				PropertyWare_OtherInformation.setWeight1(null);
				PropertyWare_OtherInformation.setWeight2(null);
				PropertyWare_OtherInformation.setWeight3(null);
				
				
				//Arraylist
				setPetTypes(null);
				setPetBreeds(null);
				setPetWeights(null);
				setServiceAnimalPetType(null);
				setServiceAnimalPetBreeds(null);
				setServiceAnimalPetWeights(null);
				setMoveInCharges(null);
				setautoCharges(null);
				try
				{
				String query = "drop table if exists automation.LeaseCloseOutsChargeChargesConfiguration_"
						+ SNo;
				DataBase.updateTable(query);
				}
				catch(Exception e) {}
				driver.quit();
			}
		}

	}
	
	public static String getFailedReason() {
		if(failedReasonThreadLocal.get()==null)
			return "";
			else return failedReasonThreadLocal.get();
	}

	public static void setFailedReason(String failedReason) {
		failedReasonThreadLocal.set(failedReason);
	}
	
	public static String getFileName() {
		 return fileNameThreadLocal.get();
	}

	public static void setFileName(String failedReason) {
		fileNameThreadLocal.set(failedReason);
	}
	
	public static String getStartDate() {
		if(startDateThreadLocal.get()==null)
			return "Error";
		else
		 return startDateThreadLocal.get();
	}

	public static void setStartDate(String startDate) {
		startDateThreadLocal.set(startDate);
	}
	
	public static String getEndDate() {
		if(endDateThreadLocal.get()==null)
			return "Error";
		else
		 return endDateThreadLocal.get();
	}

	public static void setEndDate(String endDate) {
		endDateThreadLocal.set(endDate);
	}
	
	public static String getMonthlyRent() {
		if(monthlyRentThreadLocal.get()==null)
			return "Error";
		else
		 return monthlyRentThreadLocal.get();
	}

	public static void setMonthlyRent(String monthlyRent) {
		monthlyRentThreadLocal.set(monthlyRent);
	}
	public static String getMonthlyRentTaxAmount() {
		if(monthlyRentTaxAmountThreadLocal.get()==null)
			return "Error";
		else
		 return monthlyRentTaxAmountThreadLocal.get();
	}

	public static void setMonthlyRentTaxAmount(String monthlyRentTaxAmount) {
		monthlyRentTaxAmountThreadLocal.set(monthlyRentTaxAmount);
	}
	public static String getOccupants() {
		if(occupantsThreadLocal.get()==null)
			return "Error";
		else
		 return occupantsThreadLocal.get();
	}

	public static void setOccupants(String occupants) {
		occupantsThreadLocal.set(occupants);
	}
	
	public static boolean getMonthlyRentTaxFlag() {
		if(monthlyRentTaxFlagThreadLocal.get()==null)
			return false;
		else
		 return monthlyRentTaxFlagThreadLocal.get();
	}

	public static void setMonthlyRentTaxFlag(boolean monthlyRentTaxFlag) {
		monthlyRentTaxFlagThreadLocal.set(monthlyRentTaxFlag);
	}
	
	public static boolean getresidentBenefitsPackageAvailabilityCheckFlag() {
		if(residentBenefitsPackageAvailabilityCheckThreadLocal.get()==null)
			return false;
		else
		 return residentBenefitsPackageAvailabilityCheckThreadLocal.get();
	}

	public static void setresidentBenefitsPackageAvailabilityCheckFlag(boolean residentBenefitsPackageAvailabilityCheckFlag) {
		residentBenefitsPackageAvailabilityCheckThreadLocal.set(residentBenefitsPackageAvailabilityCheckFlag);
	}
	public static boolean getHVACFilterFlag() {
		if(HVACFilterFlagThreadLocal.get()==null)
			return false;
		else
		 return HVACFilterFlagThreadLocal.get();
	}

	public static void setHVACFilterFlag(boolean HVACFilterFlag) {
		HVACFilterFlagThreadLocal.set(HVACFilterFlag);
	}
	
	public static boolean getpetFlag() {
		if(petFlagThreadLocal.get()==null)
			return false;
		else
		 return petFlagThreadLocal.get();
	}

	public static void setpetFlag(boolean petFlag) {
		petFlagThreadLocal.set(petFlag);
	}
	
	public static boolean getserviceAnimalFlag() {
		if(serviceAnimalFlagThreadLocal.get()==null)
			return false;
		else
		 return serviceAnimalFlagThreadLocal.get();
	}

	public static void setserviceAnimalFlag(boolean serviceAnimalFlag) {
		serviceAnimalFlagThreadLocal.set(serviceAnimalFlag);
	}
	
	public static boolean getconcessionAddendumFlag() {
		if(concessionAddendumFlagThreadLocal.get()==null)
			return false;
		else
		 return concessionAddendumFlagThreadLocal.get();
	}

	public static void setconcessionAddendumFlag(boolean concessionAddendumFlag) {
		concessionAddendumFlagThreadLocal.set(concessionAddendumFlag);
	}
	
	
		
	public static String getAdminFee() {
		if(adminFeeThreadLocal.get()==null)
			return "Error";
		else
		 return adminFeeThreadLocal.get();
	}
	
	public static void setAdminFee(String adminFee) {
		adminFeeThreadLocal.set(adminFee);
	}
	
	public static String getProrateRent() {
		if(prorateRentThreadLocal.get()==null)
			return "Error";
		else
		 return prorateRentThreadLocal.get();
	}

	public static void setProrateRent(String prorateRent) {
		prorateRentThreadLocal.set(prorateRent);
	}
	
	public static String getProrateRentDate() {
		if(prorateRentDateThreadLocal.get()==null)
			return "Error";
		else
		 return prorateRentDateThreadLocal.get();
	}

	public static void setProrateRentDate(String prorateRentDate) {
		prorateRentDateThreadLocal.set(prorateRentDate);
	}
	public static String getairFilterFee() {
		if(airFilterFeeThreadLocal.get()==null)
			return "Error";
		else
		 return airFilterFeeThreadLocal.get();
	}

	public static void setairFilterFee(String airFilterFee) {
		airFilterFeeThreadLocal.set(airFilterFee);
	}
	public static String getprepaymentCharge() {
		if(prepaymentChargeThreadLocal.get()==null)
			return "Error";
		else
		 return prepaymentChargeThreadLocal.get();
	}

	public static void setprepaymentCharge(String prepaymentCharge) {
		prepaymentChargeThreadLocal.set(prepaymentCharge);
	}
	
	public static String getresidentBenefitsPackage() {
		if(residentBenefitsPackageThreadLocal.get()==null)
			return "Error";
		else
		 return residentBenefitsPackageThreadLocal.get();
	}

	public static void setresidentBenefitsPackage(String residentBenefitsPackage) {
		residentBenefitsPackageThreadLocal.set(residentBenefitsPackage);
	}
	
	public static void setproratedPetRent(String proratedPetRent) {
		proratedPetRentThreadLocal.set(proratedPetRent);
	}
	
	public static String getproratedPetRent() {
		if(proratedPetRentThreadLocal.get()==null)
			return "Error";
		else
		 return proratedPetRentThreadLocal.get();
	}
	
	public static void setPetRent(String petRent) {
		petRentThreadLocal.set(petRent);
	}
	
	public static String getPetRent() {
		if(petRentThreadLocal.get()==null)
			return "Error";
		else
		 return petRentThreadLocal.get();
	}
	public static void setTotalPetRentWithTax(String totalPetRentWithTax) {
		totalPetRentWithTaxThreadLocal.set(totalPetRentWithTax);
	}
	
	public static String getTotalPetRentWithTax() {
		if(totalPetRentWithTaxThreadLocal.get()==null)
			return "Error";
		else
		 return totalPetRentWithTaxThreadLocal.get();
	}
	public static void setPetOneTimeNonRefundableFee(String petOneTimeNonRefundableFee) {
		petOneTimeNonRefundableFeeThreadLocal.set(petOneTimeNonRefundableFee);
	}
	
	public static String getPetOneTimeNonRefundableFee() {
		if(petOneTimeNonRefundableFeeThreadLocal.get()==null)
		return "Error";
		else
		 return petOneTimeNonRefundableFeeThreadLocal.get();
	}
	public static void setSmartHomeAgreementCheck(boolean smartHomeAgreementCheck) {
		smartHomeAgreementCheckThreadLocal.set(smartHomeAgreementCheck);
	}
	
	public static boolean getSmartHomeAgreementCheck() {
		if(smartHomeAgreementCheckThreadLocal.get()==null)
			return false;
		else
		 return smartHomeAgreementCheckThreadLocal.get();
	}
	public static void setSmartHomeAgreementFee(String smartHomeAgreementFee) {
		smartHomeAgreementFeeThreadLocal.set(smartHomeAgreementFee);
	}
	
	public static String getSmartHomeAgreementFee() {
		if(smartHomeAgreementFeeThreadLocal.get()==null)
			return "Error";
		else
		 return smartHomeAgreementFeeThreadLocal.get();
	}
	public static void setEarlyTermination(String earlyTermination) {
		earlyTerminationThreadLocal.set(earlyTermination);
	}
	
	public static String getEarlyTermination() {
		if(earlyTerminationThreadLocal.get()==null)
			return "Error";
		else
		 return earlyTerminationThreadLocal.get();
	}
	public static void setTotalMonthlyRentWithTax(String totalMonthlyRentWithTax) {
		totalMonthlyRentWithTaxThreadLocal.set(totalMonthlyRentWithTax);
	}
	
	public static String getTotalMonthlyRentWithTax() {
		if(totalMonthlyRentWithTaxThreadLocal.get()==null)
			return "Error";
		else
		 return totalMonthlyRentWithTaxThreadLocal.get();
	}
	public static void setOnePercentOfRentAmount(String onePercentOfRentAmount) {
		OnePercentOfRentAmountThreadLocal.set(onePercentOfRentAmount);
	}
	
	public static String getOnePercentOfRentAmount() {
		if(OnePercentOfRentAmountThreadLocal.get()==null)
			return "Error";
		else
		 return OnePercentOfRentAmountThreadLocal.get();
	}
	public static void setOnePercentOfProrateRentAmount(String onePercentOfProrateRentAmount) {
		OnePercentOfProrateRentAmountThreadLocal.set(onePercentOfProrateRentAmount);
	}
	
	public static String getOnePercentOfProrateRentAmount() {
		if(OnePercentOfProrateRentAmountThreadLocal.get()==null)
			return "Error";
		else
		 return OnePercentOfProrateRentAmountThreadLocal.get();
	}
	public static void setOnePercentOfPetRentAmount(String OnePercentOfPetRentAmount) {
		OnePercentOfPetRentAmountThreadLocal.set(OnePercentOfPetRentAmount);
	}
	
	public static String getOnePercentOfPetRentAmount() {
		if(OnePercentOfPetRentAmountThreadLocal.get()==null)
			return "Error";
		 return OnePercentOfPetRentAmountThreadLocal.get();
	}
	public static void setOnePercentOfProratePetRentAmount(String OnePercentOfProratePetRentAmount) {
		OnePercentOfProratePetRentAmountThreadLocal.set(OnePercentOfProratePetRentAmount);
	}
	
	public static String getOnePercentOfProratePetRentAmount() {
		if(OnePercentOfProratePetRentAmountThreadLocal.get()==null)
			return "Error";
		else
		 return OnePercentOfProratePetRentAmountThreadLocal.get();
	}
	
	public static boolean getResidentBenefitsPackageTaxAvailabilityCheck() {
		if(residentBenefitsPackageTaxAvailabilityCheckThreadLocal.get()==null)
			return false;
		else
		 return residentBenefitsPackageTaxAvailabilityCheckThreadLocal.get();
	}
	public static void setResidentBenefitsPackageTaxAvailabilityCheck(boolean ResidentBenefitsPackageTaxAvailabilityCheck) {
		residentBenefitsPackageTaxAvailabilityCheckThreadLocal.set(ResidentBenefitsPackageTaxAvailabilityCheck);
	}
	
	public static void setResidentBenefitsPackageTaxAmount(String residentBenefitsPackageTaxAmount) {
		residentBenefitsPackageTaxAmountThreadLocal.set(residentBenefitsPackageTaxAmount);
	}
	
	public static String getResidentBenefitsPackageTaxAmount() {
		if(residentBenefitsPackageTaxAmountThreadLocal.get()==null)
			return "Error";
		else
		 return residentBenefitsPackageTaxAmountThreadLocal.get();
	}
	public static void setPortfolioName(String portfolioName) {
		portfolioNameThreadLocal.set(portfolioName);
	}
	
	public static String getPortfolioName() {
		if(portfolioNameThreadLocal.get()==null)
			return "Error";
		else
		 return portfolioNameThreadLocal.get();
	}
	public static void setPortfolioType(String portfolioType) {
		portfolioTypeThreadLocal.set(portfolioType);
	}
	
	public static String getPortfolioType() {
		if(portfolioTypeThreadLocal.get()==null)
			return "Error";
		else
		 return portfolioTypeThreadLocal.get();
	}
	public static boolean getIncrementRentFlag() {
		if(incrementRentFlagThreadLocal.get()==null)
			return false;
		else
		 return incrementRentFlagThreadLocal.get();
	}
	public static void setIncrementRentFlag(boolean incrementRentFlag) {
		incrementRentFlagThreadLocal.set(incrementRentFlag);
	}
	public static void setIncreasedRent_amount(String increasedRent_amount) {
		increasedRent_amountThreadLocal.set(increasedRent_amount);
	}
	
	public static String getIncreasedRent_amount() {
		if(increasedRent_amountThreadLocal.get()==null)
			return "Error";
		else
		 return increasedRent_amountThreadLocal.get();
	}
	
	public static void setIncreasedRent_newStartDate(String increasedRent_newStartDate) {
		increasedRent_newStartDateThreadLocal.set(increasedRent_newStartDate);
	}
	
	public static String getIncreasedRent_newStartDate() {
		if(increasedRent_newStartDateThreadLocal.get()==null)
			return "Error";
		else
		 return increasedRent_newStartDateThreadLocal.get();
	}
	public static boolean getPetRentTaxFlag() {
		if(petRentTaxFlagThreadLocal.get()==null)
			return false;
		else
		 return petRentTaxFlagThreadLocal.get();
	}
	public static void setPetRentTaxFlag(boolean petRentTaxFlag) {
		petRentTaxFlagThreadLocal.set(petRentTaxFlag);
	}
	
	public static void setProrateRUBS(String prorateRUBS) {
		prorateRUBSThreadLocal.set(prorateRUBS);
	}
	
	public static String getProrateRUBS() {
		if(prorateRUBSThreadLocal.get()==null)
			return "Error";
		else
		 return prorateRUBSThreadLocal.get();
	}
	
	public static boolean getResidentUtilityBillFlag() {
		if(residentUtilityBillFlagThreadLocal.get()==null)
			return false;
		else
		 return residentUtilityBillFlagThreadLocal.get();
	}
	public static void setResidentUtilityBillFlag(boolean residentUtilityBillFlag) {
		residentUtilityBillFlagThreadLocal.set(residentUtilityBillFlag);
	}
	
	public static void setRUBS(String RUBS) {
		rUBSThreadLocal.set(RUBS);
	}
	
	public static String getRUBS() {
		if(rUBSThreadLocal.get()==null)
			return "Error";
		else
		 return rUBSThreadLocal.get();
	}
	
	public static void setIncreasedRent_previousRentEndDate(String increasedRent_previousRentEndDate) {
		increasedRent_previousRentEndDateThreadLocal.set(increasedRent_previousRentEndDate);
	}
	
	public static String getIncreasedRent_previousRentEndDate() {
		if(increasedRent_previousRentEndDateThreadLocal.get()==null)
			return "Error";
		else
		 return increasedRent_previousRentEndDateThreadLocal.get();
	}
	public static void setCaptiveInsurenceATXFee(String captiveInsurenceATXFee) {
		captiveInsurenceATXFeeThreadLocal.set(captiveInsurenceATXFee);
	}
	
	public static String getCaptiveInsurenceATXFeeThreadLocal() {
		if(captiveInsurenceATXFeeThreadLocal.get()==null)
			return "Error";
		else
		 return captiveInsurenceATXFeeThreadLocal.get();
	}
	
	public static boolean getCaptiveInsurenceATXFlag() {
		if(captiveInsurenceATXFlagThreadLocal.get()==null)
			return false;
		else
		 return captiveInsurenceATXFlagThreadLocal.get();
	}
	public static void setCaptiveInsurenceATXFlag(boolean captiveInsurenceATXFlag) {
		captiveInsurenceATXFlagThreadLocal.set(captiveInsurenceATXFlag);
	}
	
	public static void setDueDay_GreaterOf(String dueDay_GreaterOf) {
		dueDay_GreaterOfThreadLocal.set(dueDay_GreaterOf);
	}
	
	public static String getDueDay_GreaterOf() {
		if(dueDay_GreaterOfThreadLocal.get()==null)
			return "Error";
		else
		 return dueDay_GreaterOfThreadLocal.get();
	}
	public static void setPercentage(String percentage) {
		percentageThreadLocal.set(percentage);
	}
	
	public static String getPercentage() {
		if(percentageThreadLocal.get()==null)
			return "Error";
		else
		 return percentageThreadLocal.get();
	}
	public static void setFlatFee(String flatFee) {
		flatFeeThreadLocal.set(flatFee);
	}
	
	public static String getFlatFee() {
		if(flatFeeThreadLocal.get()==null)
			return "Error";
		else
		 return flatFeeThreadLocal.get();
	}
	public static void setInitialFeeAmount(String initialFeeAmount) {
		initialFeeAmountThreadLocal.set(initialFeeAmount);
	}
	
	public static String getInitialFeeAmount() {
		if(initialFeeAmountThreadLocal.get()==null)
			return "Error";
		else
		 return initialFeeAmountThreadLocal.get();
	}
	public static void setPerDayFeeAmount(String perDayFeeAmount) {
		perDayFeeAmountThreadLocal.set(perDayFeeAmount);
	}
	
	public static String getPerDayFeeAmount() {
		if(perDayFeeAmountThreadLocal.get()==null)
			return "Error";
		else
		 return perDayFeeAmountThreadLocal.get();
	}
	public static void setAdditionalLateChargesLimit(String additionalLateChargesLimit) {
		additionalLateChargesLimitThreadLocal.set(additionalLateChargesLimit);
	}
	
	public static String getAdditionalLateChargesLimit() 
	{
		if(additionalLateChargesLimitThreadLocal.get()==null)
			return "Error";
		else
		 return additionalLateChargesLimitThreadLocal.get();
	}
	public static void setDueDay_initialFee(String dueDay_initialFee) {
		dueDay_initialFeeThreadLocal.set(dueDay_initialFee);
	}
	
	public static String getDueDay_initialFee() {
		if(dueDay_initialFeeThreadLocal.get()==null)
			return "Error";
		else
		 return dueDay_initialFeeThreadLocal.get();
	}
	public static void setStartDateInPW(String startDateInPW) {
		startDateInPWThreadLocal.set(startDateInPW);
	}
	
	public static String getStartDateInPW() {
		if(startDateInPWThreadLocal.get()==null)
			return "Error";
		else
		 return startDateInPWThreadLocal.get();
	}
	public static void setEndDateInPW(String endDateInPW) {
		endDateInPWThreadLocal.set(endDateInPW);
	}
	
	public static String getEndDateInPW() {
		if(endDateInPWThreadLocal.get()==null)
			return "Error";
		else
		 return endDateInPWThreadLocal.get();
	}
	public static void setPetSecurityDeposit(String petSecurityDeposit) {
		petSecurityDepositThreadLocal.set(petSecurityDeposit);
	}
	
	public static String getPetSecurityDeposit() {
		if(petSecurityDepositThreadLocal.get()==null)
			return "Error";
		else
		 return petSecurityDepositThreadLocal.get();
	}
	
	public static boolean getHVACFilterOptOutAddendum() {
		if(HVACFilterOptOutAddendumThreadLocal.get()==null)
			return false;
		else
		 return HVACFilterOptOutAddendumThreadLocal.get();
	}
	public static void setHVACFilterOptOutAddendum(boolean HVACFilterOptOutAddendum) {
		HVACFilterOptOutAddendumThreadLocal.set(HVACFilterOptOutAddendum);
	}
	public static boolean getRBPOptOutAddendumCheck() {
		if(RBPOptOutAddendumCheckThreadLocal.get()==null)
			return false;
		else
		 return RBPOptOutAddendumCheckThreadLocal.get();
	}
	public static void setRBPOptOutAddendumCheck(boolean RBPOptOutAddendumCheck) {
		RBPOptOutAddendumCheckThreadLocal.set(RBPOptOutAddendumCheck);
	}
	public static boolean getFloridaLiquidizedAddendumOption1Check() {
		if(floridaLiquidizedAddendumOption1CheckThreadLocal.get()==null)
			return false;
		else
		 return floridaLiquidizedAddendumOption1CheckThreadLocal.get();
	}
	public static void setFloridaLiquidizedAddendumOption1Check(boolean floridaLiquidizedAddendumOption1Check) {
		floridaLiquidizedAddendumOption1CheckThreadLocal.set(floridaLiquidizedAddendumOption1Check);
	}
	public static void setProrateResidentBenefitPackage(String prorateResidentBenefitPackage) {
		prorateResidentBenefitPackageThreadLocal.set(prorateResidentBenefitPackage);
	}
	
	public static String getProrateResidentBenefitPackage() {
		if(prorateResidentBenefitPackageThreadLocal.get()==null)
			return "Error";
		else
		 return prorateResidentBenefitPackageThreadLocal.get();
	}
	
	public static void setArizonaCityFromBuildingAddress(String arizonaCityFromBuildingAddress) {
		arizonaCityFromBuildingAddressThreadLocal.set(arizonaCityFromBuildingAddress);
	}
	
	public static String getArizonaCityFromBuildingAddress() {
		if(arizonaCityFromBuildingAddressThreadLocal.get()==null)
			return "Error";
		else
		 return arizonaCityFromBuildingAddressThreadLocal.get();
	}
	public static void setArizonaRentCode(String arizonaRentCode) {
		arizonaRentCodeThreadLocal.set(arizonaRentCode);
	}
	
	public static String getArizonaRentCode() {
		if(arizonaRentCodeThreadLocal.get()==null)
			return "Error";
		else
		 return arizonaRentCodeThreadLocal.get();
	}
	public static boolean getArizonaCodeAvailable() {
		if(arizonaCodeAvailableThreadLocal.get()==null)
			return false;
		else
		 return arizonaCodeAvailableThreadLocal.get();
	}
	public static void setArizonaCodeAvailable(boolean arizonaCodeAvailable) {
		arizonaCodeAvailableThreadLocal.set(arizonaCodeAvailable);
	}
	public static void setPDFFormatType(String PDFFormatType) {
		PDFFormatTypeThreadLocal.set(PDFFormatType);
	}
	
	public static String getPDFFormatType() {
		if(PDFFormatTypeThreadLocal.get()==null)
			return "Error";
		else
		 return PDFFormatTypeThreadLocal.get();
	}
	public static void setStatusID(int statusID) {
		statusIDThreadLocal.set(statusID);
	}
	
	public static int getStatusID() {
		if(statusIDThreadLocal.get()==null)
			return 0;
		 return statusIDThreadLocal.get();
	}
	
	public static void setPortfolioTypeForClientType(String PDFFormatType) {
		portfolioTypeForClientTypeThreadLocal.set(PDFFormatType);
	}
	
	public static String getPortfolioTypeForClientType() {
		if(portfolioTypeForClientTypeThreadLocal.get()==null)
			return "Error";
		else
		 return portfolioTypeForClientTypeThreadLocal.get();
	}
	
	
	//Array getter and setter methods
    public static ArrayList<String> getPetTypes() {
        return petTypeThreadLocal.get();
    }
	
	public static void setPetTypes(ArrayList<String> petTypes) {
        petTypeThreadLocal.set(petTypes);
    }
	
	public static ArrayList<String> getPetBreeds() {
        return petBreedThreadLocal.get();
    }
	
	public static void setPetBreeds(ArrayList<String> petBreed) {
		petBreedThreadLocal.set(petBreed);
    }

	public static ArrayList<String> getPetWeights() {
        return petWeightThreadLocal.get();
    }
	
	public static void setPetWeights(ArrayList<String> petWeight) {
		petWeightThreadLocal.set(petWeight);
    }
	
	public static ArrayList<String> getServiceAnimalPetType() {
        return serviceAnimalPetTypeThreadLocal.get();
    }
	
	public static void setServiceAnimalPetType(ArrayList<String> servicePetTypes) {
		serviceAnimalPetTypeThreadLocal.set(servicePetTypes);
    }
	
	public static ArrayList<String> getServiceAnimalPetBreeds() {
        return serviceAnimalPetBreedThreadLocal.get();
    }
	
	public static void setServiceAnimalPetBreeds(ArrayList<String> servicePetBreed) {
		serviceAnimalPetBreedThreadLocal.set(servicePetBreed);
    }
	
	public static ArrayList<String> getServiceAnimalPetWeights() {
        return serviceAnimalPetWeightThreadLocal.get();
    }
	
	public static void setServiceAnimalPetWeights(ArrayList<String> servicePetWeight) {
		serviceAnimalPetWeightThreadLocal.set(servicePetWeight);
    }
	
	
	
	  // Getter method for moveInCharges
    public static String[][] getMoveInCharges() {
        return moveInChargesThreadLocal.get();
    }

    // Setter method for moveInCharges
    public static void setMoveInCharges(String[][] moveInCharges) {
        moveInChargesThreadLocal.set(moveInCharges);
    }
	
    // Getter method for autoCharges
    public static String[][] getautoCharges() {
        return autoChargesThreadLocal.get();
    }

    // Setter method for autoCharges
    public static void setautoCharges(String[][] autoCharges) {
    	autoChargesThreadLocal.set(autoCharges);
    }
    
    
	public static File getLastModified(String fileName) throws Exception {
	    File directory = new File(AppConfig.downloadFilePath);
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null) {
	        for (File file : files) {
	            if (file.getName().startsWith(fileName) && file.lastModified() > lastModifiedTime) {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }

	    return chosenFile;
	}

	public static String convertDate(String dateRaw) throws Exception {
		try {
			SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd, yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
			Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
			System.out.println(format2.format(date));
			return format2.format(date).toString();
		} catch (Exception e) {
			try {
				SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd yyyy");
				SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
				Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
				System.out.println(format2.format(date));
				return format2.format(date).toString();
			} catch (Exception e2) {
				if (dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("st")
						|| dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("nd")
						|| dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("th"))
					dateRaw = dateRaw.trim().replaceAll(" +", " ").replace("st", "").replace("nd", "").replace("th",
							"");
				try {
					SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd yyyy");
					SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
					Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
					System.out.println(format2.format(date));
					return format2.format(date).toString();
				} catch (Exception e3) {
					try {
						SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd,yyyy");
						SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
						Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
						System.out.println(format2.format(date));
						return format2.format(date).toString();
					} catch (Exception e4) {
						try {
							SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd. yyyy");
							SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
							Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
							System.out.println(format2.format(date));
							return format2.format(date).toString();
						} catch (Exception e5) {
							try {
								SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd ,yyyy");
								SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
								Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
								System.out.println(format2.format(date));
								return format2.format(date).toString();
							} catch (Exception e6) {
								return "";
							}
						}

					}
				}
			}
		}
	}

	public static String firstDayOfMonth(String date, int month) throws Exception {
		// String string = "02/05/2014"; //assuming input
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = sdf.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		// if(portfolioType=="MCH")
		c.add(Calendar.MONTH, month); // adding a month directly - gives the start of next month.
		// else c.add(Calendar.MONTH, 2);
		c.set(Calendar.DAY_OF_MONTH, 01);
		String firstDate = sdf.format(c.getTime());
		System.out.println(firstDate);
		return firstDate;
	}

	public static String getCurrentDateTime() {
		currentTime = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(dtf.format(now));
		currentTime = dtf.format(now);
		return currentTime;
	}

	public static String lastDateOfTheMonth(String date) throws Exception {
		// String date =RunnerClass.convertDate("January 1, 2023");
		LocalDate lastDayOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
				.with(TemporalAdjusters.lastDayOfMonth());
		String newDate = new SimpleDateFormat("MM/dd/yyyy")
				.format(new SimpleDateFormat("yyyy-MM-dd").parse(lastDayOfMonth.toString()));
		return newDate;
	}

	public static String monthDifference(String date1, String date2) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date firstDate = sdf.parse(date1);
		Date secondDate = sdf.parse(date2);

		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("MM/dd/yyyy")
				.parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
				// .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
				.toFormatter();

		String x = Duration.between(LocalDate.parse(date1, formatter), LocalDate.parse(date2, formatter)).toString();
		return "";
	}

	public static String getCurrentDate() {
		currentTime = "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(dtf.format(now));
		currentTime = dtf.format(now);
		return currentTime;
	}

	public static boolean onlyDigits(String str) {
		str = str.replace(",", "").replace(".", "").trim();
		if (str == "")
			return false;
		int numberCount = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				numberCount++;
				// return true;
			}
		}
		if (numberCount == str.length())
			return true;
		else
			return false;
	}

	public static int nthOccurrence(String str1, String str2, int n) {

		String tempStr = str1;
		int tempIndex = -1;
		int finalIndex = 0;
		for (int occurrence = 0; occurrence < n; ++occurrence) {
			tempIndex = tempStr.indexOf(str2);
			if (tempIndex == -1) {
				finalIndex = 0;
				break;
			}
			tempStr = tempStr.substring(++tempIndex);
			finalIndex += tempIndex;
		}
		return --finalIndex;
	}

	public static String extractNumber(String str) {
		// String str = "26.23,for";
		StringBuilder myNumbers = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (Character.isDigit(str.charAt(i)) || (String.valueOf(c).equals(".") && i != str.length() - 1)) {
				myNumbers.append(str.charAt(i));
				// System.out.println(str.charAt(i) + " is a digit.");
			} else {
				// System.out.println(str.charAt(i) + " not a digit.");
			}
		}
		// System.out.println("Your numbers: " + myNumbers.toString());
		return myNumbers.toString();
	}

	public static String replaceConsecutiveCommas(String input) {
        // Define the regular expression pattern to match consecutive commas
        String regex = ",+";
        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        // Create a matcher object
        Matcher matcher = pattern.matcher(input);
        // Replace consecutive commas with a single comma
        String result = matcher.replaceAll(",");
        
        
        String regex2 = ",+$";
        // Replace trailing commas with an empty string
       result = result.replaceAll(regex2, "");
        return result;
    }
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static void processAfterBuildingIsSelected(WebDriver driver,String SNo,String company, String buildingAbbreviation, String ownerName,String failedReason)
			throws Exception {
		if (PropertyWare.downloadLeaseAgreement(driver,buildingAbbreviation, ownerName) == true) {

			if (PDFReader.readPDFPerMarket(company,SNo) == true) {
				PropertyWare_updateValues.configureValues(driver,company,buildingAbbreviation,SNo);
				PropertyWare_AutoCharges.addingAutoCharges(driver,buildingAbbreviation,SNo);
				PropertyWare_OtherInformation.addOtherInformation(driver,company,buildingAbbreviation);
				
				failedReason = getFailedReason();
				// Update Completed Status
				if (failedReason == null||failedReason.equals(""))
					failedReason = "";
				else if (getFailedReason().charAt(0) == ',')
					failedReason = failedReason.substring(1);
				String updateSuccessStatus = "";
				if (getStatusID() == 0)
					updateSuccessStatus = "Update [Automation].LeaseInfo Set Status ='Completed', StatusID=4,NotAutomatedFields='"
							+ failedReason + "',LeaseCompletionDate= getDate() where BuildingName like '%"
							+ buildingAbbreviation + "%'";
				else
					updateSuccessStatus = "Update [Automation].LeaseInfo Set Status ='Review', StatusID=5,NotAutomatedFields='"
							+ failedReason + "',LeaseCompletionDate= getDate() where BuildingName like '%"
							+ buildingAbbreviation + "%'";
				DataBase.updateTable(updateSuccessStatus);
			} else {
				failedReason = getFailedReason();
				if (failedReason == null||failedReason.equals(""))
					failedReason = "";
				else if (getFailedReason().charAt(0) == ',')
					failedReason = failedReason.substring(1);
				String updateSuccessStatus = "Update [Automation].LeaseInfo Set Status ='Failed', StatusID=3,NotAutomatedFields='"
						+ failedReason + "',LeaseCompletionDate= getDate() where BuildingName like '%"
						+ buildingAbbreviation + "%'";
				DataBase.updateTable(updateSuccessStatus);
			}

		} else {
			failedReason = getFailedReason();
			if (failedReason == null||failedReason.equals(""))
				failedReason = "";
			else if (getFailedReason().charAt(0) == ',')
				failedReason = failedReason.substring(1);
			String updateSuccessStatus = "Update [Automation].LeaseInfo Set Status ='Failed', StatusID=3,NotAutomatedFields='"
					+ failedReason + "',LeaseCompletionDate= getDate() where BuildingName like '%"
					+ buildingAbbreviation + "%'";
			DataBase.updateTable(updateSuccessStatus);
		}
	}
	
	
	public static int getDaysInMonth(String dateStr) {
        // Split the date string into month, day, and year
        String[] parts = dateStr.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[2]);

        // Create a Calendar instance
        Calendar calendar = Calendar.getInstance();

        // Set the year and month in the calendar
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Calendar months are zero-based

        // Get the maximum value for the day of the month
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        return daysInMonth;
    }

	public static boolean hasSpecialCharacters(String inputString) {
		// Define a regular expression pattern to match characters other than digits,
		// dots, and commas
		Pattern pattern = Pattern.compile("[^0-9.,]");

		// Use a Matcher to find any match in the input string
		Matcher matcher = pattern.matcher(inputString);

		return matcher.find();
	}

	@DataProvider(name = "testData", parallel = true)
	public Object[][] testData() {
		try {
			DataBase.getBuildingsList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pendingRenewalLeases;
	}

}