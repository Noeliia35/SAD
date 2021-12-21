/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad3;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author Noeliia35
 */
public class ChatServidor {

    public static final int PORT = 4444;

    public static void main(String[] args) throws Exception {
        final MyServerSocket ss = new MyServerSocket(PORT);
        final ConcurrentHashMap<MySocket, String> biblioteca
                = new ConcurrentHashMap<MySocket, String>();

        while (true) {
            final MySocket s = ss.accept();
            int random = 0;
            int numero=30;
            while (s.name.isEmpty()) {
                random = new Random().nextInt(100);
                if (!biblioteca.containsValue("Guest" + random)) {
                    s.name = "Guest"+random;
                }
            }
            biblioteca.put(s, s.name);
            System.out.println(s.name + " connected...");

            new Servidor(s,biblioteca).start();
        }
    }
}