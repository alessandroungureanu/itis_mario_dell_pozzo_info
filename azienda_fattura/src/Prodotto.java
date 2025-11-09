public class Prodotto {
    private String name;
    private double prezzo;
    private int id;

    public Prodotto(String name, double prezzo, int id) {
        this.name = name;
        this.prezzo = prezzo;
        this.id = id;
    }

    public Prodotto(String line) {
        String[] data = line.split(";");
        this.name = data[0];
        this.prezzo = Double.parseDouble(data[1]);
        this.id = Integer.parseInt(data[2]);
    }

    public String getName() { return name; }
    public double getPrezzo() { return prezzo; }
    public int getId() { return id; }

    public String[] toRow() {
        return new String[]{String.valueOf(id), name, String.valueOf(prezzo)};
    }

    public String toLine() {
        return name + ";" + prezzo + ";" + id;
    }

    @Override
    public String toString() {
        return name + " (" + prezzo + "€)";
    }
}
