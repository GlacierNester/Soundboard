package com.glacier.soundboard.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Constants {
	public static String baseDrive = File.listRoots()[0].getPath();
	public static String filename = "SoundbarProperties.properties";
	public static String propertiesPath = baseDrive+"Glacier Nester/properties/"+filename;
	public static String logPath = baseDrive+"Glacier Nester/logs/";
	public static String howTo = "How to do things?\n"
							   + "1. Select an audio\n"
							   + "\t a. Simply press the button that says add sounds\n"
							   + "\t b. Select your sound to get added to the board\n"
							   + "2. Select a photo\n"
							   + "\t a. Want a photo to click instead of that garden variety button?\n"
							   + "\t b. Hit that add photos button, and select a photo!\n"
							   + "3. Select which audio that photo is for\n"
							   + "\t a. You'll see a list of available audios to pick for!\n"
							   + "4. Select a board\n"
							   + "\t a. You'll be given a list of boards to pick from.\n"
							   + "\t b. You can also make another board file by typing the name\n"
							   + "\t in the text box, and select that button to make a new board.\n"
							   + "5. Start a board\n"
							   + "\t a. This simply starts the board you have selected, if you don't\n"
							   + "\t want to go to the trouble of selecting it every time!\n"
							   + "6. Delete a board\n"
							   + "\t a. This'll bring up a list of boards, and you can pick\n"
							   + "\t which board you'd like to delete. Be careful, this can't\n"
							   + "\t be undone!\n"
							   + "7. Delete a sound\n"
							   + "\t a. This brings up a list of sounds and photos for your current board.\n"
							   + "\t b. Choose an item to delete, and off it'll go!\n"
							   + "\t c. Be careful, like deleting a board, this can't be undone.\n"
							   + "\t d. Deleting a sound deletes its photo choice as well.";
	public static int openingWidth = 407;
	public static int openingHeight = 99;
	public static int howToWidth = 510;
	public static int howToHeight = 550;
	public static int errorWidth = 400;
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
		propertiesPath = baseDrive+"Glacier Nester/properties/"+filename;
	}
}
