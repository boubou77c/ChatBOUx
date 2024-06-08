module boubou.chatbox.chatboxui {
    requires javafx.controls;
    requires javafx.fxml;


    opens boubou.chatbox.chatboxui to javafx.fxml;
    exports boubou.chatbox.chatboxui;
}