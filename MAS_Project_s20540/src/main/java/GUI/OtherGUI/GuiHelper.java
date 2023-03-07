package GUI.OtherGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class GuiHelper {
    public static void escActionToCloseWindow(JFrame currentJFrame) {
        Action dispatchClosing = new AbstractAction() {
            public void actionPerformed(ActionEvent event) {
                currentJFrame.dispatchEvent(
                        new WindowEvent(currentJFrame, WindowEvent.WINDOW_CLOSING));
            }
        };

        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

        JRootPane rootPane = currentJFrame.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "closeWindow");
        rootPane.getActionMap().put("closeWindow", dispatchClosing);
    }

    public static void closeAppMethod(JFrame currentJFrame){
        JOptionPane.showMessageDialog(currentJFrame, "The booking process has been canceled", "Cancel Booking Process", JOptionPane.INFORMATION_MESSAGE);
        currentJFrame.setVisible(false);
        System.exit(0);
    }

    public static void windowsSettings(JFrame currentJFrame){
        currentJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        currentJFrame.setLocationRelativeTo(null);
        currentJFrame.setTitle(currentJFrame.getClass().getSimpleName());
        currentJFrame.pack();
        currentJFrame.setVisible(true);
    }
}
