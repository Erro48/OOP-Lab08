package it.unibo.oop.lab.advanced;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    
    private static final int N_VIEWS = 4;
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;
    private static final String CONFIG_FILE = System.getProperty("user.home")
                                              + File.separator + "eclipse-workspaces"
                                              + File.separator + "unibo"
                                              + File.separator + "lab08"
                                              + File.separator + "res"
                                              + File.separator + "config.yml";
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * 
     */
    public DrawNumberApp() {
        //this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        this.model = new DrawNumberImpl(CONFIG_FILE);
        this.views = new ArrayList<>();

        this.views.add(new DrawNumberViewImpl());
        this.views.add(new DrawNumberViewImpl());
        this.views.add(new DrawNumberViewConsole());
        this.views.add(new DrawNumberViewLogfile());
        
        for (final var view : this.views) {
            view.setObserver(this);
            view.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (DrawNumberView view : this.views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            for (DrawNumberView view : this.views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
