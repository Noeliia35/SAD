/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad3;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Noeliia35
 */
public class ClientOutput extends Thread {
    
    private ChatGraf cg;
    
    public ClientOutput(ChatGraf cg) {
        this.cg = cg;
    }

    private void addName(final String name) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    cg.getListModel().addElement(name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void removeName(final String name) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    cg.getListModel().removeElement(name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addTextArea(final String line) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    cg.getTextArea().append(line);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    // Output Thread
    @Override
    public void run() {
        String line;
        try {
            while ((line = cg.getSocket().readLine()) != null) {
                if (line.startsWith("/User ")) {
                    addName(line.substring(6));
                } else {
                    if (line.startsWith("/Bye ")) {
                        removeName(line.substring(5));
                    } else {
                        if (line.startsWith("/update ")) {
                            String[] nicks = line.substring(8).split("\\s");
                            removeName(nicks[0]);
                            addName(nicks[1]);
                        } else {
                            addTextArea(line + "\n");
                        }
                    }
                }
            }
            cg.getSocket().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
