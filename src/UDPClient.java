// Клиент UDP...

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPClient {

  public static void main(String[] args) throws IOException {

    DatagramSocket datagramSocket = new DatagramSocket();

    InetAddress inetAddress = InetAddress.getByName("localhost");
    int port = 3000;

    byte buf[] = {12, 13};
    byte buf1[] = new byte[2];

    DatagramPacket datagramPacket = new DatagramPacket(buf, 2, inetAddress, port);
    DatagramPacket datagramPacketTorec = new DatagramPacket(buf1, 2);

    datagramSocket.connect(inetAddress, port);

    System.out.println("Is connected : " + datagramSocket.isConnected());
    System.out.println("InetAddress : " + datagramSocket.getInetAddress());
    System.out.println("Port : " + datagramSocket.getPort());

    datagramSocket.send(datagramPacket);
    System.out.println("Packet was successfully sending");

    datagramSocket.receive(datagramPacketTorec);
    System.out.println("Received packet data : " + Arrays.toString(datagramPacketTorec.getData()));

  }
}