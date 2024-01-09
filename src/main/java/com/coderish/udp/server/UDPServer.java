package com.coderish.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(1989);

        byte[] requestBytes = new byte[512];
        DatagramPacket requestPacket;
        DatagramPacket responsePacket;
        while (true) {
            requestPacket = new DatagramPacket(requestBytes, requestBytes.length);
            serverSocket.receive(requestPacket);

            String requestData = new String(requestBytes, 0, requestPacket.getLength());
            System.out.println("Command received: " + requestData);

            if ("n".equalsIgnoreCase(requestData)) {
                System.out.println("Shutting Down");
                break;
            }

            String quote = "HelloHelloHello";
            responsePacket = new DatagramPacket(quote.getBytes(), quote.getBytes().length, requestPacket.getAddress(), requestPacket.getPort());
            serverSocket.send(responsePacket);
        }

    }
}
