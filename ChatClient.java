//=============================================================
//Client Side
//=============================================================

import java.io.*;
import java.util.*;
import java.net.*;

public class ChatClient 
{
    public static void main(String[] args) 
    {
        String serverAddress = "localhost";
        int port = 5000;
        
        try 
        {
            Socket socket = new Socket(serverAddress,port);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

            Scanner scanner = new Scanner(System.in);

            //Read messages from server
            new Thread(() ->
            {
                try 
                {
                    String msg;
                    while((msg = reader.readLine()) != null)
                    {
                        System.out.println(msg);
                    }    
                } catch (IOException e) {
                    System.out.println("Connection closed");
                }
            }).start();

             //Send messages
             while (true) 
            {
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("/exit")) 
                {
                    writer.println("User left chat");
                    socket.close();
                    System.out.println("Disconnected from chat");
                    break;    
                }

                writer.println(message);
            }

        } catch (IOException e) {
            System.out.println("Unable to connect to server");
        }
    }    
}
