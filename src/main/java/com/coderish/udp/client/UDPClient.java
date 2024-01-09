package com.coderish.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket(1988);
        Scanner scanner = new Scanner(System.in);
        byte[] responseBytes = new byte[65535];
        DatagramPacket requestPacket;
        DatagramPacket responsePacket;

        while (true) {
            System.out.println("Press n to exit. Press enter to continue..");
            String input = scanner.nextLine();
            requestPacket = new DatagramPacket(input.getBytes(), input.getBytes().length, InetAddress.getLocalHost(), 1989);
            clientSocket.send(requestPacket);

            if ("n".equalsIgnoreCase(input)) {
                break;
            }

            responsePacket = new DatagramPacket(responseBytes, responseBytes.length);
            clientSocket.receive(responsePacket);

            System.out.println("Quote received: " + new String(responseBytes, 0, responsePacket.getLength()));

            responseBytes = new byte[65535];
        }
    }
}
