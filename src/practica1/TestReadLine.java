
package practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Noelia
 */

public class TestReadLine {
    
    public static void main(String[] args){
        EditableBufferedReader in;
        in = new EditableBufferedReader(new InputStreamReader(System.in));
        String str = null;
        try{
            str = in.readLine();
        }catch (IOException e){ e.printStackTrace();}
        System.out.println("\nline is: " + str);
    }
}
