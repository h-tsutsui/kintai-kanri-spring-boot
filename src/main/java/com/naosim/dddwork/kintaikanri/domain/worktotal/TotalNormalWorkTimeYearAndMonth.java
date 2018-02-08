package com.naosim.dddwork.kintaikanri.domain.worktotal;


import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalNormalWorkMinutes;
import lombok.Getter;


public class TotalNormalWorkTimeYearAndMonth {


    public TotalNormalWorkTimeYearAndMonth(TotalNormalWorkMinutes totalNormalWorkMinutes) {
        value = getTotalNormalWorkMinutes(totalNormalWorkMinutes);
    }

    @Getter
    private final Integer value;

    private int getTotalNormalWorkMinutes(TotalNormalWorkMinutes totalNormalWorkMinutes) {
        int totalWorkMinutes = totalNormalWorkMinutes.getTotalWorkMinutesMap().values().stream().mapToInt(x -> x).sum();

        return totalWorkMinutes;
    }
}
