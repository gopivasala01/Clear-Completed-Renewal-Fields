package mainPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcelandSendMail  {
	
	
	public static void createExcelFileWithProcessedData()
	{
		//Get Today's date in MMddyyyy format
		LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String date = dateObj.format(formatter);
        RunnerClass.currentDate =date;
        System.out.println(date);
        String filename ;
		try   
		{  
		filename = AppConfig.excelFileLocation+"\\ProrateRent_"+date+".xlsx";  
		File file = new File(filename);
		//if file exists, delete and re create it
		if(file.exists())
		{
			file.delete();
		}
		Workbook wb = new XSSFWorkbook();
		Sheet sheet1 = wb.createSheet("Sheet 1");
		Row header = sheet1.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Company");
		header.createCell(2).setCellValue("Building");
		header.createCell(3).setCellValue("LeaseName");
		header.createCell(4).setCellValue("LeaseEntityID");
		header.createCell(5).setCellValue("AutomationStatus");
		header.createCell(6).setCellValue("AutomationNotes");
		//int totalCurrentDayBuildings = RunnerClass.successBuildings.size()+RunnerClass.failedBuildings.size();
		//sheet1.createRow(sheet1.getLastRowNum()+totalCurrentDayBuildings);
		boolean getBuildings = DataBase.getCompletedBuildingsList();
		if(getBuildings==true&&RunnerClass.completedBuildingList!=null)
		{
			for(int i=0;i<RunnerClass.completedBuildingList.length;i++)
			{
				String ID = RunnerClass.completedBuildingList[i][0];
				String Company = RunnerClass.completedBuildingList[i][1].trim();
				String Building = RunnerClass.completedBuildingList[i][2].trim();
				String LeaseName = RunnerClass.completedBuildingList[i][3];
				String LeaseEntityID = RunnerClass.completedBuildingList[i][4];
				String AutomationStatus = RunnerClass.completedBuildingList[i][5];
				String AutomationNotes = RunnerClass.completedBuildingList[i][6];
				Row row = sheet1.createRow(1+i);
				row.createCell(0).setCellValue(ID);
				row.createCell(1).setCellValue(Company);
				row.createCell(2).setCellValue(Building);
				row.createCell(3).setCellValue(LeaseName);
				row.createCell(4).setCellValue(LeaseEntityID);
				row.createCell(5).setCellValue(AutomationStatus);
				row.createCell(6).setCellValue(AutomationNotes);
				
				
			}
		
		}
		
		System.out.println("Last row in the sheet = "+sheet1.getLastRowNum());
		FileOutputStream fileOut = new FileOutputStream(filename);  
		wb.write(fileOut);
		wb.close();
		fileOut.close();  
		System.out.println("Excel file has been generated successfully.");  
		sendFileToMail(filename);
		}   
		catch (Exception e)   
		{  
			e.printStackTrace();  
		}  
		
		//Send Email the attachment
	}

    static void sendFileToMail(String filename) throws MessagingException {
        // SMTP configuration
        String smtpHost = "smtp.office365.com";
        String smtpPort = "587";

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create a Session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(AppConfig.fromEmail, AppConfig.fromEmailPassword);
            }
        });

        // Create a MimeMessage object
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(AppConfig.fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(AppConfig.toEmail));
        message.setSubject(AppConfig.mailSubject+RunnerClass.currentDate);

        
        // Create MimeBodyPart for the email body
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText("Hi All,\n\n  Please find the attachment.\n\n Regards,\n HomeRiver Group.");
        // Create MimeBodyPart and attach the Excel file
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        try {
            mimeBodyPart.attachFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Create Multipart object and add MimeBodyPart objects to it
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(mimeBodyPart);

        // Set the content of the email
        message.setContent(multipart);

        // Send the email
        session.setDebug(true);
        Transport.send(message);

        System.out.println("Email sent successfully!");
    }
	
	
}
