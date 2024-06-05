package mainPackage;

import org.openqa.selenium.By;

public class Locators 
{
	public static By userName = By.id("loginEmail");
	public static By password = By.name("password");
	public static By signMeIn = By.xpath("//*[@value='Sign Me In']");
	public static By loginError = By.xpath("//*[@class='toast toast-error']");
	
	public static By searchbox = By.name("eqsSearchText");
	public static By dashboardsTab = By.linkText("Dashboards");
	public static By searchingLoader = By.xpath("//*[@id='eqsResult']/h1");
	public static By noItemsFoundMessagewhenLeaseNotFound = By.xpath("//*[text()='No Items Found']");
	public static By selectSearchedLease = By.xpath("//*[@class='results']/descendant::li/a");
	public static By getLeaseCDEType = By.xpath("//*[@id='summary']/table[1]/tbody/tr[3]/td");
    public static By leasesTab = By.xpath("//*[@class='tabbedSection']/a[4]");	
    public static By leasesTab2 = By.xpath("(//a[text()='Leases'])[2]");
    public static By popUpAfterClickingLeaseName = By.xpath("//*[@id='viewStickyNoteForm']");
    public static By scheduledMaintanancePopUp = By.xpath("//*[text()='Scheduled Maintenance Notification']");
    public static By scheduledMaintanancePopUp2 = By.xpath("//*[@class='active full-buttons ']");
    public static By scheduledMaintanancePopUpOkButton = By.id("alertDoNotShow");
    public static By scheduledMaintanancePopUpCloseButton = By.xpath("//*[@data-step='skip']");
    public static By RCDetails = By.xpath("//*[contains(text(),'Resident Coordinator [Name/Phone/Email]')]/following::div[1]");
    public static By APMField = By.xpath("//*[text()='APM']/following::input[1]");
    public static By RC = By.xpath("//*[text()='RC']/following::input[1]");
    public static By leaseStartDate_PW = By.xpath("//*[@id='infoTable']/tbody/tr[3]/td[1]");
    public static By leaseEndDate_PW = By.xpath("//*[@id='infoTable']/tbody/tr[3]/td[2]");
    public static By popupClose = By.xpath("//*[@id='editStickyBtnDiv']/input[2]");
    public static By notesAndDocs = By.id("notesAndDocuments");
    public static By documentsList = By.xpath("//*[@id='documentHolderBody']/tr/td[1]/a"); 
    public static By searchedLeaseCompanyHeadings = By.xpath("//*[@id='eqsResult']/div/div/h1");
    public static By checkPortfolioType = By.xpath("//*[@title='Click to jump to portfolio']");
    public static By status = By.xpath("//*[@id='infoTable']/tbody/tr[6]/td[2]");
    
    public static By scheduleMaintananceIFrame = By.xpath("//iframe[@srcdoc='<meta name=\"referrer\" content=\"origin\" />']");
    public static By scheduleMaintanancePopUp2 = By.xpath("//section[@role='dialog']");
    public static By maintananceCloseButton = By.xpath("//a[@aria-label='Close modal']");
    
