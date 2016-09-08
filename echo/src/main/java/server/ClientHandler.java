/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;

/**
 *
 * @author jens
 */
public class ClientHandler implements Runnable {

    private final Socket socket;

    private final PrintWriter writer;
    private final EchoServer server;
    private final Scanner input;
    private String name;
    private static boolean loggedIn = false;
    

    public ClientHandler(Socket socket, EchoServer server) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(socket.getOutputStream());
        this.server = server;
        this.input = new Scanner(socket.getInputStream());
    }
/*
    public static ClientHandler handle(Socket socket, EchoServer server) throws IOException {
        Scanner input = new Scanner(socket.getInputStream());
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        return new ClientHandler(socket, input, writer, server);
    }
*/
    public void run() {
        try {
            while (true) {
                String message = input.nextLine();
                String[] msg = message.split(":");
                System.out.println("Received: " + message);

                if (!loggedIn) {
                    if (msg[0].equals(ProtocolStrings.LOGIN)) {
                        loggedIn = true;
                        name = msg[1];
                        printClientList();
                    }
                } else {

                    switch (msg[0]) {
                        case "MSG":
                            if (msg[0].equals(ProtocolStrings.MSG) && (msg[1].equals(""))) {
                                server.sendMulticast(msg[2]);
                            } else {
                                String[] users = msg[1].split(",");
                                for (ClientHandler client : server.getClientHandlers()) {
                                    for (String user : users) {
                                        if(client.getName().equals(user)){
                                            client.sendMessage(msg[2]);
                                        }
                                    }
                                }
                            }
                            break;
                        case "LOGOUT":
                            if (msg[0].equals(ProtocolStrings.LOGOUT)) {
                                try {
                                    writer.println(ProtocolStrings.STOP);//Echo the stop message back to the client for a nice closedown
                                    socket.close();

                                } catch (IOException ex) {
                                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                        default: { 
                    System.out.println("    default    ");
                    }
                    
                }
                }
                
            }

        } finally {
            try {
               writer.println(ProtocolStrings.STOP);//Echo the stop message back to the client for a nice closedown
               socket.close();
               server.removeHandler(this);
                System.out.println("Closed a Connection");
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendMessage(String message) {
        System.out.println("Sending " + message);
        writer.println(message);
        writer.flush();
    }

    public void printClientList() {
        String clientList = "";

        for (ClientHandler c : server.getClientHandlers()) {
            if (c.getName()!= null)
                clientList = clientList.concat(c.getName() + ",");
        }
        clientList = clientList.substring(0, clientList.length() - 1);

        server.sendMulticast("CLIENTLIST:" + String.join(",", clientList));
}
    public String getName() {
        return name;
    }

    public EchoServer getServer() {
        return server;
    }

}
