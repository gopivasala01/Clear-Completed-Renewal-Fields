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
import mainPackage.PDFReader;
import mainPackage.PropertyWare;
import mainPackage.PropertyWare_updateValues;
import mainPackage.RunnerClass;
import mainPackage.TessaractTest;

public class ReadingLeaseAgreements {
	public static String format1Text = "The parties to this Lease are".toLowerCase();
	public static String format2Text = "THIS RESIDENTIAL LEASE AGREEMENT".toLowerCase();
	
	public static boolean dataRead(String fileName,String SNo,String company) throws Exception 
	{
	   
		String failedReason = "";
		String text="";
		String commencementDate ="";
		String expirationDate="";
		String proratedRent="";
		String proratedRentDate="";
		String increasedRent_previousRentEndDate="";
		String monthlyRent="";
		boolean monthlyRentTaxFlag=false;
		String monthlyRentTaxAmount="";
		String adminFee="";
		String occupants="";
		String residentBenefitsPackage="";
		boolean residentBenefitsPackageAvailabilityCheck=false;
		boolean HVACFilterFlag=false;
		boolean petFlag=false;
		boolean serviceAnimalFlag=false;
		boolean concessionAddendumFlag=false;
		String airFilterFee="";
		String prepaymentCharge="";
		String proratedPetRent="";
		String petRent="";
		String petRentTaxAmount="";
		String totalPetRentWithTax="";
		String petOneTimeNonRefundableFee="";
		String smartHomeAgreementFee="";
		boolean smartHomeAgreementCheck=false;
		String earlyTermination="";
		String totalMonthlyRentWithTax="";
		boolean residentBenefitsPackageTaxAvailabilityCheck=false;
		String residentBenefitsPackageTaxAmount="";
		String increasedRent_amount="";
		String increasedRent_newStartDate="";
		boolean incrementRentFlag=false;
		boolean petRentTaxFlag = false;
		String prorateRUBS="";
		String RUBS="";
		String captiveInsurenceATXFee = "";
		String petSecurityDeposit="";
		boolean residentUtilityBillFlag = false;
		boolean captiveInsurenceATXFlag = false;
		boolean petInspectionFeeFlag= false;
		boolean petSecurityDepositFlag = false;
		boolean HVACFilterOptOutAddendum =false;
		boolean RBPOptOutAddendumCheck = false;
		boolean floridaLiquidizedAddendumOption1Check = false;
		String PDFFormatType = "";
		
	
	
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
				RunnerClass.setStartDate(RunnerClass.convertDate(commencementDate));
			}
			catch(Exception e) {
				System.out.println("Error While Extracting Start Date");
				failedReason =  failedReason+","+"Issue in Extracting Start Date";
				RunnerClass.setFailedReason(failedReason);
				return false;
			}
			try {
				expirationDate = dataExtractionClass.getDates(text,"term:^location of the premises\\) on@term:^expiration date:@term^expires on");
				System.out.println("End date = "+ expirationDate);
				RunnerClass.setEndDate(RunnerClass.convertDate(expirationDate));
			}
			catch(Exception e) {
				RunnerClass.setEndDate("Error");
				System.out.println("Error While Extracting End Date");
				
			}
			try {
				proratedRentDate = dataExtractionClass.getDates(text,"rent:^prorated rent\\, on or before@rent:^Prorated Rent: On or before");
				System.out.println("Prorated Rent Date = "+ proratedRentDate);
				RunnerClass.setProrateRentDate(proratedRentDate);
				
			}
			catch(Exception e) {
				RunnerClass.setProrateRentDate("Error");
				System.out.println("Error While Extracting Prorated Rent Date");
				
			}
	
