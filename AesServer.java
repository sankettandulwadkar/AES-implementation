  
//File : AesServer.java  
//AESReceiver  
//Developed by Malhar Vora  
//Developed on : 9-5-2011  
import java.io.*;  
import java.net.*;  
import java.util.*;  
import java.math.*;  

import javax.crypto.*;  

import java.security.*;  

import javax.crypto.spec.*;  
  
public class AesServer  
{  
  
 public static void main(String []str)throws Exception  
 {  
    ServerSocket ss = new ServerSocket(5656);  
    Socket s = null;  
    InputStream is = null;  
    ObjectInputStream ois = null;  
    Frame f =null;  
      
    while(true)  
    {  
    s=ss.accept();  
    is = s.getInputStream();  
    ois = new ObjectInputStream(is);  
    f = (Frame)ois.readObject();   
      
    //System.out.println(decryptString(f.data,f.k,new IvParameterSpec(f.iv)));
    
    //String content = decryptString(f.data,f.k,new IvParameterSpec(f.iv));
    
    String content = decryptString(f.data, f.k);
    
    File file = new File("Data_Transferred.txt");
    
	// if file doesnt exists, then create it
	if (!file.exists()) {
		file.createNewFile();
	}

	FileWriter fw = new FileWriter(file.getAbsoluteFile());
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(content);
	bw.close();
    
    
             
    break;  
    }  
      
    ois.close();  
    is.close();  
    s.close();  
    ss.close();    
    
 }  
   
    
   
 //static String decryptString(String data,Key key,IvParameterSpec ivspec)throws Exception
 static String decryptString(String data,Key key)throws Exception
 {  
   /*  KeyGenerator kg = KeyGenerator.getInstance("AES");  
  //kg.init(128);  
  Cipher c = Cipher.getInstance("AES/CFB/PKCS5Padding");  
  Key k = key;  
    
  c.init(Cipher.DECRYPT_MODE,new SecretKeySpec("1234512345123451".getBytes(),"AES"),ivspec);  
    
   
    
  	byte[] plaintext = c.doFinal(data.getBytes());
	 
	 Key k = key; */
	 String k = "1a25s8fe5dsg65ad";
	 
	 byte[] plaintext = AES.decrypt(data.getBytes(), k.getBytes()); 
    
  return new String(plaintext);  
 }  
   
}  