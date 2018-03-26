package com.naosim.dddwork.kintaikanri.service;


import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeInputRepository;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeMinutes;
import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalWorkTimeYearAndMonth;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkDateAndTimeTotal;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkTimeTotal;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkTimeTotalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WorkTimeService {


    @Autowired
    private WorkTimeInputRepository workTimeInputRepository;

    @Autowired
    private WorkTimeTotalRepository workTimeTotalRepository;


    /**
     * 勤怠入力処理
     *
     * @param workDateAndTime
     */
    public void workTimeInput(WorkDateAndTime workDateAndTime) {
        WorkTimeMinutes workTimeMinutes = new WorkTimeMinutes(workDateAndTime);
        workTimeInputRepository.registerWork_time(workDateAndTime, workTimeMinutes);
    }

    /**
     * 勤怠合計時間表示
     *
     * @param workDateAndTimeTotal
     * @return
     */
    public TotalWorkTimeYearAndMonth workTimeTotal(WorkDateAndTimeTotal workDateAndTimeTotal) {

        log.debug(">>>>> workTimeTotal start >>>>>>");
        WorkTimeTotal workTimeTotal = workTimeTotalRepository.doWorktimeTaskExecute(workDateAndTimeTotal, null);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error(">>>> " + e);
            return null;
        }
        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth = new TotalWorkTimeYearAndMonth(workTimeTotal.getTotalNormalWorkMinutes(), workTimeTotal.getTotalOverWorkMinutes());

        log.debug(">>>>> workTimeTotal end >>>>>>");

        return totalWorkTimeYearAndMonth;
    }

    /**
     * 勤怠合計時間表示(検証用)
     *
     * @param workDateAndTimeTotal
     * @return
     */
    public Integer workTimeTotal2(WorkDateAndTimeTotal workDateAndTimeTotal) {

        log.debug(">>>>> workTimeTotal2 start >>>>>>");
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            log.error(">>>> " + e);
            return 0;
        }

        WorkTimeTotal workTimeTotal = workTimeTotalRepository.doWorktimeTaskExecute(workDateAndTimeTotal, "data2.csv");

        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth = new TotalWorkTimeYearAndMonth(workTimeTotal.getTotalNormalWorkMinutes(), workTimeTotal.getTotalOverWorkMinutes());

        log.debug(">>>>> workTimeTotal2 end >>>>>>");

        return 500;
    }
}
