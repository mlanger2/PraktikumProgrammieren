package view;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.ParameterMap;

@WebServlet("/ViewControl")
public class ViewControl extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("text/html");
		// View view = new View();
		String[] topicArr = req.getParameterValues("topic");
		String[] sourceArr = req.getParameterValues("source");
		String[] datefromArr = req.getParameterValues("datefrom");
		String[] datetoArr = req.getParameterValues("dateto");
		String[] freeSearchArr = req.getParameterValues("search");
		String[] pageTypeArr = req.getParameterValues("pagetype");
		//
		// String param = request.getParameter("someName");
		// if ((arr01[0] == null) || (arr01[0].trim().equals("")))
		// {
		// //arr01[0] = "2013-05-31";
		//
		// }
		//
		// if ((datetoArr[0] == null) || (datetoArr[0].trim().equals("")))
		// {
		// datetoArr[0] = "str_todayDate";
		// }
		// //
		// if ((topicArr[0] == null) || (topicArr[0].trim().equals("")))
		// {
		// topicArr[0] = "business";
		// }
		// //
		// if ((sourceArr[0] == null) || (sourceArr[0].trim().equals("")))
		// {
		// sourceArr[0] = "CNN";
		// }
		// //
		// if ((pageTypeArr[0] == null) || (pageTypeArr[0].trim().equals("")))
		// {
		// pageTypeArr[0] = "0";
		// }

		//
		System.out.println(topicArr[0]);
		System.out.println(sourceArr[0]);
		System.out.println(datefromArr[0]);
		System.out.println(datetoArr[0]);
		// view.blank(resp.getWriter(),req.getParameterValues("firstName")[0],req.getParameterValues("lastName")[0]);
		System.out.println(freeSearchArr[0]);
		System.out.println(pageTypeArr[0]);
		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		// public class StringToDate
		// {
		// public static void main(String[] args)
		// {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		System.out.println("Heute ist der " + dateFormat.format(today));
		System.out.println("");

		String str_minDate = "2013-05-31";
		String str_fromDate = datefromArr[0];
		String str_toDate = datetoArr[0];

		Date vonDatum = null;
		Date bisDatum = null;
		;
		Date minDatum = null;
		DateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");

		try
		{
			minDatum = (Date) formatter.parse(str_minDate);
		}// end try
		catch (ParseException e)
		{
			System.out.println("Exception :" + e);
		}// end catch
		try
		{
			vonDatum = (Date) formatter.parse(str_fromDate);
		}// end try
		catch (ParseException e)
		{
			System.out.println("Exception :" + e);
		}// end catch
		try
		{
			bisDatum = (Date) formatter.parse(str_toDate);
		}// end try
		catch (ParseException e)
		{
			System.out.println("Exception :" + e);
		}// end catch
			// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		long sec_minDatum = minDatum.getTime();
		long sec_vonDatum = vonDatum.getTime();
		long sec_bisDatum = bisDatum.getTime();
		long sec_heuteDatum = today.getTime();

		String str_todayDate = dateFormat.format(today);
		System.out.println("today_String: " + str_todayDate);

		System.out.println("Von-datum: " + str_fromDate + ": " + sec_vonDatum
				+ " ms seit 01.01.1970");
		System.out.println("Bis-datum: " + str_toDate + ": " + sec_bisDatum
				+ " ms seit 01.01.1970");
		System.out.println("Min-datum: " + str_minDate + ": " + sec_minDatum
				+ " ms seit 01.01.1970");
		System.out.println("");
		// Vergleiche
		if (sec_vonDatum > sec_heuteDatum)
		{
			System.out.println("Datumsangaben nicht zulässig (Datum in Zukunft!");
			System.out.println("Ersetze mit heutigem Datum");
		}

		if (sec_vonDatum < sec_minDatum)
		{
			System.out.println("Ihr Startdatum (" + str_fromDate
					+ ") liegt zu früh und wird durch den 31.05.2013 ersetzt");
			str_fromDate = str_minDate;
			System.out.println("Neues Von-datum: " + str_fromDate);
		}
		//
		if (sec_vonDatum < 0)
		{
			str_fromDate = str_minDate;
			System.out
					.println("Ihr Startdatum ist ungültig wird durch den 31.05.2013 ersetzt");
			System.out.println("Von-datum: " + str_fromDate);
		}
		//
		if (sec_vonDatum < sec_bisDatum)
		{
			System.out.println("Von-Datum liegt vor Bis-Datum (OK)");
		}
		// end try
		// catch (ParseException e)
		// {
		// System.out.println("Exception :" + e);
		// end catch

		// ArrayList<int[]> FehlerListe = new ArrayList<int[]>();
		// //Syntax: FehlerListe.add(index, element);
		// FehlerListe.add(0,null);
		// FehlerListe.add(1,null); //Formatfehler. Bitte Default-Datum prüfen!
		// FehlerListe.add(2,2); //Formatfehler. Bitte von-Datum prüfen!
		// FehlerListe.add(3,3); //Formatfehler. Bitte bis-Datum prüfen!
		// FehlerListe.add(4,4); //Fehler! Bis-Datum liegt in Zukunft!
		// FehlerListe.add(5,5); //Fehler! Von-Datum liegt vor Mindestdatum!
		// FehlerListe.add(6,6); //Fehler! Von-Datum ungültig!
		// FehlerListe.add(7,7); //Fehler! Bis-Datum ungültig!
		// FehlerListe.add(8,8); //Fehler! Von-Datum nach Bis-Datum!

		// Aufbau ArrayList für Fehlercodes
		ArrayList<Integer> FehlerListe = new ArrayList<Integer>();
		FehlerListe.add(1); // Formatfehler. Bitte Default-Datum prüfen!
		FehlerListe.add(2); // Formatfehler. Bitte von-Datum prüfen!
		FehlerListe.add(3); // Formatfehler. Bitte bis-Datum prüfen!
		FehlerListe.add(4); // Fehler! Bis-Datum liegt in Zukunft!
		FehlerListe.add(5); // Fehler! Von-Datum liegt vor Mindestdatum!
		FehlerListe.add(6); // Fehler! Von-Datum ungültig!
		FehlerListe.add(7); // Fehler! Bis-Datum ungültig!
		FehlerListe.add(8); // Fehler! Von-Datum nach Bis-Datum!

		// Aufbau UebergabearrayList fuer Model-Control
		ArrayList<String[]> ViewToLucene = new ArrayList<String[]>();
		ViewToLucene.add(topicArr);
		ViewToLucene.add(sourceArr);
		ViewToLucene.add(datefromArr);
		ViewToLucene.add(datetoArr);
		ViewToLucene.add(freeSearchArr);
		// ViewToLucene.add(pageTypeArr); //
	}// end of do get
}// end of class
