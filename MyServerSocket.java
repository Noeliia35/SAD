package practica2;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Noelia
 */
public class MyServerSocket {
    
    public ServerSocket socket;

    public MyServerSocket() throws IOException {
        this.socket = new ServerSocket();
    }

    public MyServerSocket(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }
    
    public MySocket accept() throws Exception{
        return new MySocket(socket.accept());
    } 
}
