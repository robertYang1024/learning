package socket;

import java.io.*;
import java.net.Socket;
import java.sql.ClientInfoStatus;

/**
 * @author bo.yang
 */
public class socketClient {
    public static void main(String[] args) throws Exception {
            String serverIp = "127.0.0.1";
            int port = 10393;

            System.out.println("连接到主机：" + serverIp + ":" + port);
            Socket client = new Socket(serverIp, port);

            System.out.println("远程主机地址："+ client.getRemoteSocketAddress());

            while (true) {

                InputStream inputFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inputFromServer);

                System.out.println("revive:" + in.readUTF());

                Thread.sleep(3000);

                OutputStream outputStream = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outputStream);

                out.writeUTF("hello from " +client.getLocalSocketAddress());
                System.out.println("send:hello from "+client.getLocalSocketAddress());




            }
        }
    }
