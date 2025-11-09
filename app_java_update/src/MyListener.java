import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("sono stato cliccato");

        //actionevent evento che ha generato la chiamata
        //con l'oggetto e chiamo il metodo getSource() per capire quale sia
        //la fonte che ha chiamato il metodo actionPerformed
        System.out.println(e.getSource());
    }
}