    public static By ledgerTab = By.id("tab2");
    public static By newCharge = By.xpath("//*[@value='New Charge']");
    public static By accountDropdown = By.name("charge.GLAccountID");
    public static By arizonaAccountDropdown = By.xpath("//*[@name='charge.GLAccountID']//following-sibling::span");
    public static By chargeCodesList = By.xpath("//*[@name='charge.GLAccountID']/optgroup/option");
    public static By accountList = By.xpath("(//*[@class='edit'])[9]/descendant::select[1]/optgroup/option");
    public static By referenceName = By.name("charge.refNo");
    public static By moveInCharges_List =By.xpath("//*[@id='ledgerDataTable']/tbody/tr/td[5]");
    public static By moveInCharge_List_Amount = By.xpath("//*[@id='ledgerDataTable']/tbody/tr/td[8]");
    public static By moveInChargeAmount = By.name("charge.editAmountAsString");
    public static By moveInChargeDate = By.name("charge.dateAsString");
    public static By moveInChargeSave = By.xpath("//*[@value='Save']");
    public static By marketDropdown = By.id("switchAccountSelect");
    public static By moveInChargeCancel = By.xpath("//*[@value='Cancel']");
    public static By moveInChargeSaveButton = By.xpath("//*[@value='Save']");
    public static By summaryTab = By.xpath("//*[text()='Summary']");
    public static By summaryEditButton = By.xpath("//*[@value='Edit']");
    public static By newAutoCharge = By.xpath("//*[@value='New Auto Charge']");
    public static By rcField = By.xpath("//*[text()='RC']/following::input[1]");
    public static By autoCharge_Description = By.name("charge.description");
    public static By autoCharge_List = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[1]");
    public static By autoCharge_List_Amounts = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[3]");
    public static By autoCharge_List_StartDates = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[5]");
    public static By autoCharge_List_EndDates = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[6]");
    public static By autoCharge_StartDate = By.name("charge.startDateAsString");
    public static By autoCharge_EndDate = By.name("charge.endDateAsString");
    public static By autoCharge_Amount = By.name("charge.amountAsString");
    public static By autoCharge_CancelButton = By.xpath("//*[@id='editAutoChargeForm']/descendant::div[4]/input[2]");
    public static By autoCharge_SaveButton = By.xpath("(//*[@class='primaryButtons'])[3]/input[1]");
    public static By autoCharge_refField = By.name("charge.refNo");
    public static By earlyTermFee2x = By.xpath("//*[text()='Early Term Fee 2x Rent?']/following::select[1]");
    public static By clientType = By.xpath("//*[text()='Client Type']//following::select[1]");
    public static By earlyTermFee2x_textbox1 = By.xpath("//*[text()='Early Term Fee 2x Rent?']/following::input[1]");
    public static By earlyTermFee2x_2 = By.xpath("//*[text()='Early Term Fee 2x Rent??']/following::select[1]");
    public static By earlyTermFee2x_textbox2 = By.xpath("//*[text()='Early Term Fee 2x Rent??']/following::input[1]");
    public static By earlyTermFee2x_textBox = By.xpath("//*[text()='Early Term Fee 2x Rent?']/following::input[1]");
    public static By enrolledInFilterEasy = By.xpath("//*[text()='Enrolled in FilterEasy rev']/following::select[1]");
    public static By enrolledInFilterEasy_List = By.xpath("//*[text()='Enrolled in FilterEasy rev']/following::select[1]");
    public static By RBPOptOut = By.xpath("//*[text()='RBP opt out']/following::Select[1]");
    public static By enrolledInRBPForPMUse = By.xpath("//*[contains(text(),'Enrolled in RBP')]/following::Select[1]");
    public static By earlyTermination_List = By.xpath("//*[text()='Early Term Fee 2x Rent?']/following::select[1]");
    public static By earlyTermination_List_2 = By.xpath("//*[text()='Early Term Fee 2x Rent??']/following::select[1]");
    public static By needsNewLease = By.xpath("//*[text()='Needs New Lease']/following::select[1]");
    public static By needsNewLease_List = By.xpath("//*[text()='Needs New Lease']/following::select[1]");
    public static By leaseOccupants = By.xpath("//*[text()='Lease Occupants']/following::input[1]");
    public static By otherOccupants = By.xpath("//*[text()='Other Occupants']/following::input[1]");
    public static By pet1Type = By.xpath("//*[text()='Pet 1 Type']/following::input[1]");
    public static By pet1Breed = By.xpath("//*[text()='Pet 1 Breed']/following::input[1]");
    public static By pet1Weight = By.xpath("//*[text()='Pet 1 Weight']/following::input[1]");
    
    public static By pet2Type = By.xpath("//*[text()='Pet 2 Type']/following::input[1]");
    public static By pet2Breed = By.xpath("//*[text()='Pet 2 Breed']/following::input[1]");
    public static By pet2Weight = By.xpath("//*[text()='Pet 2 Weight']/following::input[1]");
    
    public static By pet3Type = By.xpath("//*[text()='Pet 3 Type']/following::input[1]");
    public static By pet3Breed = By.xpath("//*[text()='Pet 3 Breed']/following::input[1]");
    public static By pet3Weight = By.xpath("//*[text()='Pet 3 Weight']/following::input[1]");
    
