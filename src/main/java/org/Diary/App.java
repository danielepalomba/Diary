package org.Diary;

import org.Diary.graphics.frame.LoginFrame;

import javax.swing.*;


public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
