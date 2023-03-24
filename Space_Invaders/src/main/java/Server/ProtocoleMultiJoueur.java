package Server;

import Game.Game;

import java.io.*;

public class ProtocoleMultiJoueur implements IProtocole {
    public void execute(IContext c, InputStream unInput, OutputStream unOutput) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        while (true) {
            try {
                if ((inputReq = is.readLine()) != null) {
                    System.out.println("Ordre reçu "+inputReq);
                    String chaines[] = inputReq.split(" ");
                    if (chaines[0].contentEquals("RIGHT")) {
                        String outputString = "You just hit RIGHT";
                        os.println(outputString);
                        os.flush();
                    }
                    if (chaines[0].contentEquals("LEFT")) {
                        String outputString = "You just hit LEFT";
                        os.println(outputString);
                        os.flush();
                    }
                }
            } catch (Exception e) {
                System.out.println(" Pb d'exception ");
            }
        }
    }
}
