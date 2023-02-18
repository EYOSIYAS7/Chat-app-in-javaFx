package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Clients  extends Thread{
    int i = 0;
    TextArea messagearea;
    TextField message ;
    Button send;
    Button attach;
     Clients(TextArea ta, TextField mes, Button attach, Button sen){

         messagearea=ta;
        message =mes;
        this.send =sen;
        this.attach = attach;
     }
        public void client(TextArea textArea, TextField mes, Button attach, Button send) throws IOException {
            Socket socket = new Socket("127.25.25.1", 2343);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            System.out.println(" connected to: "+socket.getRemoteSocketAddress());
            System.out.println("server socket"+socket.getLocalSocketAddress());
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            while (true) {
                try {

                    System.out.println(socket);

                    send.setOnAction(e->{
                        try {
                            String str = mes.getText();
                            dos.writeUTF(str);
                            textArea.appendText("you: "+str+"\n");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        mes.setText("");
                    });

                        String txt = dis.readUTF();
                        textArea.appendText("server: "+txt+"\n");
//                        System.out.println("text is received");
//
//                        if(txt.equalsIgnoreCase("okay")){
//                            dos.writeUTF("ack");
//                            try {
//                                System.out.println("kycyk");
//                                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
//
//                                BufferedImage bi = ImageIO.read(bis);
//
//                                System.out.println("image successfully received " + bi);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }


                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
     public void run() {

//         System.out.println( Thread.currentThread().getName()+ "is running");
         try {
             client(messagearea,message,send,attach);
         } catch (IOException ioException) {
             ioException.printStackTrace();
         }

     }

}
