package utils;

public abstract class RunnableMenuOption {
    private String name;

    public RunnableMenuOption(String name) {
        setName(name);
    }
    public String getName() {
        return name;
    };

    public void setName(String name) {
        this.name = name;
    }
    abstract public void run();
}
