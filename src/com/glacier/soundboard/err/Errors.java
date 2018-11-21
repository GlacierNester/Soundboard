package com.glacier.soundboard.err;

import com.glacier.soundboard.handlers.AddSoundsHandler;
import com.glacier.soundboard.util.Constants;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		else if(errorType.equals(ErrorTypes.NO_AUDIOS_AVAILABLE.toString()))
		{
			showNoAudios();
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
		primaryStage.setScene(new Scene(holdThings,Constants.errorWidth,Constants.errorHeight));
		primaryStage.setTitle("Already Set Error");
		primaryStage.show();
	}
	
	private static void showNoPhoto()
	{
		Stage primaryStage = new Stage();
		Text text = new Text("There aren't any audios to set a picture for!\nClose me to keep going!");
		HBox holdThings = new HBox();
		holdThings.getChildren().add(text);
		primaryStage.setScene(new Scene(holdThings,Constants.errorWidth,Constants.errorHeight));
		primaryStage.setTitle("Nothing to Set Error");
		primaryStage.show();
	}
	
	private static void showNoAudios() {
		Stage primaryStage = new Stage();
		Text text = new Text("Wait, there aren't any audios! Do you want to set some up?");
		Button btYes = new Button("Yeah, sure");
		Button btNo = new Button("Uh, no?");
		HBox buttons = new HBox();
		VBox wrapThings = new VBox();
		btYes.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				new AddSoundsHandler().handle(event);
			}
		});
		btNo.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				primaryStage.close();
			}
		});
		wrapThings.getChildren().addAll(text,buttons);
		buttons.getChildren().addAll(btYes,btNo);
		primaryStage.setScene(new Scene(wrapThings,Constants.errorWidth,Constants.errorHeight));
		primaryStage.setTitle("Nothing Setup Error");
		primaryStage.show();
	}
}
