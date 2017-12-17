package com.example.williamjoahnsson.myapplication;

/**
 * Created by William Joahnsson on 2017-12-17.
 */

import java.util.concurrent.CompletableFuture;

class Controller {
    private final ServerConnection connection;

     Controller() {
        connection = new ServerConnection();
    }

    void connect(String hostAddress, int portNr, PrintToOutput gui) {
        CompletableFuture.runAsync(() -> connection.connect(gui, hostAddress, portNr));
    }

    void disconnect() {
        connection.disconnect();
    }

    void sendStartGame() {
        CompletableFuture.runAsync(connection::sendStartNewGame);
    }

    void sendWord(String guess) {
        CompletableFuture.runAsync(() -> connection.sendWord(guess));
    }

    void sendChar(char guess) {
        CompletableFuture.runAsync(() -> connection.sendChar(guess));
    }
}
