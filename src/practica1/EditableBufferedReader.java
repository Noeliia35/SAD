
package practica1;
//import java.util.*;
//import java.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Noelia
 */

public class EditableBufferedReader extends BufferedReader{
    
    private final Line line;
    private final Console console;
    
    public EditableBufferedReader(Reader in) {
        super(in);
        this.line = new Line();
        this.console = new Console();
        this.line.addObserver(this.console);
    }
    
    

    public void setRaw() { //Pasa la consola de modo cooked a modo raw
        List<String> comm = Arrays.asList("/bin/sh", "-c", "stty -echo raw </dev/tty"); //Transforma un array en una Lista para tener el comando
        /*
        /bin/sh se pone para que el sistema trate todos los comandos como un script
        stty -echo raw para pasar al modo raw la consola
        dev/tty representa la terminal de control del proceso actual
        */
        ProcessBuilder p = new ProcessBuilder(comm); //Creo un objeto ProcessBuilder para crear un proceso en el sistema operativo
        try {
            p.start(); //Inicia un nuevo proceso, en el que pasamos a raw.
        } catch (IOException ex) {
            System.out.println("Error introducing Raw mode");
        }
    }
    
    public void unsetRaw(){ //Pasa la consola de modo raw a modo cooked
        List<String> comm = Arrays.asList("/bin/sh","-c", "stty -echo cooked </dev/tty");
        ProcessBuilder p = new ProcessBuilder(comm);
        
        try {
            p.start();
        } catch (IOException ex) {
            System.out.println("Error intriducing Cooked mode");
        }
    }
    

    @Override
    public int read() throws IOException { //Lee el siguiente caracter o la siguiente tecla del cursor
        int lect = 0;
        try {
            lect = super.read();
            if (lect == Key.ESC) {
                lect = super.read();
                if (lect == Key.CORCHETE) {
                    lect = super.read();
                    switch (lect) {
                        case Key.INSERT:
                            super.read();
                            lect = Key._INSERT;
                            break;
                        case Key.SUPR:
                            super.read();
                            lect = Key._SUPR;
                            break;
                        case Key.HOME:
                            lect = Key._HOME;
                            break;
                        case Key.LEFT:
                            lect = Key._LEFT;
                            break;
                        case Key.RIGHT:
                            lect = Key._RIGHT;
                            break;
                        case Key.END:
                            lect = Key._END;
                            break;
                        default:
                            lect = Key._INVINPUT;
                            break;
                    }
                } else {
                    lect = Key._INVINPUT;
                }
            } else if (lect == Key.BACKSPACE) {
                lect = Key._BACKSPACE;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lect;
    }

    @Override
    public String readLine() throws IOException { //Leee la linea con posibilid de editarla
        this.setRaw();
        int lectLine = 0;
        do {
            lectLine = this.read();

            if (lectLine >= Key._ESCAPE) {
                switch (lectLine) {
                    case Key._INSERT:
                        this.line.toInsert();
                        break;
                    case Key._SUPR:
                        this.line.suprimir();
                        break;
                    case Key._RIGHT:
                        this.line.right();
                        break;

                    case Key._BACKSPACE:
                        this.line.backspace();
                        break;
                    case Key._END:
                        this.line.end();
                        break;
                    case Key._HOME:
                        this.line.home();
                        break;
                    case Key._LEFT:
                        this.line.left();
                        break;
                    default:
                        this.line.invInput();
                        break;

                }
            } else if (lectLine!= Key.ENTER) {
                line.addCaracter((char) lectLine);
            }

        } while (lectLine != Key.ENTER);
        this.unsetRaw();
        return line.getLine().toString();
    }
    
    public String getNumCols() {
        List<String> comm = Arrays.asList("/bin/sh", "-c", "tput cols 2> /dev/tty");
        ProcessBuilder p = new ProcessBuilder(comm);
        String cols = null;
        try {
            Process pr = p.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            cols = reader.readLine();
        } catch (IOException ex) {
            System.out.println("Error introducing Raw mode");
        }
        return cols;
    }

    public String getNumFils() {
        List<String> comm = Arrays.asList("/bin/sh", "-c", "tput lines 2> /dev/tty");
        ProcessBuilder p = new ProcessBuilder(comm);
        String cols = null;
        try {
            Process pr = p.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            cols = reader.readLine();
        } catch (IOException ex) {
            System.out.println("Error introducing Raw mode");
        }
        return cols;
    }
    
}
