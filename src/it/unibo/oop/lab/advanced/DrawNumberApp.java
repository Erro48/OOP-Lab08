package it.unibo.oop.lab.advanced;

import java.io.File;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

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
    private final DrawNumberView view;

    /**
     * 
     */
    public DrawNumberApp() {
        //this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        this.model = new DrawNumberImpl(CONFIG_FILE);
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
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
