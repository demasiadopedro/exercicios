package meujogo;

import meujogo.Modelo.Fase;

import javax.swing.*;

public class Container extends JFrame {
    public Container(){
        add(new Fase());
        setTitle("Meu Jogo");
        setSize(480,360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }

}
