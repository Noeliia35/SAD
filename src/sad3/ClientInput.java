/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Noeliia35
 */
public class ClientInput extends Thread{
    
    public MySocket socket;
    
    public ClientInput(MySocket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            String line;
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));;
    
            while((line = teclado.readLine()) != null) {
                socket.println(line); 
            }
            socket.shutdownOutput(); 
        } catch (Exception ex) {
            Logger.getLogger(ClientInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}