// Использование селекторов...

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class NIOServer {

  public static void main(String[] args) throws IOException {

    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress(3000));
    serverSocketChannel.configureBlocking(false);

    ArrayList<SocketChannel> socketChannels = new ArrayList<>();

    while (true) {

      SocketChannel socketChannel = serverSocketChannel.accept();
      if (socketChannel != null) {
        socketChannels.add(socketChannel);
      }

      for (Iterator <SocketChannel> iterator = socketChannels.iterator(); iterator.hasNext(); ) {

        SocketChannel channel = iterator.next();

        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        channel.configureBlocking(false);

        int read = channel.read(byteBuffer);
        if (read == -1) {
          iterator.remove();
        }

        if (read == 0) continue;

        byteBuffer.flip();
        System.out.println("Buffer after read = " + byteBuffer);

        String string = new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
        System.out.println(string);

        byteBuffer.clear();
      }
    }
  }
}


//public class NIOServer {
//
//  public static void main(String[] args) throws IOException {
//
//    Selector selector = Selector.open();
//
//    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//    serverSocketChannel.bind(new InetSocketAddress(3000));
//    serverSocketChannel.configureBlocking(false);
//    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//
//    while (true) {
//
//      int select = selector.select();
//      if (select == 0) continue;
//
//      Set<SelectionKey> selectionKeys = selector.selectedKeys();
//
//      for (SelectionKey key : selectionKeys){
//
//        SelectableChannel channel = key.channel();
//
//        if (channel instanceof ServerSocketChannel) {
//          SocketChannel socketChannel = ((ServerSocketChannel) channel).accept();
//          if (socketChannel != null) {
//            socketChannel.configureBlocking(false);
//            socketChannel.register(selector, SelectionKey.OP_READ);
//          }
//        }
//
//        if (channel instanceof SocketChannel) {
//          ByteBuffer byteBuffer = ByteBuffer.allocate(128);
//
//          int read = ((SocketChannel) channel).read(byteBuffer);
//          if (read == -1) {
//            channel.close();
//          }
//
//          if (read == 0) continue;
//          byteBuffer.flip();
//          System.out.println("Buffer after read = " + byteBuffer);
//
//          String string = new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
//          System.out.println(string);
//
//          byteBuffer.clear();
//        }
//      }
//
//      selectionKeys.clear();
//    }
//  }
//}