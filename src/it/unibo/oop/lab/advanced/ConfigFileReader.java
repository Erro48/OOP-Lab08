package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigFileReader {
    
    private final int min;
    private final int max;
    private final int attempts;
    
    public ConfigFileReader(final String configFileName) {
        int min = 0;
        int max = 0;
        int attempts = 0;
        try {
            var reader = new BufferedReader(new FileReader(configFileName));
            min = Integer.parseInt(reader.readLine().split(": ")[1]);
            max = Integer.parseInt(reader.readLine().split(": ")[1]);
            attempts = Integer.parseInt(reader.readLine().split(": ")[1]);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            this.min = min;
            this.max = max;
            this.attempts = attempts;
        }
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public int getAttempts() {
        return this.attempts;
    }
    
}
