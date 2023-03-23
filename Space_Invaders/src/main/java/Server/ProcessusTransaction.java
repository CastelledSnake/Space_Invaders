package Server;

import Game.Game;

import java.io.IOException;
import java.net.Socket;

/**
 * Processus de Transaction (anciennement ServeurSpecifique)
 */
class ProcessusTransaction extends Thread {

    private Socket clientSocket;
    private ServeurTCP monServeurTCP;

    private Game game;

    public  ProcessusTransaction(Socket uneSocket, ServeurTCP unServeur, Game unGame) {
        super("ServeurThread");
        clientSocket = uneSocket;
        System.out.println("[ProcessusTransaction] CLIENT : " + clientSocket);
        monServeurTCP = unServeur;
        game=unGame;
    }

    public void run() {
        try {
            monServeurTCP.getProtocole().execute(monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream(), game);
            System.out.println("Processus transaction fait");
        } catch (IOException e) {
            System.err.println("[ProcessusTransaction] Exception : " + e );
            e.printStackTrace();
        }
    }
}