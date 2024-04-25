package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase 
{
	 public static void insertData(String buildingName, String status, int statusID) throws Exception
	  {

		  String currentTime = RunnerClass.getCurrentDateTime();
		  String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
		  String sql;
		  if(statusID==1)
		   sql = "Update Automation.LeaseRenewalAutomation Set Status ='"+status+"', StatusID="+statusID+",NotAutomatedFields=NULL,StartTime= "+currentTime+" where BuildingName like '%"+buildingName+"%'";
		  else 
			sql = "Update Automation.LeaseRenewalAutomation Set Status ='"+status+"', StatusID="+statusID+",StartTime= '"+currentTime+"' where BuildingName like '%"+buildingName+"%'";
          //String sql = "Update [Automation].[LeaseInfo] Set Status = 'Completed', StatusID =4 where OwnerName='Duff, V.'";
		  
		    try (Connection conn = DriverManager.getConnection(connectionUrl);
		        Statement stmt = conn.createStatement();) 
		    {
		      stmt.executeUpdate(sql);
		      System.out.println("Database updated successfully ");
		      stmt.close();
	            conn.close();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
          
	  }
	
	public static boolean getBuildingsList()
	{
		try
		{
		        Connection con = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(AppConfig.connectionUrl);
		            String SQL = AppConfig.pendingRenewalLeases;
		            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		           // stmt = con.createStatement();
		            rs = stmt.executeQuery(SQL);
		            int rows =0;
		            if (rs.last()) 
		            {
		            	rows = rs.getRow();
		            	// Move to beginning
		            	rs.beforeFirst();
		            }
		            System.out.println("No of Rows = "+rows);
		            RunnerClass.pendingRenewalLeases = new String[rows][4];
		           int  i=0;
		            while(rs.next())
		            {
		            	String 	SNo =  String.valueOf(rs.getObject(1));
		            	String 	company =  (String) rs.getObject(2);
		                String  buildingAbbreviation = (String) rs.getObject(3);
		                String  ownerName = (String) rs.getObject(4);
		                System.out.println( SNo +" | " + company +" |  "+buildingAbbreviation+" | "+ownerName);
		    				//SNo
		                	RunnerClass.pendingRenewalLeases[i][0] = SNo;
		                	//Company
		    				RunnerClass.pendingRenewalLeases[i][1] = company;
		    				//Building Abbreviation
		    				RunnerClass.pendingRenewalLeases[i][2] = buildingAbbreviation;
		    				//Owner Name
		    				RunnerClass.pendingRenewalLeases[i][3] = ownerName;
		    				i++;
		            }	
		            System.out.println("Total Pending Buildings  = " +RunnerClass.pendingRenewalLeases.length);
		            //for(int j=0;j<RunnerClass.pendingBuildingList.length;j++)
		            //{
		            //	System.out.println(RunnerClass.pendingBuildingList[j][j]);
		           // }
		            rs.close();
		            stmt.close();
		            con.close();
		 return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		 return false;
		}
	}
	
	public static void updateTable(String query)
	 {
		    try (Connection conn = DriverManager.getConnection(AppConfig.connectionUrl);
		        Statement stmt = conn.createStatement();) 
		    {
		      stmt.executeUpdate(query);
		      System.out.println("Record Updated");
		      stmt.close();
	            conn.close();
		    } catch (SQLException e) 
		    {
		      e.printStackTrace();
		    }
	 }
	
	public static boolean getAutoCharges(String buildingAbbreviation,String SNo)
	{
		try
		{
		        Connection con = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(AppConfig.connectionUrl);
		            String SQL = "Select ChargeCode, Amount, autoCharge_StartDate,EndDate,Description from automation.LeaseCloseOutsChargeChargesConfiguration_"+SNo+" Where  AutoCharge=1";
		            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		           // stmt = con.createStatement();
		            rs = stmt.executeQuery(SQL);
		            int rows =0;
		            if (rs.last()) {
		            	rows = rs.getRow();
		            	// Move to beginning
		            	rs.beforeFirst();
		            }
		            System.out.println("Auto Charges  = "+rows);
		            String[][] autoCharges = new String[rows][5];
		           int  i=0;
		            while(rs.next())
		            {
		            	
		            	String 	chargeCode =  (String) rs.getObject(1);
		                String  amount = (String) rs.getObject(2);
		                String  startDate = (String) rs.getObject(3);
		                String  endDate = (String) rs.getObject(4);
		                String  description = (String) rs.getObject(5);
		                
		                System.out.println(chargeCode +" | "+amount+" | "+startDate+" | "+endDate+" | "+description);
		    				//Company
		    				autoCharges[i][0] = chargeCode;
		    				//Building Abbreviation
		    				autoCharges[i][1] = amount;
		    				//Monthly Rent From Lease Agreement
		    				autoCharges[i][2] = startDate;
		    				//Monthly Rent In PW
		    				autoCharges[i][3] = endDate;
		    				//Start Date From Lease Agreement
		    				autoCharges[i][4] = description;
		    				i++;
		            }	
		            RunnerClass.setautoCharges(autoCharges);
		           // System.out.println("Total Pending Buildings  = " +RunnerClass.pendingBuildingList.length);
		            //for(int j=0;j<RunnerClass.pendingBuildingList.length;j++)
		            //{
		            //	System.out.println(RunnerClass.pendingBuildingList[j][j]);
		           // }
		            rs.close();
		            stmt.close();
		            con.close();
		 return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		 return false;
		}
	}
	

	
	public static boolean assignChargeCodes(String moveInChargesIDs, String autoChargesIDs,String buildingAbbreviation,String SNo)
	{
	  String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
	    String sql = "update automation.LeaseCloseOutsChargeChargesConfiguration_"+SNo+" Set MoveInCharge ='1' where ID in  ("+moveInChargesIDs+")\n"
	    		+ "update automation.LeaseCloseOutsChargeChargesConfiguration_"+SNo+" Set AutoCharge ='1' where ID in  ("+autoChargesIDs+")";

	    try (Connection conn = DriverManager.getConnection(connectionUrl);
	        Statement stmt = conn.createStatement();) 
	    {
	      stmt.executeUpdate(sql);
	      System.out.println("Charge Codes are assigned");
	      stmt.close();
            conn.close();
            return true;
	    } catch (SQLException e) 
	    {
	      e.printStackTrace();
	      return false;
	    }
	}

	public static void notAutomatedFields(String buildingName, String nextValue) throws Exception
	  {

		  String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
		  String sql = "Update [Automation].[LeaseInfo] Set NotAutomatedFields =CONCAT((Select top 1 NotAutomatedFields from Automation.LeaseInfo where  BuildingName like '%"+buildingName+"%'),',"+nextValue+"') where BuildingName like '%"+buildingName+"%'";
		    

		    try (Connection conn = DriverManager.getConnection(connectionUrl);
		        Statement stmt = conn.createStatement();) 
		    {
		      stmt.executeUpdate(sql);
		      System.out.println("Not Automated Field Updates = "+nextValue);
		      stmt.close();
	            conn.close();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    RunnerClass.setStatusID(3);
	  }
	public static String getBuildingEntityID(String company,String ownerName)
	{
		try
		{
		        Connection con = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(AppConfig.connectionUrl);
		            String queryToGetBuildingEntityID = "Select top 1 BuildingEntityID from LeaseFact_Dashboard where  LeaseName like '%"+ownerName+"%' and Company ='"+company+"'";
		            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		            stmt.setQueryTimeout(100);
		           // stmt = con.createStatement();
		            rs = stmt.executeQuery(queryToGetBuildingEntityID);
		            if(rs.next())
		            {
		            String 	buildingEntityID = rs.getObject(1).toString();
		            return buildingEntityID;
		            }
		            else return "Error";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error";
		}
	}

}
