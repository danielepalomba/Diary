package org.Diary.entity.manager;

import org.Diary.entity.Diary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class DiaryManager implements Serializable {
    private static final long serialVersionUID = 555555555L;

    public static ArrayList<Diary> diaries = new ArrayList<>();

    public DiaryManager() {
    }

    public static ArrayList<Diary> getDiaries() {
        return diaries;
    }

    public static void setDiaries(ArrayList<Diary> diaries) {
        DiaryManager.diaries = diaries;
    }

    public static boolean addDiary(Diary diary) {
        String name = diary.getName();
        Optional<Diary> optional = getDiaries().stream().filter(d->d.getName().equals(name)).findAny();
        if(optional.isEmpty()){
            getDiaries().add(diary);
            return true;
        }else{
            return false;
        }
    }

    public static void removeDiary(Diary diary) {
        getDiaries().remove(diary);
    }

    public static Diary retrieveDiary(String name) {
        return getDiaries().stream().filter(d -> d.getName().trim().equals(name.trim())).findAny().get();
    }

    public static ArrayList<Diary> retrieveAll(){
        return getDiaries();
    }

}
