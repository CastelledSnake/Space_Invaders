package Server;

import Game.Game;

import java.io.*;

public class ProtocoleMultiJoueur implements IProtocole {
    public void execute(IContext c, InputStream unInput, OutputStream unOutput, Game game) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        while(true) {
            try {
                if ((inputReq = is.readLine()) != null) {
                    System.out.println("Serveur a reçu " + inputReq);
                    String chaines[] = inputReq.split(" ");
                    System.out.println(" Ordre Recu " + chaines[0]);
                    if (chaines[0].contentEquals("LEFT")) {
                        game.depPlayer2();
                        System.out.println("We have attempted to move player 2");
                        //String outputString = "You are a PC";
                        //os.println(outputString);
                        //os.flush();
                    } else if (chaines[0].contentEquals("RIGHT")) {
                        game.depPlayer2();
                        System.out.println("We have attempted to move player 2");
                        //System.out.println("EUREKA!");
                        //os.println(outputString);
                        //os.flush();
                    } else {
                        os.println("Erreur : Vous ne pouvez acceder au serveur de l'historique de la banque \n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
