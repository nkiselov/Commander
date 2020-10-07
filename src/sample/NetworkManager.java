package sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager {
    private InputStream is;
    private OutputStream os;
    private NetworkDelegate nd;
    private ListenThread lt;

    public NetworkManager(NetworkDelegate nd){
        this.nd = nd;
    }

    public void connect(InetAddress address, int port){
        try {
            Socket socket = new Socket(address, port);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            nd.onConnect();
            lt = new ListenThread(is,nd);
            lt.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void listen(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            is = socket.getInputStream();
            os = socket.getOutputStream();
            nd.onConnect();
            lt = new ListenThread(is,nd);
            lt.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void write(byte[] data){
        try {
            os.write(data);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
