package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author bo.yang
 */
public class scoketServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(10393);
        serverSocket.setSoTimeout(10000);

        System.out.println("等待远程连接，本地server端口号："+serverSocket.getLocalPort() + "...");

        Socket socket = serverSocket.accept();
        System.out.println("远程主机地址："+ socket.getRemoteSocketAddress());

        while (true) {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("接受到客户端的消息：" +in.readUTF());

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("谢谢连接我，\nGoodbye!");
        }


    }
}
