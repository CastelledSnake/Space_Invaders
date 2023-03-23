package Server;

import Game.Game;

import java.io.InputStream;
import java.io.OutputStream;

public interface IProtocole {
    public void execute(IContext c, InputStream unInput, OutputStream unOutput, Game game);
}
