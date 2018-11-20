package com.glacier.soundboard.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Constants {
	public static String baseDrive = File.listRoots()[0].getPath();
	public static String propertiesPath = baseDrive+"Glacier Nester/properties/SoundbarProperties.properties";
	public static String logPath = baseDrive+"Glacier Nester/logs/";
	public static String howTo = "How to add audio?\n"
							   + "1. Select an audio\n"
							   + "\t a. Simply press the button that says add things\n"
							   + "\t b. Select your sound to get added to the board\n"
							   + "2. Select a photo\n"
							   + "\t a. Want a photo to click instead of that garden variety button?\n"
							   + "\t b. Hit that add things button again, and pick a photo!\n"
							   + "3. Select which audio that photo is for\n"
							   + "\t a. Pick what audio you want that photo to represent\n"
							   + "\t b. Picked the wrong audio? Well, now that audio you picked has a photo to it! Yay!";
	public static int openingSizeOne = 407;
	public static int openingSizeTwo = 99;
	public static int howToSizeOne = 602;
	public static int howToSizeTwo = 256;
	private static String[] photooptions = {"*.png","*.bmp","*.gif","*.jpeg"};
	private static String[] audiooptions = {"*.mp3","*.aiff","*.m4a","*.wav"};
	public static List<String> photoExtensions = Arrays.asList(photooptions);
	public static List<String> audioExtensions = Arrays.asList(audiooptions);
}
