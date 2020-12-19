// Использование NIO...

import java.nio.ByteBuffer;

public class NIO {

  public static void main(String[] args) {

    ByteBuffer byteBuffer = ByteBuffer.allocate(20);
    System.out.println(byteBuffer);

    byteBuffer.putInt(14);
    System.out.println(byteBuffer);
    System.out.println("Remaining counts of cell = " + byteBuffer.remaining());

    byteBuffer.flip();
    System.out.println(byteBuffer);

    System.out.println("Remaining counts of cell = " + byteBuffer.remaining());

    write(byteBuffer);
    System.out.println(byteBuffer);

    byteBuffer.flip();
    System.out.println(byteBuffer);

  }

  private static void write(ByteBuffer byteBuffer) {

    System.out.println("Int = " + byteBuffer.getInt());
    System.out.println("Long = " + byteBuffer.getLong());
  }

}