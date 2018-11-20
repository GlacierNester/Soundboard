package com.glacier.soundboard.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UtilityMethods {
	public static void writeFileDataToProperties(File file) 
	{
		String filename = file.getName();
		String filepath = file.getPath();
		if(!isAudio(filename) && isPhoto(filename))
		{
			Stage primaryStage = new Stage();
			FileChooser chooser = new FileChooser();
			chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			chooser.setTitle("What sound is this for?");
			File temp = chooser.showOpenDialog(primaryStage);
			filename=temp.getName().substring(0, temp.getName().lastIndexOf("."));
			filename+=".photo";
		}
		filepath = filepath.replace('\\', '/');
		filename = filename.replace(' ', '_');
		//turns out that properties files don't like having spaces in the key, so we simply write the key to have underscores instead of spaces
		//ezpz
		File propertiesFile = new File(Constants.propertiesPath);
		if(!propertiesFile.exists())
		{
			createPropertiesFile();
			propertiesFile = new File(Constants.propertiesPath);
		}
		try 
		{
			if(!getProperties().containsKey(filename))
			{
				FileOutputStream outfos = new FileOutputStream(propertiesFile,true);
				//I was today (11/19/2018) years old when I realized again
				//that without that boolean there, it doesn't just append to the file
				PrintStream outps = new PrintStream(outfos);
				outps.println(filename + "=" + filepath);
				System.out.println("Filename written is " + filename);
				System.out.println("Filepath written is " + filepath);
				System.out.println("Filename/path written at " + getCurrentTimestamp());
				outps.close();
			}
		} catch (FileNotFoundException e) 
		{
			System.err.println("Error writing to properties file, not found despite our efforts to create it");
		}
	}
	
	private static boolean isPhoto(String filename) {
		boolean ret = false;
		if(filename.substring(filename.lastIndexOf(".")).contains("bmp"))
		{
			ret = true;
		}
		if(filename.substring(filename.lastIndexOf(".")).contains("gif"))
		{
			ret = true;
		}
		if(filename.substring(filename.lastIndexOf(".")).contains("jpeg"))
		{
			ret = true;
		}
		if(filename.substring(filename.lastIndexOf(".")).contains("png"))
		{
			ret = true;
		}
		return ret;
	}

	private static boolean isAudio(String filename) {
		boolean ret = false;
		if(filename.substring(filename.lastIndexOf(".")).contains("mp3"))
		{
			ret = true;
		}
		if(filename.substring(filename.lastIndexOf(".")).contains("aiff"))
		{
			ret = true;
		}
		if(filename.substring(filename.lastIndexOf(".")).contains("wav"))
		{
			ret = true;
		}
		if(filename.substring(filename.lastIndexOf(".")).contains("m4a"))
		{
			ret = true;
		}
		return ret;
	}

	public static void setupLogs()
	{
		File logFolder = new File(Constants.logPath);
    	File outFile = null;
    	File errFile = null;
    	if(!logFolder.exists())
    	{
    		logFolder.setWritable(true);
    		if(logFolder.mkdirs())
    		{
    			outFile = new File(Constants.logPath+"SoundBar.log");
    			errFile = new File(Constants.logPath+"SoundBarErrors.log");
    		}
    	}
    	else
    	{
    		outFile = new File(Constants.logPath+"SoundBar.log");
    		errFile = new File(Constants.logPath+"SoundBarErrors.log");
    	}
    	try {
	    	FileOutputStream outfos = new FileOutputStream(outFile);
			PrintStream outps = new PrintStream(outfos);
			FileOutputStream errfos = new FileOutputStream(errFile);
			PrintStream errps = new PrintStream(errfos);
			System.setOut(outps);
			System.setErr(errps);
			System.out.println("Started SoundBar at " + getCurrentTimestamp());
			System.err.println("Started Soundbar at " + getCurrentTimestamp());
    	}
    	catch(IOException ex)
    	{
    		System.err.println("Oh dear, making the log failed. That's an issue!");
    	}
	}

	private static void createPropertiesFile() 
	{
		File folder = new File(Constants.propertiesPath.substring(0,Constants.propertiesPath.lastIndexOf("/")));
		if(!folder.exists())
		{
			folder.setWritable(true);
			if(folder.mkdirs())
			{
				File properties = new File(Constants.propertiesPath);
				try {
					properties.createNewFile();
					System.out.println("Properties File created at " + getCurrentTimestamp());
				} catch (IOException e) {
					System.err.println("Oh dear, making the properties file failed. That's an issue!");
				}
			}
		}
		else
		{
			File properties = new File(Constants.propertiesPath);
			try {
				properties.createNewFile();
				System.out.println("Properties File created at " + getCurrentTimestamp());
			} catch (IOException e) {
				System.err.println("Oh dear, making the properties file failed. That's an issue!");
			}
		}
	}
	
	public static String getCurrentTimestamp() 
	{
		return DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss").format(LocalDateTime.now());
	}
	
	public static String[] getKeysList()
	{
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File(Constants.propertiesPath)));
		} 
		catch (IOException e) 
		{
			System.err.println("Error in fetching the list of keys at " + getCurrentTimestamp());
		}
		//note: filenames can't contain spaces, handle that in the input method
		ArrayList<String> listKeys = new ArrayList<String>();
		props.forEach((key,value) -> listKeys.add((String) key));
		System.out.println("List of Keys Fetched at " + getCurrentTimestamp());
		String[] keys = listKeys.toArray(new String[listKeys.size()]);
		return keys;
	}
	
	public static Properties getProperties()
	{
		Properties props = new Properties();
		try 
		{
			props.load(new FileInputStream(new File(Constants.propertiesPath)));
		} 
		catch (IOException e) 
		{
			System.err.println("Error in fetching the properties at " + getCurrentTimestamp());
		}
		System.out.println("Properties Fetched at " + getCurrentTimestamp());
		return props;
	}
}
