package gui.net;

import net.Client;
import user.User;

import javax.swing.*;

public class MainPanel extends JPanel {
    User user;
    static ChatPanel chatPanel;
    JScrollPane chatPane;
    public InputPanel inputPanel;
    ButtonPanel buttonPanel;
    MainPanel(User user){
        this.user = user;
    }
    private void setTextNull() {
        inputPanel.textInput.setText(null);
    }
    public static void writeMessage(String s) {
        chatPanel.printMessage(s);
    }
    public static void writeMessage(Message message) {
        chatPanel.printMessage(message.getMsg());
    }
}
