

package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class server extends Thread{
     int i = 0;
    public static ArrayList<String> list = new ArrayList<>();
    TextField message ;
    Button send;
    TextArea textArea;
    Button attach;
    TextArea menu;
    Socket socket ;
    Stage stage;
    public server(TextArea ta, TextField tf, Button sen, Button attach, TextArea menu, Stage primaryStage) {
        textArea=ta;
        message =tf;
        send =sen;
        this.attach=attach;
        this.menu = menu;
        stage = primaryStage;
    }


    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2343);
            System.out.println("server socket"+serverSocket);
            System.out.println("server socket"+serverSocket.getReceiveBufferSize());

        while (true) {
            try {


                socket = serverSocket.accept();
                System.out.println("just connectd to " +socket.getRemoteSocketAddress());
               System.out.println(socket);

                i++;
                String name = "client "+i;
                list.add(name);
                menu.setText("");
                for (String item:list) {
                    menu.appendText(item+"\n");
                }
                processes processes = new processes(socket ,i,textArea,message,send,menu,attach,stage);
                processes.start();


            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
