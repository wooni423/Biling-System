package com.jiwoong.billingsystem.global.util;

public class TimeConverter {

    public static String convertSecondsToTime(Long seconds){
        // 전체 초 값을 시간, 분, 초로 계산
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }
}
