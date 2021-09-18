package com.epam.tc.hw9.exceptions;

public class PropertyFileReaderException extends RuntimeException {

    public PropertyFileReaderException(String file) {
        super("Property file \"" + file + "\" could not be read.");
    }
}
