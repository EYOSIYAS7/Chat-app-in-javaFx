package sample;

import javafx.application.Application;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{




        BorderPane root = new BorderPane();

        Label intro = new Label("Welcome!!");
        intro.getStyleClass().add("intro");
        Label item = new Label(" click start to start messaging");
        item.getStyleClass().add("item");
        Button start = new Button("start");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(7);
        TextArea chatBox = new TextArea();
        chatBox.setMaxHeight(560);
        chatBox.setMaxWidth(500);
        TextField message = new TextField();
        message.setPrefWidth(330);

        message.setPromptText("write ur message here");
        Button send = new Button("send");
        HBox hBox = new HBox();
        Button attach = new Button("attach file");
        hBox.setSpacing(15);
        hBox.getChildren().addAll(message,attach,send);
        Button p1 = new Button("person 1");

        TextArea menu = new TextArea();


        menu.setPrefWidth(150);
        root.setCenter(chatBox);
        root.setBottom(hBox);
        root.setRight(menu);

        Scene scene1 = new Scene(root, 600, 400);

        BorderPane Start = new BorderPane();

        vBox.getChildren().addAll(intro,item,start);
        Start.setCenter(vBox);
        Scene mainScene = new Scene(Start,300,300);
        mainScene.getStylesheets().addAll("mystyle.css");
        start.setOnAction( e->{
            startServer(chatBox,message,send,menu, attach,primaryStage);
            primaryStage.setScene(scene1);
        });
        primaryStage.setTitle("Server Window");
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    public void startServer(TextArea ta, TextField tf, Button sen, TextArea menu, Button attach, Stage primaryStage){
        server sr1 = new server(ta,tf,sen,attach,menu,primaryStage );
        sr1.start();
    }
    public static void main(String[] args) {
        launch(args);

    }
}
