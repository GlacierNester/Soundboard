package com.glacier.soundboard.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Constants {
	public static String baseDrive = File.listRoots()[0].getPath();
	public static String filename = "SoundbarProperties.properties";
	public static String propertiesPath = baseDrive+"Glacier Nester/properties/"+filename;
	public static String logPath = baseDrive+"Glacier Nester/logs/";
	public static String howTo = "How to add audio?\n"
							   + "1. Select an audio\n"
							   + "\t a. Simply press the button that says add sounds\n"
							   + "\t b. Select your sound to get added to the board\n"
							   + "2. Select a photo\n"
							   + "\t a. Want a photo to click instead of that garden variety button?\n"
							   + "\t b. Hit that add photos button, and select a photo!\n"
							   + "3. Select which audio that photo is for\n"
							   + "\t a. You'll see a list of available audios to pick for!\n";
	public static int openingWidth = 407;
	public static int openingHeight = 99;
	public static int howToWidth = 602;
	public static int howToHeight = 256;
	public static int errorWidth = 300;
	public static int errorHeight = 100;
	public static int pickPhotoWidth = 244;
	public static int pickPhotoHeight = 96;
	public static int rowSize = 5;
	private static String[] photooptions = {"*.png","*.bmp","*.gif","*.jpeg"};
	private static String[] audiooptions = {"*.mp3","*.aiff","*.m4a","*.wav"};
	public static List<String> photoExtensions = Arrays.asList(photooptions);
	public static List<String> audioExtensions = Arrays.asList(audiooptions);
	public static void setSoundboard(String string) {
		filename = string;
	}
}
