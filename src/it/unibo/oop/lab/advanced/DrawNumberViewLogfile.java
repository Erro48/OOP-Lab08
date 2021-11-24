package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class DrawNumberViewLogfile implements DrawNumberView {

    private DrawNumberViewObserver observer;
    PrintStream out;
    
    public DrawNumberViewLogfile(final String path) throws IOException {
        this.out = new PrintStream(new FileOutputStream(new File(path)));
    }

    public DrawNumberViewLogfile(final PrintStream stream) {
        this.out = stream;
    }



    @Override
    public void setObserver(DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void start() {

    }

    @Override
    public void numberIncorrect() {
        this.out.println("Number is incorrect... Try again");
    }

    @Override
    public void result(final DrawResult res) {
        this.out.println(res.getDescription());
    }


    @Override
    public void displayError(final String message) {
        this.out.println(message);
    }

}
