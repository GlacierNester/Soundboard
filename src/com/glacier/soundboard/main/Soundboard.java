package com.glacier.soundboard.main;

import com.glacier.soundboard.handlers.AddThingsHandler;
import com.glacier.soundboard.handlers.ShowHowTo;
import com.glacier.soundboard.handlers.ShowMakeSounds;
import com.glacier.soundboard.util.Constants;
import com.glacier.soundboard.util.UtilityMethods;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Soundboard extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox wrapThings = new VBox();
		HBox buttons = new HBox();
		Text question = new Text("What would you like to do?");
		Button btYes = new Button("Add Things");
		Button btNo = new Button("SoundBoard");
		Button btHow = new Button("Help me!");
		btYes.setOnAction(new AddThingsHandler());
		btNo.setOnAction(new ShowMakeSounds());
		btHow.setOnAction(new ShowHowTo());
		buttons.getChildren().addAll(btYes,btNo,btHow);
		wrapThings.getChildren().addAll(question,buttons);
		Scene primaryScene = new Scene(wrapThings,Constants.openingSizeOne,Constants.openingSizeTwo);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
        UtilityMethods.setupLogs();
		launch(args);
    }

}
