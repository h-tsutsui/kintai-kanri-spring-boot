package com.naosim.dddwork.kintaikanri.domain.worktotal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkTimeTotal {
    @Getter
    private final TotalNormalWorkMinutes totalNormalWorkMinutes;

    @Getter
    private final TotalOverWorkMinutes totalOverWorkMinutes;
}
