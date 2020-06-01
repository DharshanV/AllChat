import java.io.*;
import java.net.Socket;
import java.util.Set;

/*
    This class handles, when a new user joins.
    Runs each user on its own thread to let
    server handle multiple clients.
 */
public class UserThread extends Thread {
    private Socket userSocket;
    private ChatServer chatServer;
    private PrintWriter socketWrite;

    UserThread(Socket userSocket, ChatServer chatServer){
        this.userSocket = userSocket;
        this.chatServer = chatServer;
    }

    public void run(){
        try{
            //Get the socket streams, to write or read from/to client.
            BufferedReader socketRead = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            socketWrite = new PrintWriter(userSocket.getOutputStream(),true);

            printUsers();

            String userName = socketRead.readLine();       //First read is always name because user enters their name first
            chatServer.addUser(userName);                   //and name is first sent to read by the server.

            String messageToServer = userName + " connected!";
            chatServer.relayMessage(messageToServer,this);

            String clientMessage;
            do{
                clientMessage = socketRead.readLine();
                messageToServer = "["+userName+"]: "+clientMessage;
                chatServer.relayMessage(messageToServer,this);
            }while (!clientMessage.equals("quit"));

            chatServer.removeUser(userName,this);
            userSocket.close();

            messageToServer = userName + " disconnected";
            chatServer.relayMessage(messageToServer,this);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void sendMessage(String message){
        socketWrite.println(message);
    }

    private void printUsers(){
        if(chatServer.hasUsers()){
            socketWrite.println("Users connected: "+chatServer.getUserNames());
        }
        else{
            socketWrite.println("No users connected");
        }
    }
}
