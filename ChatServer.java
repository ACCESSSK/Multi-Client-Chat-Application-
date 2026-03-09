//=============================================================
//Client Side
//=============================================================

import java.io.*;
import java.util.*;
import java.net.*;



public class ChatServer
{
    private static Map<String, ClientHandler> clients = new HashMap<>();

    public static void main(String[] args) 
    {
        int port = 5000;
        
        try (ServerSocket serverSocket  = new ServerSocket(port))
        {
            System.out.println("Chat Server Started on Port " + port);

            while (true) 
            {
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket);
                
                new Thread(client).start();
            }

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static synchronized void broadcast(String message)
    {
        for(ClientHandler client : clients.values())
            {
                client.sendMessage(message);
            }

            logMessage(message);
    }

    public static synchronized void addClient(String username,ClientHandler client)
    {
        clients.put(username,client);
        broadcast("SERVER: " + username + " joined the chat");
    }

    public static synchronized void removeClient(String username)
    {
        clients.remove(username);
        broadcast("SERVER: " + username + " left the chat");
    }

    public static synchronized void privateMessage(String user, String message)
    {
        ClientHandler client = clients.get(user);

        if (client != null) 
        {
            client.sendMessage(message);    
        }

    }

    public static void logMessage(String msg)
    {
        try(FileWriter fw = new FileWriter("chat_log.txt",true)) 
        {
            fw.write(msg + "\n");
        } catch (IOException e) 
        {
            e.printStackTrace();    
        }
    }
    
}

class ClientHandler implements Runnable 
{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String username;

    public ClientHandler(Socket socket)
    {
        this.socket = socket;

        try 
        {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);    
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        try
        {
            writer.println("Enter Your username: ");
            username = reader.readLine();

            ChatServer.addClient(username,this);

            String message;

            while ((message = reader.readLine()) !=null) 
            {
                //Private Message
                if (message.startsWith("@")) 
                {
                    String[] parts = message.split(" ",2);
                    String targetUser = parts[0].substring(1);

                    ChatServer.privateMessage(targetUser,"(Private)" + username + ":" + parts[1]);
                }
                else
                {
                    ChatServer.broadcast(username + ":" + message);
                }   
            }
        }catch(IOException e)
        {
            System.out.println(username + "disconnected");
        }
        finally
        {
            ChatServer.removeClient(username);

            try 
            {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMessage(String message)
    {
        writer.println(message);
    }
}
