import java.io.File;
import java.util.ArrayList;



public class AktualisierungMain {

	public static void main(String[] args) throws Exception {
		
		File fs = new File("E:/Studium/Master2.Semester/Praktikum Programmieren/Archive/viewernotification/");
		while(true){
			for (File file : fs.listFiles()) {
				ReadText read = new ReadText();
				ArrayList<String[]> parsedData = read.read(file.getAbsolutePath());
				
				SolrFunction interact = new SolrFunction("http://localhost:7080/solr/collection1");
				interact.SolrAdd(parsedData);
				try{boolean bool = file.delete();System.out.println(bool);}
				catch(Exception e){System.out.println(e.toString());}
			}
			new AktualisierungMain().waitMinutes();
		}
	}
	
	public void waitMinutes() throws Exception{
		Thread.sleep(900000);
	}
		
}
