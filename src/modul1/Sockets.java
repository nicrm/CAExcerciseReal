/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jens
 */
public class Sockets {

    public static void main(String[] args) throws IOException {
        // TCP
//        ServerSocket paloAlto = new ServerSocket();
//        paloAlto.bind(new InetSocketAddress(8080));
//
//        Runnable paloAltoRunnable = () -> {
//            try (Socket newConnection = paloAlto.accept();
//                OutputStream outputStream = newConnection.getOutputStream()) {
//                outputStream.write("Hello world\n".getBytes());
//                outputStream.write("Hello world2\n".getBytes());
//                Thread.sleep(2000);
//                outputStream.write("Hello world3\n".getBytes());
//                outputStream.write("Hello world4\n".getBytes());
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            } 
//        };
//        new Thread(paloAltoRunnable).start();
//
//        Socket socket = new Socket();
//        SocketAddress address = new InetSocketAddress("localhost", 8080);
//        socket.connect(address);
//        BufferedReader inputStream
//                = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        
//        String line;
//        while ((line = inputStream.readLine()) != null) {
//            System.out.println(line);
//        }
        // UDP
        // Server
        DatagramSocket datagramSocket = new java.net.DatagramSocket();
        datagramSocket.bind(new InetSocketAddress("localhost", 8080));
        datagramSocket.connect(new InetSocketAddress("localhost", 8080));
        datagramSocket.send(new DatagramPacket("test".getBytes(), 10));
        // Server
        DatagramPacket newPacket = new DatagramPacket(new byte[100], 100);
        datagramSocket.receive(newPacket);
    }

}
