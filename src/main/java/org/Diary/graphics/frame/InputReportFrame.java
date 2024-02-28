package org.Diary.graphics.frame;

import org.Diary.entity.Diary;
import org.Diary.entity.Report;
import javax.swing.*;
import java.awt.*;

public class InputReportFrame extends JFrame {

    private JTextArea area;
    private Report report;
    private static final int width = 500;
    private static final int height = 500;

    public InputReportFrame(Diary diary) {
        init(diary);
        setVisible(true);
    }

    private void init(Diary diary) {
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;

        setLocation(x, y);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Input Report");
        setLayout(new BorderLayout());

        JPanel containerButton = new JPanel();

        area = new JTextArea(50, 50);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setText("");
        area.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(area);

        JButton addButton = new JButton("Add");
        JButton closeButton = new JButton("Close");

        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(null, "Insert report's name");
            if (name.isEmpty())
                name = "None";
            report = new Report(name, area.getText());
            diary.addReport(report);
            dispose();
        });

        closeButton.addActionListener(e -> dispose());

        containerButton.add(addButton);
        containerButton.add(closeButton);

        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(containerButton, BorderLayout.SOUTH);
    }
}
