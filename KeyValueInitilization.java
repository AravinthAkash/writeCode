package com.freshWorks.Backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.json.simple.JSONObject;

import java.io.Serializable;



public class KeyValueInitilization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public KeyValueInitilization() {
		// TODO Auto-generated constructor stub
		
	}

	
	static String path = "C:/Users/freshWorks.txt";
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		try {
	        File file = new File(path);
	        if (!file.exists()) {
	            file.createNewFile();
	            
	        } else if(file.length() > 1073741824){
	        	throw new IllegalAccessException("File size has reached maximum limit.Use an alternative file");
	        }
	    
		
		
		
	FileOperations createEntry = new FileOperations();
	if(file.length() > 0){
	FileInputStream f = new FileInputStream(file);
	
	ObjectInputStream s = new ObjectInputStream(f);
    createEntry.dataStore = (HashMap<String, String>) s.readObject();
	}

	JSONObject obj = new JSONObject();

    obj.put("input1", "jsonValue");
    obj.put("input2", new Integer(100));
    obj.put("input3", new Boolean(true));
	
	
	createEntry.add("timeToLive" , obj , 3000);
	createEntry.add("TestValue1", obj);
	createEntry.add("TestValue2" , obj);
	
	       
        FileOutputStream fout = new FileOutputStream(file);
        ObjectOutputStream sout = new ObjectOutputStream(fout);
        sout.writeObject(createEntry.dataStore);
        sout.close();
        
        System.out.println(" the get functionality value" +createEntry.get("TestValue1"));
        
        
       
            }catch (IOException | IllegalAccessException e) {
        e.printStackTrace();
    }
		
		
	}
	
	
	

}
