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
		
		List<String> allIncreasedRent_amounts=new ArrayList();
		
		ArrayList<String> petType = new ArrayList(); 
		ArrayList<String> petBreed = new ArrayList();
		ArrayList<String> petWeight = new ArrayList();
		
		ArrayList<String> serviceAnimalPetType = new ArrayList();
	    ArrayList<String> serviceAnimalPetBreed  = new ArrayList();
	    ArrayList<String> serviceAnimalPetWeight = new ArrayList();
	
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
			//incrementRentFlag= dataExtractionClass.getFlags(text,"rent:^*Per the Landlord\\, Monthly Rent");
			try {
				concessionAddendumFlag = dataExtractionClass.getFlags(text, "rent:^This is a CONCESSION ADDENDUM to your Lease Agreement");
				System.out.println("Concession Addendum Flag = "+ concessionAddendumFlag);
				RunnerClass.setconcessionAddendumFlag(concessionAddendumFlag);
			}
			catch(Exception e) {
				RunnerClass.setconcessionAddendumFlag(false);
				System.out.println("Error While Extracting Concession Addendum Flag");
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
				allIncreasedRent_amounts =dataExtractionClass.getMultipleValues(text, "Monthly Rent:^Monthly Rent due in the amount of^@Monthly Rent:^Tenant will pay Landlord monthly rent in the amount of^@monthly installments,^on or before the 1st day of each month, in the amount of^@monthly installments,^Tenant will pay Landlord monthly rent in the amount of^") ;
				if (allIncreasedRent_amounts.size() > 1) {
		            double firstValue = Double.parseDouble(allIncreasedRent_amounts.get(0));
		            for (int i = 1; i < allIncreasedRent_amounts.size(); i++) {
		                double currentValue = Double.parseDouble(allIncreasedRent_amounts.get(i));
		                if (currentValue > firstValue) {
		                	incrementRentFlag = true;
		                	System.out.println("Increment Rent Flag = "+ incrementRentFlag);
		                	RunnerClass.setIncrementRentFlag(incrementRentFlag);
		                	increasedRent_amount = String.valueOf(currentValue);
		                	System.out.println("Increment Rent Amount = "+ increasedRent_amount);
		                	RunnerClass.setIncreasedRent_amount(increasedRent_amount);
		                	increasedRent_previousRentEndDate = dataExtractionClass.getSecondDate(text, "Monthly Rent:^Month",2);
		                	System.out.println("Increased Rent - Previous Rent End date =  "+increasedRent_previousRentEndDate);
		                	RunnerClass.setIncreasedRent_previousRentEndDate(increasedRent_previousRentEndDate);
		                	increasedRent_newStartDate = dataExtractionClass.getSecondDate(text, "Monthly Rent:^Month",3);
		                	System.out.println("Increased Rent - New Rent Start date =  "+increasedRent_newStartDate);
		                	RunnerClass.setIncreasedRent_newStartDate(increasedRent_newStartDate);
		                    //System.out.println("Value " + increasedRent_amounts.get(i) + " is greater than the first value.");
		                    break;
		                }
		            }
		        }
				else {
					RunnerClass.setIncrementRentFlag(incrementRentFlag);
					RunnerClass.setIncreasedRent_amount("Error");
					RunnerClass.setIncreasedRent_newStartDate("Error");
				}
				
				increasedRent_previousRentEndDate =  dataExtractionClass.getDates(text,"Monthly Rent:^to Month");
				if(!increasedRent_previousRentEndDate.equalsIgnoreCase("Error")) {
					RunnerClass.setIncreasedRent_previousRentEndDate(increasedRent_previousRentEndDate);
				}
				else {
					RunnerClass.setIncreasedRent_previousRentEndDate("Error");
				}	
			}
			catch(Exception e) {
				RunnerClass.setIncrementRentFlag(false);
				RunnerClass.setIncreasedRent_amount("Error");
				RunnerClass.setIncreasedRent_newStartDate("Error");
				RunnerClass.setIncreasedRent_previousRentEndDate("Error");
				System.out.println("Error While Extracting Increment Rent Details");
				
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
				adminFee = dataExtractionClass.getValues(text, "Lease Administrative Fee(s):^preparation fee in the amount of^@Lease Administrative Fee(s):^An annual lease preparation fee in the amount of@Lease Administrative Fee(s):^Tenant administrative fee in the amount of");
				System.out.println("Admin Fee = "+ adminFee);
				RunnerClass.setAdminFee(adminFee);
			}
			catch(Exception e) {
				RunnerClass.setAdminFee("Error");
				System.out.println("Error While Extracting Admin Fee");
				
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
			try {
				HVACFilterFlag = dataExtractionClass.getFlags(text, "rent:^HVAC FILTER MAINTENANCE PROGRAM OPT-OUT ADDENDUM@rent:^HVAC Filter Maintenance Program Fee of $");
				System.out.println("HVAC Filter Flag = "+ HVACFilterFlag);
				RunnerClass.setHVACFilterFlag(HVACFilterFlag);
			}
			catch(Exception e) {
				RunnerClass.setHVACFilterFlag(false);
				System.out.println("Error While Extracting HVAC Filter Flag");
			
			}
			try {
				if(HVACFilterFlag == true) {
					airFilterFee = dataExtractionClass.getValues(text, "HVAC FILTER MAINTENANCE PROGRAM OPT-OUT ADDENDUM^HVAC Filter Maintenance Program Fee of@HVAC Filter Maintenance Program and Fee:^HVAC Filter Maintenance Program Fee of");
					System.out.println("HVAC Air Filter Fee = "+ airFilterFee);
					RunnerClass.setairFilterFee(airFilterFee);
				}
			}
			catch(Exception e) {
				RunnerClass.setairFilterFee("Error");
				System.out.println("Error While Extracting HVAC Filter Fee");
			}
			
			 //HAVC Opt-Out Addendum check
		    try
		    {
		    	if(text.contains(("HVAC FILTER MAINTENANCE PROGRAM OPT-OUT ADDENDUM").toLowerCase()))
		    	{
		    		HVACFilterOptOutAddendum= true;
		    		
		    	}
		    	RunnerClass.setHVACFilterOptOutAddendum(HVACFilterOptOutAddendum);
		    }
		    catch(Exception e) {
		    	RunnerClass.setHVACFilterOptOutAddendum(false);
		    	System.out.println("Error While Extracting HVAC Filter Opt-Out Addendum Flag");
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
		   
		    //Occupants
		    try {
		    	occupants= dataExtractionClass.getTextWithStartandEndValue(text, "USE AND OCCUPANCY:^this Lease are:^Only two Tenants@USE AND OCCUPANCY:^this Lease are:^B. Phone Numbers@USE AND OCCUPANCY:^ages of all occupants):^NO OTHER OCCUPANTS SHALL RESIDE@USE AND OCCUPANCY:^ages of all occupants):^B. Phone Numbers:@USE AND OCCUPANCY:^listed as follows:^Property shall be used by Tenant@USE AND OCCUPANCY:^Name, Age ^The Tenant and the Minor Occupants listed above^@USE AND OCCUPANCY:^this Lease are^B. Phone Numbers@OCCUPANTS^Landlord/Landlord�s Broker:^11. MAINTENANCE@OCCUPANTS^Broker:^10. MAINTENANCE@SUBLET AND ASSIGNMENT^persons listed as follows:^Property shall be used by Tenant");
				occupants = capitalizeFirstLetter(occupants);
				System.out.println("Occupants Name = "+ occupants);
				RunnerClass.setOccupants(occupants);
		    }
		    catch(Exception e) {
		    	RunnerClass.setOccupants("Error");
				System.out.println("Error While Extracting Occupants Name");
				
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
			
		/*	if(RunnerClass.portfolioType.contains("MCH"))
	  		{
	  			if(proratedRent.equalsIgnoreCase("n/a")||proratedRent.equalsIgnoreCase("Error")||proratedRent.equalsIgnoreCase(""))
	  			{
	  				prepaymentCharge = "Error";
	  				RunnerClass.setprepaymentCharge(prepaymentCharge);
	  			}
	  			else
	  			{
		  		try
		  		{
		  			prepaymentCharge =String.valueOf(Double.parseDouble(monthlyRent.trim().replace(",", "")) - Double.parseDouble(proratedRent.trim().replace(",", ""))); 
		  			RunnerClass.setprepaymentCharge(prepaymentCharge);
		  		}
		  		catch(Exception e)
		  		{
		  			prepaymentCharge ="Error";
		  			RunnerClass.setprepaymentCharge(prepaymentCharge);
		  		}
		  		}
	  			System.out.println("Prepayment Charge = "+prepaymentCharge);
	  		 } */
			try {
				 if(text.contains(("SPECIAL PROVISIONS:").toLowerCase()))
		  		 {
		  			residentUtilityBillFlag = true;
		  			RunnerClass.setResidentUtilityBillFlag(residentUtilityBillFlag);
		  			RUBS = dataExtractionClass.getValues(text, "Utility Charges^Tenant shall pay a@UTILITIES:^Tenant shall pay a");
		  			System.out.println("RUBS = "+RUBS);
		  			RunnerClass.setRUBS(RUBS);
		  			prorateRUBS = dataExtractionClass.getValues(text, "UTILITIES:^Tenant will pay Landlord@UTILITIES:^RUBS fee of");
		  			System.out.println("Prorate RUBS = "+prorateRUBS);
		  			RunnerClass.setProrateRUBS(prorateRUBS);
		  		 }
		  		 else {
		  			residentUtilityBillFlag = false;
		  			RunnerClass.setResidentUtilityBillFlag(residentUtilityBillFlag);
		  			RunnerClass.setProrateRUBS("Error");
		  			RunnerClass.setRUBS("Error");
		  		 }
			}
	  		catch(Exception e) {
	  			RunnerClass.setResidentUtilityBillFlag(false);
	  			RunnerClass.setProrateRUBS("Error");
	  			RunnerClass.setRUBS("Error");
	  			System.out.println("Error While Extracting RUBS Amount");		
	  		}
	  		try {
	  			if(text.contains("pet inspection fee"))
		    	{
		    		
		    		petInspectionFeeFlag = true;
		    	}
		  		PropertyWare_updateValues.setPetInspectionFeeFlag(petInspectionFeeFlag);
	  		}
	  		catch(Exception e) {
	  			PropertyWare_updateValues.setPetInspectionFeeFlag(false);
	  			System.out.println("Error While Extracting Pet Inspection Fee Flag");	
	  		}
	  		
	  		try {
	  			petFlag = dataExtractionClass.getFlags(text, "rent:^THIS PET ADDENDUM (this@rent^PET AUTHORIZATION AND PET DESCRIPTION:");
				System.out.println("Pet Flag = "+ petFlag);
				RunnerClass.setpetFlag(petFlag);
	  		}
	  		catch(Exception e) {
	  			RunnerClass.setpetFlag(false);
	  			System.out.println("Error While Extracting Pet Flag");	
	  		}
			if(petFlag == true) {
				try {
					petSecurityDeposit = dataExtractionClass.getValues(text, "PET AUTHORIZATION AND PET DESCRIPTION:^On or before the date Tenant moves into the Property, Tenant will pay Landlord an additional deposit of@THIS PET ADDENDUM^Tenant will, upon execution of this agreement, pay Landlord");
					System.out.println("Pet Security Deposit = "+ petSecurityDeposit);
					RunnerClass.setPetSecurityDeposit(petSecurityDeposit);
				}
				catch(Exception e) {
					RunnerClass.setPetSecurityDeposit("Error");
					System.out.println("Error While Extracting Pet Security Deposit");	
				}	
				try {
					if(!petSecurityDeposit.equalsIgnoreCase("Error")) {
						petSecurityDepositFlag = true;
						PropertyWare_updateValues.setPetSecurityDepositFlag(petSecurityDepositFlag);
					}
					else {
						PropertyWare_updateValues.setPetSecurityDepositFlag(false);
					}
				}
				catch(Exception e) {
					PropertyWare_updateValues.setPetSecurityDepositFlag(false);
					System.out.println("Error While Extracting Pet Security Deposit Flag");	
				}
				try {
					proratedPetRent = dataExtractionClass.getValues(text, "Prorated Pet Rent:^Tenant will pay Landlord");
					System.out.println("Prorated Pet Rent = "+ proratedPetRent);
					RunnerClass.setproratedPetRent(proratedPetRent);
				}
				catch(Exception e) {
					RunnerClass.setproratedPetRent("Error");
					System.out.println("Error While Extracting Prorated Pet Rent");	
				}
				try {
					petRent = dataExtractionClass.getValues(text, "THIS PET ADDENDUM^Tenant will pay Landlord monthly pet rent in the amount of@PET AUTHORIZATION AND PET DESCRIPTION:^Tenant will pay Landlord monthly pet rent in the amount of@PET AUTHORIZATION AND PET DESCRIPTION:^Tenant will pay Landlord a monthly pet inspection fee in the amount of@PET AUTHORIZATION AND PET DESCRIPTION:^The monthly rent in the lease is increased by");
					System.out.println("Pet Rent = "+ petRent);
					RunnerClass.setPetRent(petRent);
				}
				catch(Exception e) {
					RunnerClass.setPetRent("Error");
					System.out.println("Error While Extracting Pet Rent");	
				}
				try {
					petRentTaxAmount = dataExtractionClass.getValues(text, "THIS PET ADDENDUM^tax and administrative fees of@PET AUTHORIZATION AND PET DESCRIPTION:^tax and administrative fees of");
					System.out.println("Pet Rent Tax Amount = "+ petRentTaxAmount);
				}
				catch(Exception e) {
					petRentTaxAmount = "";
					System.out.println("Error While Extracting Pet Rent Tax Amount");
				}
				try {
					if(petRentTaxAmount.trim().equalsIgnoreCase("0.00")||petRentTaxAmount.trim().equalsIgnoreCase("N/A")||petRentTaxAmount.trim().equalsIgnoreCase("n/a")||petRentTaxAmount.trim().equalsIgnoreCase("na")||petRentTaxAmount.trim().equalsIgnoreCase(""))
			 	    {
			 	    	petRentTaxFlag = false;
			 	    	RunnerClass.setPetRentTaxFlag(petRentTaxFlag);
			 	    }
			 	    else
			 	    {
			 	    	petRentTaxFlag = true;
			 	    	RunnerClass.setPetRentTaxFlag(petRentTaxFlag);
						totalPetRentWithTax = dataExtractionClass.getValues(text, "THIS PET ADDENDUM^for a total of@PET AUTHORIZATION AND PET DESCRIPTION:^for a total of");
						System.out.println("Total Pet Rent With Tax = "+ totalPetRentWithTax);
						RunnerClass.setTotalPetRentWithTax(totalPetRentWithTax);
					}
				}
				catch(Exception e) {
					RunnerClass.setPetRentTaxFlag(false);
					RunnerClass.setTotalPetRentWithTax("Error");
					System.out.println("Error While Extracting Total Pet Rent With Tax");
				}
				try {
					petOneTimeNonRefundableFee = dataExtractionClass.getValues(text, "THIS PET ADDENDUM^Tenant will, upon execution of this agreement, pay Landlord@PET AUTHORIZATION AND PET DESCRIPTION:^Tenant will, upon execution of this agreement, pay Landlord");
					System.out.println("Pet One Time Non Refundable Fee = "+ petOneTimeNonRefundableFee);
					RunnerClass.setPetOneTimeNonRefundableFee(petOneTimeNonRefundableFee);
				}
				catch(Exception e) {
					RunnerClass.setPetOneTimeNonRefundableFee("Error");
					System.out.println("Error While Extracting Pet One Time Non Refundable Fee");
				}
				try {
					String typeSubString = dataExtractionClass.getTextWithStartandEndValue(text, "THIS PET ADDENDUM^Tenant may keep the following pet(s) on the Property until the above-referenced lease ends.^B. CONSIDERATION:@PET AUTHORIZATION AND PET DESCRIPTION:^Tenant may keep the following pet(s) on the Property until the above-referenced lease ends.^B. CONSIDERATION:");
					String newText = typeSubString.replace("type:","");
				    int countOfTypeWordInText = ((typeSubString.length() - newText.length())/"type:".length());
				    System.out.println("Type: occurence = "+countOfTypeWordInText);
				    for(int i =0;i<countOfTypeWordInText;i++)
				    {
				    	String type = typeSubString.substring(nthOccurrence(typeSubString, "type:", i+1)+"type:".length(),nthOccurrence(typeSubString, "breed:", i+1)).trim();
				    	if(type.contains("N/A")||type.contains("n/a"))
				    		break;
				    	System.out.println(type);
				    	petType.add(type);
				    	int pet1Breedindex1 = nthOccurrence(typeSubString, "breed:", i+1)+"breed:".length()+1;
					    String subString = typeSubString.substring(pet1Breedindex1);
					    //int pet1Breedindex2 = RunnerClass.nthOccurrence(subString,"Name:",i+1);
					   // System.out.println("Index 2 = "+(index2+index1));
					    String breed = subString.split("name:")[0].trim();//typeSubString.substring(pet1Breedindex1,(pet1Breedindex2+pet1Breedindex1));
					    System.out.println(breed);
					    petBreed.add(breed);
					    int pet1Weightindex1 = nthOccurrence(typeSubString, "weight:", i+1)+"weight:".length()+1;
					    String pet1WeightSubstring = typeSubString.substring(pet1Weightindex1);
					    //int pet1WeightIndex2 = RunnerClass.nthOccurrence(pet1WeightSubstring,"Age:",i+1);
					   // System.out.println("Index 2 = "+(index2+index1));
					    String weight = pet1WeightSubstring.split("age:")[0].trim(); //typeSubString.substring(pet1Weightindex1,(pet1WeightIndex2+pet1Weightindex1));
					    System.out.println(weight);
					    petWeight.add(weight);
				    }
				    RunnerClass.setPetTypes(petType);
				    RunnerClass.setPetBreeds(petBreed);
				    RunnerClass.setPetWeights(petWeight);
				}
				catch(Exception e) {
					  RunnerClass.setPetTypes(petType);
					  RunnerClass.setPetBreeds(petBreed);
					  RunnerClass.setPetWeights(petWeight);
					  System.out.println("Error While Extracting Pet Name,Breed and Weight");
				}
				
				}
			
			try {
				serviceAnimalFlag = dataExtractionClass.getFlags(text,"SERVICE/SUPPORT ANIMAL AGREEMENT^SERVICE/SUPPORT ANIMAL AUTHORIZATION");
				System.out.println("Service Animal Flag = "+ serviceAnimalFlag);
					
				RunnerClass.setserviceAnimalFlag(serviceAnimalFlag);
			}
			catch(Exception e) {
				RunnerClass.setserviceAnimalFlag(false);
				System.out.println("Error While Extracting Service Animal Flag");
			}
			try {
				if(serviceAnimalFlag == true) {
					System.out.println("Service Animal Addendum is available");
				 	String typeSubStrings = dataExtractionClass.getTextWithStartandEndValue(text, "THIS PET ADDENDUM^Tenant has the following Service/Support Animal(s) on the Property until the above-referenced lease ends.^B. SERVICE/SUPPORT ANIMAL RULES@PET AUTHORIZATION AND PET DESCRIPTION:^Tenant has the following Service/Support Animal(s) on the Property until the above-referenced lease ends.^B. SERVICE/SUPPORT ANIMAL RULES@SERVICE/SUPPORT ANIMAL AUTHORIZATION AND SERVICE/SUPPORT ANIMAL DESCRIPTION:^Tenant has the following Service/Support Animal(s) on the Property until the above-referenced lease ends.^B. SERVICE/SUPPORT ANIMAL RULES");
			    	
			    	String newTexts = typeSubStrings.replace("type:","");
				    int  countOftypeWords_ServiceAnimal = ((typeSubStrings.length() - newTexts.length())/"type:".length());
				    System.out.println("Service Animal - Type: occurences = "+countOftypeWords_ServiceAnimal);
				    
				  
				    for(int i =0;i<countOftypeWords_ServiceAnimal;i++)
				    {
				    	String type = typeSubStrings.substring(RunnerClass.nthOccurrence(typeSubStrings, "type:", i+1)+"type:".length(),RunnerClass.nthOccurrence(typeSubStrings, "breed:", i+1)).trim();
				    	if(type.contains("N/A")||type.contains("n/a"))
				    		break;
				    	System.out.println(type);
				    	serviceAnimalPetType.add(type);
				    	int pet1Breedindex1 = RunnerClass.nthOccurrence(typeSubStrings, "breed:", i+1)+"breed:".length()+1;
					    String subString = typeSubStrings.substring(pet1Breedindex1);
					    //int pet1Breedindex2 = RunnerClass.nthOccurrence(subString,"Name:",i+1);
					   // System.out.println("Index 2 = "+(index2+index1));
					    String breed = subString.split("name:")[0].trim();//typeSubString.substring(pet1Breedindex1,(pet1Breedindex2+pet1Breedindex1));
					    System.out.println(breed);
					    serviceAnimalPetBreed.add(breed);
					    int pet1Weightindex1 = RunnerClass.nthOccurrence(typeSubStrings, "weight:", i+1)+"weight:".length()+1;
					    String pet1WeightSubstring = typeSubStrings.substring(pet1Weightindex1);
					    //int pet1WeightIndex2 = RunnerClass.nthOccurrence(pet1WeightSubstring,"Age:",i+1);
					   // System.out.println("Index 2 = "+(index2+index1));
					    String weight = pet1WeightSubstring.split("age:")[0].trim(); //typeSubString.substring(pet1Weightindex1,(pet1WeightIndex2+pet1Weightindex1));
					    System.out.println(weight);
					    serviceAnimalPetWeight.add(weight);
				    }
				    RunnerClass.setServiceAnimalPetType(serviceAnimalPetType);
				    RunnerClass.setServiceAnimalPetBreeds(serviceAnimalPetBreed);
				    RunnerClass.setServiceAnimalPetWeights(serviceAnimalPetWeight);
				    
				}
			}
			catch(Exception e){
					RunnerClass.setServiceAnimalPetType(serviceAnimalPetType);
				    RunnerClass.setServiceAnimalPetBreeds(serviceAnimalPetBreed);
				    RunnerClass.setServiceAnimalPetWeights(serviceAnimalPetWeight);
				    System.out.println("Error While Extracting Service Animal Name,Breed and Weight");
			}
			    
			try {
				smartHomeAgreementCheck = dataExtractionClass.getFlags(text, "rent:^This Smart Home Agreement is subject");
				System.out.println("Smart Home Agreement Flag = "+ smartHomeAgreementCheck);
				RunnerClass.setSmartHomeAgreementCheck(smartHomeAgreementCheck);
			}
			catch(Exception e) {
				RunnerClass.setSmartHomeAgreementCheck(false);
				System.out.println("Error While Extracting Smart Home Agreement Flag");
			}
			try {
				if(smartHomeAgreementCheck == true) {
					smartHomeAgreementFee = dataExtractionClass.getValues(text, "This Smart Home Agreement is subject^Smart Home Agreement shall be");
					System.out.println("Smart Home Agreement Fee = "+smartHomeAgreementFee);
					RunnerClass.setSmartHomeAgreementFee(smartHomeAgreementFee);
				}
				else {
					RunnerClass.setSmartHomeAgreementFee("Error");
				}
			}
			catch(Exception e) {
				RunnerClass.setSmartHomeAgreementFee("Error");
				System.out.println("Error While Extracting Smart Home Agreement Fee");
			}
			try {
				earlyTermination = dataExtractionClass.getTextWithStartandEndValue(text, "Early Termination:^Landlord of^rent at the time the Notice is provided@EARLY TERMINATION BY TENANT^Landlord of^rent at the time the Notice is provided@Early Termination:^Landlord of^rent at the time the Notice is provided");
	    		System.out.println("Early Termination  = "+earlyTermination.trim());
	    		RunnerClass.setEarlyTermination(earlyTermination);
			}
			catch(Exception e) {
				RunnerClass.setEarlyTermination("Error");
				System.out.println("Error While Extracting Early Termination");
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
