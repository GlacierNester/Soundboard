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
	}
	public static void showErrorStage(ErrorTypes error)
	{
		showErrorStage(error.toString());
	}
	private static void showNoPhoto()
	{
		Stage primaryStage = new Stage();
		Text text = new Text("There aren't any audios to set a picture for!\nClose me to keep going!");
		HBox holdThings = new HBox();
		holdThings.getChildren().add(text);
		primaryStage.setScene(new Scene(holdThings,100,100));
		primaryStage.show();
	}
}
