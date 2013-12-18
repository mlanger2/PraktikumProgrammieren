import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class ReadText {

	private ArrayList<String[]> readTxtFile (String url) throws Exception{
		ArrayList<String[]> xmlDocURL = new ArrayList<String[]>();
		
		
		
		//Lese txtAktualisierungsFile
		FileReader txtFile = new FileReader(url);
		BufferedReader bufTxtFile = new BufferedReader(txtFile);
		if(bufTxtFile.ready()){
			String a = bufTxtFile.readLine();
			//wenn das Dokument existiert
		
			
			//Lese jeweils eine Zeile des txt-Dokuments
			while(a != null){
					//			  0      1       2      3       4     5     6       7          8
					//parse[] = Land, Sprache, Thema, Quelle, Datum, URL, Titel, Kurztext, Artikeltext
					String [] parse = new String [9];
					
					//Lese alle Details aus dem PfadString aus
					String [] arr = a.split("/");
					parse[0] = arr[6];//Land
					parse[1] = arr[7];//Sprache
					parse[2] = arr[8];//Thema
					parse[3] = arr[9];//Quelle
					//Datum
					parse[4] = arr[12].replaceFirst("d", "") + "-"+arr[11].replaceFirst("m", "")+"-"+ arr[10].replaceFirst("y", "")+"T00:00:00Z";
					parse[5] = a;//URL
					xmlDocURL.add(parse);
					a = bufTxtFile.readLine();
					a = bufTxtFile.readLine();
			}
		}
		bufTxtFile.close();
		return xmlDocURL;
	}
	
	private void readXmlFile(String url, String [] strings ) throws Exception {
		//Lese XML-File
		File xmlFile = new File(url);
		
		//Parse XML-File
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document parseXmlFileGes = dBuilder.parse(xmlFile);
		parseXmlFileGes.getDocumentElement().normalize();
		
		//gehe zu Inhaltsbereich
		Node item = parseXmlFileGes.getElementsByTagName("item").item(0);
		
		int i = 0;
		Node child = item.getChildNodes().item(i++);
		
		//Lese Inhaltsbereich aus
		//hier zu viele Durchgänge! Viele Nodes mit Namen #text. Muss später optimiert werden.
		
		while (child != null){
			//System.out.println(child.getNodeName());
			if (child.getNodeName().equals("title")){
				strings[6] = child.getTextContent();
			}
			else if (child.getNodeName().equals("description")){
				strings[7] = child.getTextContent();
			}
			else if (child.getNodeName().equals("ExtractedText")){
				strings[8] = child.getTextContent();
			}
		    
			child = item.getChildNodes().item(i++);
		}
	}
	
	public ArrayList<String[]> read (String url) throws Exception{
	
		ArrayList<String[]> parse = readTxtFile (url);
		
		for (String[] strings : parse) {
			readXmlFile(strings[5], strings);
		}
		
		return parse;

		/*System.out.println("Land: " + parse.get(0)[0] + 
							"\n Sprache: " + parse.get(0)[1] + 
							"\n thema: " + parse.get(0)[2] + 
							"\n Quelle: " + parse.get(0)[3] + 
							"\n Datum: " + parse.get(0)[4] + 
							"\n URL: " + parse.get(0)[5] + 
							"\n Titel: " + parse.get(0)[6] + 
							"\n Kurztext: " + parse.get(0)[7] +
							"\n Artikeltext:" + parse.get(0)[8]);
				
				*/
				
				
	}
	
}
