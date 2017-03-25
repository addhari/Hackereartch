/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4.pkg0;

import java.awt.Image;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 * @author Harry
 */
public class Engine extends Thread {
    int state=0;
    String url="";
    String body="";
    String cookie="";
    String host="";
    String respone="";
    GetMethod Getmethod=null;
    PostMethod Postmethod=null;
            HttpClient client=new HttpClient();
    
    Engine()
    {
        
    }
    
    
    
    
    public void run()
    {
        automate();
    }
    
      void FetchCookie(int method)
    {
        
        
          String[] cookiesArray=new String[4];
        Header[] headers=null;
        if(method==1)
            headers=Getmethod.getResponseHeaders();
        else
            headers=Postmethod.getResponseHeaders();
        int[] index=new int[4];
        int position=0;
        for(int currentLocation=0;currentLocation<headers.length;currentLocation++)
        {
            
            if(headers[currentLocation].getName().equalsIgnoreCase("Set-Cookie"))//Set-Cookie
            {
                index[position]=currentLocation;
                position++;
                
            }
        }
        
        for(int i=0;i<4;i++)
        {
            cookiesArray[i]="";
            String tempval= headers[index[i]].getValue().trim().replace("path=/; HttpOnly","").replace("path=/;httponly","").replace("HttpOnly","").replace("HttpOnly","").replace("Path=/; HttpOnly","").replace("path=/;httponly","").replace("set-cookie: ","").replace("HttpOnly,","").replace("path=/", "").replace("httponly, ", "").replace(" Path=/, ", "").replace("Domain=.irctc.co.in; Path=/;","").trim();
            
            if(tempval.indexOf("JSESSIONID")!=-1&&tempval!=null)cookiesArray[0]=tempval;
            else   if(tempval.indexOf("SLB_Cookie")!=-1&&tempval!=null)cookiesArray[1]=tempval;
            else   if(tempval.indexOf("citrix_ns_id_.irctc.co.in_%2F_wat")!=-1&&tempval!=null)cookiesArray[3]=tempval;
            else   if(tempval.indexOf("citrix_ns_id")!=-1&&tempval!=null)cookiesArray[2]=tempval;
            
        }
        cookie=cookie.replaceAll("null","").replaceAll("nullnull","");
        cookie=cookiesArray[0]+cookiesArray[1]+cookiesArray[2]+cookiesArray[3];
        
        System.out.println("cookie "+cookie);
    }
    
    
    void automate()
    {
        while(true)
        {
            if(state==0)
            {
                url="https://www.irctc.co.in/eticketing/loginHome.jsf";
                host="www.irctc.co.in";
                get();
                
                new Save("1",respone).start();
                FetchCookie(1);
                
            }
            
            state++;
        }
        
        
        
        
    }
    
    
    
  public void GenerateCapctha()
    {
       
       

    
        GetMethod  Getmethod= new GetMethod("https://www.irctc.co.in/eticketing/captchaImage");
        Getmethod.addRequestHeader("Referer","https://www.irctc.co.in/eticketing/trainbetweenstns.jsf");
        Getmethod.addRequestHeader("Accept-Encoding","gzip,deflate,sdch");
        Getmethod.addRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        Getmethod.addRequestHeader("Cookie",cookie);
        Getmethod.addRequestHeader("Host","www.irctc.co.in");
 
        
        try{
         
            client.executeMethod(Getmethod);
            
       
            {
                InputStream in = Getmethod.getResponseBodyAsStream();
                Image image = ImageIO.read(in);
                ImageIcon icon = new ImageIcon(image);
                cap.captcha.setIcon(icon);
            
  
            }
          
            
        }
        
        catch(Exception e){
            
            e.printStackTrace();
        }
        
    }
   
    
    void get()
{
 try {
             Getmethod= new GetMethod(url);
            Getmethod.addRequestHeader("Host",host);
            Getmethod.addRequestHeader("User-Agent"," Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
            Getmethod.addRequestHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,*/*;q=0.5");
            Getmethod.addRequestHeader("Accept-Language","en-US,en;q=0.8");
            Getmethod.addRequestHeader("Accept-Encoding","gzip,deflate,sdch");
            Getmethod.addRequestHeader("X-Client-Data","CKK2yQEIxLbJAQj9lcoB");
            Getmethod.addRequestHeader("Connection","keepalive,Keep-Alive");
            Getmethod.addRequestHeader("Cookie",cookie);
            Getmethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
        
            Getmethod.addRequestHeader("Content-Length",""+body.length());
            Getmethod.getFollowRedirects();
            Getmethod.setFollowRedirects(true);
            HttpClient client = new HttpClient();
            client.setTimeout(20000);
            client.setConnectionTimeout(15000);
            int status=    client.executeMethod(Getmethod);
           respone=Getmethod.getResponseBodyAsString();
            
        }
        catch(Exception m)
        {
            post();
            m.printStackTrace();
        }
}
       void getThread()
{

}
    
    
    
  void post()
    {
        try {
            PostMethod Postmethod= new PostMethod(url);
            Postmethod.addRequestHeader("Host",host);
            Postmethod.addRequestHeader("User-Agent"," Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
            Postmethod.addRequestHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,*/*;q=0.5");
            Postmethod.addRequestHeader("Accept-Language","en-US,en;q=0.8");
            Postmethod.addRequestHeader("Accept-Encoding","gzip,deflate,sdch");
            Postmethod.addRequestHeader("X-Client-Data","CKK2yQEIxLbJAQj9lcoB");
            Postmethod.addRequestHeader("Connection","keepalive,Keep-Alive");
            Postmethod.addRequestHeader("Cookie",cookie);
            Postmethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
            Postmethod.setRequestBody(body);
            Postmethod.addRequestHeader("Content-Length",""+body.length());
            Postmethod.getFollowRedirects();
            Postmethod.setFollowRedirects(true);
            HttpClient client = new HttpClient();
            client.setTimeout(20000);
            client.setConnectionTimeout(15000);
            int status=    client.executeMethod(Postmethod);
           respone=Postmethod.getResponseBodyAsString();
            
        }
        catch(Exception m)
        {
            post();
            m.printStackTrace();
        }
    }  
    
    void postThread()
    {
        
    }
    
    
    
    
    void fetchCookie()
    {
        
    }
}



