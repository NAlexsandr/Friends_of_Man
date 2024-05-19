package Service;

import java.io.File;

public class Counter {
    private int count;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    File file = new File("count.txt");

    public Counter() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void add() {
        count++;
    }

}
