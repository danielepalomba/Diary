package org.Diary.graphics.frame;

import org.Diary.entity.manager.DiaryManager;
import org.Diary.graphics.CardPanelEnum;
import org.Diary.graphics.panel.DiaryManagerPanel;
import org.Diary.util.Export;
import org.Diary.util.FileIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DiaryManagerFrame extends JFrame {

    public static CardLayout cardLayout = new CardLayout();
    public static JPanel cardsPanel = new JPanel(cardLayout);
    private static final int width = 800;
    private static final int height = 800;

    public DiaryManagerFrame() {
        init();
        setVisible(true);
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;

        setLocation(x, y);

        setTitle("Diary");
        setLayout(new BorderLayout());

        DiaryManagerPanel managerPanel = new DiaryManagerPanel(DiaryManager.retrieveAll());
        cardsPanel.add(managerPanel, CardPanelEnum.DIARY_MANAGER_PANEL.getValore());

        setMenuBar();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                if(!FileIO.isFileEmpty()){
                    DiaryManager.setDiaries(FileIO.readDiariesFromFile());
                }
                System.out.println("aprendo");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                FileIO.writeDiariesToFile(DiaryManager.getDiaries());
                System.out.println(DiaryManager.getDiaries());
                System.out.println("chiudendo");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Finestra chiusa");
            }
        });

        getContentPane().add(cardsPanel, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    private void setMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Export");
        JMenuItem exportItems = new JMenuItem("Export Diaries");
        menu.add(exportItems);

        menuBar.add(menu);

        exportItems.addActionListener(actionEvent -> {
            if(DiaryManager.getDiaries() != null && !DiaryManager.getDiaries().isEmpty())
                Export.exportDiary(DiaryManager.getDiaries());
            else
                JOptionPane.showMessageDialog(null, "No diaries here!", "Export error", JOptionPane.ERROR_MESSAGE);
        });

        add(menuBar, BorderLayout.NORTH);
    }
}
