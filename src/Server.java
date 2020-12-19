// Реализация работы сервера...

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

  public static void main(String[] args) throws IOException {

    ServerSocket serverSocket = new ServerSocket(3000);

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    while (true) {

      final Socket accept = serverSocket.accept();

      executorService.submit(() -> {

        try {
          BufferedReader bufferedReader = new BufferedReader(
                  new InputStreamReader(accept.getInputStream(), StandardCharsets.UTF_8));

          PrintWriter printWriter = new PrintWriter(accept.getOutputStream());

          while (true) {
            String line = bufferedReader.readLine();
            if (line == null) break;

            printWriter.println("Hello " + line);
            printWriter.flush();
          }

        } catch (IOException e){
          e.printStackTrace();
        } finally {
          try {
            accept.close();
          } catch (IOException e){
            e.printStackTrace();
          }
        }

      });
    }

  }

}


// Реализация взаимодействия клиента и сервера.

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;

//public class Server {
//  public static void main(String[] args){
//    int port = 3000;
//    try {
//      // создаем сокет сервера и привязываем его к вышеуказанному порту...
//      ServerSocket serverSocket = new ServerSocket(port);
//      System.out.println("Waiting for a client...");
//
//      // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером...
//      Socket socket = serverSocket.accept();
//      System.out.println("Got a client.");
//      System.out.println();
//
//      // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту...
//      InputStream sin = socket.getInputStream();
//      OutputStream sout = socket.getOutputStream();
//
//      // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения...
//      DataInputStream in = new DataInputStream(sin);
//      DataOutputStream out = new DataOutputStream(sout);
//
//      while(true) {
//        String line = in.readUTF(); // ожидаем пока клиент пришлет строку текста...
//        System.out.println("Client sent line : " + line);
//        System.out.println("Sending back with greeting...");
//        out.writeUTF("Hello " + line); // отсылаем клиенту обратно ту самую строку текста...
//        out.flush(); // заставляем поток закончить передачу данных...
//        System.out.println("Waiting for the next line...");
//        System.out.println();
//      }
//    } catch(Exception x) { x.printStackTrace(); }
//  }
//}
//
//
//package ru.samoilov;
//
//        import java.io.*;
//        import java.net.Socket;
//
//
//public class Client{
//  public static void main(String[] args){
//    int serverPort = 3000; // Порт к которому привязывается сервер...
//    try {
//      // Создаем сокет используя IP-адрес и порт сервера...
//      Socket socket = new Socket("localhost", serverPort);
//
//      // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом...
//      InputStream sin = socket.getInputStream();
//      OutputStream sout = socket.getOutputStream();
//
//      // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения...
//      DataInputStream in = new DataInputStream(sin);
//      DataOutputStream out = new DataOutputStream(sout);
//
//      // Создаем поток для чтения с клавиатуры...
//      BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//      String line = null;
//      System.out.println("Type in something and press enter.");
//      System.out.println();
//
//      while (true) {
//        // Ждем пока пользователь введет что-то и нажмет кнопку Enter...
//        line = keyboard.readLine();
//        System.out.println("Sending this line to the server...");
//        out.writeUTF(line); // Отсылаем введенную строку текста серверу...
//        out.flush(); // Заставляем поток закончить передачу данных...
//        line = in.readUTF(); // ждем пока сервер отошлет строку текста.
//        System.out.println("Server sent : " + line);
//        System.out.println("Enter more lines.");
//        System.out.println();
//      }
//    } catch (Exception x) {
//      x.printStackTrace();
//    }
//  }
//}