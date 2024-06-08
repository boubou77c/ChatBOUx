package boubou.chatbox.chatboxui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

import java.net.Socket;

public class chatWindowController{

    Stage stage;

    Socket socket;

    private PrintWriter out;

    private BufferedReader in;
    @FXML
    private Text connectName;

    @FXML
    private TextField field;

    @FXML
    private TextArea textArea;

    @FXML private Text iPort;

    @FXML private Text iAdress;

    private String pseudo;

    String SERVER_ADDRESS;
    int SERVER_PORT;

    public void initialize(Stage stage) {
        this.stage = stage;
        try {
            SERVER_PORT = getPA.setPort();
            SERVER_ADDRESS = getPA.setServerAddress();

            iAdress.setText("Adress-Server :" + SERVER_ADDRESS);
            iPort.setText("PORT :" + SERVER_PORT);
            //Create a new socket
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            pseudo = getPseudo.getPseudonyme();
            connectName.setText("Connected : "+pseudo);

            // Start a new thread to read messages from the server
            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        final String msg = message;
                        Platform.runLater(() -> textArea.appendText(msg + "\n"));
                    }
                } catch (IOException e) {
                    System.out.println("Socket has been closed. No server online.");
                }
            }).start();

        } catch (IOException e) {
            messageError();
        }
    }

    //Message BOX ERROR
    protected void messageError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error has occured. It may be :\n -Server is not online.\n-Spelling mistake PORT / SERVER-ADRESS");
        alert.showAndWait();
    }

    @FXML
    public void sendMSG(){
        String message = field.getText();
        //Send the message from the server
        out.println(pseudo+" : "+message);
        field.clear();

    }




}

