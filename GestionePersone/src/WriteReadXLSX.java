import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

// scaricare la libreria Apache POI
public class WriteReadXLSX implements WriteRead {

    private static String[] cols = {"nome", "cognome", "citta", "telefono", "data"};

    @Override
    public ArrayList<Person> read(String fileName) throws IOException {

        ArrayList<Person> people = new ArrayList<>();

        // dichiaro il file su cui voglio leggere
        FileInputStream fileInputStream = new FileInputStream(fileName);

        //crae un workbook passando l'oggetto file
        Workbook workbook = new HSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheet("people");
        // restituisce il numero di rows nel nostro foglio di calcolo
        int rows = sheet.getLastRowNum();
        System.out.println(rows);

        for (int i = 1; i <= rows; i++) {
            // mi posiziono sulla tupla
            Row row = sheet.getRow(i);
            // prendo ogni singola cella
            Cell cell = row.getCell(0);
            //sulla cella leggo il valore

            String nome = cell.getStringCellValue();
            String cognome = row.getCell(1).getStringCellValue();
            String citta = row.getCell(2).getStringCellValue();
            String telefono = row.getCell(3).getStringCellValue();
            LocalDate data = LocalDate.parse(row.getCell(4).getStringCellValue());

            people.add(new Person(nome, cognome, citta, telefono, data));

        }

        return people;
    }

    @Override
    public void write(ArrayList<Person> people, String fileName) throws IOException {

        // workbook è un oggetto contenente il file excel
        Workbook workbook = new HSSFWorkbook();

        // creo il foglio nel workbook
        Sheet sheet = workbook.createSheet("people");

        // intestazioni
        Row row = sheet.createRow(0);
        for (int i = 0; i < cols.length; i++) {
            row.createCell(i).setCellValue(cols[i]);
        }

        // riempimento dati
        int i = 1;
        for (Person person : people) {
            Row r = sheet.createRow(i);

            Cell cell = r.createCell(0);
            cell.setCellValue(person.getNome());

            r.createCell(1).setCellValue(person.getCognome());
            r.createCell(2).setCellValue(person.getCitta());
            r.createCell(3).setCellValue(person.getTelefono());
            r.createCell(4).setCellValue(person.getData().toString());

            i++;
        }

        workbook.write(new FileOutputStream(fileName));
    }
}