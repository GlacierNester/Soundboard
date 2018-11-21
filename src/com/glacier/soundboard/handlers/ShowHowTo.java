package com.glacier.soundboard.handlers;

import com.glacier.soundboard.util.Constants;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowHowTo implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		Stage primaryStage = new Stage();
		VBox wrapThings = new VBox();
		wrapThings.getChildren().add(new Text(Constants.howTo));
		Scene primaryScene = new Scene(wrapThings,Constants.howToWidth,Constants.howToHeight);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
		
	}

}
