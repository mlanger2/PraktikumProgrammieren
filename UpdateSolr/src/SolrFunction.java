import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;


public class SolrFunction {
	
	private SolrServer server;
	
	public SolrFunction (String url){
		try{
			//Verbindungsaufbau mit dem Solr-Server
			server = new HttpSolrServer(url);
		}
		catch(Exception e){
			System.out.println("Verbindungsaufbau zum Server nicht möglich.\n"+e.getMessage());
		}
	}
	
	private Collection<SolrInputDocument> ErstelleDokumentCollection(ArrayList<String[]> parsedData) {
		
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		
		for (String [] strings : parsedData){
			
			//Dokument zusammenstellen
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField( "id", strings[5], 1.0f );
		    doc.addField( "Land", strings[0], 1.0f );
		    doc.addField( "Thema", strings[2], 1.0f );
		    doc.addField( "Sprache", strings[1], 1.0f );
		    doc.addField( "Quelle", strings[3], 1.0f );
		    doc.addField( "Datum", strings[4]);
		    doc.addField( "Titel", strings[6], 1.0f );
		    doc.addField( "Kurztext", strings[7], 1.0f );
		    doc.addField( "Artikeltext", strings[8], 1.0f );
	    
		    //Collection erstellen um mehrere Dokumente zu übertragen
		    docs.add( doc );
		}
	    
	    return docs;
	}
	
	public void SolrAdd (ArrayList<String[]> parsedData )
	{
		Collection<SolrInputDocument> docs = ErstelleDokumentCollection(parsedData);
		
		 try {
			 
		    //diese Collection soll übertragen werden
		    server.add( docs );
		    server.commit();
		    
		    //Abfrage, ob Update erfolgreich war
		    UpdateRequest req = new UpdateRequest();
		    req.setAction( UpdateRequest.ACTION.COMMIT, false, false );
		    req.add( docs );
		    UpdateResponse rsp = req.process( server );
		    System.out.println(rsp.toString());  
		}
	    catch (SolrServerException e) {
			e.printStackTrace();
		    System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		    System.out.println(e.getMessage());
	    }
	}

}
