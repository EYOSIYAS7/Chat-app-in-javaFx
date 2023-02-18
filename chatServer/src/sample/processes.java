package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;


import java.io.*;
import java.net.Socket;


public class processes extends Thread {
    Socket socket;
    TextField message ;
    Button send;
    TextArea textArea;
    Integer counter;
    TextArea menu;
    Button attach;
    Stage stage;
    DataInputStream dis;
    DataOutputStream dos;

    public processes(Socket sokt, int i, TextArea ta, TextField tf, Button sen, TextArea menu, Button attach, Stage stage) {
        socket = sokt;
        textArea=ta;
        message =tf;
        send =sen;
        counter = i ;
        this.menu=menu;
        this.attach = attach;
        this.stage = stage;

    }

    @Override
    public void run() {

      String txt = null;
        String name = "client"+ counter;
        System.out.println("connection made with "+ name);
        InputStream is = null;
        OutputStream os = null;

        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        dis  = new DataInputStream(is);
        dos = new DataOutputStream(os);

        while (true){
            try {

                txt =  dis.readUTF();

                if(txt.equalsIgnoreCase("bye")){
                    System.out.println("connection ended with" + name);

                    break;
                }


            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Text tx = new Text(name+": "+ txt+"\n");
            tx.setFill(Color.AQUA);

            textArea.appendText(tx.getText());


            send.setOnAction(e->{

                String str = message.getText();
                try {
                    dos.writeUTF(str);

                    textArea.appendText("Server: " + str+ "\n");

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                message.setText("");
            });

            attach.setOnAction( e-> {
                String str = message.getText();
                try {
                    dos.writeUTF(str);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                try {


                BufferedOutputStream dos = new BufferedOutputStream(socket.getOutputStream());
                    File image = new File("C:\\Users\\HP\\Pictures\\873ed2200aa43400e4382a09787232e2.jpg");

                    BufferedImage bi = ImageIO.read(image);
                    ImageIO.write(bi, "jpg", dos);

                    System.out.println("image successfully send " + bi.getGraphics());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            });
        }

    }
}