			try {
				monthlyRent =dataExtractionClass.getValues(text,"Monthly Rent:^Monthly Rent due in the amount of^@Monthly Rent:^Tenant will pay Landlord monthly rent in the amount of^@monthly installments,^on or before the 1st day of each month, in the amount of^@monthly installments,^Tenant will pay Landlord monthly rent in the amount of^");
				System.out.println("Monthly Rent Amount = "+ monthlyRent);
				RunnerClass.setMonthlyRent(monthlyRent);
			}
			catch(Exception e) {
				RunnerClass.setMonthlyRent("Error");
				System.out.println("Error While Extracting Monthly Rent Amount");
				
			}
		
			
			try {
				monthlyRentTaxFlag =dataExtractionClass.getFlags(text,"rent:^plus the additional amount of $@rent:^plus applicable sales tax and administrative fees of $");
				System.out.println("Monthly Rent Tax Flag = "+ monthlyRentTaxFlag);
				RunnerClass.setMonthlyRentTaxFlag(monthlyRentTaxFlag);
			}
			catch(Exception e) {
				RunnerClass.setMonthlyRentTaxFlag(false);
				System.out.println("Error While Extracting Monthly Rent Tax Flag");
				
			}
			try {
				if(monthlyRentTaxFlag == true) {
					monthlyRentTaxAmount= dataExtractionClass.getValues(text, "Monthly Rent:^plus applicable sales tax and administrative fees of^@Monthly Rent:^plus the additional amount of^@monthly installments,^plus the additional amount of^");
					System.out.println("Monthly Rent Tax Amount = "+ monthlyRentTaxAmount);
					RunnerClass.setMonthlyRentTaxAmount(monthlyRentTaxAmount);
					if(RunnerClass.hasSpecialCharacters(monthlyRentTaxAmount.trim())==true||monthlyRentTaxAmount.trim().equalsIgnoreCase("0.00")||monthlyRentTaxAmount.trim().equalsIgnoreCase("N/A")||monthlyRentTaxAmount.trim().equalsIgnoreCase("n/a")||monthlyRentTaxAmount.trim().equalsIgnoreCase("na")||monthlyRentTaxAmount.trim().equalsIgnoreCase(""))
			    	{
			    		monthlyRentTaxAmount = "Error";
			    		
			    	}
			    	else
			    	{
			    		try {
			    			totalMonthlyRentWithTax = dataExtractionClass.getValues(text, "Monthly Rent:^for a total monthly Rent of^@Monthly Rent:^assessed, for a total of^@monthly installments,^assessed, for a total of@Monthly Rent:^for a total of");
				    		System.out.println("Total Monthly Rent With Tax Amount = "+ totalMonthlyRentWithTax);
				    		RunnerClass.setTotalMonthlyRentWithTax(totalMonthlyRentWithTax);
			    		}
			    		catch(Exception e) {
			    			RunnerClass.setTotalMonthlyRentWithTax("Error");
							System.out.println("Error While Extracting Total Monthly Rent With Tax Amount ");
							
						}
			    		
			    	}
					
				}
				else {
					RunnerClass.setMonthlyRentTaxAmount("Error");
				}
				//Even if there Flag text available and Flag says True but the TAx amount is empty, then check again if the amount is Error, if Error, mark flag false.
				if(RunnerClass.getMonthlyRentTaxAmount().equals("Error"))
					RunnerClass.setMonthlyRentTaxFlag(false);
			}
			
			
			
			catch(Exception e) {
				RunnerClass.setMonthlyRentTaxAmount("Error");
				System.out.println("Error While Extracting Total Monthly Rent Amount");
				
			}

