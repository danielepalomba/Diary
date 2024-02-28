package org.Diary.util;

import org.Diary.entity.Diary;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Export {

    public static void exportDiary(ArrayList<Diary> diaries){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select path");

        int userSelection = fileChooser.showSaveDialog(null);

        if(userSelection == JFileChooser.APPROVE_OPTION){
            try{
                File file = fileChooser.getSelectedFile();
                if(!file.getAbsolutePath().endsWith(".txt")){
                    file = new File(STR."\{file.getAbsolutePath()}.txt");
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(diaries.toString());

                fileOutputStream.close();
                objectOutputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
