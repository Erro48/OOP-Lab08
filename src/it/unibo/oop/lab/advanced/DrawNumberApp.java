package it.unibo.oop.lab.advanced;

import java.io.File;

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
    private final DrawNumberView[] views;

    /**
     * 
     */
    public DrawNumberApp() {
        //this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        this.model = new DrawNumberImpl(CONFIG_FILE);
        this.views = new DrawNumberView[N_VIEWS];

        this.views[0] = new DrawNumberViewImpl();
        this.views[1] = new DrawNumberViewImpl();
        this.views[2] = new DrawNumberViewConsole();
        this.views[3] = new DrawNumberViewLogfile();
        
        for (final var view : this.views) {
            view.setObserver(this);
            view.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        
        for (DrawNumberView view : this.views) {
            try {
                final DrawResult result = model.attempt(n);
                    view.result(result);
            } catch (IllegalArgumentException e) {
                view.numberIncorrect();
            } catch (AttemptsLimitReachedException e) {
                view.limitsReached();
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
