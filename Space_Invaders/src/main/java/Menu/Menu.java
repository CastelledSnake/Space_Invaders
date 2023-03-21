package Menu;

import Server.ClientTCP;
import Server.Contexte;
import Server.ProtocoleMultiJoueur;
import Server.ServeurTCP;

import java.beans.PropertyChangeSupport;

public class Menu {
    private PropertyChangeSupport pcSupport;
    private int AlienTirSelector_1J=1;
    private int PlayerTirSelector_1J=1;
    private int AlienSelector_1J=1;
    private int ShipSelector_1J=1;
    private int AlienTirSelector_2J=1;
    private int PlayerTirSelector_2J_1=1;
    private int PlayerTirSelector_2J_2=1;
    private int AlienSelector_2J=1;
    private int ShipSelector_2J_1=1;
    private int ShipSelector_2J_2=1;
    private Boolean network=false;
    private Boolean host=false;

    private ClientTCP monClientTCP;
    private ServeurTCP monServeur;
    private int port = 2507;
    public Menu() {
        pcSupport = new PropertyChangeSupport(this);
        monClientTCP =new ClientTCP("192.168.0.27", port);
        monServeur = new ServeurTCP(new Contexte(), new ProtocoleMultiJoueur(), port);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcSupport;
    }

    public void lowerAlienTirSelector_1J(int nb_images) {
        int ancien = getAlienTirSelector_1J();
        if (ancien!=1)
            AlienTirSelector_1J--;
        else
            AlienTirSelector_1J=nb_images;
        pcSupport.firePropertyChange("AlienTirSelector_1J", ancien, getAlienTirSelector_1J());
        if (network && host) {

        }
    }

    public void raiseAlienTirSelector_1J(int nb_images) {
        int ancien = getAlienTirSelector_1J();
        if (ancien!=nb_images)
            AlienTirSelector_1J++;
        else
            AlienTirSelector_1J=1;
        pcSupport.firePropertyChange("AlienTirSelector_1J", ancien, getAlienTirSelector_1J());
        if (network && host) {

        }
    }

    public void lowerPlayerTirSelector_1J(int nb_images) {
        int ancien = getPlayerTirSelector_1J();
        if (ancien!=1)
            PlayerTirSelector_1J--;
        else
            PlayerTirSelector_1J=nb_images;
        pcSupport.firePropertyChange("PlayerTirSelector_1J", ancien, getPlayerTirSelector_1J());
        if (network && host) {

        }
    }

    public void raisePlayerTirSelector_1J(int nb_images) {
        int ancien = getPlayerTirSelector_1J();
        if (ancien!=nb_images)
            PlayerTirSelector_1J++;
        else
            PlayerTirSelector_1J=1;
        pcSupport.firePropertyChange("PlayerTirSelector_1J", ancien, getPlayerTirSelector_1J());
        if (network && host) {

        }
    }

    public void lowerAlienSelector_1J(int nb_images) {
        int ancien = getAlienSelector_1J();
        if (ancien!=1)
            AlienSelector_1J--;
        else
            AlienSelector_1J=nb_images;
        pcSupport.firePropertyChange("AlienSelector_1J", ancien, getAlienSelector_1J());
        if (network && host) {

        }
    }

    public void raiseAlienSelector_1J(int nb_images) {
        int ancien = getAlienSelector_1J();
        if (ancien!=nb_images)
            AlienSelector_1J++;
        else
            AlienSelector_1J=1;
        pcSupport.firePropertyChange("AlienSelector_1J", ancien, getAlienSelector_1J());
        if (network && host) {

        }
    }

    public void lowerShipSelector_1J(int nb_images) {
        int ancien = getShipSelector_1J();
        if (ancien!=1)
            ShipSelector_1J--;
        else
            ShipSelector_1J=nb_images;
        pcSupport.firePropertyChange("ShipSelector_1J", ancien, getShipSelector_1J());
        if (network && host) {

        }
    }

    public void raiseShipSelector_1J(int nb_images) {
        int ancien = getShipSelector_1J();
        if (ancien!=nb_images)
            ShipSelector_1J++;
        else
            ShipSelector_1J=1;
        pcSupport.firePropertyChange("ShipSelector_1J", ancien, getShipSelector_1J());
        if (network && host) {

        }
    }

    public void lowerAlienTirSelector_2J(int nb_images) {
        int ancien = getAlienTirSelector_2J();
        if (ancien!=1)
            AlienTirSelector_2J--;
        else
            AlienTirSelector_2J=nb_images;
        pcSupport.firePropertyChange("AlienTirSelector_2J", ancien, getAlienTirSelector_2J());
        if (network && host) {

        }

    }

    public void raiseAlienTirSelector_2J(int nb_images) {
        int ancien = getAlienTirSelector_2J();
        if (ancien!=nb_images)
            AlienTirSelector_2J++;
        else
            AlienTirSelector_2J=1;
        pcSupport.firePropertyChange("AlienTirSelector_2J", ancien, getAlienTirSelector_2J());
        if (network && host) {

        }
    }

    public void lowerPlayerTirSelector_2J_1(int nb_images) {
        int ancien = getPlayerTirSelector_2J_1();
        if (ancien!=1)
            PlayerTirSelector_2J_1--;
        else
            PlayerTirSelector_2J_1=nb_images;
        pcSupport.firePropertyChange("PlayerTirSelector_2J_1", ancien, getPlayerTirSelector_2J_1());
        if (network && host) {

        }
    }

