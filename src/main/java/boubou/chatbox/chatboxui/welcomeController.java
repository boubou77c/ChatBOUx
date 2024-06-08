package boubou.chatbox.chatboxui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class welcomeController {

    @FXML private TextField askPseudo;

    @FXML private TextField askPort;

    @FXML private TextField askAdress;

    @FXML
    protected void enterOntheChatBox() {
        while(true){
            String pseudo = askPseudo.getText();
            if(pseudo.isEmpty()){
                System.out.println("Please enter a pseudonyme");
                break;
            }
            else{
                getPseudo.setPseudo(pseudo);

                String port = askPort.getText();
                getPA.getPort(port);

                String adress = askAdress.getText();
                getPA.getServerAddress(adress);
                Application.chatWindow();
                break;
            }
        }

    }
}