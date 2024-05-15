package DataReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mainPackage.AppConfig;
import mainPackage.RunnerClass;




public class dataExtractionClass {
	public static String [][] FieldValues;
	public static String databaseValue="";
	

	
	public static String getValues(String text, String names) {
		String datevalue =names;
        String[] data = datevalue.split("\\@");
        for (int i = 0; i < data.length; i++) {
           // String date = "";
            String subStringValue = data[i].split("\\^")[0].toLowerCase();
            String priorText = data[i].split("\\^")[1].toLowerCase();
            try {
            	 String patternString = priorText + "\\s*\\$?\\s*([0-9]+(?:,[0-9]{1,3})*(?:\\.[0-9]{1,2})?)?";
            	 String modifiedtext = text.substring(text.indexOf(subStringValue));
            	 // Constructing regex pattern to match the amount
            	 Pattern pattern = Pattern.compile(patternString);
            	 Matcher matcher = pattern.matcher(modifiedtext);

                if (matcher.find()) {
                    return matcher.group(1).trim(); // Group 1 contains the matched amount
                }
                else {
                	continue;
                }
            } catch (Exception e) {
            	//e.printStackTrace();
            	continue;
            }
        }
        return "Error";
    }
	
	
	public static String getDates(String text,String values) { //String datevalue
	    try {
	    	String datevalue =values;
	        String[] data = datevalue.split("\\@");
	        for (int i = 0; i < data.length; i++) {
	           // String date = "";
	            String subStringValue = data[i].split("\\^")[0].toLowerCase();
	            String priorText = data[i].split("\\^")[1].toLowerCase();
	            //String splitBy = data[i].split("\\^")[2].toLowerCase(); // Assuming splitBy is at index 3
	            String patternString = priorText + "\\s*(?:January|February|March|April|May|June|July|August|September|October|November|December)\\s+\\d{1,2}+\\s*(?:,\\s*\\d{4})?";
	            try {
	                String modifiedtext = text.substring(text.indexOf(subStringValue));
	                //date = modifiedtext.substring(modifiedtext.indexOf(priorText) + priorText.length()).trim();
	                Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
	                Matcher matcher = pattern.matcher(modifiedtext);

	                // Find the first match
	                if (matcher.find()) {
	                    return matcher.group().replaceFirst(priorText, "").trim();	                }
	                else {
	                    continue;
	                }
	           
	            } catch (Exception e) {
	                continue;
	            }
	        }
	    } catch (Exception e) {
	    	//e.printStackTrace();
	        return "Error";
	    }

	    return "Error";
	}

	
	
	public static String getTextWithStartandEndValue(String text,String datavalue) 
	{
		try {
			String[] data = datavalue.split("\\@");
			for(int i=0;i<data.length;i++)
			{
				String value ="";
				String subStringValue  = data[i].split("\\^")[0].toLowerCase();
				String priorText  = data[i].split("\\^")[1].toLowerCase();
				String afterText  = data[i].split("\\^")[2].toLowerCase();
				try {
					String modifiedtext = text.substring(text.indexOf(subStringValue));
					//value = modifiedtext.substring(modifiedtext.indexOf(priorText)+priorText.length()).trim();
					value = modifiedtext.substring(modifiedtext.indexOf(priorText), modifiedtext.indexOf(afterText)).trim();//.replaceAll("[a-ZA-Z,]", "");
					if(value.contains(priorText)) {
						value = value.replace(priorText, "").trim();
						if(value.contains("docusign")) {
							value = value.substring(0, value.indexOf("docusign")).trim();
						}
						return value;
					}
					else {
						continue;
					}
					}
				catch(Exception e) {
					continue;
					//continue;n/a
				}
					
			}
		}
		catch(Exception e) {
			return "Error";
		}
		
		return "Error";
			
		
	}
	
