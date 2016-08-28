/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * What happens if you keep creating sockets?
 *
 * @author jens
 */
public final class SocketExample {

    private SocketExample() {
        /* No instantiation */ }

    public static void main(String[] args) throws IOException {
        Server.start(8080);
        ArrayList<Client> clientList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Client client = Client.connect("localhost", 8080);
            clientList.add(client);
            System.out.println("Created client #" + i);
        }

    }

    private static class Client implements AutoCloseable {

        private final Socket socket;

        private Client(Socket socket) {
            this.socket = socket;
        }

        private static Client connect(String host, int port) throws IOException {
            return new Client(new Socket(host, port));
        }

        @Override
        public void close() throws Exception {
            socket.close();
        }

    }

    private static class Server implements AutoCloseable {

        private final ServerSocket serverSocket;

        private Server(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        private static Server start(int port) throws IOException {
            ServerSocket serverSocket = new ServerSocket(port);
            AtomicInteger numberOfConnections = new AtomicInteger();
            ExecutorService threadPool = Executors.newCachedThreadPool();
            threadPool.execute(() -> {
                while (!serverSocket.isClosed()) {
                    try {
                        Socket newConnection = serverSocket.accept();
                        System.out.printf("Client %d connected on port %d\n", 
                                numberOfConnections.incrementAndGet(), 
                                newConnection.getPort());
                    } catch (IOException ex) {
                        // Handle exception on incoming connections
                    }
                }
            });
            return new Server(serverSocket);
        }

        @Override
        public void close() throws Exception {
            serverSocket.close();
        }

    }

}
