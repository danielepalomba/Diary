package org.Diary.graphics;

public enum CardPanelEnum {
    DIARY_MANAGER_PANEL("DiaryManagerPanel"),
    DIARY_PANEL("DiaryPanel"),
    DIARY_REPORTS_PANEL("DiaryReportsPanel"),
    REPORT_PANEL("ReportPanel");

    private final String valore;

    private CardPanelEnum(String valore) {
        this.valore = valore;
    }

    public String getValore() {
        return valore;
    }
}
