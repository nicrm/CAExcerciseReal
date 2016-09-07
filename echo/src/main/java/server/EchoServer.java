package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.Log;
import shared.ProtocolStrings;

public class EchoServer {

    private static final ExecutorService clientHandlers = Executors.newCachedThreadPool();
    private static final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private static boolean keepRunning = true;
    private static ServerSocket serverSocket;
    private String ip;
    private int port;

    public static void stopServer() {
        keepRunning = false;
    }

    private void runServer(String ip, int port) {
        this.port = port;
        this.ip = ip;

        System.out.println("Server started. Listening on: " + port + ", bound to: " + ip);
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ip, port));
            do {
                Socket socket = serverSocket.accept(); //Important Blocking call
                System.out.println("Connected to a client");
                ClientHandler clientHandler = ClientHandler.handle(socket, this);
                clients.add(clientHandler);
                clientHandlers.submit(clientHandler);
            } while (keepRunning);
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void sendMulticast(String message) {
        clients.forEach(client -> client.sendMessage(message));
        clients.forEach(new Consumer<ClientHandler>() {
            public void accept(ClientHandler handler) {
                
            }
        });
    }

    void removeHandler(ClientHandler handler) {
        clients.remove(handler);
    }

    public static void main(String[] args) {
        try {
            Log.setLogger("logFile.txt", "ServerLog");
//            if (args.length != 2) {
//                throw new IllegalArgumentException("Error: Use like: java -jar EchoServer.jar <ip> <port>");
//            }
            String ip = "localhost";
            int port = 7777;
            new EchoServer().runServer(ip, port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<ClientHandler> getClientList() {
        return clients;
    }
}
