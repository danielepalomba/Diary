package org.Diary.graphics.panel;

import org.Diary.entity.Diary;
import org.Diary.entity.Report;
import org.Diary.graphics.CardPanelEnum;
import org.Diary.graphics.frame.DiaryManagerFrame;
import org.Diary.util.DateFormatter;
import javax.swing.*;
import java.awt.*;


public class ReportPanel extends JPanel {

    public ReportPanel(Diary diary, Report report) {
        init(diary, report);
    }

    private void init(Diary diary, Report report) {

        setLayout(new BorderLayout());
        JLabel date = new JLabel(DateFormatter.getFormattedDate(report.getDate()));

        JTextArea area = new JTextArea(50, 100);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setText(report.getText());
        JScrollPane scrollPane = new JScrollPane(area);

        JButton deleteButton = new JButton("Delete");
        JButton goBackButton = new JButton("Go Back");
        JButton returnHomeButton = new JButton("Home");
        JButton modifyReport = new JButton("Modify");
        JButton saveReport = new JButton("Save");
        saveReport.setVisible(false);

        goBackButton.addActionListener(e -> DiaryManagerFrame.cardLayout.show(DiaryManagerFrame.cardsPanel, CardPanelEnum.DIARY_REPORTS_PANEL.getValore()));

        returnHomeButton.addActionListener(e -> DiaryManagerFrame.cardLayout.show(DiaryManagerFrame.cardsPanel, CardPanelEnum.DIARY_MANAGER_PANEL.getValore()));

        deleteButton.addActionListener(e -> {
            diary.removeReport(report);
            DiaryManagerFrame.cardLayout.show(DiaryManagerFrame.cardsPanel, CardPanelEnum.DIARY_MANAGER_PANEL.getValore());
        });

        modifyReport.addActionListener(e->{
            modifyReport.setVisible(false);
            saveReport.setVisible(true);
            area.setEditable(true);
        });

        saveReport.addActionListener(e->{
            report.setText(area.getText());
            area.setText(report.getText());
            area.repaint();
            area.revalidate();
            area.setEditable(false);
            modifyReport.setVisible(true);
            saveReport.setVisible(false);
        });

        JPanel container = new JPanel();
        container.add(deleteButton);
        container.add(goBackButton);
        container.add(returnHomeButton);
        container.add(modifyReport);
        container.add(saveReport);

        add(container, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
