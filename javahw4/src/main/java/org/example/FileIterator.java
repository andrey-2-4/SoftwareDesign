package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class FileIterator implements Iterator<String> {
    private Vector<String> lines;
    private int vectorIterator;
    private BufferedReader reader;
    public FileIterator(String pathToFile) throws IOException {
        reader =  new BufferedReader(new FileReader(pathToFile));
        vectorIterator = 0;
        lines = new Vector<String>();
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }
        reader.close();
    }

    @Override
    public boolean hasNext() {
        return vectorIterator < lines.size();
    }

    @Override
    public String next() {
        return lines.get(vectorIterator++);
    }
}
