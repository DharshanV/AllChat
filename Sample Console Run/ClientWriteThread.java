import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriteThread extends Thread{
    private Socket serverSocket;
    private ChatClient client;
    private PrintWriter socketWrite;
    private Scanner in;
    
    ClientWriteThread(Socket serverSocket,ChatClient client){
        this.serverSocket = serverSocket;
        this.client = client;
        in = new Scanner(System.in);
        try{
            socketWrite = new PrintWriter(serverSocket.getOutputStream(),true);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void run(){
        System.out.print("\nEnter your name: ");
        String userName = in.nextLine();
        System.out.println("Hello "+userName+"!");
        client.setUserName(userName);

        String clientMessage;

        do{
            if(serverSocket.isClosed())return;
            System.out.print("["+userName+"]: ");
            clientMessage = in.nextLine();
            socketWrite.println(clientMessage);
        }while (!clientMessage.equals("quit"));

        try{
            System.out.println("User quit, trying to close server");
            serverSocket.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
