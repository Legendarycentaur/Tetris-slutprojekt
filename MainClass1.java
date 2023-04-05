import java.awt.Color;
import java.awt.Transparency;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass1{
    public static void main(String[] args) {
        JFrame dialogruta = new JFrame();
        dialogruta.setSize(300, 200);
        dialogruta.setAlwaysOnTop(true);
        dialogruta.setBackground(Color.black);
        dialogruta.setVisible(true);
        dialogruta.add("you lost");
    }

}
