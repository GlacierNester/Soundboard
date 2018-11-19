package com.glacier.soundboard.handlers;

import java.io.File;
import java.util.Properties;

import com.glacier.soundboard.util.UtilityMethods;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ShowMakeSounds implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		// TODO For each name/file pair in the properties file, show a button that plays a sound
		// potentially, if we can show images in buttons instead, make it so, but we gotta get soundmaking first 
		Stage primaryStage = new Stage();
		HBox wrapThings = new HBox();
		VBox buttons = new VBox();
		Properties props = UtilityMethods.getProperties();
		String[] keys = UtilityMethods.getKeysList();
		for(String key : keys)
		{
			if(!key.toLowerCase().contains(".photo"))
			{
				System.out.println("Creating button for file " + props.getProperty(key));
				Media media = new Media(new File(props.getProperty(key)).toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(media);
				Button btnItem = new Button(key);
				btnItem.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						mediaPlayer.seek(mediaPlayer.getStartTime());
						mediaPlayer.play();
						System.out.println("Playing item " +mediaPlayer.getMedia().getSource()+" at " + UtilityMethods.getCurrentTimestamp());
					}
				});
				if(props.containsKey(key.substring(0,key.lastIndexOf("."))+".photo"))
				{
					Image img = new Image(new File(props.getProperty(key.substring(0,key.lastIndexOf("."))+".photo")).toURI().toString());
					BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			        Background background = new Background(backgroundImage);
			        btnItem.setBackground(background);
			        btnItem.setText(" ");
			        btnItem.setMaxSize(img.getWidth(),img.getHeight());
			        btnItem.setPrefSize(img.getWidth(),img.getHeight());
			        btnItem.setMinSize(img.getWidth(),img.getHeight());
				}
				buttons.getChildren().add(btnItem);
				System.out.println("Button for file " + props.getProperty(key) + " created at " + UtilityMethods.getCurrentTimestamp());
			}
		}
//		buttons.setFillWidth(true);
//		wrapThings.setFillHeight(true);
		wrapThings.getChildren().add(buttons);
		//TODO: size this to the overall size of the buttons
		Scene primaryScene = new Scene(wrapThings,200,200);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}

}
