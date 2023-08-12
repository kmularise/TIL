package defaultMethod;

import java.io.ObjectInput;
import java.io.Serializable;

public class MultiImplemention extends classC implements Serializable
{
    public void hello() {
        System.out.println("own method");
    }


}