    public void raisePlayerTirSelector_2J_1(int nb_images) {
        int ancien = getPlayerTirSelector_2J_1();
        if (ancien!=nb_images)
            PlayerTirSelector_2J_1++;
        else
            PlayerTirSelector_2J_1=1;
        pcSupport.firePropertyChange("PlayerTirSelector_2J_1", ancien, getPlayerTirSelector_2J_1());
        if (network && host) {

        }
    }

    public void lowerPlayerTirSelector_2J_2(int nb_images) {
        int ancien = getPlayerTirSelector_2J_2();
        if (ancien!=1)
            PlayerTirSelector_2J_2--;
        else
            PlayerTirSelector_2J_2=nb_images;
        pcSupport.firePropertyChange("PlayerTirSelector_2J_2", ancien, getPlayerTirSelector_2J_2());
    }

    public void raisePlayerTirSelector_2J_2(int nb_images) {
        int ancien = getPlayerTirSelector_2J_2();
        if (ancien!=nb_images)
            PlayerTirSelector_2J_2++;
        else
            PlayerTirSelector_2J_2=1;
        pcSupport.firePropertyChange("PlayerTirSelector_2J_2", ancien, getPlayerTirSelector_2J_2());
    }

    public void lowerAlienSelector_2J(int nb_images) {
        int ancien = getAlienSelector_2J();
        if (ancien!=1)
            AlienSelector_2J--;
        else
            AlienSelector_2J=nb_images;
        pcSupport.firePropertyChange("AlienSelector_2J", ancien, getAlienSelector_2J());
        if (network && host) {

        }
    }

    public void raiseAlienSelector_2J(int nb_images) {
        int ancien = getAlienSelector_2J();
        if (ancien!=nb_images)
            AlienSelector_2J++;
        else
            AlienSelector_2J=1;
        pcSupport.firePropertyChange("AlienSelector_2J", ancien, getAlienSelector_2J());
        if (network && host) {

        }
    }

    public void lowerShipSelector_2J_1(int nb_images) {
        int ancien = getShipSelector_2J_1();
        if (ancien!=1)
            ShipSelector_2J_1--;
        else
            ShipSelector_2J_1=nb_images;
        pcSupport.firePropertyChange("ShipSelector_2J_1", ancien, getShipSelector_2J_1());
        if (network && host) {

        }
    }

    public void raiseShipSelector_2J_1(int nb_images) {
        int ancien = getShipSelector_2J_1();
        if (ancien!=nb_images)
            ShipSelector_2J_1++;
        else
            ShipSelector_2J_1=1;
        pcSupport.firePropertyChange("ShipSelector_2J_1", ancien, getShipSelector_2J_1());
        if (network && host) {

        }
    }

    public void lowerShipSelector_2J_2(int nb_images) {
        int ancien = getShipSelector_2J_2();
        if (ancien!=1)
            ShipSelector_2J_2--;
        else
            ShipSelector_2J_2=nb_images;
        pcSupport.firePropertyChange("ShipSelector_2J_2", ancien, getShipSelector_2J_2());
    }

    public void raiseShipSelector_2J_2(int nb_images) {
        int ancien = getShipSelector_2J_2();
        if (ancien!=nb_images)
            ShipSelector_2J_2++;
        else
            ShipSelector_2J_2=1;
        pcSupport.firePropertyChange("ShipSelector_2J_2", ancien, getShipSelector_2J_2());
    }

    public int getAlienTirSelector_1J() {return AlienTirSelector_1J;}

    public void setAlienTirSelector_1J(int alienTirSelector_1J) {AlienTirSelector_1J = alienTirSelector_1J;}

    public int getPlayerTirSelector_1J() {return PlayerTirSelector_1J;}

    public void setPlayerTirSelector_1J(int playerTirSelector_1J) {PlayerTirSelector_1J = playerTirSelector_1J;}

    public int getAlienSelector_1J() {return AlienSelector_1J;}

    public void setAlienSelector_1J(int alienSelector_1J) {AlienSelector_1J = alienSelector_1J;}

    public int getShipSelector_1J() {return ShipSelector_1J;}

    public void setShipSelector_1J(int shipSelector_1J) {ShipSelector_1J = shipSelector_1J;}

    public int getAlienTirSelector_2J() {return AlienTirSelector_2J;}

    public void setAlienTirSelector_2J(int alienTirSelector_2J) {AlienTirSelector_2J = alienTirSelector_2J;}

    public int getPlayerTirSelector_2J_1() {return PlayerTirSelector_2J_1;}

    public void setPlayerTirSelector_2J_1(int playerTirSelector_2J_1) {PlayerTirSelector_2J_1 = playerTirSelector_2J_1;}

    public int getPlayerTirSelector_2J_2() {return PlayerTirSelector_2J_2;}

    public void setPlayerTirSelector_2J_2(int playerTirSelector_2J_2) {PlayerTirSelector_2J_2 = playerTirSelector_2J_2;}

    public int getAlienSelector_2J() {return AlienSelector_2J;}

    public void setAlienSelector_2J(int alienSelector_2J) {AlienSelector_2J = alienSelector_2J;}

    public int getShipSelector_2J_1() {return ShipSelector_2J_1;}

    public void setShipSelector_2J_1(int shipSelector_2J_1) {ShipSelector_2J_1 = shipSelector_2J_1;}

    public int getShipSelector_2J_2() {return ShipSelector_2J_2;}

    public void setShipSelector_2J_2(int shipSelector_2J_2) {ShipSelector_2J_2 = shipSelector_2J_2;}

    public Boolean getNetwork() {return network;}

    public void setNetwork(Boolean network) {this.network = network;}

    public Boolean getHost() {return host;}

    public void setHost(Boolean host) {this.host = host;}

    public ClientTCP getMonClientTCP() {return monClientTCP;}

    public ServeurTCP getMonServeur() {return monServeur;}
}