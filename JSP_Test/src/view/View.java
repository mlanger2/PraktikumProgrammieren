package view;

import java.io.PrintWriter;

public class View {
	
	public void blank (PrintWriter a, String vname, String nname)
	{
		a.println(
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n"+
      "<HTML>\n" +
      "<HEAD><TITLE>Test</TITLE></HEAD>\n" +
      "<BODY BGCOLOR=\"#FDF5E6\">\n" +
      "<H1 ALIGN=\"CENTER\">Test</H1>\n" +
      "<p>\n" +
      vname + " " + nname +
      "</p>\n" +
      "Now, wasn't that an interesting sample\n" +
      "of code?\n" +
      "</BODY></HTML>");
	}

}
