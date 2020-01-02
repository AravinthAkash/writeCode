package com.freshWorks.Backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.TimerTask;

public class TimeToLive extends TimerTask {
	
	
	    private String key;
	 
	    /**
	     * Constructor
	     * @param key The key to use & store
	     */
	    public TimeToLive(String key){
	        this.key = key;
	    }
	 
	    @SuppressWarnings("unchecked")
		@Override
	    public void run() {
	    	FileOperations time = new FileOperations();
	    	System.out.println("Time-To-Live in progress");
	            	try {
	            		
	        	File file = new File(KeyValueInitilization.path);
	        	FileInputStream f = new FileInputStream(file);
	            ObjectInputStream s = new ObjectInputStream(f);
	            time.dataStore = (HashMap<String, String>) s.readObject();
	            
	            if(this.key != null & this.key.length() > 0){
		        	time.delete(this.key);
		        }
	            
	        	FileOutputStream fout = new FileOutputStream(file);
	            ObjectOutputStream sout = new ObjectOutputStream(fout);
	            sout.writeObject(time.dataStore);
	            sout.close();
	            
	            System.out.println("Time - To -Live operation completed");
	        	
	        }catch (IOException e) {
	            e.printStackTrace();
	        	
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	

}
