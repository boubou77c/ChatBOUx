package boubou.chatbox.chatboxui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void chatWindow() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("chatBox.fxml"));
            Parent root =fxmlLoader.load();
            Stage stage = new Stage();

            stage.minWidthProperty().setValue(564);
            stage.minHeightProperty().setValue(568);
            stage.maxWidthProperty().setValue(871);
            stage.maxHeightProperty().setValue(605);
            stage.setTitle("ChatBox");

            chatWindowController controller = fxmlLoader.getController();
            controller.initialize(stage);

            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
