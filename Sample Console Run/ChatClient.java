import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    private Socket serverSocket;
    private String userName;
    private String hostName;
    private int portNumber;

    ChatClient(String hostName,int portNumber){
        this.portNumber = portNumber;
        this.hostName = hostName;
    }

    public void run(){
        try{
            serverSocket = new Socket(hostName,portNumber);
            System.out.println("Connected to server");

            new ClientReadThread(serverSocket,this).start();
            new ClientWriteThread(serverSocket,this).start();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public static void main(String[] args) {
        int portNumber = 1234;
        String hostName = "128.54.109.52";
        ChatClient client = new ChatClient(hostName,portNumber);
        client.run();
    }
}
