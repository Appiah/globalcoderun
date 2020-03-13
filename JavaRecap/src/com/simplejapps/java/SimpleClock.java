package com.simplejapps.java;

import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimpleClock extends Application {
    
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        
        Label label = new Label();
        label.setFont(Font.font(null, 18.0));
        root.getChildren().add(label);
        
        Scene scene = new Scene(root, 300, 50);
        
        stage.setTitle("Simple Clock");
        stage.setScene(scene);
        stage.show();
        
        startTimer(label);
    }
    
    private void startTimer(final Label label) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(1_000), 
                         new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                    label.setText(new Date().toString());
                                }
                            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String... args) {
        launch(args);
    }    
}
