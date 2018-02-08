package com.naosim.dddwork.kintaikanri.domain.worktotal;

import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkDateAndTimeTotal;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkTimeTotal;

/**
 * 勤怠時間合計時間集計処理
 */
public interface WorkTimeTotalRepository {
    public WorkTimeTotal doWorktimeTaskExecute(WorkDateAndTimeTotal workDateAndTimeTotal);
}
