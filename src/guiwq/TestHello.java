package guiwq;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//--module-path "P:\\playground\\javaplay\\bookmanage\\bookmanage\\openjfx-20.0.1_windows-x64_bin-sdk\javafx-sdk-20.0.1\lib" --add-modules javafx.controls,javafx.fxml
//演示简单的JavaFX程序
//JavaFX程序的入口类继承自Application
public class TestHello extends Application {

    public static void main(String[] args) {
        launch(args); // 启动JavaFX应用，接下来会跳到start方法
    }

    @Override
    public void start(Stage stage) { // 应用程序开始运行
        stage.setTitle("Hello World"); // 设置舞台的标题
        Group group = new Group(); // 创建一个小组
        Scene scene = new Scene(group, 400, 50, Color.WHITE); // 创建一个场景
        stage.setScene(scene); // 设置舞台的场景
        stage.setResizable(true); // 设置舞台的尺寸是否允许变化
        stage.centerOnScreen();
        stage.toFront();

        GridPane gridPane = new GridPane();
        gridPane.add(new Button("user"), 0, 0);
        gridPane.add(new Button("admin"), 0, 1);
        gridPane.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(gridPane);
        stage.setScene(scene1);
        stage.setMinHeight(300);
        stage.setMinWidth(300);
        stage.show(); // 显示舞台。相当于JFrame的setVisible(true)
    }

}