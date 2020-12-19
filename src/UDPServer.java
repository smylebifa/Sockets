// Сервер UDP...

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

  public static void main(String[] args) throws IOException {

    DatagramSocket datagramSocket = new DatagramSocket(3000);

    byte[] buf = new byte[128];

    while (true){

      DatagramPacket datagramPacketReceive = new DatagramPacket(buf, buf.length);
      datagramSocket.receive(datagramPacketReceive);

      System.out.println(new String(datagramPacketReceive.getData(),
              datagramPacketReceive.getOffset(),
              datagramPacketReceive.getLength()));

      datagramSocket.send(datagramPacketReceive);
    }
  }
}