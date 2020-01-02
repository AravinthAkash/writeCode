package com.freshWorks.Backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONObject;




public class FileOperations {
	
	
	HashMap<String, String> dataStore =  new HashMap<String , String>();
	File writeFile;
	 
    
    public  void add(String k , JSONObject v){
    	if(dataStore.containsKey(k)){
    		
    		//throw new IllegalArgumentException("Already Key exist.Kindly use Unique key");
    	}
    	
    	if(v.size() > 16000){
    		throw new IllegalArgumentException("The size of the JSON object exceeds limit");
    	}
    	    	    	    	
    	dataStore.put(k, v.toString());
        
    }
 
    
    public void add(String k,JSONObject v, long timeout){
        
        
        if(dataStore.containsKey(k)){
    		
    		//throw new IllegalArgumentException("Already Key exist.Kindly use Unique key");
    	}
        if(v.size() > 16000){
    		throw new IllegalArgumentException("The size of the JSON object exceeds limit");
    	}
 
              
        dataStore.put(k, v.toString());
 
        //Create a delete operation after timeout
        if(timeout > 0){
            new Timer().schedule(new TimeToLive(k), timeout);
        }
 
         }
 
    
    public  Object get(String key){
        if(dataStore.containsKey(key)){
            return dataStore.get(key);
        }else{
        	throw new IllegalArgumentException("No such Key.Provide correct key to retrieve value");
            
        }
    }
 
   
    public   boolean delete(String key){
        if(dataStore.containsKey(key)){
            dataStore.remove(key);
            return true;
        }
        return false;
    }
 
    
    public void createFile(String path) throws IOException, IllegalAccessException{
    	
    	File file = new File(path);
    	writeFile = file;
        if (!file.exists()) {
            file.createNewFile();
            
        } else if(file.length() > 1073741824){
        	throw new IllegalAccessException("File size has reached maximum limit.Use an alternative file");
        }
    }
    
    public synchronized void writeToFile() throws IOException{
    	
    	 FileOutputStream fout = new FileOutputStream(writeFile);
         ObjectOutputStream sout = new ObjectOutputStream(fout);
         sout.writeObject(this.dataStore);
         sout.close();
    }
    
    public synchronized void readFromFile() throws IOException, ClassNotFoundException{
    	
    	if(writeFile.length() > 0){
    		FileInputStream f = new FileInputStream(writeFile);
    		
    	   ObjectInputStream s = new ObjectInputStream(f);
    	    this.dataStore = (HashMap<String, String>) s.readObject();
    		}
    }

}
