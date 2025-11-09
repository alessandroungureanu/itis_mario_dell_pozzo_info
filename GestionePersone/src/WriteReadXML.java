import nu.xom.Builder;
import org.w3c.dom.Node;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import nu.xom.Element;
import nu.xom.Document;

import java.io.IOException;
import java.io.PrintWriter;

public class WriteReadXML implements WriteRead {
    String[] tags = {
            "person", "name", "surname", "city", "phoneNumber", "birthday"
    };

    /*public ArrayList<Person> read(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);

        Builder builder = new Builder();

        Document document = builder.build(fis);

        Element tagPeople = document.getRootElement(); //dal document prendo root element
        int numChildren = tagPeople.get(); //verifico quanti children ha

        for(int i = 0; i < numChildren; i++){

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


        }
    }*/
    public ArrayList<Person> read(String fileName) throws IOException {

        Element tagPeople = new Element("people");

        ArrayList<Person> people = new ArrayList<>();

        for(Person p : people){
            Element tagPerson = new Element(tags[0]);

            Element tagName = new Element(tags[1]);
            tagName.appendChild(p.getNome());
            tagPerson.appendChild(tagName);

            Element tagSurname = new Element(tags[2]);
            tagSurname.appendChild(p.getCognome());
            tagPerson.appendChild(tagSurname);

            Element tagCity = new Element(tags[3]);
            tagCity.appendChild(p.getCitta());
            tagPerson.appendChild(tagCity);

            Element tagPhoneNumber = new Element(tags[4]);
            tagPhoneNumber.appendChild(p.getTelefono());
            tagPerson.appendChild(tagName);

            Element tagBirthday = new Element(tags[5]);
            tagBirthday.appendChild(String.valueOf(p.getData()));
            tagPerson.appendChild(tagBirthday);

        }

        Document document = new Document(tagPeople);
        String xml = document.toXML();
        PrintWriter pr = new PrintWriter(fileName);
        pr.println(xml);
        pr.close();

        return people;
    }
    public void write (ArrayList<Person> people, String fileName) throws  IOException {
        Element tagPeople = new Element ("people");

        for(Person person : people){
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
            tagData.appendChild(person.getData().toString());
            tagPerson.appendChild(tagData);

            tagPeople.appendChild(tagPerson);
        }

        Document document = new Document(tagPeople);

        String xmlString = document.toXML();

        PrintWriter pr = new PrintWriter(fileName);

        pr.println(xmlString);
        pr.close();

    }

}

