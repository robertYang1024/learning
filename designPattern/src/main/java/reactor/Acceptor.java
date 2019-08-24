package reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @author bo.yang
 */
public class Acceptor implements Runnable {

    private Reactor reactor;
    public Acceptor(Reactor reactor){
        this.reactor=reactor;
    }
    @Override
    public void run() {
        try {
            // 在server socket channel接收到/准备好 一个新的 TCP连接后,就会向程序返回一个新的socketChannel。
            // 但是这个新的socket channel并没有在selector“选择器/代理器”中注册，
            // 所以程序还没法通过selector通知这个socket channel的事件。
            // 于是我们拿到新的socket channel后，要做的第一个事情就是到selector“选择器/代理器”中注册这个
            // 即后面Handler里面要去注册socketChannel到selector

            SocketChannel socketChannel=reactor.serverSocketChannel.accept();
            //调用Handler来处理channel，在Handler里会注册socketChannel
            if(socketChannel!=null) {
                new SocketReadHandler(reactor.selector, socketChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
         System.out.println(1<<0);
         System.out.println(1<<2);
         System.out.println(1<<3);
         System.out.println(1<<4);
    }
}
