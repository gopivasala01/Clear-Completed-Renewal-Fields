package mainPackage;

public class AppConfig 
{
		public static boolean saveButtonOnAndOff= true;
		public static String username= "mds0418@gmail.com";
		public static String password="KRm#V39fecMDGg#";
		public static String URL="https://app.propertyware.com/pw/login.jsp";
		public static String[] Buildings= {"SABA2399"};
		public static String[] Names= {"Baxter - Hernandez"};
		
		public static String pdfImage = "C:\\SantoshMurthyP\\Tessaract Images\\";
		
	
	    public static String pendingRenewalLeases = "Select ID,Company,Building,LeaseName,LeaseEntityID from Automation.RenewalProrationRentsUpdate WHERE CAST(AsOFDate AS DATE) = CAST(GETDATE()-1 AS DATE) and AutomationStatus = 'Pending' ";
			   //"Select  Company,buildingabbreviation,LeaseName from LeaseFact_dashboard where DATEDIFF(month, StartDate, GETDATE()) = 1 and Company ='Florida'  order by id asc";
	   //public static String lastMonthLeases1 = "Select  Company,buildingabbreviation,LeaseName from LeaseFact_dashboard where DATEDIFF(month, StartDate, GETDATE()) = 1 and Company ='Alabama'  order by id asc";
		//public static String lastMonthLeases = "Select  Company,buildingabbreviation,LeaseName from [Automation].[leaseAuditAutomation] where notes = 'Values did not match'";
	    public static String getBuildingsWithStatusforCurrentDay = "Select ID,Company,Building,LeaseName,LeaseEntityID,AutomationStatus,AutomationNotes from Automation.RenewalProrationRentsUpdate WHERE CAST(AsOFDate AS DATE) = CAST(GETDATE()-1 AS DATE)";
	    
	    public static String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
	    public static String downloadFilePath = "C:\\SantoshMurthyP\\Lease Audit Automation";
	    public static String[] LeaseAgreementFileNames = {"RT Renewal Signed","RT-RENEWAL","RT - RENEWAL","RT_Full_Lease","Full Lease -","IAG_full_lease","-IAG_FULL_LEASE","-IAG_full_lease","IAG-[Full_Lease_Renewal]","IAG-PRX-[Full_Lease_Renewal]","IAG_Full_Lease_Renewal","RENEWAL","renewal_","Renewal","Full_Lease","Full"};
	    
	    public static String leasePageURL = "https://app.propertyware.com/pw/leases/lease_detail.do?entityID=";
	    
	    public static  String PDFFormatConfirmationText = "The parties to this lease are:";
		public static  String PDFFormat2ConfirmationText = "THIS RESIDENTIAL LEASE AGREEMENT";
		
		//Mail credentials
		   public static String fromEmail = "bireports@beetlerim.com";
		   public static String fromEmailPassword = "Welcome@123";
		   
		   public static String toEmail ="gopi.v@beetlerim.com,naveen.p@beetlerim.com";
		   public static String CCEmail = "naveen.p@beetlerim.com";
		   
		   public static String mailSubject = "Renewal Proration Rents Update -  ";
		   
		   public static String excelFileLocation = "C:\\SantoshMurthyP\\Lease Audit Automation\\Excel File";
		   
		  // public static String getAutoCharges = "Select ChargeCode, Amount, autoCharge_StartDate,EndDate,Description from automation.LeaseCloseOutsChargeChargesConfiguration_"+RunnerClass.buildingAbbreviation+" Where  AutoCharge=1";
		   
		  // public static String getMoveInCharges = "Select ChargeCode, Amount, StartDate,EndDate,Description from automation.LeaseCloseOutsChargeChargesConfiguration_"+RunnerClass.buildingAbbreviation+" Where MoveInCharge =1";
		   
		   
		   public static String[] IAGClientList = {"510.","AVE.","BTH.","CAP.","FOR.","HRG.","HS.","MAN.","MCH.","OFF.","PIN.","RF.","SFR3.","TH.","HH.","Lofty.Ai","TA.","SA.","68V.","ATX.","LT.LOFTY","HOH.","LT.","PRX."};
		
