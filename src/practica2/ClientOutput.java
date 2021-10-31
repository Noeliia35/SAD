package practica2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica2.MySocket;

/**
 *
 * @author Noelia
 */
public class ClientOutput extends Thread{
    
    public String name;
    public MySocket socket;

    public ClientOutput(MySocket socket) {
        this.socket = socket;
    }
    
    public void run(){
        try {
            String line;
            //Hay linea en el servidor
            while((line=socket.readLine()) != null){
                //Escribe la linea por pantalla
                System.out.println(line);
            }
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
