package org.Diary.graphics.frame;

import org.Diary.entity.manager.DiaryManager;
import org.Diary.secur.SecureLogin;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField input;
    private JLabel errorMsg;
    private static final int width = 300;
    private static final int height = 120;

    public LoginFrame() {
        init();
        setVisible(true);
    }

    private void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;

        setLocation(x, y);

        input = new JTextField(10);
        JButton submit = new JButton("Submit");
        JButton exit = new JButton("Exit");
        errorMsg = new JLabel("Hey! Quando ci siamo fidanzati?");
        errorMsg.setVisible(false);

        JPanel container = new JPanel();
        container.add(submit);
        container.add(exit);

        submit.addActionListener(e -> {
            if (input.getText().matches("\\d+") && SecureLogin.checkSecure(Integer.parseInt(input.getText()))) {
                DiaryManager dm = new DiaryManager();
                SwingUtilities.invokeLater(DiaryManagerFrame::new);
                dispose();
            } else {
                errorMsg.setVisible(true);
                input.setText("");
            }
        });

        getContentPane().add(input, BorderLayout.CENTER);
        getContentPane().add(container, BorderLayout.SOUTH);
        getContentPane().add(errorMsg, BorderLayout.NORTH);
    }
}
