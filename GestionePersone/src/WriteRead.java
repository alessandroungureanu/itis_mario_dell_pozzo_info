
import java.io.IOException;
import java.util.ArrayList;

public interface WriteRead {

    //una interfacia e una classe che al suo interno contiene solo metodi astratti
    // la classe astrata puo contenere un metodo astratto o piu al suo interno

    
    public ArrayList<Person> read(String FileName) throws IOException;
    public void write (ArrayList<Person> people, String FileName) throws IOException;

}
