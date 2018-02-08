package com.naosim.dddwork.kintaikanri.service

import com.naosim.dddwork.kintaikanri.api.form.WorkTimeInputForm
import com.naosim.dddwork.kintaikanri.api.form.WorkTimeTotalForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
class WorkTimeServiceSpec extends Specification {

    @Autowired
    WorkTimeService workTimeService;

    def 勤務時間入力処理_正常パターン_入力() {
        setup:


        when:
        String[] args = ["input", "20170101", "0900", "1800"]
        def workTimeInputForm = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString())
        def result = workTimeService.workTimeInput(workTimeInputForm.getValueObject())

        then:
        File dataFile = new File("data.csv")
        dataFile.exists()
    }

    def 勤務時間入力処理_正常パターン_合計() {
        setup:


        when:
        String[] args = ["total", "201701"]
        def workTimeTotalForm = new WorkTimeTotalForm(args[1])
        def workTimeTotal = workTimeService.workTimeTotal(workTimeTotalForm.getValueObject())

        then:
        assert 8 == workTimeTotal.getTotalNormalWorkTimeYearAndMonth().getValue() / 60 + workTimeTotal.getTotalNormalWorkTimeYearAndMonth().getValue() % 60
        assert 0 == workTimeTotal.getTotalOverWorkTimeYearAndMonth().getValue() / 60 + workTimeTotal.getTotalOverWorkTimeYearAndMonth().getValue()
    }

    def cleanupSpec() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
