import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReadThread extends Thread{
    private Socket serverSocket;
    private ChatClient client;
    private BufferedReader sockerReader;

    ClientReadThread(Socket serverSocket,ChatClient client){
        this.serverSocket = serverSocket;
        this.client = client;
        try{
            sockerReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void run(){
        while (true){
            try {
                if(serverSocket.isClosed())return;
                String serverResponse = sockerReader.readLine();
                System.out.println("\n"+serverResponse);

                if(client.getUserName() != null){
                    System.out.println("["+client.getUserName()+"]: ");
                }
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
