package practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica2.MySocket;

/**
 *
 * @author Noelia
 */
public class ClientInput extends Thread{
    
    public MySocket socket;
    
    public ClientInput(MySocket socket){
        this.socket = socket;
    }
    
    @Override
    public void run(){        
        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
            while((line = in.readLine()) != null){
                socket.println(line);
            }
            socket.shutdownOutput();
        } catch (IOException ex) {
            Logger.getLogger(ClientInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
