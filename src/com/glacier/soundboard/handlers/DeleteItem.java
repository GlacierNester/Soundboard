package com.glacier.soundboard.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import com.glacier.soundboard.err.ErrorTypes;
import com.glacier.soundboard.err.Errors;
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

public class DeleteItem implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		Stage primaryStage = new Stage();
		HBox wrapthings = new HBox();
		VBox radioButtons = new VBox();
		Button btChoose = new Button("Delete This One");
		ToggleGroup toggle = new ToggleGroup();
		for(String x : UtilityMethods.getKeysList())
		{
			if(!x.equalsIgnoreCase("issoundboard"))
			{

				RadioButton temp = new RadioButton(x);
				temp.setToggleGroup(toggle);
				radioButtons.getChildren().add(temp);
			}
		}
		btChoose.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event)
			{
				if(toggle.getToggles().isEmpty())
				{
					primaryStage.close();
					return;
				}
				RadioButton rad = (RadioButton) toggle.getSelectedToggle();
				try {
					String filePath = UtilityMethods.getProperties().getProperty(rad.getText());
					Scanner fin = new Scanner(new FileInputStream(new File(Constants.propertiesPath)));
					File temp = File.createTempFile("rad", ".properties");
					PrintStream print = new PrintStream(temp);
					while(fin.hasNextLine())
					{
						String line = fin.nextLine();
						if(UtilityMethods.isAudio(rad.getText()))
						{
							if(!line.contains(rad.getText().substring(0,rad.getText().lastIndexOf("."))))
							{
								print.println(line);
							}
						}
						else
						{
							if(!line.contains(filePath))
							{
								print.println(line);
							}
						}
					}
					//short version? If they're deleting a photo,
					//only remove the photo line from the properties file
					//by not writing that line to the file
					//otherwise, remove that line AND the photo it's referencing
					fin.close();
					print.flush();
					print.close();
					System.gc();
					Files.copy(temp.toPath(), new File(Constants.propertiesPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					System.err.println("Exception in deleting lines from file at " + UtilityMethods.getCurrentTimestamp());
					e.printStackTrace();
				}
				primaryStage.close();
			}
		});
		if(radioButtons.getChildren().isEmpty())
		{
			primaryStage.close();
			Errors.showErrorStage(ErrorTypes.NO_SOUNDBOARDS_AVAILABLE);
		}
		wrapthings.getChildren().add(radioButtons);
		wrapthings.getChildren().add(btChoose);
		primaryStage.setScene(new Scene(wrapthings,radioButtons.getPrefWidth()+btChoose.getPrefWidth(),radioButtons.getPrefHeight()+btChoose.getPrefHeight()));
		primaryStage.setTitle("Delete a soundboard");
		primaryStage.showAndWait();
	}

}
