package pl.ach.helloworld.Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.ini4j.*;
import org.ini4j.Profile.Section;

public class Main {

	public static void main(String[] args) {
		String filepath = "C:\\test.ini";
		Main.iniParser(filepath);
	}

	/*
	 * parse ini file
	 */
	public static void iniParser(String filepath){
		try {
			Ini ini = new Ini(new FileReader(filepath));
			Map<String, String> hashMapToReplace = new LinkedHashMap<String, String>();
			for (String sectionName: ini.keySet()) {
	    		Section section = ini.get(sectionName);
	    		for (String optionKey: section.keySet()) {
	    			hashMapToReplace.put(optionKey, section.get(optionKey));
	    		}
	    	}
			
			System.out.println("Strings to replace: "+hashMapToReplace.toString());
			for(String key: hashMapToReplace.keySet()){
				if(hashMapToReplace.get(key).startsWith("%c_")){
					System.out.println("Found command to trigger on the console");
				}
				System.out.print(key + " replace to " + hashMapToReplace.get(key) + "\r\n");
			}
			
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * run command from console
		 */
		 try    { 
             Process p=Runtime.getRuntime().exec("cmd /c whoami"); 
             p.waitFor(); 
             BufferedReader reader=new BufferedReader(
                 new InputStreamReader(p.getInputStream())
             ); 
             String line; 
             while((line = reader.readLine()) != null) 
             { 
                 System.out.println(line);
             } 

         }
         catch(IOException e1) {} 
         catch(InterruptedException e2) {}
	}
}
