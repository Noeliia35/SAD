package practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Noelia
 */
public class MySocket {
    
    public Socket socket;
    public BufferedReader buffered;
    public PrintWriter out;
    public String nombre;
    
    public MySocket(Socket socket) throws Exception{
        
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream());
        this.buffered = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.nombre = buffered.readLine();
    }

    public MySocket(String host, int port) throws IOException {
        this.socket = new Socket(host,port);
        this.out = new PrintWriter(socket.getOutputStream());
        this.buffered = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public void close() throws IOException{
        socket.close();
    }
    
    public String readLine() throws IOException{
        return buffered.readLine();
    }
    
    public void println(String line){
        out.println(line);
        out.flush();
    }
    
    public void shutdownOutput() throws IOException{
        socket.shutdownOutput();
    }
}
