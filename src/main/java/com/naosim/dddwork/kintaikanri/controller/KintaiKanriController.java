package com.naosim.dddwork.kintaikanri.controller;

import com.naosim.dddwork.kintaikanri.api.form.WorkTimeInputForm;
import com.naosim.dddwork.kintaikanri.api.form.WorkTimeTotalForm;
import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalWorkTimeYearAndMonth;
import com.naosim.dddwork.kintaikanri.service.WorkTimeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KintaiKanriController {

    @Autowired
    WorkTimeService workTimeService;
    private Logger logger = Logger.getLogger(KintaiKanriController.class);

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public String input(@RequestBody WorkTimeInputForm workTimeInputForm) {
        //起動コマンド
        //curl -X POST -H 'Content-Type:application/json' -d '{"date":"20180207","start":"0900","end":"2030"}' http://localhost:8080/api/kintai/input --noproxy localhost
        logger.debug(">>>>>>>>> input >>>>>>>>>> ");

        workTimeService.workTimeInput(workTimeInputForm.getValueObject());
        return "INPUT END";
    }

    @RequestMapping(value = "/total", method = RequestMethod.POST)
    public TotalWorkTimeYearAndMonth total(@RequestBody WorkTimeTotalForm workTimeTotalForm) {
        //起動コマンド
        //curl -X POST -H 'Content-Type:application/json' -d '{"yearMonth":"201802"}' http://localhost:8080/api/kintai/total --noproxy localhost
        logger.debug(">>>>>>>>> total >>>>>>>>>> ");

        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth = workTimeService.workTimeTotal(workTimeTotalForm.getValueObject());

        return totalWorkTimeYearAndMonth;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public TotalWorkTimeYearAndMonth test(@RequestBody WorkTimeTotalForm workTimeTotalForm) {
        //起動コマンド
        //curl -X POST -H 'Content-Type:application/json' -d '{"yearMonth":"201802"}' http://localhost:8080/api/kintai/test --noproxy localhost
        logger.debug(">>>>>>>>> test >>>>>>>>>> ");

        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth = workTimeService.workTimeTotal(workTimeTotalForm.getValueObject());

        return totalWorkTimeYearAndMonth;
    }
}
