public class Notebook {
    private int cost;
    private int ram;
    private String name;

    public Notebook() {
        this.cost = 0;
        this.ram = 0;
        this.name = null;
    }

    public Notebook(int cost, int ram, String name) {
        this.cost = cost;
        this.ram = ram;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public int getRam() {
        return ram;
    }

    public String getName() {
        return name;
    }

}
