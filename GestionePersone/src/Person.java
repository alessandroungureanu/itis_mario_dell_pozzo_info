import java.time.LocalDate;

public class Person implements Comparable<Person>{
    private String nome=null;
    private String cognome=null;
    private String citta=null;
    private String telefono=null;
    private LocalDate data;


    public Person(String nome, String cognome, String citta, String telefono, LocalDate data){
        this.nome = nome;
        this.cognome = cognome;
        this.citta = citta;
        this.telefono = telefono;
        this.data = data;
    }

    public Person(String line) {
        String[] parts = new String [3];
        parts = line.split(";");
        this.nome = parts[0];
        this.cognome = parts[1];
        this.citta = parts[2];
        this.telefono = parts[3];
        this.data = LocalDate.parse(parts[4]);
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return this.cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCitta() {
        return this.citta;
    }
    public void setCitta(String citta) {
        this.citta = citta;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public LocalDate getData() {
        return this.data;
    }
    public void setdata(LocalDate data) {
        this.data = data;
    }
    public int compareTo(Person p) {

        int ret = 0;

        ret = this.getCognome().compareTo(p.getCognome());
        if(ret != 0 )
            return ret;

        ret = this.getNome().compareTo(p.getNome());
        if(ret != 0)
            return ret;

        return this.getData().compareTo(p.getData());

    }
    //toString
    public String toString(){
        return "[Person: "
                + "\nNome: " + this.nome
                + "\nCognome: " + this.cognome
                + "\nCitta: "  + this.citta
                + "\ndata: " + this.data
                + "\nTelefono: " + this.telefono + "]";
    }

    public String toLine(){
        return this.nome + ";" + this.cognome + ";" + this.citta + ";" + this.telefono + ";" + this.data ;
    }

    

    public String [] toRow(){
        String [] fields = new String [5];

        fields[0] = this.nome;
        fields[1] = this.cognome;
        fields[2] = this.citta;
        fields[3] = this.telefono;
        fields[4] = this.data.toString(); // oppure posso scriverlo con fields[4] = this.data+"";

        return fields;
    }
    public static void main(String[] args) {

    }


}
