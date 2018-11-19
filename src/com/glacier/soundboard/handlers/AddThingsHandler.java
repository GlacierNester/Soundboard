package com.glacier.soundboard.handlers;

import java.io.File;

import com.glacier.soundboard.util.UtilityMethods;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddThingsHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		Stage primaryStage = new Stage();
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		chooser.setTitle("Pick a sound");
		File file = chooser.showOpenDialog(primaryStage);
		if (file != null) {
			UtilityMethods.writeFileDataToProperties(file);
		}

	}

}
