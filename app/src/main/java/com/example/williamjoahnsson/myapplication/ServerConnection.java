package com.example.williamjoahnsson.myapplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


class ServerConnection{
    private Socket socket;
    private String host;
    private int port;
    private PrintToOutput gui;
    private PrintWriter toServer;
    private BufferedReader fromServer;

    ServerConnection() {
    }


    void connect(final PrintToOutput gui, String host, int port) {
        try {
            socket = new Socket(host, port);
            toServer = new PrintWriter(socket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new Listener(gui)).start();
            gui.handleMsg("connected");
        } catch (UnknownHostException e) {
            gui.handleMsg("Can't connect");
        } catch (IOException e)  {
            gui.handleMsg(e.toString());
            gui.handleMsg("Can't connect");
        }
    }

    void disconnect() {
        try {
            socket.close();
        }
        catch (IOException e) {
            gui.handleMsg("Can't disconnect");
        }
    }

    void sendStartNewGame() {
        sendMsg(MsgHeaders.START_GAME.toString(), "");
    }

    void sendChar(char guess) {
        sendMsg(MsgHeaders.GUESS_CHAR.toString(), String.valueOf(guess));
    }

    void sendWord(String guess) {
        sendMsg(MsgHeaders.GUESS_WORD.toString(), guess);
    }

    private void sendMsg(String header, String body) {
        toServer.println(header + Constants.MSG_DELIMITER + body);
    }

    private class Listener implements Runnable {
        private final PrintToOutput outputHandler;

        private Listener(PrintToOutput outputHandler) {
            this.outputHandler = outputHandler;
        }
        @Override
        public void run() {
            try {
                while(true) {
                    outputHandler.handleMsg(fromServer.readLine());
                }
            } catch (Throwable connectionFailure) {
                outputHandler.handleMsg("Connection lost");
            }
        }
    }
}
