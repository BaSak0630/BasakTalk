package gui.net;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter@Setter
public class InputPanel extends JPanel {
    JTextField textInput;
    public InputPanel() {
        textInput = new JTextField();
        setLayout(new GridLayout(1,1));

        textInput = new JTextField();
        Font font = new Font(textInput.getFont().getFamily(), Font.PLAIN, 16);
        textInput.setFont(font);
        add(textInput);
        textInput.setEditable(false);
    }
}