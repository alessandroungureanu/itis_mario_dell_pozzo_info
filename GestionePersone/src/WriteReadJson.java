import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public class WriteReadJson implements WriteRead{

    public ArrayList<Person> read(String fileName) throws IOException {

        ArrayList<Person> people = new ArrayList<>();

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();

        Type type = new TypeToken<ArrayList<Person>>(){}.getType();

        people=gson.fromJson(new FileReader(fileName), type);

        return people;
    }
    public void write (ArrayList<Person> people, String fileName) throws IOException {

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();

        String line = gson.toJson(people);

        PrintWriter pw = new PrintWriter(fileName);

        pw.println(line);

        pw.close();
    }

}
