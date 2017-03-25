/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4.pkg0;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Harry
 */
public class Save extends Thread {
    
    
    String fileName="";
    String data="";
    String baseFileName="C:\\Users\\Harry\\Desktop\\irctc";
    Save(String filename,String data)
    {
        this.fileName=filename;
        this.data=data;
    }
    public void run()
    {
        try
        {
            System.out.println("saving to "+new File(baseFileName+"\\"+fileName+".html").getAbsolutePath());
            FileWriter fw = new FileWriter(new File(baseFileName+"\\"+fileName+".html"));
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();

        }
        catch(Exception m)
        {
            
        }
    }
    
}
