package guiwq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class logicSlove implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        adminButton.setOnMouseClicked(e -> clickAdminButton(e));
userButton.setOnMouseClicked(e->clickUserButton(e));
    }

    @FXML
    private Button adminButton;
    @FXML
    private Button userButton;
    @FXML
    private Label mode;

    @FXML
    void clickUserButton(MouseEvent actionEvent) {
       System.out.println("user");
    }

    @FXML
    void clickAdminButton(MouseEvent actionEvent) {
        System.out.println("admin");
    }
}