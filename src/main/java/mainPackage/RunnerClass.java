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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RunnerClass {
	public static String[][] pendingRenewalLeases;
	public static Alert alert;

	public static ChromeOptions options;
	public static String[][] completedBuildingList;
	public static int updateStatus;
	public static String[] statusList;
	public static String currentDate = "";
	public static String downloadFilePath;
	public static String currentTime;


	private static ThreadLocal<ChromeDriver> driverThreadLocal = new ThreadLocal<ChromeDriver>();

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
	public void testMethod(String SNo,String company, String buildingAbbreviation, String ownerName,String LeaseEntityID) throws Exception {
		System.out.println(" Building -- " + buildingAbbreviation + "  Company -- "+ company);
		int statusID = 0;
		//GetterAndSetterClass.setStatusID(statusID);
		String failedReason = "";
		String completeBuildingAbbreviation="";
		ChromeDriver driver = driverThreadLocal.get();

		try {
			FileUtils.cleanDirectory(new File(AppConfig.downloadFilePath));
		} catch (Exception e) {} 
		
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
			// DataBase.insertData(buildingAbbreviation, "Started", 6);
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
				if (PropertyWare.selectBuilding(driver,company, ownerName,LeaseEntityID) == true) {
					
					if(ClearInputElements.clearAllFields(driver,company)==true) {
						failedReason = GetterAndSetterClass.getFailedReason();
						if (failedReason == null || failedReason.equalsIgnoreCase(""))
							failedReason = "";
						else if (failedReason.charAt(0) == ',')
							failedReason = failedReason.substring(1);
						String updateSuccessStatus = "Update [Automation].ClearCompletedLeaseRenewalsFields Set AutomationStatus ='Completed',AutomationCompletionDate= getDate(),AutomationNotes='"
									+ failedReason + "' where ID ="+ SNo ;
						
						DataBase.updateTable(updateSuccessStatus);
					}
					else {
						failedReason = GetterAndSetterClass.getFailedReason();
						if (failedReason == null || failedReason.equalsIgnoreCase(""))
							failedReason = "";
						else if (failedReason.charAt(0) == ',')
							failedReason = failedReason.substring(1);
						String updateSuccessStatus = "Update [Automation].ClearCompletedLeaseRenewalsFields Set AutomationStatus ='Failed',AutomationCompletionDate= getDate(),AutomationNotes='"
								+ failedReason + "' where ID ="+ SNo ;
						DataBase.updateTable(updateSuccessStatus);
					}
					
					
				}
				else {
					failedReason = GetterAndSetterClass.getFailedReason();
					if (failedReason == null || failedReason.equalsIgnoreCase(""))
						failedReason = "";
					else if (failedReason.charAt(0) == ',')
						failedReason = failedReason.substring(1);
					String updateSuccessStatus = "Update [Automation].ClearCompletedLeaseRenewalsFields Set AutomationStatus ='Failed',AutomationCompletionDate= getDate(),AutomationNotes='"
							+ failedReason + "' where ID ="+ SNo ;
					DataBase.updateTable(updateSuccessStatus);
				}
					

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				GetterAndSetterClass.setFailedReason(null);
				GetterAndSetterClass.setFileName(null);
				GetterAndSetterClass.setStartDate(null);
				GetterAndSetterClass.setEndDate(null);
				GetterAndSetterClass.setMonthlyRent(null);
				GetterAndSetterClass.setMonthlyRentTaxAmount(null);
				GetterAndSetterClass.setMonthlyRentTaxFlag(false);
				GetterAndSetterClass.setresidentBenefitsPackageAvailabilityCheckFlag(false);
				GetterAndSetterClass.setProrateRent(null);
				GetterAndSetterClass.setProrateRentDate(null);
				GetterAndSetterClass.setresidentBenefitsPackage(null);
				GetterAndSetterClass.setTotalMonthlyRentWithTax(null);
				GetterAndSetterClass.setResidentBenefitsPackageTaxAvailabilityCheck(false);
				GetterAndSetterClass.setResidentBenefitsPackageTaxAmount(null);
				GetterAndSetterClass.setStartDateInPW(null);
				GetterAndSetterClass.setEndDateInPW(null);
				GetterAndSetterClass.setRBPOptOutAddendumCheck(false);
				GetterAndSetterClass.setArizonaCityFromBuildingAddress(null);
				GetterAndSetterClass.setArizonaRentCode(null);
				GetterAndSetterClass.setArizonaCodeAvailable(false);
				GetterAndSetterClass.setStatusID(0);
				GetterAndSetterClass.setOldLeaseStartDate_ProrateRent(null);
				GetterAndSetterClass.setOldLeaseEndDate_ProrateRent(null);
				GetterAndSetterClass.setNewLeaseEndDate_ProrateRent(null);
				GetterAndSetterClass.setProrateResidentBenefitPackage(null);  //For other portfolios, it should be added as second full month in Auto Charges 
				GetterAndSetterClass.setprorateEscalationStartDate(null);
				GetterAndSetterClass.setprorateEscalationEndDate(null);
				GetterAndSetterClass.setprorateEscalationAmount(null);
				GetterAndSetterClass.setIncrementRentFlag(false);
				GetterAndSetterClass.setIncreasedRentAmounts(null);
				GetterAndSetterClass.setIncreasedRentDates(null);
				GetterAndSetterClass.setleaseStatus(false);
				GetterAndSetterClass.setRBPNoChangeRequired(false);
				GetterAndSetterClass.setOldRBPAmount(null);
				GetterAndSetterClass.setFirstFullMonth(null);
				GetterAndSetterClass.setlastDayOfTheStartDate(null);
				setautoCharges(null);
				try
				{
			//	String query = "drop table if exists automation.LeaseReneWalsAutoChargesConfiguration_"+SNo;
			//	DataBase.updateTable(query);
				}
				catch(Exception e) {}
				driver.quit();
			}
		}

	}
	@AfterSuite
    public void sendMail(){
    	try {
			CreateExcelandSendMail.createExcelFileWithProcessedData();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
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
	            if (file.getName().replace(" ","").replace("_","").startsWith(fileName.replace(" ","").replace("_","")) && file.lastModified() > lastModifiedTime) {
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

	 public static String dateMinusOneDay(String date) throws Exception {
	    	try {
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		        LocalDate startDate = LocalDate.parse(date, formatter);
		        LocalDate newDate = startDate.minusDays(1); // Subtract one day
		        System.out.println("Date minus one day: " + newDate.format(formatter));
				return newDate.format(formatter);
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		return "Error";
	    	}
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
			
			e.printStackTrace();
		}
		return pendingRenewalLeases;
	}
	
	
	
	

}