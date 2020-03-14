package iasa.po;

public abstract class CPUProcessParent implements Runnable {
    @Override
    public void run() {

    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
