package boubou.chatbox.chatboxui;
import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "192.168.0.32";
    private static final int SERVER_PORT = 5555;

    public void runClient(String name,String content){
        try{
            Socket socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            out.println(name+ " : " +content);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
