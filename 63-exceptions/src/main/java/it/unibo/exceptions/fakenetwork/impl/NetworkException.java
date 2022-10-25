package it.unibo.exceptions.fakenetwork.impl;

import java.io.IOException;

public class NetworkException extends IOException {

    public NetworkException() {
        System.out.println("Network error: no response");
    }

    public NetworkException(String message) {
        System.out.println("Network error while sending message: " + message);
    }

    
    
}
