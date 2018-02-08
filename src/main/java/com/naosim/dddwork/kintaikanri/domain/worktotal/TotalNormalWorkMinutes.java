package com.naosim.dddwork.kintaikanri.domain.worktotal;

import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkMinutesPerYMD;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TotalNormalWorkMinutes {

    @Getter
    Map<WorkMinutesPerYMD, Integer> totalWorkMinutesMap;

    public TotalNormalWorkMinutes() {
        totalWorkMinutesMap = new HashMap<WorkMinutesPerYMD, Integer>();
        totalWorkMinutesMap = new HashMap<WorkMinutesPerYMD, Integer>();
    }
}
