import org.jopendocument.dom.spreadsheet.Cell;
import org.jopendocument.dom.spreadsheet.Row;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WriteReadODS implements WriteRead{
    @Override
    public ArrayList<Person> read(String fileName) throws IOException {

        ArrayList<Person> people = new ArrayList<>();

        SpreadSheet spreadSheet = SpreadSheet.createFromFile(new File(fileName));

        Sheet sheet = spreadSheet.getSheet(0);

        int rows = sheet.getRowCount();

        for (int i = 1;  i < rows; i++){

            //Cell cell = sheet.getCellAt(0,i); // colonna 0 riga i-esima

            String nome = sheet.getCellAt(0,i).toString();
            String cognome = sheet.getCellAt(1,i).toString();
            String citta =  sheet.getCellAt(2,i).toString();
            String telefono =  sheet.getCellAt(3,i).toString();
            LocalDate data = LocalDate.parse(sheet.getCellAt(4,i).getValue().toString());

            people.add(new Person(nome,cognome,citta,telefono,data));

        }


        return people;
    }

    public void write (ArrayList<Person> people, String fileName) throws IOException {

        DefaultTableModel model = new DefaultTableModel();

        String [] cols = { "nome" , "cognome " , "citta" , "telefono " ,  "data"};


        //assegno i nomi a tutte le colonne del modello
        for (String col : cols) {
            model.addColumn(col);
        }

        for(Person person : people){
            model.addRow(person.toRow());
        }

        //creo il foglio di calcolo vuoto e lo popolo passando il modello
        SpreadSheet ss = SpreadSheet.createEmpty(model);

        fileName = "file.ods";
        ss.saveAs(new File(fileName));


    }
}
