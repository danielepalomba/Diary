package org.Diary.util;

import org.Diary.entity.Diary;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileIO {

    private static final String filePath = "savings.dat";

    public static void writeDiariesToFile(ArrayList<Diary> diaries) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(diaries);
            System.out.println(STR."Diaries saved to file: \{filePath}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Diary> readDiariesFromFile() {
        ArrayList<Diary> diaries = null;
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println(STR."File created: \{filePath}");
                return new ArrayList<>();
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                //noinspection unchecked
                diaries = (ArrayList<Diary>) ois.readObject();
                System.out.println(STR."Diaries loaded from file: \{filePath}");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return diaries;
    }

    public static boolean isFileEmpty() {
        try {
            long size = Files.size(Path.of(filePath));
            return size == 0;
        } catch (IOException e) {
            return false;
        }
    }
}
