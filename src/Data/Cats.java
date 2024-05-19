package Data;

import java.util.ArrayList;
import java.util.Date;

public class Cats extends Pets {

    String name;
    String вirthday;
    ArrayList<String> instructions = new ArrayList<>();

    public Cats(String name, String вirthday, ArrayList<String> instructions) {

        this.name = name;
        this.вirthday = вirthday;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getВirthday() {
        return вirthday;
    }

    public void setВirthday(String вirthday) {
        this.вirthday = вirthday;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Cats{" +
                "name='" + name + '\'' +
                ", вirthday='" + вirthday + '\'' +
                ", instructions=" + instructions +
                '}';
    }
}
