/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad3;
import java.io.IOException;
import java.net.ServerSocket;
/**
 *
 * @author Noeliia35
 */
public class MyServerSocket {
    
    public ServerSocket socket;
    
    public MyServerSocket() throws IOException {
        socket = new ServerSocket();
    }
    public MyServerSocket(int port) throws IOException {
        socket = new ServerSocket(port);
    }
    
    public MySocket accept() throws Exception {
        return new MySocket(socket.accept());
    }
}