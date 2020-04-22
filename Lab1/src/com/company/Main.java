package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 150);

        Rectangle r = new Rectangle(20, 10, 260, 120); //створення фігури
        root.getChildren().add(r); //додавання фігури до кореневого контейнера
        r.setFill(Color.MAROON);;
        scene.setFill(Color.OLIVE);
        Line line1 = new Line(150, 10, 150, 50); //
        line1.setStrokeWidth(4);
        root.getChildren().add(line1);
        line1.setStroke(Color.YELLOW);
        Line line2 = new Line(20, 50, 280, 50); //
        line2.setStrokeWidth(4);
        root.getChildren().add(line2);
        line2.setStroke(Color.YELLOW);
        Line line3 = new Line(20, 90, 280, 90); //
        line3.setStrokeWidth(4);
        root.getChildren().add(line3);
        line3.setStroke(Color.YELLOW);
        Line line4 = new Line(150, 90, 150, 130); //
        line4.setStrokeWidth(4);
        root.getChildren().add(line4);
        line4.setStroke(Color.YELLOW);
        Line line5 = new Line(80, 50, 80, 90); //
        line5.setStrokeWidth(4);
        root.getChildren().add(line5);
        line5.setStroke(Color.YELLOW);
        Line line6 = new Line(220, 50, 220, 90); //
        line6.setStrokeWidth(4);
        root.getChildren().add(line6);
        line6.setStroke(Color.YELLOW);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
