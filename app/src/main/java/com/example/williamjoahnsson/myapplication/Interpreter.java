package com.example.williamjoahnsson.myapplication;

class Interpreter {
    private boolean receiving;
    private Controller controller;
    private final PrintToOutput gui;

    Interpreter(PrintToOutput gui) {
        receiving = false;
        this.gui = gui;
    }

    void init() {
        if (receiving) {
            return;
        }
        receiving = true;
        controller = new Controller();
    }

    void handleMsg(String txt) {

            try {
                Command command = new Command(txt);
                switch (command.getHeader()) {
                    case START_GAME:
                        controller.sendStartGame();
                        break;
                    case GUESS_WORD:
                        controller.sendWord(command.getBody());
                        break;
                    case GUESS_CHAR:
                        controller.sendChar(command.getBody().charAt(0));
                        break;
                    case DISCONNECT:
                        controller.disconnect();
                        break;
                    case CONNECT:
                        String[] temp = command.getBody().split(" ");
                        controller.connect(temp[1],
                                Integer.parseInt(temp[2]),
                                gui);
                        break;
                }

            } catch (Exception e) {
                gui.handleMsg("Not a valid command");
            }

    }
}
