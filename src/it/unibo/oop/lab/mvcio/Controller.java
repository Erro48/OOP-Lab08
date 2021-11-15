package it.unibo.oop.lab.mvcio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    private String currentFile;
    /*
     * METHODS
     */
    /**
     * Set a file as current file.
     * 
     * @param filename
     *              the file to be set as current
     */
    public void setCurrentFile(final String filename) {
        this.currentFile = filename;
    }
    /**
     * Returns the current file.
     * 
     * @return
     *      the current file
     */
    public String getCurrentFile() {
        return this.currentFile;
    }
    /**
     * Returns the path of the current file.
     * 
     * @return
     *      the path of the current file
     */
    public String getPath() {
        return System.getProperty("user.home") + System.getProperty("file.separetor") + this.getCurrentFile();
    }
    /**
     * Writes in the current file the input given.
     * 
     * @param text
     *          The input which has to be written in the current file
     * @throws IOException
     */
    public void write(final String text) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new FileWriter(this.getPath()));
        writer.append(text);
        writer.close();
    }
}
