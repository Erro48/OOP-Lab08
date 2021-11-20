/**
 * 
 */
package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    
    private String currentString;
    private List<String> history;
    
    public ControllerImpl() {
        this.history = new ArrayList<>();
    }

    @Override
    public void setString(final String str) throws IllegalStateException {
        if (str == null) {
            throw new IllegalStateException();
        }
        this.currentString = str;
        this.history.add(str);
    }

    @Override
    public String getString() {
        return this.currentString;
    }

    @Override
    public List<String> getHistory() {
        return this.history;
    }

    @Override
    public void printString() throws IllegalStateException {
        if (this.getString() == null) {
            throw new IllegalStateException();
        }
        System.out.println(this.getString());
    }

}
