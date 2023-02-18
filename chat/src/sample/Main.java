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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane root = new BorderPane();

        Label intro = new Label("chat app");
        TextArea chatBox = new TextArea();

        chatBox.setMaxHeight(300);
        chatBox.setMaxWidth(380);

        TextField message = new TextField();
        message.setPrefWidth(330);

        message.setPromptText("write ur message here");
        Button send = new Button("send");
        HBox hBox = new HBox();
        Button attach = new Button("attach file");
     hBox.setSpacing(15);
        hBox.getChildren().addAll(message,attach,send);
        Button p1 = new Button("Connect to server");

        VBox menu = new VBox();
        menu.setSpacing(50);
        menu.getChildren().addAll(p1);
        menu.setAlignment(Pos.CENTER);
        menu.setPrefWidth(130);
        root.setCenter(chatBox);
        root.setBottom(hBox);
        root.setRight(menu);

        p1.setOnAction( e-> {
            try {
                createT(chatBox,message,send,attach);

            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
       Scene scene1 = new Scene(root, 500, 350);
        primaryStage.setTitle("client window");

        primaryStage.setScene(scene1);
        primaryStage.show();

    }
    public  void createT(TextArea ta, TextField mes, Button send, Button attach) throws InterruptedException {

        Clients  client = new Clients(ta,mes ,send,attach);

        startT(client,ta,mes,send,attach);

    }
    public void startT(Clients client, TextArea ta, TextField mes, Button send, Button attach ){

        client.start();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
