import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.text.Document;
import javax.swing.text.html.parser.Element;

import com.google.gson.*;
import nu.xom.Builder;
import nu.xom.ParsingException;
import org.w3c.dom.Node;

import java.io.FileReader;
import java.io.*;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;


public class People {
    private ArrayList<Person> people = null;
    private final String JSON_FILE = "people.json";
    private final String CSV_FILE = "people.csv";
    private final String XML_FILE = "people.xml";

    private String[] tags = {"person", "nome", "cognome", "citta", "telefono", "data"};

    public People() {
        this.people = new ArrayList<>();
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public ArrayList<Person> getPeople() {
        return this.people;
    }

    public void add(Person p) {
        this.people.add(p);
    }

    public void listForCity(String city) {
        for (Person p : people) {
            if (p.getCitta().equals(city)) {
                System.out.println(p);
            }
        }
    }

    public void listForDate(LocalDate dataNascita) {
        for (Person p : people) {
            if (p.getData().isEqual(dataNascita)) {
                System.out.println(p);
            }
        }

    }

    public void sort() {
        for (int i = 0; i < people.size() - 1; i++) {
            for (int j = i + 1; j < people.size(); j++) {
                if (people.get(i).compareTo(people.get(j)) > 0) {
                    swap(i, j);

                }
            }
        }
    }


    public void swap(int i, int j) {
        Person tmp = people.get(i);
        people.set(i, people.get(j));
        people.set(j, tmp);
    }


    public void scriviJson() throws FileNotFoundException {

        /*Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();

        String line = gson.toJson(people);

        PrintWriter pw = new PrintWriter(JSON_FILE);

        pw.println(line);

        pw.close();*/

    }

    public void leggiJson() throws FileNotFoundException {
        /*Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();

        Type type = new TypeToken<ArrayList<Person>>() {}.getType();

        this.people = gson.fromJson(new FileReader(JSON_FILE), type);*/

    }

    public void scriviCsv() throws IOException {
        /*PrintWriter pw = new PrintWriter(CSV_FILE);

        for (Person person : people) {
            pw.println(person.toLine());
        }

        pw.close();*/
    }

    public void leggiCsv() throws IOException {
        /*BufferedReader br = new BufferedReader(new FileReader(CSV_FILE));
        String line = br.readLine();
        while (line != null) {
            people.add(new Person(line));
            line = br.readLine();
        }
        br.close();*/

    }

    public void scriviXml() throws FileNotFoundException {
        //root element
        /*Element tagPeople = new Element("people");

        for (Person person : people) {
            Element tagPerson = new Element(tags[0]);

            Element tagNome = new Element(tags[1]);
            tagNome.appendChild(person.getNome());
            tagPerson.appendChild(tagNome);
            Element tagCognome = new Element(tags[2]);
            tagCognome.appendChild(person.getCognome());
            tagPerson.appendChild(tagCognome);
            Element tagCitta = new Element(tags[3]);
            tagCitta.appendChild(person.getCitta());
            tagPerson.appendChild(tagCitta);
            Element tagTelefono = new Element(tags[4]);
            tagTelefono.appendChild(person.getTelefono());
            tagPerson.appendChild(tagTelefono);
            Element tagData = new Element(tags[5]);
            tagData.appendChild(person.getDataNascita().toString());
            tagPerson.appendChild(tagData);

            tagPeople.appendChild(tagPerson);
        }

        Document document = new Document(tagPeople);

        String xmlString = document.toXML();

        PrintWriter pr = new PrintWriter(XML_FILE);

        pr.println(xmlString);
        pr.close();*/

    }

    public void leggiXML() throws IOException, ParsingException {

        /*FileInputStream fis = new FileInputStream(XML_FILE);

        Builder builder = new Builder();

        Document document = builder.build(fis);

        Element tagPeople = document.getRootElement(); //dal document prendo root element
        int numChildren = tagPeople.get(); //verifico quanti children ha

        for (int i = 0; i < numChildren; i++) {

            Node nodePerson = tagPeople.getChild(i); // scorro ogni tag Person

            //Node nodeNome = nodePerson.getChild(0);
            //String nome = nodeName.getValue();

            String Nome = nodePerson.getChild(0).getValue();

            String Cognome = nodePerson.getChild(1).getValue();
            String Citta = nodePerson.getChild(2).getValue();
            String Telefono = nodePerson.getChild(3).getValue();
            LocalDate Data = LocalDate.parse(nodePerson.getChild(4).getValue());

            Person person = new Person(Nome, Cognome, Citta, Telefono, Data);
            people.add(person);


        }*/

    }


    @Override
    public String toString() {
        return "[People: "
                + "\nPeople: " + this.people + "]";
    }


    //main

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        System.out.println(gson.toJson(date));

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Paola", "Cardone", "Cuneo","01010101010", LocalDate.of(2008, 10, 3)));
        people.add(new Person("Kevin", "Hala", "Cuneo","01010101010", LocalDate.of(2008, 10, 19)));
        people.add(new Person("Matteo", "Petrisor", "Cuneo","01010101010", LocalDate.of(2008, 8, 6)));
        System.out.println(people);

       /* try{
            people.scriviJson();
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            people.leggiJson();
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            people.scriviCsv();
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            people.scriviXml();
        }catch(IOException e){
            e.printStackTrace();
        }
        */

        try{
            WriteReadXLSX writeReadXLSX = new WriteReadXLSX();


            writeReadXLSX.write(people, "people.xls");
            writeReadXLSX.read("people.xls");

        }catch (Exception e){
            e.printStackTrace();
        }

        /*
        |=================|
        |A                |
        |=================|<=================
        |+A()             |                 =
        |=================|<=============+ create()A;
         */

    }
}
