import java.io.*;
import java.util.ArrayList;

public class WriteReadCSV implements WriteRead{

    @Override
    public ArrayList<Person> read(String fileName) throws IOException{
        ArrayList<Person> people = new ArrayList<>();
        BufferedReader br = new BufferedReader (new FileReader(fileName));
        String line = br.readLine();
        while(line != null){
            people.add(new Person(line));
            line = br.readLine();
        }
        br.close();

        return people;
    }

    @Override
    public void write(ArrayList<Person> people, String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);

        for (Person person : people){
            fw.write(person.toLine());
        }

        fw.close();
    }
}
