package gui.net;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    public ChatPanel(){
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void printMessage(String s) {
        Graphics g = getGraphics();
        g.drawString(s,50,50);
    }
}