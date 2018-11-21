package com.glacier.soundboard.handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import com.glacier.soundboard.err.ErrorTypes;
import com.glacier.soundboard.err.Errors;
import com.glacier.soundboard.util.UtilityMethods;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
		if(!(UtilityMethods.getKeysList().length == 0))
		{
		Stage primaryStage = new Stage();
		HBox wrapThings = new HBox();
		VBox buttons = new VBox();
		Properties props = UtilityMethods.getProperties();
		String[] keys = UtilityMethods.getKeysList();
		ArrayList<Button> buttonList = new ArrayList<Button>();
		ArrayList<Double> heights = new ArrayList<Double>();
		ArrayList<Double> widths = new ArrayList<Double>();
		int rowCounter = 0;
		HBox row = new HBox();
		buttons.getChildren().add(row);
		//we have to add the initial row first
		heights.add(-1.0);
		widths.add(0.0);
		//we add an initial value for the height and width of the first row
		for(String key : keys)
		{
			if(!key.toLowerCase().contains(".photo"))
			{
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
				if(UtilityMethods.hasPhoto(key))
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
				buttonList.add(btnItem);
				if(buttonList.size()%5 == 1 && buttonList.size()>5)
				{
					row = new HBox();
					buttons.getChildren().add(row);
					rowCounter++;
				}
				row.getChildren().add(btnItem);
				if(heights.get(rowCounter) < btnItem.getPrefHeight())
				{
					heights.set(rowCounter, btnItem.getPrefHeight());
				}
				widths.set(rowCounter, widths.get(rowCounter)+btnItem.getPrefWidth());
				System.out.println("Button for file " + props.getProperty(key) + " created at " + UtilityMethods.getCurrentTimestamp());
			}
		}
		wrapThings.getChildren().add(buttons);
		Scene primaryScene = new Scene(wrapThings,UtilityMethods.getLargestWidth(widths),UtilityMethods.getLargestHeight(heights));
		primaryStage.setScene(primaryScene);
		primaryStage.show();
		}
		else
		{
			Errors.showErrorStage(ErrorTypes.NO_AUDIOS_AVAILABLE);
		}
	}

}
