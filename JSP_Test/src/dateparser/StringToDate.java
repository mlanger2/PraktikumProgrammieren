package dateparser;

import java.util.*;
import java.text.*;

public class StringToDate
{
	public static void main(String[] args)
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			System.out.println("Heute ist der " + dateFormat.format(today));
			System.out.println("");
			
			String str_minDate = "2013-05-31";
			String str_fromDate = "2013-03-25";
			String str_toDate = "2013-06-30";
			
			
			Date vonDatum, bisDatum, minDatum;
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd");

			
			minDatum = (Date) formatter.parse(str_minDate);
			vonDatum = (Date) formatter.parse(str_fromDate);
			bisDatum = (Date) formatter.parse(str_toDate);
			long sec_minDatum = minDatum.getTime();
			long sec_vonDatum = vonDatum.getTime();
			long sec_bisDatum = bisDatum.getTime();
			long sec_heuteDatum = today.getTime();
			
			String str_todayDate = dateFormat.format(today);
			System.out.println("today_String: " + str_todayDate);
			
			
			System.out.println("Von-datum: " + str_fromDate +": " + sec_vonDatum  + " ms seit 01.01.1970");
			System.out.println("Bis-datum: " + str_toDate + ": " + sec_bisDatum  + " ms seit 01.01.1970");
			System.out.println("Min-datum: " + str_minDate + ": " + sec_minDatum  + " ms seit 01.01.1970"); 
			System.out.println("");
			//Vergleiche
			if (sec_vonDatum > sec_heuteDatum)
			{
			System.out.println("Datumsangaben nicht zulässig (Datum in Zukunft!");
			System.out.println("Ersetze mit heutigem Datum");
			}
			
			
			
			
			if (sec_vonDatum < sec_minDatum)
			{
				System.out.println("Ihr Startdatum (" + str_fromDate + ") liegt zu früh und wird durch den 31.05.2013 ersetzt");
				str_fromDate = str_minDate;
				System.out.println("Neues Von-datum: " + str_fromDate);
			}
			//
			if (sec_vonDatum < 0)
			{
				str_fromDate = str_minDate;
				System.out.println("Ihr Startdatum ist ungültig wird durch den 31.05.2013 ersetzt");
				System.out.println("Von-datum: " + str_fromDate);
			}
			//
			if (sec_vonDatum < sec_bisDatum)
				{
				System.out.println("Von-Datum liegt vor Bis-Datum (OK)");
				}
		}//end try
		catch (ParseException e)
		{
			System.out.println("Exception :" + e);
		}// end catch
		
		
		
		
		
		
		
		
		
		
		
		
	}//end main
}//end class
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%alter Dateparser%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//package dateparser;
//
//import java.util.*;
//import java.text.*;
//
//public class StringToDate
//{
//	public static void main(String[] args)
//	{
//		try
//		{
//			String str_date = "11-June-07";
//			DateFormat formatter;
//			Date date;
//			formatter = new SimpleDateFormat("dd-MMM-yy");
//			date = (Date) formatter.parse(str_date);
//			System.out.println("Today is " + date);
//		}// end try
//		catch (ParseException e)
//		{
//			System.out.println("Exception :" + e);
//		}//end catch
//
//	}//end main
//}// end class StringToDate 