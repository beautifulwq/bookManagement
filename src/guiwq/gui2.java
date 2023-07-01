package guiwq;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class gui2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gui2.fxml"));
        VBox vBox = new VBox(root);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickUserButton(ActionEvent actionEvent) {

        System.out.println("user");
    }

    @FXML
    void clickAdminButton(ActionEvent actionEvent) {
        System.out.println("user");
    }
}
