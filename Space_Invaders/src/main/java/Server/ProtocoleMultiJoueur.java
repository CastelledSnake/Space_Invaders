package Server;

import java.io.*;

public class ProtocoleMultiJoueur implements IProtocole {
    public void execute(IContext c, InputStream unInput, OutputStream unOutput) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        try {
            if ((inputReq = is.readLine()) != null) {
                System.out.println("Serveur a reçu " + inputReq);
                String chaines[] = inputReq.split(" ");
                System.out.println(" Ordre Recu " + chaines[0]);
                if (chaines[0].contentEquals("OHHH")){
                    String outputString = "You are a PC";
                    System.out.println("EUREKA!");
                    os.println(outputString);
                    os.flush();

                } else {
                    os.println("Erreur : Vous ne pouvez acceder au serveur de l'historique de la banque \n" );
                }
            }
        } catch (Exception e ){
            e.printStackTrace();
        }
    }
}
