package example;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    Button button;
    Label Field;
    GridPane layout;
    Scene scene;
    Stage primaryStage;
    @Override
    public void start(Stage arg0) throws IOException {
        primaryStage=arg0;
        primaryStage.setTitle("JAVAFX EXP14");

        layout=new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(10);
        layout.setHgap(10);
        button =new Button("Click me!!");
        button.setOnAction(e->{
            Field.setText("Welcome");
        GridPane.setConstraints(button, 5, 6);
        GridPane.setConstraints(Field, 5, 8);
        layout.getChildren().addAll(button, Field);
        scene=new Scene(layout, 250 ,300);
        /* setting scene so that the layout that we created will be visible to us!! and also give
        dimensions like 250 and 300 to set layout size of the scene (window) */

        primaryStage.setScene(scene);// setting the scene to the window
        primaryStage.show();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}