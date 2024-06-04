package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    out.println(string);
                    if (string.contains("msg=Hello")) {
                        output.write("Hello, dear friend.".getBytes());
                    } else if (string.contains("msg=Exit")) {
                        output.write("Сервер закрыт!".getBytes());
                        server.close();
                    } else if (string.contains("msg=What")) {
                        output.write("What?".getBytes());
                    }
                    output.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }
}

