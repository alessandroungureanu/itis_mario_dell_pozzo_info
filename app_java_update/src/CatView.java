import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatView extends JDialog implements ActionListener {



    //relay --> copia del gatto passato come parametro
    private Cat cat = null;
    private JTextField txtAge = null;
    private JTextField txtName = null;
    private JTextField txtID = null;
    private JButton btnAdd = null;
    private JComboBox<Breed> cbxBreed = null;
    private SexCat sexCat = SexCat.DEFAULT;
    private JCheckBox cbxMale = null;
    private JCheckBox cbxFemale = null;

    public CatView(Cat cat,boolean modal) {
        this.cat = cat;
        //this.setSize(300,400); funziona lo stesso
        setSize(300,400);//per creare la finestrella con le dimensioni che gli diamo in pixel
        setLocationRelativeTo(null);//se metto null lo mette al centro dello schermo
        setTitle("CatView" );
        initUI();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//chiudendo la finestra chiude il processo
        setModal(true);
        setVisible(true);
        setEdit(modal);
        populate();
        //this empties tht ram from the modal window from the ram
        this.setDefaultCloseOperation(2);

    }

    //posso mettere i metodi privati perche li richiamo solo nel costruttore che è privato
    //initializeUI
    private void initUI() {

        //pnl north segue un layout grid che suddivide in tante righe e colonne lo spazio
        //GridLayout gl = new GridLayout(2,1);
        JPanel pnlNorth = new JPanel(new GridLayout(5,1));

        //pnl name segue il flow layout che posiziona i widget da sinistra verso destra uno di seguito all'altro
        JPanel pnlName = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblName = new JLabel("Name:");
        //inizia la matriosca e aggiunge la label al pannello name
        pnlName.add(lblName);
        txtName = new JTextField(20);
        //aggiungo il field nome al pannello name
        pnlName.add(txtName);
        //aggiungo al pannello nord il pannello nome che contiene label e fields
        pnlNorth.add(pnlName);

        JPanel pnlID = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblID = new JLabel("Id:"+ cat.getId());
        pnlID.add(lblID);
        txtID = new JTextField(20);
        pnlID.add(txtID);
        txtID.setEditable(false);
        pnlNorth.add(lblID);




        JPanel pnlAge = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblAge = new JLabel("Age:");
        //inizia la matriosca e aggiunge la label al pannello name
        pnlAge.add(lblAge);
        txtAge = new JTextField(3);
        //aggiungo il field nome al pannello name
        pnlAge.add(txtAge);


        JPanel pnlBreed = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblBreed = new JLabel("Breed:");
        pnlBreed.add(lblBreed);
        cbxBreed = new JComboBox<>(Breed.values());
        cbxBreed.setSelectedIndex(-1);
        pnlBreed.add(cbxBreed);


        JPanel pnlSex  = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblMale = new JLabel("Male:");
        cbxMale = new JCheckBox();
        pnlSex.add(lblMale);
        pnlSex.add(cbxMale);

        JLabel lblFemale = new JLabel("Female:");
        cbxFemale = new JCheckBox();
        pnlSex.add(lblFemale);
        pnlSex.add(cbxFemale);

        //aggiungo al pannello nord il pannello nome che contiene label e fields
        pnlNorth.add(pnlAge);
        pnlNorth.add(pnlBreed);
        pnlNorth.add(pnlSex);

        this.add(pnlNorth, BorderLayout.NORTH);

        //JPanel pnlSouth = new JPanel(new GridLayout(1,1));
        //JPanel pnlBottone = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnAdd = new JButton("Save");
        //passo un istanza del myListener al bottone
        btnAdd.addActionListener(this); //passo this perchè l'abbiamo implementato

        //pnlBottone.add(btnAdd);
        pnlSouth.add(btnAdd);
        //pnlSouth.add(pnlBottone);

        //aggiungo pannelli al frame
        this.add(pnlSouth, BorderLayout.SOUTH);
        this.add(pnlNorth, BorderLayout.NORTH);

    }




    //inizializza i valori delle textfield prendendo name e age dall'istanza
    private void populate (){

        //String nome = this.cat.getName();
        //int age = this.cat.getAge();

        //setText è un metodo della classe JTextfield permette di modificare il valore passato
        this.txtName.setText(this.cat.getName());
        this.txtAge.setText(this.cat.getAge()+"");

        /*
         this.txtid.set(this.cat.getID()+"";

         */

    }

    public void save(){
        String name = this.txtName.getText();
        String age =this.txtAge.getText();


        if(name.isEmpty()==true ){
            txtName.setBackground(Color.RED);
            txtName.setForeground(Color.WHITE);
            return;
        }

        if(age.isEmpty() == true ){
            txtAge.setBackground(Color.RED);
            txtAge.setForeground(Color.WHITE);
            return;

        }

        // over loading reading shadowing builders and interfaces andals

        if(age.matches("^[0-9]+$") == false){
            txtAge.setBackground(Color.RED);
            txtAge.setForeground(Color.WHITE);
            return;

        }

        //getSelectedItem --> restituisce -1 nessuna selezione
        //0-n --> se è stato selezionato un item
        if(this.cbxBreed.getSelectedIndex() < 0){
            cbxBreed.setBackground(Color.RED);
            cbxBreed.setForeground(Color.WHITE);
            return;
        }

        if(checkSex() == -1){
            cbxMale.setBackground(Color.RED);
            cbxFemale.setBackground(Color.RED);
            return;
        }
        if(checkSex() == 0){

            this.cat.setSexCat(SexCat.MALE);
        }
        if(checkSex() == 1){
            this.cat.setSexCat(SexCat.FEMALE);
        }


        txtName.setBackground(Color.WHITE);
        txtName.setForeground(Color.BLACK);
        txtAge.setBackground(Color.WHITE);
        txtAge.setForeground(Color.BLACK);
        cbxBreed.setBackground(Color.WHITE);
        cbxBreed.setForeground(Color.BLACK);
        cbxMale.setBackground(Color.WHITE);
        cbxMale.setForeground(Color.BLACK);
        cbxFemale.setBackground(Color.WHITE);
        cbxFemale.setForeground(Color.BLACK);

        this.cat.setName(name);
        this.cat.setAge(Integer.parseInt(age));
        this.cat.setBreed(Breed.valueOf(cbxBreed.getSelectedItem().toString()));

        //checkSex(); the resoult can be ignored
        System.out.println(this.cat);

        //  Aggiunta: salvataggio + apertura CatsView
        //saveToFile();
        /*try {
            new CatsView(CatsView.readCsv("catsCSV.txt"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        dispose();

    }

   /* private void saveToFile(){
        try (PrintWriter pw = new PrintWriter(new FileWriter("catsCSV.txt", true))) {
            pw.println(this.cat.toLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

    public int checkSex(){
        //todo controllo che non siano selezionati o deselezionati entrambi
        //selezionati entrambi
        if(this.cbxMale.isSelected() == true && this.cbxFemale.isSelected() == true)return -1;
        //non selezionato nessuno dei due
        if(this.cbxMale.isSelected() == false && this.cbxFemale.isSelected() == false)return -1;
        if(this.cbxMale.isSelected() == true) return 0;
        return 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnAdd) {
            save();
        }
    }

    public void setEdit(boolean edit) {
        if (this.txtName != null) this.txtName.setEditable(edit);
        if (this.txtAge != null) this.txtAge.setEditable(edit);
        if (this.txtID != null) this.txtID.setEditable(false); // ID mai modificabile

        if (this.cbxBreed != null) this.cbxBreed.setEnabled(edit);
        if (this.cbxMale != null) this.cbxMale.setEnabled(edit);
        if (this.cbxFemale != null) this.cbxFemale.setEnabled(edit);

        if (this.btnAdd != null) this.btnAdd.setEnabled(edit);
    }


    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CatView(new Cat("pinco", 5, Breed.DEFAULT, SexCat.DEFAULT,1));
            }
        });*/

    }
}
