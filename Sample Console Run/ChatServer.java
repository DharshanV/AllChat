import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private ServerSocket server;
    private int portNumber;
    private Set<String> userNames;
    private Set<UserThread> userThreads;

    public ChatServer(int port){
        portNumber = port;
        userNames = new HashSet<>();
        userThreads = new HashSet<>();
    }

    public void run(){
        try{
            server = new ServerSocket(portNumber);
            System.out.println("Server listing on port "+portNumber+"...");

            /*
                1. Loop tell we receive a connection from client.
                2. A new client joined, get client socket for communication.
                3. Add the new client to list of server active users, and start user thread.
             */
            while (true){
                Socket clientSocket = server.accept();
                System.out.println("New client connected");

                UserThread user = new UserThread(clientSocket,this);
                userThreads.add(user);
                user.start();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void removeUser(String userName,UserThread userThread){
        if(userNames.remove(userName)){
            userThreads.remove(userThread);
            System.out.println("The user " + userName + " left.");
        }
    }

    public void addUser(String userName){
        userNames.add(userName);
    }

    public Set<String> getUserNames(){
        return userNames;
    }

    //Relay the message from user to other users
    public void relayMessage(String message,UserThread messageUser){
        for (UserThread user : userThreads) {
            if(user != messageUser){
                user.sendMessage(message);
            }
        }
    }

    public boolean hasUsers(){
        return !userNames.isEmpty();
    }

    public static void main(String[] args) {
        int portNumber = 1234;
        ChatServer server = new ChatServer(portNumber);
        server.run();
    }
}
