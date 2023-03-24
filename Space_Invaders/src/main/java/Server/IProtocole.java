package Server;


import java.io.InputStream;
import java.io.OutputStream;

public interface IProtocole {
    public void execute(IContext c, InputStream unInput, OutputStream unOutput);
}
