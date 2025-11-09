import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Fattura extends JFrame implements ActionListener {

    private JButton buttonAdd;
    private JButton buttonCreateReceipt;
    private JTable tabProdotti;
    private DefaultTableModel modelProdotti;

    private DefaultListModel<String> listaFatturaModel;
    private JList<String> listaFattura;

    private ArrayList<Prodotto> prodotti = new ArrayList<>();

    public Fattura() {
        setTitle("Gestione Fattura");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        prodotti = readCSV("prodotti.txt");
        initUI();
    }

    private ArrayList<Prodotto> readCSV(String fileName) {

        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {


                prodotti.add(new Prodotto(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    private void initUI() {
        String[] cols = {"ID", "NOME", "PREZZO"};
        modelProdotti = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Prodotto p : prodotti) {
            modelProdotti.addRow(p.toRow());
        }

        tabProdotti = new JTable(modelProdotti);
        tabProdotti.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollProdotti = new JScrollPane(tabProdotti);
        scrollProdotti.setBorder(BorderFactory.createTitledBorder("Seleziona prodotti"));

        listaFatturaModel = new DefaultListModel<>();
        listaFattura = new JList<>(listaFatturaModel);
        JScrollPane scrollFattura = new JScrollPane(listaFattura);
        scrollFattura.setBorder(BorderFactory.createTitledBorder("Elenco prodotti in fattura"));

        buttonAdd = new JButton("Aggiungi");
        buttonCreateReceipt = new JButton("Crea fattura");

        buttonAdd.addActionListener(this);
        buttonCreateReceipt.addActionListener(this);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.add(buttonAdd);
        panelButtons.add(buttonCreateReceipt);

        setLayout(new BorderLayout());
        add(scrollProdotti, BorderLayout.NORTH);
        add(scrollFattura, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            int selected = tabProdotti.getSelectedRow();
            if (selected != -1) {
                String nome = modelProdotti.getValueAt(selected, 1).toString();
                listaFatturaModel.addElement(nome);
            }
        }

        if (e.getSource() == buttonCreateReceipt) {
            if (listaFatturaModel.isEmpty()) {
                System.out.println("Nessun prodotto aggiunto alla fattura.");
                return;
            }

            String fattura = "Fattura creata con i seguenti prodotti:\n";
            double totale = 0;

            for (int i = 0; i < listaFatturaModel.size(); i++) {
                String nome = listaFatturaModel.get(i);
                for (Prodotto p : prodotti) {
                    if (p.getName().equals(nome)) {
                        fattura += "- " + p.getName() + " (" + p.getPrezzo() + " €)\n";
                        totale += p.getPrezzo();
                    }
                }
            }

            fattura += "\nTotale: " + String.format("%.2f", totale) + " €";
            System.out.println(fattura);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Fattura().setVisible(true));
    }
}
