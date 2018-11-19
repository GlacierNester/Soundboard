package com.glacier.soundboard.handlers;

import java.io.File;

import com.glacier.soundboard.util.UtilityMethods;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ShowPickPhoto implements EventHandler<ActionEvent> {

	private Stage closeMe;
	
	public ShowPickPhoto(Stage closeMe)
	{
		this.closeMe = closeMe;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		closeMe.close();
		Stage primaryStage = new Stage();
		VBox wrapThings = new VBox();
		HBox buttons = new HBox();
		Text question = new Text("Do you have photos to set for the sound?");
		Button btYes = new Button("Yes");
		Button btNo = new Button("No");
		btNo.setOnAction(new ShowMakeSounds());
		btYes.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Stage tempStage = new Stage();
				FileChooser chooser = new FileChooser();
				chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
				chooser.setTitle("Pick a sound");
				File file = chooser.showOpenDialog(tempStage);
				if (file != null) {
					UtilityMethods.writeFileDataToProperties(file);
				}
				tempStage.close();
			}
		});
		buttons.getChildren().addAll(btYes,btNo);
		wrapThings.getChildren().addAll(question,buttons);
		Scene primaryScene = new Scene(wrapThings,244,96);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}

}
