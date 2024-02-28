package org.Diary.secur;

public final class SecureLogin {
    //WARNING: THIS METHOD IS NOT SAFE

    private static final Integer PIN = 1508;

    public static boolean checkSecure(Integer InputPIN) {
        return PIN.equals(InputPIN);
    }
}
