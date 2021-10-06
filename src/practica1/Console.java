
package practica1;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Noelia
 */

public class Console implements Observer{

    @Override
    public void update(Observable observable, Object obj) {
        //Sobreescribimos el metodo update
        String[] action = (String[]) obj;

        if ("true".equals(action[0])) {
            System.out.print(action[1]);
        } else {
            System.out.println("Invalid input!");
        }

    }
}
