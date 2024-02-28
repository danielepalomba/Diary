package org.Diary.graphics.panel;

import org.Diary.entity.Diary;
import org.Diary.entity.Report;
import org.Diary.graphics.CardPanelEnum;
import org.Diary.graphics.frame.DiaryManagerFrame;
import org.Diary.graphics.frame.InputReportFrame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReportsManagerPanel extends JPanel {

    private JPanel container;


    public ReportsManagerPanel(Diary diary) {
        init(diary);
        ScheduledExecutorService reloadScheduler = Executors.newSingleThreadScheduledExecutor();
        reloadScheduler.scheduleAtFixedRate(() -> reload(diary), 0, 2, TimeUnit.SECONDS);
    }

    private void init(Diary diary) {
        JButton goBack = new JButton("Go Back");
        JButton addNewReport = new JButton("Add");
        JButton removeReport = new JButton("Remove");

        setLayout(new BorderLayout());
        container = new JPanel(new GridLayout(30, 1));
        ArrayList<Report> reports = diary.getReports();

        for (Report report : reports) {
            container.add(new SelectReportPanel(diary, report));
        }

        goBack.addActionListener(e -> DiaryManagerFrame.cardLayout.show(DiaryManagerFrame.cardsPanel, CardPanelEnum.DIARY_MANAGER_PANEL.getValore()));

        addNewReport.addActionListener(e -> SwingUtilities.invokeLater(() -> new InputReportFrame(diary)));

        removeReport.addActionListener(actionEvent -> {
            String name = JOptionPane.showInputDialog(null, "Insert name: ");
            diary.removeReport(diary.retrieveReport(name.trim()));
        });

        JPanel containerButton = new JPanel();
        containerButton.add(goBack);
        containerButton.add(addNewReport);
        containerButton.add(removeReport);

        add(containerButton, BorderLayout.SOUTH);
        add(container, BorderLayout.CENTER);
    }

    private void reload(Diary diary) {
        Arrays.stream(container.getComponents())
                .filter(c -> c instanceof SelectReportPanel)
                .forEach(container::remove);

        ArrayList<Report> reports = diary.getReports();
        for (Report report : reports) {
            container.add(new SelectReportPanel(diary, report));
        }

        container.revalidate();
        container.repaint();
    }
}
