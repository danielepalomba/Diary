package org.Diary.graphics.panel;

import org.Diary.entity.Diary;
import org.Diary.entity.Report;
import org.Diary.graphics.CardPanelEnum;
import org.Diary.graphics.frame.DiaryManagerFrame;
import org.Diary.util.DateFormatter;
import javax.swing.*;
import java.awt.*;


public class SelectReportPanel extends JPanel {

    private JButton openReportButton;

    public SelectReportPanel(Diary diary, Report report) {
        init(diary, report);
    }

    private void init(Diary diary, Report report) {
        JLabel date = new JLabel();
        this.openReportButton = new JButton("Open");
        JLabel name = new JLabel(report.getName());

        date.setText(STR."Written: \{DateFormatter.getFormattedDate(report.getDate())}");

        setLayout(new GridLayout(1, 5));

        setActionListener(diary, report);
        add(date);
        add(name);
        add(openReportButton);
    }

    private void setActionListener(Diary diary, Report report) {
        openReportButton.addActionListener(e -> {
            ReportPanel reportPanel = new ReportPanel(diary, report);
            DiaryManagerFrame.cardsPanel.add(reportPanel, CardPanelEnum.REPORT_PANEL.getValore());
            DiaryManagerFrame.cardLayout.show(DiaryManagerFrame.cardsPanel, CardPanelEnum.REPORT_PANEL.getValore());
        });
    }
}
