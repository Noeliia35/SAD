/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 *
 * @author Noeliia35
 */
public class MySocket {
    
    public Socket socket;
    public BufferedReader buff;
    public PrintWriter out;
    public String name;
    
    public MySocket(Socket socket) throws Exception {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream());
        this.buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.name = buff.readLine();
    }
    public MySocket(String host, int port) throws Exception {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream());
        buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void close() throws Exception {
        socket.close();
    }
    
    public String readLine() throws Exception {
        return buff.readLine();
    }
    
    public void println(String line) throws Exception {
        out.println(line);
        out.flush();
    }
    
    public void shutdownOutput() throws Exception {
        socket.shutdownOutput();
    }
}