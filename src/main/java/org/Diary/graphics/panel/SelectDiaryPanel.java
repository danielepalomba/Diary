package org.Diary.graphics.panel;

import org.Diary.entity.Diary;
import org.Diary.graphics.CardPanelEnum;
import org.Diary.graphics.frame.DiaryManagerFrame;
import org.Diary.util.DateFormatter;
import javax.swing.*;
import java.awt.*;


public class SelectDiaryPanel extends JPanel {

    private JButton openDiaryButton;

    public SelectDiaryPanel(Diary diary) {
        init(diary);
        repaint();
        revalidate();
    }

    private void init(Diary diary) {
        JLabel name = new JLabel();
        JLabel date = new JLabel();
        this.openDiaryButton = new JButton("Open");

        name.setText(diary.getName());
        date.setText(STR."Created: \{DateFormatter.getFormattedDate(diary.getDate())}");

        setLayout(new GridLayout(1, 5));
        setActionListener(diary);

        add(name);
        add(date);
        add(openDiaryButton);
    }

    private void setActionListener(Diary diary) {
        openDiaryButton.addActionListener(e -> {
            ReportsManagerPanel reportsManagerPanel = new ReportsManagerPanel(diary);
            DiaryManagerFrame.cardsPanel.add(reportsManagerPanel, CardPanelEnum.DIARY_REPORTS_PANEL.getValore());
            DiaryManagerFrame.cardLayout.show(DiaryManagerFrame.cardsPanel, CardPanelEnum.DIARY_REPORTS_PANEL.getValore());
        });
    }
}