	   public static String getMonthlyRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "40010 - Rent Income";
		   case "California":
			   return "40010 - Rent Income";
		   case "California PFW":
			   return "40010 - Rent Income";
		   case "Chattanooga":
			   return "40010 - Rent Income";
		   case "Chicago PFW":
			   return "40010 - Rent Income";
		   case "Colorado Springs":
			   return "40010 - Rent Income";
		   case "Kansas City":
			   return "40010 - Rent Income";
		   case "Houston":
			   return "40010 - Rent Income";
		   case "Maine":
			   return "40010 - Rent Income";
		   case "Savannah":
			   return "40010 - Rent Income";
		   case "North Carolina":
			   return "40010 - Rent Income";
		   case "Alabama":
			   return "40010 - Rent Income";
		   case "Arkansas":
			   return "40010 - Rent Income";
		   case "Dallas/Fort Worth":
			   return "40010 - Rent Income";
		   case "Indiana":
			   return "40010 - Rent Income";
		   case "Little Rock":
			   return "40010 - Rent Income";
		   case "San Antonio":
			   return "40010 - Rent Income";
		   case "Tulsa":
			   return "40010 - Rent Income";
		   case "Georgia":
			   return "40010 - Rent Income";
		   case "OKC":
			   return "40010 - Rent Income";
		   case "South Carolina":
			   return "40010 - Rent Income";
		   case "Florida":
			   return "40010 - Rent Income";
		   case "Tennessee":
			   return "40010 - Rent Income";
		   case "New Mexico":
			   return "40010 - Rent Income";
		   case "Ohio":
			   return "40010 - Rent Income";
		   case "Pennsylvania":
			   return "40010 - Rent Income";
		   case "Lake Havasu":
			   return "40010 - Rent Income";
		   case "Columbia - St Louis":
			   return "40010 - Rent Income";
		   case "Maryland":
			   return "40010 - Rent Income";
		   case "Virginia":
			   return "40010 - Rent Income";
		   case "Boise":
			   return "40010 - Rent Income";
		   case "Idaho Falls":
			   return "40010 - Rent Income";
		   case "Utah":
			   return "40010 - Rent Income";
		   case "Spokane":
			   return "40010 - Rent Income";
		   case "Washington DC":
			   return "40010 - Rent Income";
		   case "Hawaii":
			   if(GetterAndSetterClass.getMonthlyRentTaxFlag()==false)
			   return "40010 - Rent Income";
			   else return "40061 - Rent - Kona";
		   case "Arizona":
			   if(GetterAndSetterClass.getArizonaCodeAvailable()==false&&GetterAndSetterClass.getArizonaCityFromBuildingAddress().contains("Phoenix"))
				   return "40018 - Rent-PHX";
			   else if(GetterAndSetterClass.getArizonaCodeAvailable()==false)
			   return "40010 - Rent Income";
			   else return GetterAndSetterClass.getArizonaRentCode();
		   case "New Jersey":
			   return "40010 - Rent Income";
		   case "Montana":
			   return "40010 - Rent Income";
		   case "Delaware":
			   return "40010 - Rent Income";
		   case "Chicago":
			   return "40010 - Rent Income";
		   }
		   return "";
	   }
	   
	   public static String getPetRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "40230 - Pet Rent";
		   case "Alabama":
			   return "40230 - Pet Rent";
		   case "North Carolina":
			   return "40230 - Pet Rent";
		   case "Chattanooga":
			   return "40230 - Pet Rent";
		   case "Chicago PFW":
			   return "40230 - Pet Rent";
		   case "California":
			   return "40230 - Pet Rent";
		   case "California PFW":
			   return "40230 - Pet Rent";
		   case "Colorado Springs":
			   return "40230 - Pet Rent";
		   case "Kansas City":
			   return "40230 - Pet Rent";
		   case "Houston":
			   return "40230 - Pet Rent";
		   case "Maine":
			   return "40230 - Pet Rent";
		   case "Savannah":
			   return "40230 - Pet Rent";
		   case "Arkansas":
			   return "40230 - Pet Rent";
		   case "Dallas/Fort Worth":
			   return "40230 - Pet Rent";
		   case "Indiana":
			   return "40230 - Pet Rent";
		   case "Little Rock":
			   return "40230 - Pet Rent";
		   case "San Antonio":
			   return "40230 - Pet Rent";
		   case "Tulsa":
			   return "40230 - Pet Rent";
		   case "Georgia":
			   return "40230 - Pet Rent";
		   case "OKC":
			   return "40230 - Pet Rent";
		   case "South Carolina":
			   return "40230 - Pet Rent";
		   case "Florida":
			   return "40230 - Pet Rent";
		   case "Tennessee":
			   return "40230 - Pet Rent";
		   case "New Mexico":
			   return "40230 - Pet Rent";
		   case "Ohio":
			   return "40230 - Pet Rent";
		   case "Pennsylvania":
			   return "40230 - Pet Rent";
		   case "Lake Havasu":
			   return "40230 - Pet Rent";
		   case "Columbia - St Louis":
			   return "40230 - Pet Rent";
		   case "Maryland":
			   return "40230 - Pet Rent";
		   case "Virginia":
			   return "40230 - Pet Rent";
		   case "Boise":
			   return "40230 - Pet Rent";
		   case "Idaho Falls":
			   return "40230 - Pet Rent,43150 - Pet Inspection Fee";
		   case "Utah":
			   return "40230 - Pet Rent,43150 - Pet Inspection Fee";
		   case "Spokane":
			   return "40230 - Pet Rent";
		   case "Washington DC":
			   return "40230 - Pet Rent";
		   case "Hawaii":
			   return "40230 - Pet Rent";
		   case "Arizona":
			   if(GetterAndSetterClass.getArizonaCodeAvailable()==false&&GetterAndSetterClass.getArizonaCityFromBuildingAddress().contains("Phoenix"))
				   return "40018 - Rent-PHX";
			   else if(GetterAndSetterClass.getArizonaCodeAvailable()==false)
			   return "40010 - Rent Income";
			   else return GetterAndSetterClass.getArizonaRentCode();
		   case "New Jersey":
			   return "40230 - Pet Rent";
		   case "Montana":
			   return "40230 - Pet Rent";
		   case "Delaware":
			   return "40230 - Pet Rent";
		   case "Chicago":
			   return "40230 - Pet Rent";
		   }
		   return "";
	   }
	   public static String getTenentAdminReveueChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "43030 - Tenant Admin Fee";
		   case "Alabama":
			   return "43030 - Tenant Admin Fee";
		   case "North Carolina":
			   return "43030 - Tenant Admin Fee";
		   case "Chattanooga":
			   return "43030 - Tenant Admin Fee";
		   case "Chicago PFW":
			   return "43030 - Tenant Admin Fee";
		   case "California":
			   return "43030 - Tenant Admin Fee";
		   case "California PFW":
			   return "43030 - Tenant Admin Fee";
		   case "Colorado Springs":
			   return "43030 - Tenant Admin Fee";
		   case "Kansas City":
			   return "43030 - Tenant Admin Fee";
		   case "Houston":
			   return "43030 - Tenant Admin Fee";
		   case "Maine":
			   return "43030 - Tenant Admin Fee";
		   case "Savannah":
			   return "43030 - Tenant Admin Fee";
		   case "Arkansas":
			   return "43030 - Tenant Admin Revenue";
		   case "Dallas/Fort Worth":
			   return "43030 - Tenant Admin Fee";
		   case "Indiana":
			   return "43030 - Tenant Admin Fee";
		   case "Little Rock":
			   return "43030 - Tenant Admin Fee";
		   case "San Antonio":
			   return "43030 - Tenant Admin Fee";
		   case "Tulsa":
			   return "43030 - Tenant Admin Fee";
		   case "Georgia":
			   return "43030 - Tenant Admin Fee";
		   case "OKC":
			   return "43030 - Tenant Admin Fee";
		   case "South Carolina":
			   return "43030 - Tenant Admin Fee";
		   case "Florida":
			   return "43030 - Tenant Admin Fee";
		   case "Tennessee":
			   return "43030 - Tenant Admin Fee";
		   case "New Mexico":
			   return "43030 - Tenant Admin Fee";
		   case "Ohio":
			   return "43030 - Tenant Admin Fee";
		   case "Pennsylvania":
			   return "43030 - Tenant Admin Fee";
		   case "Lake Havasu":
			   return "43030 - Tenant Admin Fee";
		   case "Columbia - St Louis":
			   return "43030 - Tenant Admin Fee";
		   case "Maryland":
			   return "43030 - Tenant Admin Fee";
		   case "Virginia":
			   return "43030 - Tenant Admin Fee";
		   case "Boise":
			   return "43030 - Tenant Admin Fee";
		   case "Idaho Falls":
			   return "43030 - Tenant Admin Fee";
		   case "Utah":
			   return "43030 - Tenant Admin Fee";
		   case "Spokane":
			   return "43030 - Tenant Admin Fee";
		   case "Washington DC":
			   return "43030 - Tenant Admin Fee";
		   case "Hawaii":
			   return "43030 - Tenant Admin Fee";
		   case "Arizona":
			   return "43030 - Tenant Admin Fee";
		   case "New Jersey":
			   return "43030 - Tenant Admin Fee";
		   case "Montana":
			   return "43030 - Tenant Admin Revenue";
		   case "Delaware":
			   return "43030 - Tenant Admin Fee";
		   case "Chicago":
			   return "43030 - Tenant Admin Fee";
		   }
		   return "";
	   }
	   
	   public static String getProrateRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "40010 - Rent Income";
		   case "California":
			   return "40010 - Rent Income";
		   case "California PFW":
			   return "40010 - Rent Income";
		   case "North Carolina":
			   return "40010 - Rent Income";
		   case "Chattanooga":
			   return "40010 - Rent Income";
		   case "Chicago PFW":
			   return "40010 - Rent Income";
		   case "Colorado Springs":
			   return "40010 - Rent Income";
		   case "Kansas City":
			   return "40010 - Rent Income";
		   case "Houston":
			   return "40010 - Rent Income";
		   case "Maine":
			   return "40010 - Rent Income";
		   case "Savannah":
			   return "40010 - Rent Income";
		   case "Alabama":
			   return "40010 - Rent Income";
		   case "Arkansas":
			   return "40010 - Rent Income";
		   case "Dallas/Fort Worth":
			   return "40010 - Rent Income";
		   case "Indiana":
			   return "40010 - Rent Income";
		   case "Little Rock":
			   return "40010 - Rent Income";
		   case "San Antonio":
			   return "40010 - Rent Income";
		   case "Tulsa":
			   return "40010 - Rent Income";
		   case "Georgia":
			   return "40010 - Rent Income";
		   case "OKC":
			   return "40010 - Rent Income";
		   case "South Carolina":
			   return "40010 - Rent Income";
		   case "Florida":
			   return "40010 - Rent Income";
		   case "Tennessee":
			   return "40010 - Rent Income";
		   case "New Mexico":
			   return "40010 - Rent Income";
		   case "Ohio":
			   return "40010 - Rent Income";
		   case "Pennsylvania":
			   return "40010 - Rent Income";
		   case "Lake Havasu":
			   return "40010 - Rent Income";
		   case "Columbia - St Louis":
			   return "40010 - Rent Income";
		   case "Maryland":
			   return "40010 - Rent Income";
		   case "Virginia":
			   return "40010 - Rent Income";
		   case "Boise":
			   return "40010 - Rent Income";
		   case "Idaho Falls":
			   return "40010 - Rent Income";
		   case "Utah":
			   return "40010 - Rent Income";
		   case "Spokane":
			   return "40010 - Rent Income";
		   case "Washington DC":
			   return "40010 - Rent Income";
		   case "Hawaii":
			   if(GetterAndSetterClass.getMonthlyRentTaxFlag()==false)
				   return "40010 - Rent Income";
				   else return "40061 - Rent - Kona";
		   case "Arizona":
			   if(GetterAndSetterClass.getArizonaCodeAvailable()==false&&GetterAndSetterClass.getArizonaCityFromBuildingAddress().contains("Phoenix"))
				   return "40018 - Rent-PHX";
			   else if(GetterAndSetterClass.getArizonaCodeAvailable()==false)
			   return "40010 - Rent Income";
			   else return GetterAndSetterClass.getArizonaRentCode();
		   case "New Jersey":
			   return "40010 - Rent Income";
		   case "Montana":
			   return "40010 - Rent Income";
		   case "Delaware":
			   return "40010 - Rent Income";
		   case "Chicago":
			   return "40010 - Rent Income";
			   
		   }
		   return "";
	   }
	   
	   
	   public static String getProratePetRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "40230 - Pet Rent";
		   case "Alabama":
			   return "40230 - Pet Rent";
		   case "North Carolina":
			   return "40230 - Pet Rent";
		   case "Chattanooga":
			   return "40230 - Pet Rent";
		   case "Chicago PFW":
			   return "40230 - Pet Rent";
		   case "California":
			   return "40230 - Pet Rent";
		   case "California PFW":
			   return "40230 - Pet Rent";
		   case "Colorado Springs":
			   return "40230 - Pet Rent";
		   case "Kansas City":
			   return "40230 - Pet Rent";
		   case "Houston":
			   return "40230 - Pet Rent";
		   case "Maine":
			   return "40230 - Pet Rent";
		   case "Savannah":
			   return "40230 - Pet Rent";
		   case "Arkansas":
			   return "40230 - Pet Rent";
		   case "Dallas/Fort Worth":
			   return "40230 - Pet Rent";
		   case "Indiana":
			   return "40230 - Pet Rent";
		   case "Little Rock":
			   return "40230 - Pet Rent";
		   case "San Antonio":
			   return "40230 - Pet Rent";
		   case "Tulsa":
			   return "40230 - Pet Rent";
		   case "Georgia":
			   return "40230 - Pet Rent";
		   case "OKC":
			   return "40230 - Pet Rent";
		   case "South Carolina":
			   return "40230 - Pet Rent";
		   case "Florida":
			   return "40230 - Pet Rent";
		   case "Tennessee":
			   return "40230 - Pet Rent";
		   case "New Mexico":
			   return "40230 - Pet Rent";
		   case "Ohio":
			   return "40230 - Pet Rent";
		   case "Pennsylvania":
			   return "40230 - Pet Rent";
		   case "Lake Havasu":
			   return "40230 - Pet Rent";
		   case "Columbia - St Louis":
			   return "40230 - Pet Rent";
		   case "Maryland":
			   return "40230 - Pet Rent";
		   case "Virginia":
			   return "40230 - Pet Rent";
		   case "Boise":
			   return "40230 - Pet Rent";
		   case "Idaho Falls":
			   return "40230 - Pet Rent";
		   case "Utah":
			   return "40230 - Pet Rent";
		   case "Spokane":
			   return "40230 - Pet Rent";
		   case "Washington DC":
			   return "40230 - Pet Rent";
		   case "Hawaii":
			   return "40230 - Pet Rent";
		   case "Arizona":
			   if(GetterAndSetterClass.getArizonaCodeAvailable()==false&&GetterAndSetterClass.getArizonaCityFromBuildingAddress().contains("Phoenix"))
				   return "40018 - Rent-PHX";
			   else if(GetterAndSetterClass.getArizonaCodeAvailable()==false)
			   return "40010 - Rent Income";
			   else return GetterAndSetterClass.getArizonaRentCode();
		   case "New Jersey":
			   return "40230 - Pet Rent";
		   case "Montana":
			   return "40230 - Pet Rent";
		   case "Delaware":
			   return "40230 - Pet Rent";
		   case "Chicago":
			   return "40230 - Pet Rent";
		   }
		   return "";
	   }
	   public static String getIncreasedRentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "40010 - Rent Income";
		   case "California":
			   return "40010 - Rent Income";
		   case "California PFW":
			   return "40010 - Rent Income";
		   case "North Carolina":
			   return "40010 - Rent Income";
		   case "Chattanooga":
			   return "40010 - Rent Income";
		   case "Chicago PFW":
			   return "40010 - Rent Income";
		   case "Colorado Springs":
			   return "40010 - Rent Income";
		   case "Kansas City":
			   return "40010 - Rent Income";
		   case "Houston":
			   return "40010 - Rent Income";
		   case "Maine":
			   return "40010 - Rent Income";
		   case "Savannah":
			   return "40010 - Rent Income";
		   case "Alabama":
			   return "40010 - Rent Income";
		   case "Arkansas":
			   return "40010 - Rent Income";
		   case "Dallas/Fort Worth":
			   return "40010 - Rent Income";
		   case "Indiana":
			   return "40010 - Rent Income";
		   case "Little Rock":
			   return "40010 - Rent Income";
		   case "San Antonio":
			   return "40010 - Rent Income";
		   case "Tulsa":
			   return "40010 - Rent Income";
		   case "Georgia":
			   return "40010 - Rent Income";
		   case "OKC":
			   return "40010 - Rent Income";
		   case "South Carolina":
			   return "40010 - Rent Income";
		   case "Florida":
			   return "40010 - Rent Income";
		   case "Tennessee":
			   return "40010 - Rent Income";
		   case "New Mexico":
			   return "40010 - Rent Income";
		   case "Ohio":
			   return "40010 - Rent Income";
		   case "Pennsylvania":
			   return "40010 - Rent Income";
		   case "Lake Havasu":
			   return "40010 - Rent Income";
		   case "Columbia - St Louis":
			   return "40010 - Rent Income";
		   case "Maryland":
			   return "40010 - Rent Income";
		   case "Virginia":
			   return "40010 - Rent Income";
		   case "Boise":
			   return "40010 - Rent Income";
		   case "Idaho Falls":
			   return "40010 - Rent Income";
		   case "Utah":
			   return "40010 - Rent Income";
		   case "Spokane":
			   return "40010 - Rent Income";
		   case "Washington DC":
			   return "40010 - Rent Income";
		   case "Hawaii":
			   if(GetterAndSetterClass.getMonthlyRentTaxFlag()==false)
				   return "40010 - Rent Income";
				   else return "40061 - Rent - Kona";
		   case "Arizona":
			   if(GetterAndSetterClass.getArizonaCodeAvailable()==false&&GetterAndSetterClass.getArizonaCityFromBuildingAddress().contains("Phoenix"))
				   return "40018 - Rent-PHX";
			   else if(GetterAndSetterClass.getArizonaCodeAvailable()==false)
			   return "40010 - Rent Income";
			   else return GetterAndSetterClass.getArizonaRentCode();
		   case "New Jersey":
			   return "40010 - Rent Income";
		   case "Montana":
			   return "40010 - Rent Income";
		   case "Delaware":
			   return "40010 - Rent Income";
		   case "Chicago":
			   return "40010 - Rent Income";
		   }
		   return "";
	   }
	   public static String getHVACAirFilterFeeChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "43060 - Filter Fee";
		   case "Alabama":
			   return "43060 - Filter Fee";
		   case "North Carolina":
			   return "43060 - Filter Fee";
		   case "Chattanooga":
			   return "43060 - Filter Fee";
		   case "Chicago PFW":
			   return "43060 - Filter Fee";
		   case "California":
			   return "43060 - Filter Fee";
		   case "California PFW":
			   return "43060 - Filter Fee";
		   case "Colorado Springs":
			   return "43060 - Filter Fee";
		   case "Kansas City":
			   return "43060 - Filter Fee";
		   case "Houston":
			   return "43060 - Filter Fee";
		   case "Maine":
			   return "43060 - Filter Fee";
		   case "Savannah":
			   return "43060 - Filter Fee";
		   case "Arkansas":
			   return "43060 - Filter Fee";
		   case "Dallas/Fort Worth":
			   return "43060 - Filter Fee";
		   case "Indiana":
			   return "43060 - Filter Fee";
		   case "Little Rock":
			   return "43060 - Filter Fee";
		   case "San Antonio":
			   return "43060 - Filter Fee";
		   case "Tulsa":
			   return "43060 - Filter Fee";
		   case "Georgia":
			   return "43060 - Filter Fee";
		   case "OKC":
			   return "43060 - Filter Fee";
		   case "South Carolina":
			   return "43060 - Filter Fee";
		   case "Florida":
			   return "43060 - Filter Fee";
		   case "Tennessee":
			   return "43060 - Filter Fee";
		   case "New Mexico":
			   return "43060 - Filter Fee";
		   case "Ohio":
			   return "43060 - Filter Fee";
		   case "Pennsylvania":
			   return "43060 - Filter Fee";
		   case "Lake Havasu":
			   return "43060 - Filter Fee";
		   case "Columbia - St Louis":
			   return "43060 - Filter Fee";
		   case "Maryland":
			   return "43060 - Filter Fee";
		   case "Virginia":
			   return "43060 - Filter Fee";
		   case "Boise":
			   return "43060 - Filter Fee";
		   case "Idaho Falls":
			   return "43060 - Filter Fee";
		   case "Utah":
			   return "43060 - Filter Fee";
		   case "Spokane":
			   return "43060 - Filter Fee";
		   case "Washington DC":
			   return "43060 - Filter Fee";
		   case "Hawaii":
			   return "43060 - Filter Fee";
		   case "Arizona":
			   return "43060 - Filter Fee";
		   case "New Jersey":
			   return "43060 - Filter Fee";
		   case "Montana":
			   return "43060 - Filter Fee";
		   case "Delaware":
			   return "43060 - Filter Fee";
		   case "Chicago":
			   return "43060 - Filter Fee";
		   }
		   return "";
	   }
	   
	   public static String getpetOneTimeNonRefundableChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Alabama":
			   return "44010 - Non-Refundable Pet Fee";
		   case "North Carolina":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Chattanooga":
			   return "44010 - Non - Refundable Pet Fee";
		   case "Chicago PFW":
			   return "44010 - Non-Refundable Pet Fee";
		   case "California":
			   return "44010 - Non-Refundable Pet Fee";
		   case "California PFW":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Colorado Springs":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Kansas City":
			   return  "44010 - Non-Refundable Pet Fee";
		   case "Houston":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Maine":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Savannah":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Arkansas":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Dallas/Fort Worth":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Indiana":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Little Rock":
			   return "44010 - Non-Refundable Pet Fee";
		   case "San Antonio":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Tulsa":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Georgia":
			   return "44010 - Non-Refundable Pet Fee";
		   case "OKC":
			   return "44010 - Non-Refundable Pet Fee";
		   case "South Carolina":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Florida":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Tennessee":
			   return "44010 - Non-Refundable Pet Fee";
		   case "New Mexico":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Ohio":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Pennsylvania":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Lake Havasu":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Columbia - St Louis":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Maryland":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Virginia":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Boise":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Idaho Falls":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Utah":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Spokane":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Washington DC":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Hawaii":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Arizona":
			   return "44010 - Non-Refundable Pet Fee";
		   case "New Jersey":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Montana":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Delaware":
			   return "44010 - Non-Refundable Pet Fee";
		   case "Chicago":
			   return "44010 - Non-Refundable Pet Fee";
		   }
		   return "";
	   }
	   public static String getPrepaymentChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "20030 - Prepayments";
		   case "Alabama":
			   return "20030 - Prepayments";
		   case "North Carolina":
			   return "20030 - Prepayments";
		   case "Chattanooga":
			   return "20030 - Prepayments";
		   case "Chicago PFW":
			   return "20030 - Prepayments";
		   case "California":
			   return "20030 - Prepayments";
		   case "California PFW":
			   return "20030 - Prepayments";
		   case "Colorado Springs":
			   return "20030 - Prepayments";
		   case "Kansas City":
			   return "20030 - Prepayments";
		   case "Houston":
			   return "20030 - Prepayments";
		   case "Maine":
			   return "20030 - Prepayments";
		   case "Savannah":
			   return "20030 - Prepayments";
		   case "Arkansas":
			   return "20030 - Prepayments";
		   case "Dallas/Fort Worth":
			   return "20030 - Prepayments";
		   case "Indiana":
			   return "20030 - Prepayments";
		   case "Little Rock":
			   return "20030 - Prepayments";
		   case "San Antonio":
			   return "20030 - Prepayments";
		   case "Tulsa":
			   return "20030 - Prepayments";
		   case "Georgia":
			   return "20030 - Prepayments";
		   case "OKC":
			   return "20030 - Prepayments";
		   case "South Carolina":
			   return "20030 - Prepayments";
		   case "Florida":
			   return "20030 - Prepayments";
		   case "Tennessee":
			   return "20030 - Prepayments";
		   case "New Mexico":
			   return "20030 - Prepayments";
		   case "Ohio":
			   return "20030 - Prepayments";
		   case "Pennsylvania":
			   return "20030 - Prepayments";
		   case "Lake Havasu":
			   return "20030 - Prepayments";
		   case "Columbia - St Louis":
			   return "20030 - Prepayments";
		   case "Maryland":
			   return "20030 - Prepayments";
		   case "Virginia":
			   return "20030 - Prepayments";
		   case "Boise":
			   return "20030 - Prepayments";
		   case "Idaho Falls":
			   return "20030 - Prepayments";
		   case "Utah":
			   return "20030 - Prepayments";
		   case "Spokane":
			   return "20030 - Prepayments";
		   case "Washington DC":
			   return "20030 - Prepayments";
		   case "Hawaii":
			   return "20030 - Prepayments";
		   case "Arizona":
			   return "20030 - Prepayments";
		   case "New Jersey":
			   return "20030 - Prepayments";
		   case "Montana":
			   return "20030 - Prepayments";
		   case "Delaware":
			   return "20030 - Prepayments";
		   case "Chicago":
			   return "20030 - Prepayments";
		   }
		   return "";
	   }
	   
	   public static String getResidentBenefitsPackageChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "43070 - Resident Benefit Package Fee";
		   case "California":
			   return "43070 - Resident Benefit Package Fee";
		   case "California PFW":
			   return "43070 - Resident Benefit Package Fee";
		   case "Alabama":
			   return "43070 - Resident Benefit Package Fee";
		   case "North Carolina":
			   return "43070 - Resident Benefit Package Fee";
		   case "Chattanooga":
			   return "43070 - Resident Benefit Package Fee";
		   case "Chicago PFW":
			   return "43070 - Resident Benefit Package Fee";
		   case "Colorado Springs":
			   return "43070 - Resident Benefit Package Fee";
		   case "Kansas City":
			   return "43070 - Resident Benefit Package Fee";
		   case "Houston":
			   return "43070 - Resident Benefit Package Fee";
		   case "Maine":
			   return "43070 - Resident Benefit Package Fee";
		   case "Savannah":
			   return "43070 - Resident Benefit Package Fee";
		   case "Arkansas":
			   return "43070 - Resident Benefit Package Fee";
		   case "Dallas/Fort Worth":
			   return "43070 - Resident Benefit Package Fee";
		   case "Indiana":
			   return "43070 - Resident Benefit Package Fee";
		   case "Little Rock":
			   return "43070 - Resident Benefit Package Fee";
		   case "San Antonio":
			   return "43070 - Resident Benefit Package Fee";
		   case "Tulsa":
			   return "43070 - Resident Benefit Package Fee";
		   case "Georgia":
			   return "43070 - Resident Benefit Package Fee";
		   case "OKC":
			   return "43070 - Resident Benefit Package Fee";
		   case "South Carolina":
			   return "43070 - Resident Benefit Package Fee";
		   case "Florida":
			   return "43070 - Resident Benefit Package Fee";
		   case "Tennessee":
			   return "43070 - Resident Benefit Package Fee";
		   case "New Mexico":
			   return "43070 - Resident Benefit Package Fee";
		   case "Ohio":
			   return "43070 - Resident Benefit Package Fee";
		   case "Pennsylvania":
			   return "43070 - Resident Benefit Package Fee";
		   case "Lake Havasu":
			   return "43070 - Resident Benefit Package Fee";
		   case "Columbia - St Louis":
			   return "43070 - Resident Benefit Package Fee";
		   case "Maryland":
			   return "43070 - Resident Benefit Package Fee";
		   case "Virginia":
			   return "43070 - Resident Benefit Package Fee";
		   case "Boise":
			   return "43070 - Resident Benefit Package Fee";
		   case "Idaho Falls":
			   return "43070 - Resident Benefit Package Fee";
		   case "Utah":
			   return "43070 - Resident Benefit Package Fee";
		   case "Spokane":
			   return "43070 - Resident Benefit Package Fee";
		   case "Washington DC":
			   return "43070 - Resident Benefit Package Fee";
		   case "Hawaii":
			   return "43070 - Resident Benefit Package Fee";
		   case "Arizona":
			   return "43070 - Resident Benefit Package Fee";
		   case "New Jersey":
			   return "43070 - Resident Benefit Package Fee";
		   case "Montana":
			   return "43070 - Resident Benefit Package Fee";
		   case "Delaware":
			   return "43070 - Resident Benefit Package Fee";
		   case "Chicago":
			   return "43070 - Resident Benefit Package Fee";
		   }
		   return "";
	   }
	   public static String getResidentBenefitsPackageTaxChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "43030 - Tenant Admin Revenue";
		   case "California":
			   return "43030 - Tenant Admin Revenue";
		   case "California PFW":
			   return "43030 - Tenant Admin Revenue";
		   case "Alabama":
			   return "43030 - Tenant Admin Revenue";
		   case "North Carolina":
			   return "43030 - Tenant Admin Revenue";
		   case "Chattanooga":
			   return "43030 - Tenant Admin Revenue";
		   case "Chicago PFW":
			   return "43030 - Tenant Admin Revenue";
		   case "Colorado Springs":
			   return "43030 - Tenant Admin Revenue";
		   case "Kansas City":
			   return "43030 - Tenant Admin Revenue";
		   case "Houston":
			   return "43030 - Tenant Admin Revenue";
		   case "Maine":
			   return "43030 - Tenant Admin Revenue";
		   case "Savannah":
			   return "43030 - Tenant Admin Revenue";
		   case "Arkansas":
			   return "43030 - Tenant Admin Revenue";
		   case "Dallas/Fort Worth":
			   return "43030 - Tenant Admin Revenue";
		   case "Indiana":
			   return "43030 - Tenant Admin Revenue";
		   case "Little Rock":
			   return "43030 - Tenant Admin Revenue";
		   case "San Antonio":
			   return "43030 - Tenant Admin Revenue";
		   case "Tulsa":
			   return "43030 - Tenant Admin Revenue";
		   case "Georgia":
			   return "43030 - Tenant Admin Revenue";
		   case "OKC":
			   return "43030 - Tenant Admin Revenue";
		   case "South Carolina":
			   return "43030 - Tenant Admin Revenue";
		   case "Florida":
			   return "43030 - Tenant Admin Revenue";
		   case "Tennessee":
			   return "43030 - Tenant Admin Revenue";
		   case "New Mexico":
			   return "43030 - Tenant Admin Revenue";
		   case "Ohio":
			   return "43030 - Tenant Admin Revenue";
		   case "Pennsylvania":
			   return "43030 - Tenant Admin Revenue";
		   case "Lake Havasu":
			   return "43030 - Tenant Admin Revenue";
		   case "Columbia - St Louis":
			   return "43030 - Tenant Admin Revenue";
		   case "Maryland":
			   return "43030 - Tenant Admin Revenue";
		   case "Virginia":
			   return "43030 - Tenant Admin Revenue";
		   case "Boise":
			   return "43030 - Tenant Admin Revenue";
		   case "Idaho Falls":
			   return "43030 - Tenant Admin Revenue";
		   case "Utah":
			   return "43030 - Tenant Admin Revenue";
		   case "Spokane":
			   return "43030 - Tenant Admin Revenue";
		   case "Washington DC":
			   return "43030 - Tenant Admin Revenue";
		   case "Hawaii":
			   return "43030 - Tenant Admin Revenue";
		   case "Arizona":
			   return "43030 - Tenant Admin Revenue";
		   case "New Jersey":
			   return "43030 - Tenant Admin Revenue";
		   case "Montana":
			   return "43030 - Tenant Admin Revenue";
		   case "Delaware":
			   return "43030 - Tenant Admin Revenue";
		   case "Chicago":
			   return "43030 - Tenant Admin Revenue";
		   }
		   return "";
	   }
	   
	   public static String getpetSecurityDepositChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "20020 - Security Deposit";
		   case "Alabama":
			   return "20020 - Security Deposit";
		   case "North Carolina":
			   return "20020 - Security Deposit";
		   case "Chattanooga":
			   return "20020 - Security Deposit";
		   case "California":
			   return "20020 - Security Deposit";
		   case "California PFW":
			   return "20020 - Security Deposit";
		   case "Chicago PFW":
			   return "20020 - Security Deposit";
		   case "Colorado Springs":
			   return "20020 - Security Deposit";
		   case "Kansas City":
			   return "20020 - Security Deposit";
		   case "Houston":
			   return "20020 - Security Deposit";
		   case "Maine":
			   return "20020 - Security Deposit";
		   case "Savannah":
			   return "20020 - Security Deposit";
		   case "Arkansas":
			   return "20020 - Security Deposit";
		   case "Dallas/Fort Worth":
			   return "20020 - Security Deposit";
		   case "Indiana":
			   return "20020 - Security Deposit";
		   case "Little Rock":
			   return "20020 - Security Deposit";
		   case "San Antonio":
			   return "20020 - Security Deposit";
		   case "Tulsa":
			   return "20020 - Security Deposit";
		   case "Georgia":
			   return "20020 - Security Deposit";
		   case "OKC":
			   return "20020 - Security Deposit";
		   case "South Carolina":
			   return "20020 - Security Deposit";
		   case "Florida":
			   return "20020 - Security Deposit";
		   case "Tennessee":
			   return "20020 - Security Deposit";
		   case "New Mexico":
			   return "20020 - Security Deposit";
		   case "Ohio":
			   return "20020 - Security Deposit";
		   case "Pennsylvania":
			   return "20020 - Security Deposit";
		   case "Lake Havasu":
			   return "20020 - Security Deposit";
		   case "Columbia - St Louis":
			   return "20020 - Security Deposit";
		   case "Maryland":
			   return "20020 - Security Deposit";
		   case "Virginia":
			   return "20020 - Security Deposit";
		   case "Boise":
			   return "20020 - Security Deposit";
		   case "Idaho Falls":
			   return "20020 - Security Deposit";
		   case "Utah":
			   return "20020 - Security Deposit";
		   case "Spokane":
			   return "20020 - Security Deposit";
		   case "Washington DC":
			   return "20020 - Security Deposit";
		   case "Hawaii":
			   return "20020 - Security Deposit";
		   case "Arizona":
			   return "20020 - Security Deposit";
		   case "New Jersey":
			   return "20020 - Security Deposit";
		   case "Montana":
			   return "20020 - Security Deposit";
		   case "Delaware":
			   return "20020 - Security Deposit";
		   case "Chicago":
			   return "20020 - Security Deposit";
		   }
		   return "";
	   }
	   public static String getResidentUtilityBillChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Alabama":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "North Carolina":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Chattanooga":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "California":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "California PFW":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Chicago PFW":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Colorado Springs":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Kansas City":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Houston":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Maine":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Savannah":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Arkansas":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Dallas/Fort Worth":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Indiana":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Little Rock":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "San Antonio":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Tulsa":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Georgia":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "OKC":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "South Carolina":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Florida":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Tennessee":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "New Mexico":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Ohio":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Pennsylvania":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Lake Havasu":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Columbia - St Louis":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Maryland":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Virginia":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Boise":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Idaho Falls":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Utah":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Spokane":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Washington DC":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Hawaii":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Arizona":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "New Jersey":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Montana":
			   return "42030 - Utility Reimbursement";
		   case "Delaware":
			   return "44040 - Resident Utility Bill Serv Rev";
		   case "Chicago":
			   return "44040 - Resident Utility Bill Serv Rev";
		   }
		return "";
	   }
	   public static String getMonthlyRentTaxCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "40051 - Rent-St. Clair";
		   case "Alabama":
			   return "40051 - Rent-St. Clair";
		   case "North Carolina":
			   return "40051 - Rent-St. Clair";
		   case "Chattanooga":
			   return "40051 - Rent-St. Clair";
		   case "California":
			   return "40051 - Rent-St. Clair";
		   case "California PFW":
			   return "40051 - Rent-St. Clair";
		   case "Chicago PFW":
			   return "40051 - Rent-St. Clair";
		   case "Colorado Springs":
			   return "40051 - Rent-St. Clair";
		   case "Kansas City":
			   return "40051 - Rent-St. Clair";
		   case "Houston":
			   return "40051 - Rent-St. Clair";
		   case "Maine":
			   return "40051 - Rent-St. Clair";
		   case "Savannah":
			   return "40051 - Rent-St. Clair";
		   case "Arkansas":
			   return "40051 - Rent-St. Clair";
		   case "Dallas/Fort Worth":
			   return "40051 - Rent-St. Clair";
		   case "Indiana":
			   return "40051 - Rent-St. Clair";
		   case "Little Rock":
			   return "40051 - Rent-St. Clair";
		   case "San Antonio":
			   return "40051 - Rent-St. Clair";
		   case "Tulsa":
			   return "40051 - Rent-St. Clair";
		   case "Georgia":
			   return "40051 - Rent-St. Clair";
		   case "OKC":
			   return "40051 - Rent-St. Clair";
		   case "South Carolina":
			   return "40051 - Rent-St. Clair";
		   case "Florida":
			   return "40051 - Rent-St. Clair";
		   case "Tennessee":
			   return "40051 - Rent-St. Clair";
		   case "New Mexico":
			   return "40051 - Rent-St. Clair";
		   case "Ohio":
			   return "40051 - Rent-St. Clair";
		   case "Pennsylvania":
			   return "40051 - Rent-St. Clair";
		   case "Lake Havasu":
			   return "40051 - Rent-St. Clair";
		   case "Columbia - St Louis":
			   return "40051 - Rent-St. Clair";
		   case "Maryland":
			   return "40051 - Rent-St. Clair";
		   case "Virginia":
			   return "40051 - Rent-St. Clair";
		   case "Boise":
			   return "40051 - Rent-St. Clair";
		   case "Idaho Falls":
			   return "40051 - Rent-St. Clair";
		   case "Utah":
			   return "40051 - Rent-St. Clair";
		   case "Spokane":
			   return "40051 - Rent-St. Clair";
		   case "Washington DC":
			   return "40051 - Rent-St. Clair";
		   case "Hawaii":
			   return "40061 - Rent - Kona";
		   case "Arizona":
			   return "43050 - Month-to-Month Admin Fee";
		   case "New Jersey":
			   return "40061 - Rent - Kona";
		   case "Montana":
			   return "40061 - Rent - Kona";
		   case "Delaware":
			   return "40061 - Rent - Kona";
		   case "Chicago":
			   return "40051 - Rent-St. Clair";
		   }
		return "";
	   }
	   
	   public static String getProrateRentGETCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "42100 - GET Tenant Recovery";
		   case "Alabama":
			   return "42100 - GET Tenant Recovery";
		   case "North Carolina":
			   return "42100 - GET Tenant Recovery";
		   case "Chattanooga":
			   return "42100 - GET Tenant Recovery";
		   case "California":
			   return "42100 - GET Tenant Recovery";
		   case "California PFW":
			   return "42100 - GET Tenant Recovery";
		   case "Chicago PFW":
			   return "42100 - GET Tenant Recovery";
		   case "Colorado Springs":
			   return "42100 - GET Tenant Recovery";
		   case "Kansas City":
			   return "42100 - GET Tenant Recovery";
		   case "Houston":
			   return "42100 - GET Tenant Recovery";
		   case "Maine":
			   return "42100 - GET Tenant Recovery";
		   case "Savannah":
			   return "42100 - GET Tenant Recovery";
		   case "Arkansas":
			   return "42100 - GET Tenant Recovery";
		   case "Dallas/Fort Worth":
			   return "42100 - GET Tenant Recovery";
		   case "Indiana":
			   return "42100 - GET Tenant Recovery";
		   case "Little Rock":
			   return "42100 - GET Tenant Recovery";
		   case "San Antonio":
			   return "42100 - GET Tenant Recovery";
		   case "Tulsa":
			   return "42100 - GET Tenant Recovery";
		   case "Georgia":
			   return "42100 - GET Tenant Recovery";
		   case "OKC":
			   return "42100 - GET Tenant Recovery";
		   case "South Carolina":
			   return "42100 - GET Tenant Recovery";
		   case "Florida":
			   return "42100 - GET Tenant Recovery";
		   case "Tennessee":
			   return "42100 - GET Tenant Recovery";
		   case "New Mexico":
			   return "42100 - GET Tenant Recovery";
		   case "Ohio":
			   return "42100 - GET Tenant Recovery";
		   case "Pennsylvania":
			   return "42100 - GET Tenant Recovery";
		   case "Lake Havasu":
			   return "42100 - GET Tenant Recovery";
		   case "Columbia - St Louis":
			   return "42100 - GET Tenant Recovery";
		   case "Maryland":
			   return "42100 - GET Tenant Recovery";
		   case "Virginia":
			   return "42100 - GET Tenant Recovery";
		   case "Boise":
			   return "42100 - GET Tenant Recovery";
		   case "Idaho Falls":
			   return "42100 - GET Tenant Recovery";
		   case "Utah":
			   return "42100 - GET Tenant Recovery";
		   case "Spokane":
			   return "42100 - GET Tenant Recovery";
		   case "Washington DC":
			   return "42100 - GET Tenant Recovery";
		   case "Hawaii":
			   return "42100 - GET Tenant Recovery";
		   case "Arizona":
			   return "42100 - GET Tenant Recovery";
		   case "New Jersey":
			   return "42100 - GET Tenant Recovery";
		   case "Montana":
			   return "42100 - GET Tenant Recovery";
		   case "Delaware":
			   return "42100 - GET Tenant Recovery";
		   case "Chicago":
			   return "42100 - GET Tenant Recovery";
		   }
		return "";
	   }
	   
	   public static String getMonthlyRentGETCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "42100 - GET Tenant Recovery";
		   case "Alabama":
			   return "42100 - GET Tenant Recovery";
		   case "North Carolina":
			   return "42100 - GET Tenant Recovery";
		   case "Chattanooga":
			   return "42100 - GET Tenant Recovery";
		   case "California":
			   return "42100 - GET Tenant Recovery";
		   case "California PFW":
			   return "42100 - GET Tenant Recovery";
		   case "Chicago PFW":
			   return "42100 - GET Tenant Recovery";
		   case "Colorado Springs":
			   return "42100 - GET Tenant Recovery";
		   case "Kansas City":
			   return "42100 - GET Tenant Recovery";
		   case "Houston":
			   return "42100 - GET Tenant Recovery";
		   case "Maine":
			   return "42100 - GET Tenant Recovery";
		   case "Savannah":
			   return "42100 - GET Tenant Recovery";
		   case "Arkansas":
			   return "42100 - GET Tenant Recovery";
		   case "Dallas/Fort Worth":
			   return "42100 - GET Tenant Recovery";
		   case "Indiana":
			   return "42100 - GET Tenant Recovery";
		   case "Little Rock":
			   return "42100 - GET Tenant Recovery";
		   case "San Antonio":
			   return "42100 - GET Tenant Recovery";
		   case "Tulsa":
			   return "42100 - GET Tenant Recovery";
		   case "Georgia":
			   return "42100 - GET Tenant Recovery";
		   case "OKC":
			   return "42100 - GET Tenant Recovery";
		   case "South Carolina":
			   return "42100 - GET Tenant Recovery";
		   case "Florida":
			   return "42100 - GET Tenant Recovery";
		   case "Tennessee":
			   return "42100 - GET Tenant Recovery";
		   case "New Mexico":
			   return "42100 - GET Tenant Recovery";
		   case "Ohio":
			   return "42100 - GET Tenant Recovery";
		   case "Pennsylvania":
			   return "42100 - GET Tenant Recovery";
		   case "Lake Havasu":
			   return "42100 - GET Tenant Recovery";
		   case "Columbia - St Louis":
			   return "42100 - GET Tenant Recovery";
		   case "Maryland":
			   return "42100 - GET Tenant Recovery";
		   case "Virginia":
			   return "42100 - GET Tenant Recovery";
		   case "Boise":
			   return "42100 - GET Tenant Recovery";
		   case "Idaho Falls":
			   return "42100 - GET Tenant Recovery";
		   case "Utah":
			   return "42100 - GET Tenant Recovery";
		   case "Spokane":
			   return "42100 - GET Tenant Recovery";
		   case "Washington DC":
			   return "42100 - GET Tenant Recovery";
		   case "Hawaii":
			   return "42100 - GET Tenant Recovery";
		   case "Arizona":
			   return "42100 - GET Tenant Recovery";
		   case "New Jersey":
			   return "42100 - GET Tenant Recovery";
		   case "Montana":
			   return "42100 - GET Tenant Recovery";
		   case "Delaware":
			   return "42100 - GET Tenant Recovery";
		   case "Chicago":
			   return "42100 - GET Tenant Recovery";
		   }
		return "";
	   }
	   
	   public static String getPetRentTaxCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "40231 - Pet Rent-St. Clair";
		   case "Alabama":
			   return "40231 - Pet Rent-St. Clair";
		   case "North Carolina":
			   return "40231 - Pet Rent-St. Clair";
		   case "Chattanooga":
			   return "40231 - Pet Rent-St. Clair";
		   case "California":
			   return "40231 - Pet Rent-St. Clair";
		   case "California PFW":
			   return "40231 - Pet Rent-St. Clair";
		   case "Chicago PFW":
			   return "40231 - Pet Rent-St. Clair";
		   case "Colorado Springs":
			   return "40231 - Pet Rent-St. Clair";
		   case "Kansas City":
			   return "40231 - Pet Rent-St. Clair";
		   case "Houston":
			   return "40231 - Pet Rent-St. Clair";
		   case "Maine":
			   return "40231 - Pet Rent-St. Clair";
		   case "Savannah":
			   return "40231 - Pet Rent-St. Clair";
		   case "Arkansas":
			   return "40231 - Pet Rent-St. Clair";
		   case "Dallas/Fort Worth":
			   return "40231 - Pet Rent-St. Clair";
		   case "Indiana":
			   return "40231 - Pet Rent-St. Clair";
		   case "Little Rock":
			   return "40231 - Pet Rent-St. Clair";
		   case "San Antonio":
			   return "40231 - Pet Rent-St. Clair";
		   case "Tulsa":
			   return "40231 - Pet Rent-St. Clair";
		   case "Georgia":
			   return "40231 - Pet Rent-St. Clair";
		   case "OKC":
			   return "40231 - Pet Rent-St. Clair";
		   case "South Carolina":
			   return "40231 - Pet Rent-St. Clair";
		   case "Florida":
			   return "40231 - Pet Rent-St. Clair";
		   case "Tennessee":
			   return "40231 - Pet Rent-St. Clair";
		   case "New Mexico":
			   return "40231 - Pet Rent-St. Clair";
		   case "Ohio":
			   return "40231 - Pet Rent-St. Clair";
		   case "Pennsylvania":
			   return "40231 - Pet Rent-St. Clair";
		   case "Lake Havasu":
			   return "40231 - Pet Rent-St. Clair";
		   case "Columbia - St Louis":
			   return "40231 - Pet Rent-St. Clair";
		   case "Maryland":
			   return "40231 - Pet Rent-St. Clair";
		   case "Virginia":
			   return "40231 - Pet Rent-St. Clair";
		   case "Boise":
			   return "40231 - Pet Rent-St. Clair";
		   case "Idaho Falls":
			   return "40231 - Pet Rent-St. Clair";
		   case "Utah":
			   return "40231 - Pet Rent-St. Clair";
		   case "Spokane":
			   return "40231 - Pet Rent-St. Clair";
		   case "Washington DC":
			   return "40231 - Pet Rent-St. Clair";
		   case "Hawaii":
			   return "40231 - Pet Rent-St. Clair";
		   case "Arizona":
			   return "43050 - Month-to-Month Admin Fee";
		   case "New Jersey":
			   return "40231 - Pet Rent-St. Clair";
		   case "Montana":
			   return "40231 - Pet Rent-St. Clair";
		   case "Delaware":
			   return "40231 - Pet Rent-St. Clair";
		   case "Chicago":
			   return "40231 - Pet Rent-St. Clair";
		   }
		return "";
	   }
	   
	   public static String getProratePetRentTaxCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "40231 - Pet Rent-St. Clair";
		   case "Alabama":
			   return "40231 - Pet Rent-St. Clair";
		   case "North Carolina":
			   return "40231 - Pet Rent-St. Clair";
		   case "Chattanooga":
			   return "40231 - Pet Rent-St. Clair";
		   case "California":
			   return "40231 - Pet Rent-St. Clair";
		   case "California PFW":
			   return "40231 - Pet Rent-St. Clair";
		   case "Chicago PFW":
			   return "40231 - Pet Rent-St. Clair";
		   case "Colorado Springs":
			   return "40231 - Pet Rent-St. Clair";
		   case "Kansas City":
			   return "40231 - Pet Rent-St. Clair";
		   case "Houston":
			   return "40231 - Pet Rent-St. Clair";
		   case "Maine":
			   return "40231 - Pet Rent-St. Clair";
		   case "Savannah":
			   return "40231 - Pet Rent-St. Clair";
		   case "Arkansas":
			   return "40231 - Pet Rent-St. Clair";
		   case "Dallas/Fort Worth":
			   return "40231 - Pet Rent-St. Clair";
		   case "Indiana":
			   return "40231 - Pet Rent-St. Clair";
		   case "Little Rock":
			   return "40231 - Pet Rent-St. Clair";
		   case "San Antonio":
			   return "40231 - Pet Rent-St. Clair";
		   case "Tulsa":
			   return "40231 - Pet Rent-St. Clair";
		   case "Georgia":
			   return "40231 - Pet Rent-St. Clair";
		   case "OKC":
			   return "40231 - Pet Rent-St. Clair";
		   case "South Carolina":
			   return "40231 - Pet Rent-St. Clair";
		   case "Florida":
			   return "40231 - Pet Rent-St. Clair";
		   case "Tennessee":
			   return "40231 - Pet Rent-St. Clair";
		   case "New Mexico":
			   return "40231 - Pet Rent-St. Clair";
		   case "Ohio":
			   return "40231 - Pet Rent-St. Clair";
		   case "Pennsylvania":
			   return "40231 - Pet Rent-St. Clair";
		   case "Lake Havasu":
			   return "40231 - Pet Rent-St. Clair";
		   case "Columbia - St Louis":
			   return "40231 - Pet Rent-St. Clair";
		   case "Maryland":
			   return "40231 - Pet Rent-St. Clair";
		   case "Virginia":
			   return "40231 - Pet Rent-St. Clair";
		   case "Boise":
			   return "40231 - Pet Rent-St. Clair";
		   case "Idaho Falls":
			   return "40231 - Pet Rent-St. Clair";
		   case "Utah":
			   return "40231 - Pet Rent-St. Clair";
		   case "Spokane":
			   return "40231 - Pet Rent-St. Clair";
		   case "Washington DC":
			   return "40231 - Pet Rent-St. Clair";
		   case "Hawaii":
			   return "40231 - Pet Rent-St. Clair";
		   case "Arizona":
			   return "43050 - Month-to-Month Admin Fee";
		   case "New Jersey":
			   return "40231 - Pet Rent-St. Clair";
		   case "Montana":
			   return "40231 - Pet Rent-St. Clair";
		   case "Delaware":
			   return "40231 - Pet Rent-St. Clair";
		   case "Chicago":
			   return "40231 - Pet Rent-St. Clair";
		   }
		return "";
	   }
	   
	   public static String getSmartHomeAgreementCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "43200 - Smart Home Fee";
		   case "Alabama":
			   return "43200 - Smart Home Fee";
		   case "North Carolina":
			   return "43200 - Smart Home Fee";
		   case "Chattanooga":
			   return "43200 - Smart Home Fee";
		   case "California":
			   return "43200 - Smart Home Fee";
		   case "California PFW":
			   return "43200 - Smart Home Fee";
		   case "Chicago PFW":
			   return "43200 - Smart Home Fee";
		   case "Colorado Springs":
			   return "43200 - Smart Home Fee";
		   case "Kansas City":
			   return "43200 - Smart Home Fee";
		   case "Houston":
			   return "43200 - Smart Home Fee";
		   case "Maine":
			   return "43200 - Smart Home Fee";
		   case "Savannah":
			   return "43200 - Smart Home Fee";
		   case "Arkansas":
			   return "43200 - Smart Home Fee";
		   case "Dallas/Fort Worth":
			   return "43200 - Smart Home Fee";
		   case "Indiana":
			   return "43200 - Smart Home Fee";
		   case "Little Rock":
			   return "43200 - Smart Home Fee";
		   case "San Antonio":
			   return "43200 - Smart Home Fee";
		   case "Tulsa":
			   return "43200 - Smart Home Fee";
		   case "Georgia":
			   return "43200 - Smart Home Fee";
		   case "OKC":
			   return "43200 - Smart Home Fee";
		   case "South Carolina":
			   return "43200 - Smart Home Fee";
		   case "Florida":
			   return "43200 - Smart Home Fee";
		   case "Tennessee":
			   return "43200 - Smart Home Fee";
		   case "New Mexico":
			   return "43200 - Smart Home Fee";
		   case "Ohio":
			   return "43200 - Smart Home Fee";
		   case "Pennsylvania":
			   return "43200 - Smart Home Fee";
		   case "Lake Havasu":
			   return "43200 - Smart Home Fee";
		   case "Columbia - St Louis":
			   return "43200 - Smart Home Fee";
		   case "Maryland":
			   return "43200 - Smart Home Fee";
		   case "Virginia":
			   return "43200 - Smart Home Fee";
		   case "Boise":
			   return "43200 - Smart Home Fee";
		   case "Idaho Falls":
			   return "43200 - Smart Home Fee";
		   case "Utah":
			   return "43200 - Smart Home Fee";
		   case "Spokane":
			   return "43200 - Smart Home Fee";
		   case "Washington DC":
			   return "43200 - Smart Home Fee";
		   case "Hawaii":
			   return "43200 - Smart Home Fee";
		   case "Arizona":
			   return "43200 - Smart Home Fee";
		   case "New Jersey":
			   return "43200 - Smart Home Fee";
		   case "Montana":
			   return "43200 - Smart Home Fee";
		   case "Delaware":
			   return "43200 - Smart Home Fee";
		   case "Chicago":
			   return "43200 - Smart Home Fee";
		   }
		return "";
	   }
	   
	   public static String getCaptiveInsurenceATXChargeCode(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "42060 - Captive Insurance - ATX";
		   case "Alabama":
			   return "42060 - Captive Insurance - ATX";
		   case "North Carolina":
			   return "42060 - Captive Insurance - ATX";
		   case "Chattanooga":
			   return "42060 - Captive Insurance - ATX";
		   case "California":
			   return "42060 - Captive Insurance - ATX";
		   case "California PFW":
			   return "42060 - Captive Insurance - ATX";
		   case "Chicago PFW":
			   return "42060 - Captive Insurance - ATX";
		   case "Colorado Springs":
			   return "42060 - Captive Insurance - ATX";
		   case "Kansas City":
			   return "42060 - Captive Insurance - ATX";
		   case "Houston":
			   return "42060 - Captive Insurance - ATX";
		   case "Maine":
			   return "42060 - Captive Insurance - ATX";
		   case "Savannah":
			   return "42060 - Captive Insurance - ATX";
		   case "Arkansas":
			   return "42060 - Captive Insurance - ATX";
		   case "Dallas/Fort Worth":
			   return "42060 - Captive Insurance - ATX";
		   case "Indiana":
			   return "42060 - Captive Insurance - ATX";
		   case "Little Rock":
			   return "42060 - Captive Insurance - ATX";
		   case "San Antonio":
			   return "42060 - Captive Insurance - ATX";
		   case "Tulsa":
			   return "42060 - Captive Insurance - ATX";
		   case "Georgia":
			   return "42060 - Captive Insurance - ATX";
		   case "OKC":
			   return "42060 - Captive Insurance - ATX";
		   case "South Carolina":
			   return "42060 - Captive Insurance - ATX";
		   case "Florida":
			   return "42060 - Captive Insurance - ATX";
		   case "Tennessee":
			   return "42060 - Captive Insurance - ATX";
		   case "New Mexico":
			   return "42060 - Captive Insurance - ATX";
		   case "Ohio":
			   return "42060 - Captive Insurance - ATX";
		   case "Pennsylvania":
			   return "42060 - Captive Insurance - ATX";
		   case "Lake Havasu":
			   return "42060 - Captive Insurance - ATX";
		   case "Columbia - St Louis":
			   return "42060 - Captive Insurance - ATX";
		   case "Maryland":
			   return "42060 - Captive Insurance - ATX";
		   case "Virginia":
			   return "42060 - Captive Insurance - ATX";
		   case "Boise":
			   return "42060 - Captive Insurance - ATX";
		   case "Idaho Falls":
			   return "42060 - Captive Insurance - ATX";
		   case "Utah":
			   return "42060 - Captive Insurance - ATX";
		   case "Spokane":
			   return "42060 - Captive Insurance - ATX";
		   case "Washington DC":
			   return "42060 - Captive Insurance - ATX";
		   case "Hawaii":
			   return "42060 - Captive Insurance - ATX";
		   case "Arizona":
			   return "42060 - Captive Insurance - ATX";
		   case "New Jersey":
			   return "42060 - Captive Insurance - ATX";
		   case "Montana":
			   return "42060 - Captive Insurance - ATX";
		   case "Delaware":
			   return "42060 - Captive Insurance - ATX";
		   case "Chicago":
			   return "42060 - Captive Insurance - ATX";
		   }
		return "";
	   }
	   
	   public static String getEnrolledINRBPForPMUse(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "YES";
		   case "Florida":
			   return "YES";
		   case "Alabama":
			   return "YES";
		   case "North Carolina":
			   return "YES";
		   case "Dallas/Fort Worth":
			   return "YES"; 
		   case "California":
			   return "YES";
		   case "California PFW":
			   return "YES";
		   case "Chattanooga":
			   return "YES";
		   case "Chicago PFW":
			   return "YES";
		   case "Colorado Springs":
			   return "YES";
		   case "Kansas City":
			   return "YES";
		   case "Houston":
			   return "YES";
		   case "Maine":
			   return "YES";
		   case "Savannah":
			   return "YES";
		   case "Arkansas":
			   return "YES";
		   case "Indiana":
			   return "YES";
		   case "Little Rock":
			   return "YES";
		   case "San Antonio":
			   return "YES";
		   case "Tulsa":
			   return "YES";
		   case "Georgia":
			   return "YES";
		   case "OKC":
			   return "YES";
		   case "South Carolina":
			   return "YES";
		   case "Tennessee":
			   return "YES";
		   case "New Mexico":
			   return "YES";
		   case "Ohio":
			   return "YES";
		   case "Pennsylvania":
			   return "YES";
		   case "Lake Havasu":
			   return "YES";
		   case "Columbia - St Louis":
			   return "YES";
		   case "Maryland":
			   return "YES";
		   case "Virginia":
			   return "YES";
		   case "Boise":
			   return "YES";
		   case "Idaho Falls":
			   return "YES";
		   case "Utah":
			   return "YES";
		   case "Spokane":
			   return "YES";
		   case "Washington DC":
			   return "YES";
		   case "Hawaii":
			   return "YES";
		   case "Arizona":
			   return "YES";
		   case "New Jersey":
			   return "YES";
		   case "Montana":
			   return "YES";
		   case "Delaware":
			   return "YES";
		   case "Chicago":
			   return "YES";
		   }
		   return "";
	   }
	   public static String getRBPenrollmentCompleteForSNUseOnly(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "Yes";
		   case "Florida":
			   return "Yes";
		   case "Alabama":
			   return "Yes";
		   case "North Carolina":
			   return "Yes";
		   case "Dallas/Fort Worth":
			   return "Yes"; 
		   case "California":
			   return "Yes";
		   case "California PFW":
			   return "Yes";
		   case "Chattanooga":
			   return "Yes";
		   case "Chicago PFW":
			   return "Yes";
		   case "Colorado Springs":
			   return "Yes";
		   case "Kansas City":
			   return "Yes";
		   case "Houston":
			   return "Yes";
		   case "Maine":
			   return "Yes";
		   case "Savannah":
			   return "Yes";
		   case "Arkansas":
			   return "Yes";
		   case "Indiana":
			   return "Yes";
		   case "Little Rock":
			   return "Yes";
		   case "San Antonio":
			   return "Yes";
		   case "Tulsa":
			   return "Yes";
		   case "Georgia":
			   return "Yes";
		   case "OKC":
			   return "Yes";
		   case "South Carolina":
			   return "Yes";
		   case "Tennessee":
			   return "Yes";
		   case "New Mexico":
			   return "Yes";
		   case "Ohio":
			   return "Yes";
		   case "Pennsylvania":
			   return "Yes";
		   case "Lake Havasu":
			   return "Yes";
		   case "Columbia - St Louis":
			   return "Yes";
		   case "Maryland":
			   return "Yes";
		   case "Virginia":
			   return "Yes";
		   case "Boise":
			   return "Yes";
		   case "Idaho Falls":
			   return "Yes";
		   case "Utah":
			   return "Yes";
		   case "Spokane":
			   return "Yes";
		   case "Washington DC":
			   return "Yes";
		   case "Hawaii":
			   return "Yes";
		   case "Arizona":
			   return "Yes";
		   case "New Jersey":
			   return "Yes";
		   case "Montana":
			   return "Yes";
		   case "Delaware":
			   return "Yes";
		   case "Chicago":
			   return "Yes";
		   }
		   return "";
	   }
	   
	   public static String getEarlyTermination(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "YES";
		   case "Florida":
			   return "YES";
		   case "Alabama":
			   return "YES";
		   case "North Carolina":
			   return "YES";
		   case "Dallas/Fort Worth":
			   return "YES"; 
		   case "California":
			   return "YES";
		   case "California PFW":
			   return "YES";
		   case "Chattanooga":
			   return "YES";
		   case "Chicago PFW":
			   return "YES";
		   case "Colorado Springs":
			   return "YES";
		   case "Kansas City":
			   return "YES";
		   case "Houston":
			   return "YES";
		   case "Maine":
			   return "YES";
		   case "Savannah":
			   return "YES";
		   case "Arkansas":
			   return "YES";
		   case "Indiana":
			   return "YES";
		   case "Little Rock":
			   return "YES";
		   case "San Antonio":
			   return "Yes";
		   case "Tulsa":
			   return "YES";
		   case "Georgia":
			   return "YES";
		   case "OKC":
			   return "YES";
		   case "South Carolina":
			   return "YES";
		   case "Tennessee":
			   return "YES";
		   case "New Mexico":
			   return "YES";
		   case "Ohio":
			   return "YES";
		   case "Pennsylvania":
			   return "YES";
		   case "Lake Havasu":
			   return "Yes";
		   case "Columbia - St Louis":
			   return "Yes";
		   case "Maryland":
			   return "Yes";
		   case "Virginia":
			   return "Yes";
		   case "Boise":
			   return "Yes";
		   case "Idaho Falls":
			   return "Yes";
		   case "Utah":
			   return "Yes";
		   case "Spokane":
			   return "Yes";
		   case "Washington DC":
			   return "Yes";
		   case "Hawaii":
			   return "Yes";
		   case "Arizona":
			   return "Yes";
		   case "New Jersey":
			   return "Yes";
		   case "Montana":
			   return "Yes";
		   case "Delaware":
			   return "Yes";
		   case "Chicago":
			   return "YES";
		   }
		   return "";
	   }
	   
	   public static String getNeedsNewLease(String company)
	   {
		   switch(company)
		   {
		   case "Austin":
			   return "NO";
		   case "California":
			   return "NO";
		   case "California PFW":
			   return "No";
		   case "Alabama":
			   return "NO";
		   case "North Carolina":
			   return "NO";
		   case "Dallas/Fort Worth":
			   return "No"; 
		   case "Chattanooga":
			   return "NO";
		   case "Chicago PFW":
			   return "No";
		   case "Colorado Springs":
			   return "NO";
		   case "Kansas City":
			   return "NO";
		   case "Houston":
			   return "NO";
		   case "Maine":
			   return "NO";
		   case "Savannah":
			   return "NO";
		   case "Arkansas":
			   return "NO";
		   case "Indiana":
			   return "NO";
		   case "Little Rock":
			   return "NO";
		   case "San Antonio":
			   return "NO";
		   case "Tulsa":
			   return "No";
		   case "Georgia":
			   return "NO";
		   case "OKC":
			   return "No";
		   case "South Carolina":
			   return "No";
		   case "Florida":
			   return "No";
		   case "Tennessee":
			   return "No";
		   case "New Mexico":
			   return "NO";
		   case "Ohio":
			   return "NO";
		   case "Pennsylvania":
			   return "No";
		   case "Lake Havasu":
			   return "No";
		   case "Columbia - St Louis":
			   return "NO";
		   case "Maryland":
			   return "NO";
		   case "Virginia":
			   return "NO";
		   case "Boise":
			   return "NO";
		   case "Idaho Falls":
			   return "NO";
		   case "Utah":
			   return "NO";
		   case "Spokane":
			   return "NO";
		   case "Washington DC":
			   return "NO";
		   case "Hawaii":
			   return "No";
		   case "Arizona":
			   return "NO";
		   case "New Jersey":
			   return "NO";
		   case "Montana":
			   return "NO";
		   case "Delaware":
			   return "NO";
		   case "Chicago":
			   return "NO";
		   }
		   return "";
	   }
	   
	   public static String getCompanyCode(String company)
		{
			switch(company)
			{
			case "Austin":
				return "Irvin";
			case "Alabama":
				return "AL";
			case "Arizona":
				return "AZ";
			case "Arkansas":
				return "AR";
			case "Dallas/Fort Worth":
				return "DFW";
			case "Florida":
				return "FL";
			case "Georgia":
				return "GA";
			case "Indiana":
				return "IN";
			case "Little Rock":
				return "LR";
			case "North Carolina":
				return "NC";
			case "OKC":
				return "OKC";
			case "San Antonio":
				return "SATX";
			case "South Carolina":
				return "SC";
			case "Tennessee":
				return "TN";
			case "Tulsa":
				return "TUL";
			case "Chattanooga":
				return "CHAT";
			case "Colorado Springs":
				return "";
			case "Kansas City":
				return "KC";
			case "Houston":
				return "Houston";
			case "Maine":
				return "ME";
			case "Savannah":
				return "SAV";
			case "New Jersey":
				return "NJ";
				
			}
			return "";
		}

	   
	}