			try {
				residentBenefitsPackageAvailabilityCheck = dataExtractionClass.getFlags(text,"rent:^Resident Benefits Package (�RBP�) Program and Fee:@rent:^Resident Benefits Package (RBP) Lease Addendum@rent:^Resident Benefits Package Opt\\-Out Addendum");
				System.out.println("resident benefit package Availability Flag = "+ residentBenefitsPackageAvailabilityCheck); 
				RunnerClass.setresidentBenefitsPackageAvailabilityCheckFlag(residentBenefitsPackageAvailabilityCheck);
			}
			catch(Exception e) {
				RunnerClass.setresidentBenefitsPackageAvailabilityCheckFlag(false);
				System.out.println("Error While Extracting RBP Flag");
				
			}
			try {
				if(residentBenefitsPackageAvailabilityCheck == true) {
					residentBenefitsPackage = dataExtractionClass.getValues(text, "Program and Fee:^Tenant agrees to pay a Resident Benefits Package Fee of^");
					System.out.println("Resident Benefit Package Fee = "+ residentBenefitsPackage);
					RunnerClass.setresidentBenefitsPackage(residentBenefitsPackage);
				}
				else {
					RunnerClass.setresidentBenefitsPackage("Error");
				}
			}
			catch(Exception e) {
				RunnerClass.setresidentBenefitsPackage("Error");
				System.out.println("Error While Extracting RBP Fee");
			}
			try {
				if(text.contains(("TOTAL CHARGE TO TENANT $").toLowerCase()))
			    {
			    	residentBenefitsPackageTaxAvailabilityCheck = true;
			    	RunnerClass.setResidentBenefitsPackageTaxAvailabilityCheck(residentBenefitsPackageTaxAvailabilityCheck);
			    	if(residentBenefitsPackageTaxAvailabilityCheck == true) {
			    		try {
			    			 residentBenefitsPackageTaxAmount  = dataExtractionClass.getValues(text, "Resident Benefits Package (�RBP�) Program and Fee:^(Inclusive of@TOTAL CHARGE TO TENANT^(Inclusive of");
				    		 RunnerClass.setResidentBenefitsPackageTaxAmount(residentBenefitsPackageTaxAmount);
			    		}
			    		catch(Exception e) {
							 RunnerClass.setResidentBenefitsPackageTaxAmount("Error");
							 System.out.println("Error While Extracting RBP Tax Amount");
						}
			    	}
			    }
				else {
					residentBenefitsPackageTaxAvailabilityCheck = false;
			    	RunnerClass.setResidentBenefitsPackageTaxAvailabilityCheck(residentBenefitsPackageTaxAvailabilityCheck);
				}
			}
			catch(Exception e) {
				 RunnerClass.setResidentBenefitsPackageTaxAvailabilityCheck(false);
				 System.out.println("Error While Extracting RBP Tax Flag");
				
			}
		
		
		   
		  //RBP Opt - Out Addendum Check
		    try
		    {
		    	 if(text.contains(("Resident Benefits Package Opt-Out Addendum").toLowerCase()))
		 	    {
		 	    	RBPOptOutAddendumCheck= true;
		 	    	
		 	    }
		    	 RunnerClass.setRBPOptOutAddendumCheck(RBPOptOutAddendumCheck);
		    }
		    catch(Exception e) {
		    	RunnerClass.setRBPOptOutAddendumCheck(false);
		    	System.out.println("Error While Extracting RBP Opt-Out Addendum Flag");
		    }
		   
		   
		    try {
		    	proratedRent = dataExtractionClass.getValues(text, "Prorated Rent:^Tenant will pay Landlord@prorated rent,^Tenant will pay Landlord@Prorated Rent:^Tenant will pay Landlord");
				System.out.println("Prorated rent = "+ proratedRent);
				RunnerClass.setProrateRent(proratedRent);
		    }
		    catch(Exception e) {
		    	RunnerClass.setProrateRent("Error");
				System.out.println("Error While Extracting Prorated rent");		
			}
			
	
		
    	
         
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	    
	}
	
	public static int nthOccurrence(String str1, String str2, int n) 
    {
    	    
            String tempStr = str1;
            int tempIndex = -1;
            int finalIndex = 0;
            for(int occurrence = 0; occurrence < n ; ++occurrence)
            {
                tempIndex = tempStr.indexOf(str2);
                if(tempIndex==-1){
                    finalIndex = 0;
                    break;
                }
                tempStr = tempStr.substring(++tempIndex);
                finalIndex+=tempIndex;
            }
            return --finalIndex;
      }
	
	
	

	 public static String capitalizeFirstLetter(String str) {
	    	// Convert the string to char array
	        char[] chars = str.toCharArray();
	        boolean capitalizeNext = true;

	        // Iterate through each character
	        for (int i = 0; i < chars.length; i++) {
	            // If the current character is a letter and we need to capitalize the next letter
	            if (Character.isLetter(chars[i]) && capitalizeNext) {
	                chars[i] = Character.toUpperCase(chars[i]);
	                capitalizeNext = false; // Set to false as we have capitalized the letter
	            }
	            // If the current character is a space, set capitalizeNext to true
	            else if (Character.isWhitespace(chars[i])) {
	                capitalizeNext = true;
	            }
	        }
	     // Convert the char array back to string and return
	        return new String(chars);
	    }

	
	}
