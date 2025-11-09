import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class CatsView  extends JFrame implements ActionListener {
    private ArrayList<Cat> cats = null;
    private JButton booton_delete = null;
    private JButton booton_datail = null;
    private JButton booton_new = null;
    private JButton button_save = null;
    private JButton button_detail = null;

    JTable tabCats = null;

    public CatsView() {
        this.cats = this.readCsv("catsCSV.txt");
        //this.setSize(300,400); funziona lo stesso
        setSize(300, 400);//per creare la finestrella con le dimensioni che gli diamo in pixel
        setLocationRelativeTo(null);//se metto null lo mette al centro dello schermo
        setTitle("CatsView");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//chiudendo la finestra chiude il processo
        setVisible(true);
        initUI();

    }


    private void initUI() {

        /*
        JPanel pnlNorth = new JPanel(new GridLayout(2, 1));
        JPanel pnlView = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.add(pnlNorth, BorderLayout.NORTH);
        */
        tabCats = new JTable();
        tabCats.getTableHeader().setReorderingAllowed(false);
        // table modell
        populate();
        //the table is inserted in the jpanell

        JScrollPane pnlCenter = new JScrollPane(tabCats);

        this.add(pnlCenter, BorderLayout.CENTER);

        //delete boton
        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        booton_delete = new JButton("Delete");
        booton_delete.addActionListener(this);
        pnlSouth.add(booton_delete);

        //new cat
        booton_new = new JButton("New Cat");
        booton_new.addActionListener(this);
        pnlSouth.add(booton_new);

        button_save = new JButton("Save");
        button_save.addActionListener(this);
        pnlSouth.add(button_save);

        button_detail = new JButton("Detail");
        button_detail.addActionListener(this);
        pnlSouth.add(button_detail);



        this.add(pnlSouth, BorderLayout.SOUTH);


    }



    private void populate() {
        /*
        wit the add colum (needs string)  had the slot and whit the add row (needs String[])
         */

        DefaultTableModel model = new DefaultTableModel(){

            // this is a metod of the anonimus class that enable us to se if the colums are editable or not
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        String [] cols = {"NAME","AGE","BREED","SEX","CAT ID"};

        for (String col : cols) {
            model.addColumn(col);
        }

        for(Cat cat : cats) {
            model.addRow(cat.toRow());
        }

        tabCats.setModel(model);

    }



    private void delete(){

        //getselected returns the tupla index if redurns -1 notings retuns YAP YAP YAP YAP
        int selected = this.tabCats.getSelectedRow();

        if(selected<0) {
            System.out.println("DEFICENTE TESTA DI MICHIA FIGLIO DI P°ç#@* NON HA SELEZIONATO NIENTE");
        }

        cats.remove(selected);
        populate();
        //this metod updates the csv file
        //this.writeCSV();
        return;
    }

    private void writeCSV() {

        try {
            PrintWriter pw = new PrintWriter("catsCSV.txt");

            for(Cat cat : cats) {
                pw.println(cat.toLine());
            }

            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == booton_delete) {
            delete();
        }

        if (e.getSource() == booton_new) {
            Cat newCat = new Cat(null, 0, Breed.DEFAULT, SexCat.DEFAULT);
            cats.add(newCat); // add the new cat to the list
            new CatView(newCat,false); // open the CatView window
            populate();
        }

        if (e.getSource() == button_save) {
            this.writeCSV();
        }

        if (e.getSource() == button_detail) {
            detail();
        }

    }

    private void detail() {

        //getselected returns the tupla index if redurns -1 notings retuns YAP YAP YAP YAP
        int selected = this.tabCats.getSelectedRow();

        if(selected<0) {
            System.out.println();
            return;
        }

        Cat selectedCat = cats.get(selected);

        /*
            Cat cat = cats.get(selected)
            new Caview()
         */

        // usa il nuovo costruttore che accetta il flag editable=false
        new CatView(selectedCat,false);
    }


    public ArrayList<Cat> readCsv(String filename){

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            ArrayList<Cat> cats = new ArrayList<>();
            while (line != null) {
                cats.add(new Cat(line));
                line = br.readLine();
            }
            br.close();

            return cats;


        }catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    new CatsView();


                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}

/*
our objective is to create a grid that shows the id,name,breed,sex
of the cats saved in our colection, we need to implement a pannel that has a scroll panell
we use the JScroollPannel and fot the table we use the JTable
need to be center =)
 */
