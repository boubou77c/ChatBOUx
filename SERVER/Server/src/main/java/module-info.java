module boubou.chatbox.server.server {
    requires javafx.controls;
    requires javafx.fxml;


    opens boubou.chatbox.server.server to javafx.fxml;
    exports boubou.chatbox.server.server;
}