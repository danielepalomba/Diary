package org.Diary.graphics.panel;

import org.Diary.entity.Diary;
import org.Diary.entity.manager.DiaryManager;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class DiaryManagerPanel extends JPanel {

    private JButton addDiary;
    private JButton removeDiary;
    private JButton startButton;
    private JPanel diaryContainer;


    public DiaryManagerPanel(ArrayList<Diary> diaries) {
        init(diaries);
    }

    private void init(ArrayList<Diary> diaries) {
        diaryContainer = new JPanel(new GridLayout(30, 1));
        setLayout(new BorderLayout());
        addDiary = new JButton("Add");
        startButton = new JButton("Start");
        addDiary.setVisible(false);
        removeDiary = new JButton("Remove diary");
        removeDiary.setVisible(false);

        JPanel container = new JPanel();

        container.add(addDiary);
        container.add(startButton);
        container.add(removeDiary);

        addDiary.addActionListener(e -> {
            boolean check;
            do {
                String name = JOptionPane.showInputDialog(null, "Insert Name");
                if(name == null)
                    return;
                else if(name.isEmpty())
                    return;
                Diary diary = new Diary(name);
                check = DiaryManager.addDiary(diary);
                reload(DiaryManager.retrieveAll());
            }while(!check);
        });

        startButton.addActionListener(actionEvent -> {
            ArrayList<Diary> diaries1 = DiaryManager.retrieveAll();
            if(diaries1 == null)
                return;
            else
                reload(diaries1);
            addDiary.setVisible(true);
            removeDiary.setVisible(true);
            startButton.setVisible(false);
        });

        removeDiary.addActionListener(actionEvent -> {
            String id = JOptionPane.showInputDialog(null, "Insert name");
            if(id == null)
                return;
            else if(id.isEmpty())
                return;
            DiaryManager.removeDiary(DiaryManager.retrieveDiary(id));
            reload(DiaryManager.getDiaries());
        });

        add(diaryContainer, BorderLayout.CENTER);
        add(container, BorderLayout.SOUTH);

    }

    private void reload(ArrayList<Diary> diaries) {
        Arrays.stream(diaryContainer.getComponents())
                .filter(c -> c instanceof SelectDiaryPanel)
                .forEach(diaryContainer::remove);

        for (Diary diary : diaries) {
            diaryContainer.add(new SelectDiaryPanel(diary));
        }

        diaryContainer.revalidate();
        diaryContainer.repaint();
    }

}
