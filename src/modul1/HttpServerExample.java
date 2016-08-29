/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author jeep
 */
public class HttpServerExample {
    
    public static void main(String[] args) throws IOException {
        HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.start();
        
        Socket s = new Socket("localhost", 8080);
        PrintWriter printer = new PrintWriter(s.getOutputStream());
        printer.print("GET /index.html HTTP/1.1\r\n");
        printer.print("Host: localhost\r\n");
        printer.println();
        printer.flush();
        
        Scanner scanner = new Scanner(s.getInputStream());
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        
    }
    
    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            he.sendResponseHeaders(200, 0);
        }
        
    }
    
}
