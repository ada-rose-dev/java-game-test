package main;

//simple tuple class. May need to implement comparison and etc. later on.
public class KeyTuple {

    public static KeyTuple undefined = new KeyTuple("Undefined", 0x0);

    public boolean held;
    public final int code;
    public final String name;

    public KeyTuple(String name, int code) {
        this.name = name;
        this.code = code;
        this.held = false;
    }
}
