package com.naosim.dddwork.kintaikanri.api.form;

import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkDateAndTimeTotal;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkTotalYeaAndMonth;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 合計時間計算入力オブジェクト
 */
@RequiredArgsConstructor
public class WorkTimeTotalForm implements FormToValueObject<WorkDateAndTimeTotal> {

    @Getter
    @NotBlank
    private final String yearMonth;

    @Override
    public WorkDateAndTimeTotal getValueObject() {
        return new WorkDateAndTimeTotal(new WorkTotalYeaAndMonth(yearMonth));
    }
}
