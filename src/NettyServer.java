// Использование технологии Netty для реализации работы сервера...

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

  private static byte[] helloBytes = "Hello".getBytes();

  public static void main(String[] args) throws InterruptedException {

    ServerBootstrap serverBootstrap = new ServerBootstrap();

    // Создание канала; селектора с пулом потоков, получаемых из EventLoop;
    // хэндлера для обработки входящих и исходящих сообщений, реализованной в виде событийной модели...
    serverBootstrap
            .channel(NioServerSocketChannel.class)
            .group(new NioEventLoopGroup())
            .childHandler(new ChannelInboundHandlerAdapter(){
              @Override
              public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                if (msg instanceof ByteBuf){
                  // ctx.write(ctx.alloc().buffer().writeBytes("Hello".getBytes()));

                  // Используется память существующая, не пересоздается...
                  ctx.write(Unpooled.wrappedBuffer(helloBytes));
                  ctx.writeAndFlush(msg);
                }

                // Освобождение памяти в случае, если не передается msg...
//                try {
//                  System.out.println(msg);
//
//                } finally {
//                  ReferenceCountUtil.release(msg);
//                }
              }

            }).bind(3000).sync();
  }
}