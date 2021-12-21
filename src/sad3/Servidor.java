/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @author Noeliia35
 */
public class Servidor extends Thread {
    
    private ConcurrentHashMap<MySocket, String> dictionary;
    private MySocket socket;
    public static final int PORT = 444;
    
    public Servidor(MySocket socket, ConcurrentHashMap<MySocket,String> dicc){
        this.socket = socket;
        this.dictionary = dicc;
    }

    public String getClientNick(MySocket socket) throws Exception {
        return dictionary.get(socket);
    }

    public void setClientName(MySocket socket, String name) throws Exception {
        dictionary.replace(socket, name);
    }

    public void sendBroadcast(String message) throws Exception {
        Iterator<MySocket> sockets = dictionary.keySet().iterator();
        while (sockets.hasNext()) {
            MySocket client = sockets.next();
            client.println(message);
        }
    }

    public void run() {
        String line;
        try {
            String initialNick = getClientNick(socket);

            sendBroadcast("/User " + initialNick);

            Iterator<String> nicks = dictionary.values().iterator();
            while (nicks.hasNext()) {
                String nick = nicks.next();
                if (!initialNick.equals(nick)) { 
                    socket.println("/User " + nick);
                }
            }

            while ((line = socket.readLine()) != null) {
                if (line.startsWith("/nick ")) {
                    String name = line.substring(6);
                    if (!dictionary.contains(name)) {
                        String previousNick = getClientNick(socket);
                        setClientName(socket, name);
                        sendBroadcast("/update " + previousNick + " " + name);
                    }
                } else {
                    String clientSendingMessage = getClientNick(socket);
                    sendBroadcast(clientSendingMessage + ": " + line);
                }
            }
            String nick = getClientNick(socket);
            System.out.println(nick + " disconnected...");
            dictionary.remove(socket);
            socket.close();
            sendBroadcast("/Bye " + nick);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}