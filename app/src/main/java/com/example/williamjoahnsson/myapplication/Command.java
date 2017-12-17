package com.example.williamjoahnsson.myapplication;



class Command {
    private MsgHeaders header;
    private String body;

    Command(String command) {
        String command1 = command.toLowerCase();
        extractHeaderAndBody(command);
    }

    private void extractHeaderAndBody(String command) {
        if (command.equals("start game")) {
            header = MsgHeaders.START_GAME;
            body = "";
        } else if (command.equals("disconnect")) {
            header = MsgHeaders.DISCONNECT;
            body = "";
        } else if (command.split(" ")[0].equals("connect")) {
            header = MsgHeaders.CONNECT;
            body = command;
        } else if (command.length() == 1) {
            header = MsgHeaders.GUESS_CHAR;
            body = command;
        } else if (!command.contains(" ")) {
            header = MsgHeaders.GUESS_WORD;
            body = command;
        }
    }

    MsgHeaders getHeader() {
        return header;
    }

    String getBody() {
        return body;
    }
}