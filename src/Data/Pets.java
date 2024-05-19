package Data;

import java.util.ArrayList;

public abstract class Pets extends FriendOfMan{

    public Pets() {
    }
    public void display() {
        System.out.println("Это класс: Домашние животные");
    }
    public abstract void setInstructions(ArrayList<String> instructions);
}
