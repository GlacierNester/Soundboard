package com.glacier.soundboard.err;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Errors {
	public static void showErrorStage(String errorType)
	{
		if(errorType.equals(ErrorTypes.NO_AUDIO_TO_SET.toString()))
		{
			showNoPhoto();
		}
		else if(errorType.equals(ErrorTypes.ALREADY_EXISTS.toString()))
		{
			showAlreadySet();
		}
	}
	public static void showErrorStage(ErrorTypes error)
	{
		showErrorStage(error.toString());
	}
	private static void showAlreadySet() {
		Stage primaryStage = new Stage();
		Text text = new Text("Oh dear, you've already set that up!\nClose me to keep going!");
		HBox holdThings = new HBox();
		holdThings.getChildren().add(text);
		primaryStage.setScene(new Scene(holdThings,255,100));
		primaryStage.show();
	}
	
	private static void showNoPhoto()
	{
		Stage primaryStage = new Stage();
		Text text = new Text("There aren't any audios to set a picture for!\nClose me to keep going!");
		HBox holdThings = new HBox();
		holdThings.getChildren().add(text);
		primaryStage.setScene(new Scene(holdThings,292,100));
		primaryStage.show();
	}
}
