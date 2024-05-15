package DataReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.TimeoutException;

import io.github.bonigarcia.wdm.WebDriverManager;
import mainPackage.AppConfig;
import mainPackage.GetterAndSetterClass;
import mainPackage.PropertyWare;
import mainPackage.PropertyWare_updateValues;
import mainPackage.RunnerClass;
import mainPackage.TessaractTest;

public class ReadingLeaseAgreements {

	
	public static boolean dataRead(String fileName) throws Exception 
	{
	   
		String failedReason = "";
		String text="";
		String commencementDate ="";
		String expirationDate="";
		String proratedRent="";
		String proratedRentDate="";
		String monthlyRent="";
		boolean monthlyRentTaxFlag=false;
		String monthlyRentTaxAmount="";
		String residentBenefitsPackage="";
		boolean residentBenefitsPackageAvailabilityCheck=false;
		String totalMonthlyRentWithTax="";
		boolean residentBenefitsPackageTaxAvailabilityCheck=false;
		String residentBenefitsPackageTaxAmount="";
		boolean RBPOptOutAddendumCheck = false;
		String increasedRent_amount="";
		String increasedRent_newStartDate="";
		boolean incrementRentFlag=false;
		String increasedRent_previousRentEndDate="";
		
		List<String> allIncreasedRent_StartDate=new ArrayList();
		
		
		List<String> allIncreasedRent_amounts=new ArrayList();
		
	
	
         try {
        	 File file = RunnerClass.getLastModified(fileName);
 			//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Lease_02.22_02.23_200_Doc_Johns_Dr_ATX_Smith (3).pdf");
 			FileInputStream fis = new FileInputStream(file);
 			PDDocument document = PDDocument.load(fis);
 		    text = new PDFTextStripper().getText(document);
 		    text = text.replaceAll(System.lineSeparator(), " ");
 		    text = text.trim().replaceAll(" +", " ");
 		    text = text.toLowerCase();
 		 
 		  
		       
		            
			//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Lease_02.22_02.23_200_Doc_Johns_Dr_ATX_Smith (3).pdf");
		
			//System.out.println(text);
			System.out.println("------------------------------------------------------------------");
			try {
				commencementDate = dataExtractionClass.getDates(text,"term:^shall commence on@term:^commencement date:@term^commences on");
				System.out.println("Start date = "+ commencementDate);
				GetterAndSetterClass.setStartDate(RunnerClass.convertDate(commencementDate));
			}
			catch(Exception e) {
				System.out.println("Error While Extracting Start Date");
				failedReason =  failedReason+","+"Issue in Extracting Start Date";
				GetterAndSetterClass.setFailedReason(failedReason);
				return false;
			}
			try {
				expirationDate = dataExtractionClass.getDates(text,"term:^location of the premises\\) on@term:^expiration date:@term^expires on");
				System.out.println("End date = "+ expirationDate);
				GetterAndSetterClass.setEndDate(RunnerClass.convertDate(expirationDate));
			}
			catch(Exception e) {
				GetterAndSetterClass.setEndDate("Error");
				System.out.println("Error While Extracting End Date");
				
			}
			try {
				proratedRentDate = dataExtractionClass.getDates(text,"rent:^prorated rent\\, on or before@rent:^Prorated Rent: On or before");
				System.out.println("Prorated Rent Date = "+ proratedRentDate);
				GetterAndSetterClass.setProrateRentDate(proratedRentDate);
				
			}
			catch(Exception e) {
				GetterAndSetterClass.setProrateRentDate("Error");
				System.out.println("Error While Extracting Prorated Rent Date");
				
			}
	
			try {
				monthlyRent =dataExtractionClass.getValues(text,"Monthly Rent:^Monthly Rent due in the amount of^@Monthly Rent:^Tenant will pay Landlord monthly rent in the amount of^@monthly installments,^on or before the 1st day of each month, in the amount of^@monthly installments,^Tenant will pay Landlord monthly rent in the amount of^");
				System.out.println("Monthly Rent Amount = "+ monthlyRent);
				GetterAndSetterClass.setMonthlyRent(monthlyRent);
			}
			catch(Exception e) {
				GetterAndSetterClass.setMonthlyRent("Error");
				System.out.println("Error While Extracting Monthly Rent Amount");
				
			}
		
			
			try {
				monthlyRentTaxFlag =dataExtractionClass.getFlags(text,"rent:^plus the additional amount of $@rent:^plus applicable sales tax and administrative fees of $");
				System.out.println("Monthly Rent Tax Flag = "+ monthlyRentTaxFlag);
				GetterAndSetterClass.setMonthlyRentTaxFlag(monthlyRentTaxFlag);
			}
			catch(Exception e) {
				GetterAndSetterClass.setMonthlyRentTaxFlag(false);
				System.out.println("Error While Extracting Monthly Rent Tax Flag");
				
			}
			try {
				if(monthlyRentTaxFlag == true) {
					monthlyRentTaxAmount= dataExtractionClass.getValues(text, "Monthly Rent:^plus applicable sales tax and administrative fees of^@Monthly Rent:^plus the additional amount of^@monthly installments,^plus the additional amount of^");
					System.out.println("Monthly Rent Tax Amount = "+ monthlyRentTaxAmount);
					GetterAndSetterClass.setMonthlyRentTaxAmount(monthlyRentTaxAmount);
					if(RunnerClass.hasSpecialCharacters(monthlyRentTaxAmount.trim())==true||monthlyRentTaxAmount.trim().equalsIgnoreCase("0.00")||monthlyRentTaxAmount.trim().equalsIgnoreCase("N/A")||monthlyRentTaxAmount.trim().equalsIgnoreCase("n/a")||monthlyRentTaxAmount.trim().equalsIgnoreCase("na")||monthlyRentTaxAmount.trim().equalsIgnoreCase(""))
			    	{
			    		monthlyRentTaxAmount = "Error";
			    		
			    	}
			    	else
			    	{
			    		try {
			    			totalMonthlyRentWithTax = dataExtractionClass.getValues(text, "Monthly Rent:^for a total monthly Rent of^@Monthly Rent:^assessed, for a total of^@monthly installments,^assessed, for a total of@Monthly Rent:^for a total of");
				    		System.out.println("Total Monthly Rent With Tax Amount = "+ totalMonthlyRentWithTax);
				    		GetterAndSetterClass.setTotalMonthlyRentWithTax(totalMonthlyRentWithTax);
			    		}
			    		catch(Exception e) {
			    			GetterAndSetterClass.setTotalMonthlyRentWithTax("Error");
							System.out.println("Error While Extracting Total Monthly Rent With Tax Amount ");
							
						}
			    		
			    	}
					
				}
				else {
					GetterAndSetterClass.setMonthlyRentTaxAmount("Error");
				}
				//Even if there Flag text available and Flag says True but the TAx amount is empty, then check again if the amount is Error, if Error, mark flag false.
				if(GetterAndSetterClass.getMonthlyRentTaxAmount().equals("Error"))
					GetterAndSetterClass.setMonthlyRentTaxFlag(false);
			}
			
			
			
			catch(Exception e) {
				GetterAndSetterClass.setMonthlyRentTaxAmount("Error");
				System.out.println("Error While Extracting Total Monthly Rent Amount");
				
			}

			try {
				allIncreasedRent_amounts = dataExtractionClass.getMultipleValues(text, "Monthly Rent:^Monthly Rent due in the amount of^@Monthly Rent:^Tenant will pay Landlord monthly rent in the amount of^@monthly installments,^on or before the 1st day of each month, in the amount of^@monthly installments,^Tenant will pay Landlord monthly rent in the amount of^") ;
				if (allIncreasedRent_amounts.size() > 1) {
					incrementRentFlag = true;
					System.out.println("Increment Rent Flag = "+ incrementRentFlag);
					GetterAndSetterClass.setIncrementRentFlag(incrementRentFlag);
					GetterAndSetterClass.setIncreasedRentAmounts((ArrayList<String>) allIncreasedRent_amounts);
		            //double firstValue = Double.parseDouble(allIncreasedRent_amounts.get(0).replace(",",""));
		           
		            allIncreasedRent_StartDate = dataExtractionClass.getMultipleDates(text, "Monthly Rent:^Month");
		            GetterAndSetterClass.setIncreasedRentDates((ArrayList<String>) allIncreasedRent_StartDate);
		              }
		            
				else {
					GetterAndSetterClass.setIncrementRentFlag(incrementRentFlag);
					GetterAndSetterClass.setIncreasedRent_amount("Error");
					
				}
				
			/*	increasedRent_previousRentEndDate =  dataExtractionClass.getDates(text,"Monthly Rent:^to Month");
				if(!increasedRent_previousRentEndDate.equalsIgnoreCase("Error")) {
					GetterAndSetterClass.setIncreasedRent_previousRentEndDate(increasedRent_previousRentEndDate);
				}
				else {
					GetterAndSetterClass.setIncreasedRent_previousRentEndDate("Error");
				}	*/
			}
			catch(Exception e) {
				e.printStackTrace();
				GetterAndSetterClass.setIncrementRentFlag(false);
				GetterAndSetterClass.setIncreasedRent_amount("Error");
				System.out.println("Error While Extracting Increment Rent Details");
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			try {
				residentBenefitsPackageAvailabilityCheck = dataExtractionClass.getFlags(text,"rent:^Resident Benefits Package (�RBP�) Program and Fee:@rent:^Resident Benefits Package (RBP) Lease Addendum@rent:^Resident Benefits Package Opt\\-Out Addendum");
				System.out.println("resident benefit package Availability Flag = "+ residentBenefitsPackageAvailabilityCheck); 
				GetterAndSetterClass.setresidentBenefitsPackageAvailabilityCheckFlag(residentBenefitsPackageAvailabilityCheck);
			}
			catch(Exception e) {
				GetterAndSetterClass.setresidentBenefitsPackageAvailabilityCheckFlag(false);
				System.out.println("Error While Extracting RBP Flag");
				
			}
			try {
				if(residentBenefitsPackageAvailabilityCheck == true) {
					residentBenefitsPackage = dataExtractionClass.getValues(text, "Program and Fee:^Tenant agrees to pay a Resident Benefits Package Fee of^");
					System.out.println("Resident Benefit Package Fee = "+ residentBenefitsPackage);
					GetterAndSetterClass.setresidentBenefitsPackage(residentBenefitsPackage);
				}
				else {
					GetterAndSetterClass.setresidentBenefitsPackage("Error");
				}
			}
			catch(Exception e) {
				GetterAndSetterClass.setresidentBenefitsPackage("Error");
				System.out.println("Error While Extracting RBP Fee");
			}
			try {
				if(text.contains(("TOTAL CHARGE TO TENANT $").toLowerCase()))
			    {
			    	residentBenefitsPackageTaxAvailabilityCheck = true;
			    	GetterAndSetterClass.setResidentBenefitsPackageTaxAvailabilityCheck(residentBenefitsPackageTaxAvailabilityCheck);
			    	if(residentBenefitsPackageTaxAvailabilityCheck == true) {
			    		try {
			    			 residentBenefitsPackageTaxAmount  = dataExtractionClass.getValues(text, "Resident Benefits Package (�RBP�) Program and Fee:^(Inclusive of@TOTAL CHARGE TO TENANT^(Inclusive of");
			    			 GetterAndSetterClass.setResidentBenefitsPackageTaxAmount(residentBenefitsPackageTaxAmount);
			    		}
			    		catch(Exception e) {
			    			GetterAndSetterClass.setResidentBenefitsPackageTaxAmount("Error");
							 System.out.println("Error While Extracting RBP Tax Amount");
						}
			    	}
			    }
				else {
					residentBenefitsPackageTaxAvailabilityCheck = false;
					GetterAndSetterClass.setResidentBenefitsPackageTaxAvailabilityCheck(residentBenefitsPackageTaxAvailabilityCheck);
				}
			}
			catch(Exception e) {
				GetterAndSetterClass.setResidentBenefitsPackageTaxAvailabilityCheck(false);
				 System.out.println("Error While Extracting RBP Tax Flag");
				
			}
		
		
		   
		  //RBP Opt - Out Addendum Check
		    try
		    {
		    	 if(text.contains(("Resident Benefits Package Opt-Out Addendum").toLowerCase()))
		 	    {
		 	    	RBPOptOutAddendumCheck= true;
		 	    	
		 	    }
		    	 GetterAndSetterClass.setRBPOptOutAddendumCheck(RBPOptOutAddendumCheck);
		    }
		    catch(Exception e) {
		    	GetterAndSetterClass.setRBPOptOutAddendumCheck(false);
		    	System.out.println("Error While Extracting RBP Opt-Out Addendum Flag");
		    }
		   
		   
		    try {
		    	proratedRent = dataExtractionClass.getValues(text, "Prorated Rent:^Tenant will pay Landlord@prorated rent,^Tenant will pay Landlord@Prorated Rent:^Tenant will pay Landlord");
				System.out.println("Prorated rent = "+ proratedRent);
				GetterAndSetterClass.setProrateRent(proratedRent);
		    }
		    catch(Exception e) {
		    	GetterAndSetterClass.setProrateRent("Error");
				System.out.println("Error While Extracting Prorated rent");		
			}
			
	
		
    	
         
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	    
	}
}
	
	

