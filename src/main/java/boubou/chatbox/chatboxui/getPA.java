package boubou.chatbox.chatboxui;

public class getPA {
    public static String PORT;

    public static String SERVER_ADRESS;

    public static void getPort(String PORT){
        getPA.PORT = PORT;
    }

    public static int setPort(){
        int PORTint = Integer.parseInt(PORT);
        return PORTint;
    }

    public static void getServerAddress(String SERVER_ADRESS){
        getPA.SERVER_ADRESS = SERVER_ADRESS;
    }

    public static String setServerAddress(){
        return SERVER_ADRESS;
    }

}