	public static List<String> getMultipleValues(String text, String names) {
        List<String> values = new ArrayList<>();
        String[] data = names.split("\\@");
        for (String datum : data) {
            String subStringValue = datum.split("\\^")[0].toLowerCase();
            String priorText = datum.split("\\^")[1].toLowerCase();
            try {
                String patternString = priorText + "\\s*\\$?\\s*([0-9]+(?:,[0-9]{1,3})*(?:\\.[0-9]{1,2})?)?";
                String modifiedtext = text.substring(text.indexOf(subStringValue));
                // Constructing regex pattern to match the amount
                Pattern pattern = Pattern.compile(patternString);
                Matcher matcher = pattern.matcher(modifiedtext);

                while(matcher.find()) {
                    values.add(matcher.group(1).trim()); // Group 1 contains the matched amount
                }
                
            } catch (Exception e) {
            	continue;
            }
        }
        return values;
    }
	
	
	public static List<String> getMultipleDates(String text, String value) {
	   
	    	 List<String> dates = new ArrayList<>();
	    	 String[] data = value.split("\\@");
	         for (String datum : data) {
	             String subStringValue = datum.split("\\^")[0].toLowerCase();
	             String priorText = datum.split("\\^")[1].toLowerCase();
	             String patternString = priorText + "\\s*(?:January|February|March|April|May|June|July|August|September|October|November|December)\\s+\\d{1,2}(?:,\\s*\\d{4})?";
	             //String patternString = priorText + "\\s*(?:January|February|March|April|May|June|July|August|September|October|November|December)\\s+\\d{1,2},\\s*\\d{4}";
	             try {
	                String modifiedtext = text.substring(text.indexOf(subStringValue));
	                Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
	                Matcher matcher = pattern.matcher(modifiedtext);

	                while(matcher.find()) {
	                	dates.add(RunnerClass.convertDate(matcher.group().replaceFirst(priorText, "").trim())); // Group 1 contains the matched amount
	                }
	            } catch (Exception e) {
	                continue;
	            }
	        }
	  

	    return dates;
	}
	
	public static boolean getFlags(String text,String getFlags) {
		
		String datevalue =getFlags;
		
		String[] getChecks = datevalue.split("\\@");
		for(int i=0;i<getChecks.length;i++)
		{
			String subStringValue = getChecks[i].split("\\^")[0].toLowerCase();
            String flagValue = getChecks[i].split("\\^")[1].toLowerCase();
            try {
            	String modifiedtext = text.substring(text.indexOf(subStringValue));
			
				if(modifiedtext.contains(flagValue)) {
					return true;
				}
			}
			catch(Exception e) {
				continue;
			}
		}
		
		return false;
		
	}
	
	public static boolean hasSpecialCharacters(String inputString) 
    {
        // Define a regular expression pattern to match characters other than digits, dots, and commas
        Pattern pattern = Pattern.compile("[^0-9.,]");

        // Use a Matcher to find any match in the input string
        Matcher matcher = pattern.matcher(inputString);

        return matcher.find();
    }
	
	
	
	public static String getDataOf(String fieldName)
	{
	try
	{
	        Connection con = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(AppConfig.connectionUrl);
	            String SQL = "Select Value from Automation.PDFDataExtractConfig where Name='" + fieldName +"' ";
	            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	           // stmt = con.createStatement();
	            stmt.setQueryTimeout(60);
	            rs = stmt.executeQuery(SQL);
	            int rows =0;
	            if (rs.last()) 
	            {
	            	rows = rs.getRow();
	            	// Move to beginning
	            	rs.beforeFirst();
	            }
	            //System.out.println("No of Rows = "+rows);
	            FieldValues = new String[rows][1];
	           int  i=0;
	            while(rs.next())
	            {
	  
	            	String 	value = rs.getObject(1).toString();
	            	
	              //stateCode
	                try 
	                {
	                	
	                	FieldValues[i][0] = value;
	                	databaseValue = FieldValues[i][0];
	                	
	                }
	                catch(Exception e)
	                {
	                	FieldValues[i][0] = "";
	                }
	            }
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return "";
	}
	return databaseValue;
}

}
