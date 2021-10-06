
package practica1;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Noelia
 */

public class Line extends Observable{

  ArrayList<Integer> buffer;
    private int posCursor;
    private int length;
    private int pos;
    private boolean insert;
    private String[] action;
    private StringBuilder line;

    public Line() {
        this.buffer = new ArrayList<>();
        this.line = new StringBuilder();
        this.posCursor = 0;
        this.length = 0;
        this.insert = false;
        this.action = new String[2];

    }

    public StringBuilder getLine() {
        return this.line;
    }

    public int getPosCursor() {
        return this.posCursor;
    }

    public int getLength() {
        return this.line.length();
    }

    public Boolean getMode() {
        return this.insert;
    }

    public void invInput() {
        action[0] = "false";
        this.setChanged();
        this.notifyObservers(action);

    }

    public void addCaracter(char newChar) {

        if (getMode()) { // mode insert
            this.line.replace(this.posCursor, this.posCursor + 1, "" + newChar);
            action[0] = "true";
            action[1] = "" + newChar;
            this.setChanged();
            this.notifyObservers(action);

        } else { // add character

            this.line = this.line.insert(this.posCursor, newChar);
            action[0] = "true";// "insertChar";
            action[1] = Key.SINSERT + newChar;
            this.setChanged();
            this.notifyObservers(action);

        }

        this.posCursor++;

    }

    public void toInsert() {
        this.insert = !this.insert;

    }

    public void backspace() {
        if ((this.getLength() > 0) && this.posCursor > 0) { // min carac+cursor
            int pos = this.posCursor - 1;
            this.line.deleteCharAt(pos);
            this.posCursor--;
            action[0] = "true";
            action[1] = Key.BS;
            this.setChanged();
            this.notifyObservers(action);
        }
    }

    public void end() {
        int posFin = this.getLength() - this.posCursor;
        this.posCursor = this.getLength();
        action[0] = "true";
        action[1] = Key.END1 + posFin + Key.END2; // In this case we pass the number to move
        this.setChanged();
        this.notifyObservers(action);
    }

    public void home() {
        this.posCursor = 0;
        action[0] = "true";
        action[1] = Key.SHOME;
        this.setChanged();
        this.notifyObservers(action);
    }

    public void left() {
        if (this.posCursor > 0) {
            this.posCursor--;
            action[0] = "true";
            action[1] = Key.SLEFT;
            this.setChanged();
            this.notifyObservers(action);
        }
    }

    public void right() {
        if (this.posCursor < this.getLength()) {
            this.posCursor++;
            action[0] = "true";
            action[1] = Key.SRIGHT;
            this.setChanged();
            this.notifyObservers(action);
        }
    }

    public void suprimir() {
        if (this.posCursor < getLength()) {
            int pos = this.posCursor;
            this.line.deleteCharAt(pos);
            action[0] = "true";
            action[1] = Key.DEL;
            this.setChanged();
            this.notifyObservers(action);
        }
    }
}