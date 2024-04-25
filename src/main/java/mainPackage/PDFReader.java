package mainPackage;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import DataReader.ReadingLeaseAgreements;


public class PDFReader 
{
	

	private static ThreadLocal<String> prorateRentGETThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> lateFeeRuleTypeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> lateChargeDayThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> lateFeePercentageThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> lateFeeTypeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> lateChargeFeeThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> lateFeeChargePerDayThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> additionalLateChargesLimitThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> additionalLateChargesThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<String> RCDetailsThreadLocal = new ThreadLocal<>();
	
	
	
	public static void setProrateRentGET(String prorateRentGET) {
		prorateRentGETThreadLocal.set(prorateRentGET);
	}
	
	public static String getProrateRentGET() {
		if(prorateRentGETThreadLocal.get()==null)
			return "Error";
		else
		 return prorateRentGETThreadLocal.get();
	}
	
	public static void setLateFeeRuleType(String lateFeeRuleType) {
		lateFeeRuleTypeThreadLocal.set(lateFeeRuleType);
	}
	
	public static String getLateFeeRuleType() {
		if(lateFeeRuleTypeThreadLocal.get()==null)
			return "Error";
		else
		 return lateFeeRuleTypeThreadLocal.get();
	}
	
	public static void setLateChargeDay(String lateChargeDay) {
		lateChargeDayThreadLocal.set(lateChargeDay);
	}
	
	public static String getLateChargeDay() {
		if(lateChargeDayThreadLocal.get()==null)
			return "Error";
		else
		 return lateChargeDayThreadLocal.get();
	}
	
	public static void setLateFeePercentage(String lateFeePercentage) {
		lateFeePercentageThreadLocal.set(lateFeePercentage);
	}
	
	public static String getLateFeePercentage() {
		if(lateFeePercentageThreadLocal.get()==null)
			return "Error";
		else
		 return lateFeePercentageThreadLocal.get();
	}
	
	public static void setLateFeeType(String lateFeeType) {
		lateFeeTypeThreadLocal.set(lateFeeType);
	}
	
	public static String getLateFeeType() {
		if(lateFeeTypeThreadLocal.get()==null)
			return "Error";
		else
		 return lateFeeTypeThreadLocal.get();
	}
	public static void setLateChargeFee(String lateChargeFee) {
		lateChargeFeeThreadLocal.set(lateChargeFee);
	}
	
	public static String getLateChargeFee() {
		if(lateChargeFeeThreadLocal.get()==null)
			return "Error";
		else
		 return lateChargeFeeThreadLocal.get();
	}
	public static void setLateFeeChargePerDay(String lateFeeChargePerDay) {
		lateFeeChargePerDayThreadLocal.set(lateFeeChargePerDay);
	}
	
	public static String getLateFeeChargePerDay() {
		if(lateFeeChargePerDayThreadLocal.get()==null)
			return "Error";
		else
		 return lateFeeChargePerDayThreadLocal.get();
	}
	public static void setAdditionalLateChargesLimit(String additionalLateChargesLimit) {
		additionalLateChargesLimitThreadLocal.set(additionalLateChargesLimit);
	}
	
	public static String getAdditionalLateChargesLimit() {
		if(additionalLateChargesLimitThreadLocal.get()==null)
			return "Error";
		else
		 return additionalLateChargesLimitThreadLocal.get();
	}
	public static void setAdditionalLateCharges(String additionalLateCharges) {
		additionalLateChargesThreadLocal.set(additionalLateCharges);
	}
	
	public static String getAdditionalLateCharges() {
		if(additionalLateChargesThreadLocal.get()==null)
			return "Error";
		else
		 return additionalLateChargesThreadLocal.get();
	}
	public static void setRCDetails(String RCDetails) {
		RCDetailsThreadLocal.set(RCDetails);
	}
	
