package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.in;
import static java.lang.System.out;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    out.println(string);
                    if ("msg=Hello".contains(string)) {
                        output.write("Приветствуем Вас!".getBytes());
                    }
                    if (string.contains("msg=Bye")) {
                        output.write("Сервер закрыт!".getBytes());
                        server.close();
                    }
                    output.flush();
                }
            }
        }
    }
}

