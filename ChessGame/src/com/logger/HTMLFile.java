package com.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HTMLFile{

	File outFile = null;
	PrintWriter pw = null;
	
	public HTMLFile(String filename){
		try {
			outFile = new File(filename);
			pw = new PrintWriter(outFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	public void h1(String text){
		pw.println("<h1>"+text+"</h1>");
	}
	
	public void hr(){
		pw.println("<hr/>");
	}
	*/
	
	public void write(String html){
		pw.println(html);
	}
	
	public void close(){
		pw.close();
	}
	
	
}
