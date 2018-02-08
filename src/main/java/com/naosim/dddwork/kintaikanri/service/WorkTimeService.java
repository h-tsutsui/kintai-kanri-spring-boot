package com.naosim.dddwork.kintaikanri.service;


import com.naosim.dddwork.kintaikanri.datasource.WorkTimeInputRepositoryFile;
import com.naosim.dddwork.kintaikanri.datasource.WorkTimeTotalRepositoryFile;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeInputRepository;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeMinutes;
import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalWorkTimeYearAndMonth;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkDateAndTimeTotal;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkTimeTotal;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkTimeTotalRepository;
import org.springframework.stereotype.Component;

@Component
public class WorkTimeService {
    //WorkTimeRepository workTimeRepository;

    private WorkTimeInputRepository workTimeInputRepository;
    private WorkTimeTotalRepository workTimeTotalRepository;


    /**
     * 勤怠入力処理
     *
     * @param workDateAndTime
     */
    public void workTimeInput(WorkDateAndTime workDateAndTime) {
        WorkTimeMinutes workTimeMinutes = new WorkTimeMinutes(workDateAndTime);
        workTimeInputRepository = new WorkTimeInputRepositoryFile();
        workTimeInputRepository.registerWork_time(workDateAndTime, workTimeMinutes);
    }

    /**
     * 勤怠合計時間表示
     *
     * @param workDateAndTimeTotal
     * @return
     */
    public TotalWorkTimeYearAndMonth workTimeTotal(WorkDateAndTimeTotal workDateAndTimeTotal) {
        workTimeTotalRepository = new WorkTimeTotalRepositoryFile();
        WorkTimeTotal workTimeTotal = workTimeTotalRepository.doWorktimeTaskExecute(workDateAndTimeTotal);

        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth = new TotalWorkTimeYearAndMonth(workTimeTotal.getTotalNormalWorkMinutes(), workTimeTotal.getTotalOverWorkMinutes());

        return totalWorkTimeYearAndMonth;
    }
}