    public static By serviceAnimalType = By.xpath("//*[text()='Pet 2 Type']/following::input[1]");
    public static By serviceAnimalBreed = By.xpath("//*[text()='Pet 2 Breed']/following::input[1]");
    public static By serviceAnimalWeight = By.xpath("//*[text()='Pet 2 Weight']/following::input[1]");
    public static By petAmount = By.xpath("//*[text()='Pet Rent Amount']/following::input[1]");
    public static By petAmount2 = By.xpath("//*[text()='Pet Rent Amount']/following::input[1]");
    public static By petDepositAmount = By.xpath("//*[text()='Pet Deposit Amount']/following::input[1]");
    public static By tenantOneTimePetFee = By.xpath("//*[contains(text(),'ime Pet Fee')]/following::input[1]");
    public static By tenantOneTimePetFee2 = By.xpath("//*[text()='Tenant One-Time Pet Fee']/following::input[1]");
    public static By petSpecialProvisions = By.xpath("//*[text()='Pet Special Provisions']/following::input[1]");
    public static By lateFeeDueDay = By.name("entity.lateFee.dueDay");
    public static By initialFee = By.name("entity.lateFee.initial");
    public static By perDayFee = By.xpath("(//*[text()='Per Day Fee'])[2]/following::input[1]");
    public static By initialFeeDropdown = By.name("entity.lateFee.feeTypeMethodInitial");
    public static By perDayFeeDropdown = By.name("entity.lateFee.feeTypeMethod");
    public static By maximumYesNoDropdown = By.name("entity.lateFee.limitAmount");
    public static By maximumDropdown2 = By.name("entity.lateFee.limitTypeMethod");
    public static By maximumDatField = By.name("entity.lateFee.maxAmountAsString");
    public static By saveLease = By.xpath("(//*[@class='primaryButtons'])[2]/input[1]");
    public static By initialMonthlyRent = By.xpath("//*[text()='Initial Monthly Rent']/following::input[1]");
    public static By initialPetRentAmount = By.xpath("//*[text()='Initial Pet Rent Amount']/following::input[1]");
    public static By petRentAmount = By.xpath("//*[text()='Pet Rent Amount']/following::input[1]");
    public static By currentMonthlyRent = By.xpath("//*[text()='Current Monthly Rent']/following::input[1]");
    public static By somethingWrongInSavingCharge = By.xpath("//*[text()='You must correct the following:']");
    public static By monthlyRentAmount = By.xpath("//*[text()='Monthly Rent Amount']/following::input[1]");
    
    public static By lateFeeType = By.name("entity.lateFee.feeType");
    public static By flatFee = By.xpath("//*[text()='Flat Fee']/following::input[1]");
    public static By lateFeePercentage =By.xpath("//*[text()='Percentage']/following::input[1]"); 
    
    public static By residentBenefitsPackage =By.xpath("//*[contains(text(),'Enrolled in RBP')]/following::select[1]"); //By.xpath("//*[text()='RBP Enrollment Complete *For SN Use Only*']/preceding::th[1]/following::select[1]");
    
    public static By cancelLease = By.xpath("(//*[@class='primaryButtons'])[2]/input[2]");
    
    public static By advancedSearch = By.linkText("Advanced Search >>");
    public static By advancedSearch_buildingsSection = By.id("searchResultTable_buildings");
    public static By advancedSearch_buildingAddresses = By.xpath("//*[@id='searchResultTable_buildings']/following::table[1]/tbody/tr/td[2]/a");
    
    public static By baseRent = By.xpath("//*[text()='Base Rent']/following::input[1]");
    public static By buildingAddress = By.xpath("//*[@title='This address has been validated']/following::td[1]");
    
    public static By captiveInsurence = By.xpath("//*[text()='Captive Insurance']/following::select[1]");
    
    //Renewal Status
    public static By renewalStatus = By.xpath("//th[text()='Renewal Status']//following::td[1]/select");
    public static By priorMonthlyRent = By.xpath("//*[text()='Prior Monthly Rent']/following::input[1]");
    
    //Building Click on Lease Page
    public static By buildingLinkInLeasePage = By.xpath("//a[@title=\"House\"]");



}
