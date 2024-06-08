package boubou.chatbox.server.server;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Controller {

    @FXML
    private TextField portField;

    //List to add the new client connected
    private static final List<PrintWriter> writers = new CopyOnWriteArrayList<>();

    @FXML
    private Text status;

    @FXML
    private Button startServ;
    private int PORT;

    private ServerSocket serverSocket;

    @FXML
    public void startServer() throws IOException {
        if(serverSocket !=null && !serverSocket.isClosed()){
            serverSocket.close();

            System.out.println("Server stopped");

        }else{
            try{
                //Start the server
                System.out.println("Server started");
                //Thread to start the server
                new Thread(()-> setServer()).start();

            }catch(Exception e){
                System.out.println("Error starting server");
            }

        }

    }

    public void setServer(){
        try{
            //Get the port in INT
            String getPORT = portField.getText();
            int PORT = Integer.parseInt(getPORT);
            this.PORT = PORT;
            //Create a socket
            serverSocket = new ServerSocket(PORT);

            Platform.runLater(()->{
                startServ.setText("Shut Server");
                status.setText("Status : Online");


            });


            System.out.println("The chat server is listening on port " + PORT);

            //Infinite loop to receipt the clientSocket
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                new Thread(()-> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port " + PORT);
        }finally {
            try{
                serverSocket.close();

                Platform.runLater(()->{
                    status.setText("Status : Offline");
                    startServ.setText("Start Server");
                });

            }catch(IOException e){
                System.out.println("Could not close server socket");
            }
        }

    }

    public static void handleClient(Socket clientSocket){

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            //add a client
            writers.add(out);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Message received: " + message);

                // Broadcast the message to all clients
                for (PrintWriter writer : writers) {
                    writer.println(message);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                clientSocket.close();
                writers.clear();
            }catch (IOException e){
                e.printStackTrace();

            }
        }


    }
}