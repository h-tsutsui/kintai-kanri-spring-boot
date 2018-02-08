package com.naosim.dddwork.kintaikanri.domain.worktotal;

import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalNormalWorkMinutes;
import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalNormalWorkTimeYearAndMonth;
import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalOverWorkMinutes;
import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalOverWorkTimeYearAndMonth;
import lombok.Getter;

/**
 * 勤務時間合計、残業時間合計
 */
public class TotalWorkTimeYearAndMonth {

    private final TotalNormalWorkMinutes totalNormalWorkMinutes;
    private final TotalOverWorkMinutes totalOverWorkMinutes;


    @Getter
    private final TotalNormalWorkTimeYearAndMonth totalNormalWorkTimeYearAndMonth;

    @Getter
    private final TotalOverWorkTimeYearAndMonth totalOverWorkTimeYearAndMonth;

    public TotalWorkTimeYearAndMonth(TotalNormalWorkMinutes totalNormalWorkMinutes, TotalOverWorkMinutes totalOverWorkMinutes) {
        this.totalNormalWorkMinutes = totalNormalWorkMinutes;
        this.totalOverWorkMinutes = totalOverWorkMinutes;
        this.totalNormalWorkTimeYearAndMonth = new TotalNormalWorkTimeYearAndMonth(this.totalNormalWorkMinutes);
        this.totalOverWorkTimeYearAndMonth = new TotalOverWorkTimeYearAndMonth(this.totalOverWorkMinutes);
    }


}