	public static String getRCDetails() {
		if(RCDetailsThreadLocal.get()==null)
			return "Error";
		else
		 return RCDetailsThreadLocal.get();
	}
	
	
		public static boolean readPDFPerMarket(String company,String SNo) throws Exception  
		{
		    if(ReadingLeaseAgreements.dataRead(RunnerClass.getFileName(),SNo,company)==false) {
		    	return false;
		    }
		    	
		    String prorateRent = "";
		    String monthlyRent = "";
			
			//Converting amounts in proper format if they have more than one dot
			try
			{
				if(RunnerClass.getTotalMonthlyRentWithTax().replace(",", "").matches(".*\\..*\\..*"))
					RunnerClass.setTotalMonthlyRentWithTax( RunnerClass.getTotalMonthlyRentWithTax().replace(",", ""));
				if(RunnerClass.getTotalMonthlyRentWithTax().substring(RunnerClass.getTotalMonthlyRentWithTax().length()-1).equals("."))
					RunnerClass.setTotalMonthlyRentWithTax(RunnerClass.getTotalMonthlyRentWithTax().substring(0,RunnerClass.getTotalMonthlyRentWithTax().length()-1));
				
			}
			catch(Exception e)
			{}
			try
			{
				monthlyRent = RunnerClass.getMonthlyRent();
				if(monthlyRent.replace(",", "").matches(".*\\..*\\..*"))
					monthlyRent = monthlyRent.replace(",", "").replaceFirst("[.]", "");
					RunnerClass.setMonthlyRent(monthlyRent);
			}
			catch(Exception e)
			{}
			try
			{
				prorateRent = RunnerClass.getProrateRent();
				if(prorateRent.replace(",", "").matches(".*\\..*\\..*"))
					prorateRent = prorateRent.replace(",", "").replaceFirst("[.]", "");
				RunnerClass.setProrateRent(prorateRent);
			}
			catch(Exception e)
			{}
			try
			{
				if(RunnerClass.getPetRent().replace(",", "").matches(".*\\..*\\..*"))
					RunnerClass.setPetRent(RunnerClass.getPetRent().replace(",", "").replaceFirst("[.]", ""));
			}
			catch(Exception e)
			{}
			try
			{
				if(RunnerClass.getproratedPetRent().replace(",", "").matches(".*\\..*\\..*"))
					RunnerClass.setproratedPetRent(RunnerClass.getproratedPetRent().replace(",", "").replaceFirst("[.]", ""));
			}
			catch(Exception e)
			{}
			
			//Prepayment charge 
			if(((company.equals("Alabama")||company.equals("Hawaii")||company.equals("Arizona"))&&RunnerClass.getMonthlyRentTaxFlag()==true))
			{
				try
				{
					RunnerClass.setprepaymentCharge(String.valueOf(RunnerClass.round((Double.parseDouble(RunnerClass.getTotalMonthlyRentWithTax().replace(",", "")) - Double.parseDouble(RunnerClass.getProrateRent().replace(",", ""))),2))); 
				}
				catch(Exception e)
				{
					RunnerClass.setprepaymentCharge("Error");
				}
				
				//Prorate Rent when Taxes available in Alabama and Hawaii
				if(!RunnerClass.getProrateRent().equalsIgnoreCase("n/a")||!RunnerClass.getProrateRent().equalsIgnoreCase("na")||!RunnerClass.getProrateRent().equalsIgnoreCase("n/a.")||!RunnerClass.getProrateRent().equalsIgnoreCase("0.00"))
				try
				{
					double rent = Double.parseDouble(monthlyRent.replace(",", ""));
					double proratedRent = Double.parseDouble(RunnerClass.getProrateRent().replace(",", ""));
					double  totalMonthlyRentWithTax= Double.parseDouble(RunnerClass.getTotalMonthlyRentWithTax().replace(",", ""));
					double prorateRentCalculated = RunnerClass.round(((rent*proratedRent)/totalMonthlyRentWithTax),2);
					//For Hawaii Prorate Rent GET
					setProrateRentGET( String.valueOf(RunnerClass.round((proratedRent - prorateRentCalculated),2)));
					prorateRent =String.valueOf(RunnerClass.round(prorateRentCalculated,2)); 
					RunnerClass.setProrateRent(prorateRent);
				}
				catch(Exception e)
				{
					prorateRent ="Error";
					RunnerClass.setProrateRent(prorateRent);
				}
			}
			else
			{
			try
			{
				RunnerClass.setprepaymentCharge(String.valueOf(RunnerClass.round((Double.parseDouble(monthlyRent.replace(",", "")) - Double.parseDouble(RunnerClass.getProrateRent().replace(",", ""))),2))); 
			}
			catch(Exception e)
			{
				RunnerClass.setprepaymentCharge("Error");
			}
			}
			System.out.println("Prepayment Charge = "+RunnerClass.getprepaymentCharge());
			
			//Prorate pet Rent when Taxes available in Alabama and Hawaii
			if(((company.equals("Alabama")||company.equals("Hawaii")||company.equals("Arizona"))&&RunnerClass.getPetRentTaxFlag()==true))
			{
			if(!RunnerClass.getproratedPetRent().equalsIgnoreCase("n/a")||!RunnerClass.getproratedPetRent().equalsIgnoreCase("na")||!RunnerClass.getproratedPetRent().equalsIgnoreCase("n/a.")||!RunnerClass.getproratedPetRent().equalsIgnoreCase("0.00"))
			try
			{
				double petRent = Double.parseDouble(RunnerClass.getPetRent().replace(",", ""));
				double proratePetRent = Double.parseDouble(RunnerClass.getproratedPetRent().replace(",", ""));
				double  totalPetRentWithTax= Double.parseDouble(RunnerClass.getTotalPetRentWithTax().replace(",", ""));
				double prorateRentCalculated = (petRent*proratePetRent)/totalPetRentWithTax;
				RunnerClass.setproratedPetRent(String.valueOf(RunnerClass.round(prorateRentCalculated,2))); 
			}
			catch(Exception e)
			{
				RunnerClass.setproratedPetRent("Error");
			}
			}
			
			//Increased Rent New Start Date
			
			try
			{
				RunnerClass.setIncreasedRent_newStartDate(RunnerClass.convertDate(RunnerClass.getIncreasedRent_newStartDate()));
			}
			catch(Exception e)
			{
				RunnerClass.setIncreasedRent_newStartDate("Error");
			}
			System.out.println("Increased Rent New Start Date = "+RunnerClass.getIncreasedRent_newStartDate());
			
			// 1% of Monthly rent
			try
			{
				double monthRent = Double.parseDouble(monthlyRent.replace(",", ""));
				double onePercentOfRent = monthRent*0.01;
				RunnerClass.setOnePercentOfRentAmount(String.valueOf(RunnerClass.round( onePercentOfRent,2)));
			}
			catch(Exception e)
			{
				RunnerClass.setOnePercentOfRentAmount("Error");
			}
			// 1% of Prorate rent
			try
			{
				double proratedRent = Double.parseDouble(RunnerClass.getProrateRent().replace(",", ""));
				double onePercentOfProrateRent = proratedRent*0.01;
				RunnerClass.setOnePercentOfProrateRentAmount(String.valueOf(RunnerClass.round(onePercentOfProrateRent,2)));
			}
			catch(Exception e)
			{
				RunnerClass.setOnePercentOfProrateRentAmount("Error");
			}
			// 1% of Pet rent
			try
			{
				double petRent = Double.parseDouble(RunnerClass.getPetRent().replace(",", ""));
				double onePercentOfPetRent = petRent*0.01;
				RunnerClass.setOnePercentOfPetRentAmount(String.valueOf(RunnerClass.round(onePercentOfPetRent,2)));
			}
			catch(Exception e)
			{
				RunnerClass.setOnePercentOfPetRentAmount("Error");
			}
			// 1% of Prorate Pet rent
			try
			{
				double proratePetRent2 = Double.parseDouble(RunnerClass.getproratedPetRent().replace(",", ""));
				double onePercentOfProratedPetRent = proratePetRent2*0.01;
				RunnerClass.setOnePercentOfProratePetRentAmount(String.valueOf(RunnerClass.round(onePercentOfProratedPetRent,2)));
			}
			catch(Exception e)
			{
				RunnerClass.setOnePercentOfProratePetRentAmount("Error");
		    }
			System.out.println("1% of Monthly Rent = "+RunnerClass.getOnePercentOfRentAmount());
			System.out.println("1% of Prorate Rent = "+RunnerClass.getOnePercentOfProrateRentAmount());
			System.out.println("1% of Pet Rent = "+RunnerClass.getOnePercentOfPetRentAmount());
			System.out.println("1% of Prorate Pet Rent = "+RunnerClass.getOnePercentOfProratePetRentAmount());
			

			
			
			//Calculating Prorate Resident Benefit Package if RBP amount is 49.95
			try
			{ 
				String startDate = RunnerClass.getStartDate();
				int dayInMoveInDate = Integer.parseInt(startDate.split("/")[1]);
				int daysInMonth = RunnerClass.getDaysInMonth(startDate);
				double RBPAmount = Double.parseDouble(RunnerClass.getresidentBenefitsPackage());
				double RBPPerDay = RBPAmount /daysInMonth;
				int differenceInDays = (daysInMonth - dayInMoveInDate)+1;
				if(daysInMonth==differenceInDays||(startDate.split("/")[1].equals("01")||startDate.split("/")[1].equals("1")))
				{
					RunnerClass.setProrateResidentBenefitPackage(RunnerClass.getresidentBenefitsPackage());
				}
				else
				{
				double prorateRBP = differenceInDays*RBPPerDay; 
				RunnerClass.setProrateResidentBenefitPackage(String.format("%.2f", prorateRBP));
				}
			}
			catch(Exception e)
			{
				RunnerClass.setProrateResidentBenefitPackage("Error");
				e.printStackTrace();
			}
			System.out.println("Prorate RBP = "+RunnerClass.getProrateResidentBenefitPackage());
			
			
			return true;
			
		}
		

	  


}
