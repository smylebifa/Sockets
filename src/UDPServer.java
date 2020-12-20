// Сервер UDP...

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPServer {

  public static void main(String[] args) throws IOException {

    DatagramSocket datagramSocket = new DatagramSocket(3000);

    byte buf[] = new byte[2];
    byte send[] = {13, 18};

    DatagramPacket datagramPacket = new DatagramPacket(buf, 2);

    datagramSocket.receive(datagramPacket);

    DatagramPacket sendPacket = new DatagramPacket(send, 2,
            datagramPacket.getAddress(), datagramPacket.getPort());

    System.out.println("Received packet data : " + Arrays.toString(datagramPacket.getData()));

    datagramSocket.send(sendPacket);

  }
}



//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//
//public class UDPServer {
//
//  public static void main(String[] args) throws IOException {
//
//    DatagramSocket datagramSocket = new DatagramSocket(3000);
//
//    byte[] buf = new byte[128];
//
//    while (true){
//
//      DatagramPacket datagramPacketReceive = new DatagramPacket(buf, buf.length);
//      datagramSocket.receive(datagramPacketReceive);
//
//      System.out.println(new String(datagramPacketReceive.getData(),
//              datagramPacketReceive.getOffset(),
//              datagramPacketReceive.getLength()));
//
//      datagramSocket.send(datagramPacketReceive);
//    }
//  }
//}