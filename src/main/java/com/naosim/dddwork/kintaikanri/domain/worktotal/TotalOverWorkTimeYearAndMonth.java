package com.naosim.dddwork.kintaikanri.domain.worktotal;


import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalOverWorkMinutes;
import lombok.Getter;


public class TotalOverWorkTimeYearAndMonth {


    public TotalOverWorkTimeYearAndMonth(TotalOverWorkMinutes totalOverWorkMinutes) {
        value = getTotalOverWorkMinutes(totalOverWorkMinutes);
    }

    @Getter
    private final Integer value;

    private int getTotalOverWorkMinutes(TotalOverWorkMinutes totalOverWorkMinutes) {
        int totalOverWorkMinutesValue = totalOverWorkMinutes.getOverWorkMinutesMap().values().stream().mapToInt(x -> x).sum();
        return totalOverWorkMinutesValue;
    }
}
