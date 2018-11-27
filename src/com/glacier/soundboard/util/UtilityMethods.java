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

import com.glacier.soundboard.err.ErrorTypes;
import com.glacier.soundboard.err.Errors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UtilityMethods {
	/**
	 * Writes the data of a given file to the properties file so the rest of the soundbar can use it
	 * @param file the file whose data we're writing to the properties file
	 */
	public static void writeFileDataToProperties(File file) 
	{
		String filename = file.getName();
		String filepath = file.getPath();
		if(!isAudio(filename) && isPhoto(filename))
		{
			filename = getChosenFile() + ".photo";
		}
		if(!filename.equals(".photo"))
		{
			//because if it equals ".photo" then they're coming back from the photo selector without having any audios to assign it to
			filepath = filepath.replace('\\', '/');
			filename = filename.replace(' ', '_');
			//these two replacements make the key and the filepath play nice in the properties file
			File propertiesFile = new File(Constants.propertiesPath);
			try {
				propertiesFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!propertiesFile.exists())
			{
				System.out.println("didn't exist?");
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
				else
				{
					Errors.showErrorStage(ErrorTypes.ALREADY_EXISTS);
				}
			} catch (FileNotFoundException e) 
			{
				System.err.println("Error writing to properties file, not found despite our efforts to create it");
			}
		}
		else
		{
			Errors.showErrorStage(ErrorTypes.NO_AUDIO_TO_SET);
		}
	}

	private static String getChosenFile() {
		Stage primaryStage = new Stage();
		HBox wrapthings = new HBox();
		VBox radioButtons = new VBox();
		ToggleGroup toggle = new ToggleGroup();
		Button btChoose = new Button("I Choose This One");
		ArrayList<String> choice = new ArrayList<String>();
		double height = 0.0;
		double width = 0.0;
		for(String x : getKeysList())
		{
			if(isAudio(x))
			{
				if(!hasPhoto(x))
				{
					RadioButton option = new RadioButton(x);
					option.setToggleGroup(toggle);
					radioButtons.getChildren().add(option);
					height += option.getPrefHeight();
					if(option.getPrefWidth() > width)
					{
						width = option.getPrefWidth();
					}
				}
			}
		}
		btChoose.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event)
			{
				RadioButton rad = (RadioButton) toggle.getSelectedToggle();
				choice.add(rad.getText());
				primaryStage.close();
			}
		});
		if(radioButtons.getChildren().isEmpty())
		{
			primaryStage.close();
			return "";
		}
		wrapthings.getChildren().add(radioButtons);
		wrapthings.getChildren().add(btChoose);
		primaryStage.setScene(new Scene(wrapthings,width+btChoose.getPrefWidth(),height+btChoose.getPrefHeight()));
		primaryStage.setTitle("Choose a sound for this image");
		primaryStage.showAndWait();
		return choice.get(choice.size()-1).substring(0, choice.get(choice.size()-1).lastIndexOf("."));
	}

	public static boolean hasPhoto(String x) {
		return getProperties().containsKey(x.substring(0, x.lastIndexOf("."))+ ".photo");
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
		ArrayList<String> listKeys = new ArrayList<String>();
		props.forEach((key,value) -> listKeys.add((String) key));
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
		return props;
	}

	public static double getLargestWidth(ArrayList<Double> widths) {
		double ret = -1.0;
		for(double x : widths)
		{
			if(x>ret)
			{
				ret=x;
			}
		}
		return ret;
	}

	public static double getLargestHeight(ArrayList<Double> heights) {
		double ret = -1.0;
		for(double x : heights)
		{
			if(x>ret)
			{
				ret=x;
			}
		}
		return ret;
	}
}
