package com.naosim.dddwork.kintaikanri.domain.worktotal;

import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkMinutesPerYMD;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TotalOverWorkMinutes {


    @Getter
    Map<WorkMinutesPerYMD, Integer> overWorkMinutesMap;

    public TotalOverWorkMinutes() {
        overWorkMinutesMap = new HashMap<WorkMinutesPerYMD, Integer>();
    }
}
