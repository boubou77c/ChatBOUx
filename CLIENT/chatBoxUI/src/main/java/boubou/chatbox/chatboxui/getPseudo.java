package boubou.chatbox.chatboxui;

public class getPseudo {
    private static String pseudo;

    public static void setPseudo(String pseudo){
        getPseudo.pseudo = pseudo;
    }

    public static String getPseudonyme(){
        return pseudo;
    }
}
