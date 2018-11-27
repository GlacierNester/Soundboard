package com.glacier.soundboard.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.glacier.soundboard.util.Constants;
import com.glacier.soundboard.util.UtilityMethods;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ChooseABoard implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		Stage primaryStage = new Stage();
		HBox wrapthings = new HBox();
		VBox radioButtons = new VBox();
		Button btChoose = new Button("Choose This One");
		ToggleGroup toggle = new ToggleGroup();
		ArrayList<String> choice = new ArrayList<String>();
		choice.add("SoundbarProperties.properties");
		for(File x : new File(Constants.propertiesPath.substring(0,Constants.propertiesPath.lastIndexOf("/"))).listFiles())
		{
			if(x.getName().contains(".properties"))
			{
				Properties tempProps = new Properties();
				try {
					tempProps.load(new FileInputStream(x));
					if(tempProps.containsKey("issoundboard"))
					{
						RadioButton temp = new RadioButton(x.getName());
						temp.setToggleGroup(toggle);
						radioButtons.getChildren().add(temp);
					}
				} catch (IOException e) 
				{
					System.err.println("Error in picking a soundboard at " + UtilityMethods.getCurrentTimestamp());
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
		}
		wrapthings.getChildren().add(radioButtons);
		wrapthings.getChildren().add(btChoose);
		primaryStage.setScene(new Scene(wrapthings,radioButtons.getPrefWidth()+btChoose.getPrefWidth(),radioButtons.getPrefHeight()+btChoose.getPrefHeight()));
		primaryStage.setTitle("Choose a soundboard");
		primaryStage.showAndWait();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{

			@Override
			public void handle(WindowEvent arg0) {
				// I just need it to call showMakeSounds when we close the window
				//I know these inline declarations are gross 
				new ShowMakeSounds().handle(new ActionEvent());
			}
		});
		Constants.setSoundboard(choice.get(choice.size()));
	}

}
