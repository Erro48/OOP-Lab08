package it.unibo.oop.lab.advanced;

public class DrawNumberViewConsole implements DrawNumberView {
    
    private DrawNumberViewObserver observer;
    
    @Override
    public void setObserver(DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void start() {
    }

    @Override
    public void numberIncorrect() {
        System.err.println("Incorrect number... try again");
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        System.out.println("You lost");
    }

    @Override
    public void displayError(final String message) {
        System.err.println(message);
    }

}
