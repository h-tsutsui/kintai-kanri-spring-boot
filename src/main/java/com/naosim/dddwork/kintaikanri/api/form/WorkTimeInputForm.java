package com.naosim.dddwork.kintaikanri.api.form;

import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkDate;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeEnd;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeNow;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeStart;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * 勤怠入力入力オブジェクト
 */
@RequiredArgsConstructor
public class WorkTimeInputForm implements FormToValueObject<WorkDateAndTime> {

    @Getter
    @NotBlank
    private final String date;

    @Getter
    @NotBlank
    private final String start;

    @Getter
    @NotBlank
    private final String end;

    @Getter
    private final String now;

    @Override
    public WorkDateAndTime getValueObject() {
        if (now != null) {
            return new WorkDateAndTime(new WorkDate(date), new WorkTimeStart(start), new WorkTimeEnd(end), new WorkTimeNow(now));
        } else {
            return new WorkDateAndTime(new WorkDate(date), new WorkTimeStart(start), new WorkTimeEnd(end), new WorkTimeNow(LocalDateTime.now().toString()));
        }

    }
}
