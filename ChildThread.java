/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4.pkg0;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;


public class ChildThread  extends Thread{
  String    url="";
  String body="";
  String Cookie="";
  int state=0;
  String host="";
  
    ChildThread(String url,String body,String cookie,int state,String host)
            
    {
        this.Cookie=cookie;
        this.body=body;
        this.url=url;
    this.state=state;
    this.host=host;
    }
 
    public void run()
    {
      
         if(state==0)FetchCookie();
        else if(state==1)Get();
        else Post();
        
    }
  


     void Get()
    {
        try{
            
        
            
            URL    url1            = new URL( url );
            HttpURLConnection con= (HttpURLConnection) url1.openConnection();
            con.setDoOutput( true );
            con.setInstanceFollowRedirects( true );
            con.setRequestMethod( "GET" );
            con.setRequestProperty( "Content-Type", " application/json; charset=UTF-8");
            con.setRequestProperty( "charset", "utf-8");
            con.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Host",host );
            con.setRequestProperty("Cookie",Cookie);
            con.setUseCaches( false );
            DataOutputStream wr = new DataOutputStream( con.getOutputStream());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
           while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            String resp=response1.toString();
            in.close();
        }
        catch(Exception m){
            
        }
    }
  void Post()
    {
        
        try{
            
            byte[] postData       = body.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            
            URL    url1            = new URL( url );
            HttpURLConnection con= (HttpURLConnection) url1.openConnection();
            con.setDoOutput( true );
            con.setInstanceFollowRedirects( true );
            con.setRequestMethod( "POST" );
            con.setRequestProperty( "Content-Type", " application/json; charset=UTF-8");
            con.setRequestProperty( "charset", "utf-8");
            con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            con.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Host", host);
            con.setRequestProperty("Cookie",Cookie);
            con.setUseCaches( false );
            DataOutputStream wr = new DataOutputStream( con.getOutputStream());
            wr.write( postData );
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            String resp=response1.toString();
            in.close();
        }
        catch(Exception m){
       m.getMessage();
        }
    }
     void FetchCookie()
    {
        System.out.println("fetch cookie called ");
        try {
       PostMethod      Postmethod= new PostMethod(url);
            Postmethod.addRequestHeader("Host",host);
            Postmethod.addRequestHeader("User-Agent"," Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
            Postmethod.addRequestHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,*/*;q=0.5");
            Postmethod.addRequestHeader("Accept-Language","en-US,en;q=0.8");
            Postmethod.addRequestHeader("Accept-Encoding","gzip,deflate,sdch");
            Postmethod.addRequestHeader("X-Client-Data","CKK2yQEIxLbJAQj9lcoB");
            Postmethod.addRequestHeader("Connection","keepalive,Keep-Alive");
            Postmethod.addRequestHeader("Cookie",Cookie);
            Postmethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
            Postmethod.setRequestBody(body);
            Postmethod.addRequestHeader("Content-Length",""+body.length());
            Postmethod.getFollowRedirects();
            
            
            Postmethod.setFollowRedirects(true);
            HttpClient client = new HttpClient();
            int status=    client.executeMethod(Postmethod);
                Cookie[] cookies = client.getState().getCookies();
        int i=0;
        String cookie="";
        while(i<cookies.length)
        {
            cookie=cookie+";"+cookies[i].getName()+"="+cookies[i].getValue();
            i++;
        }
           cookie=cookie.trim().substring(1);
            System.out.println("cookie "+cookie);
          
         
        }
        catch(Exception m)
        {
            System.out.println(""+m.getMessage());

     
        }
        
        
        
        System.out.println("fetch cookie compleetd");
    
    }
    
}
