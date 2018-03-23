package com.naosim.dddwork.kintaikanri.controller;

import com.naosim.dddwork.kintaikanri.api.form.WorkTimeInputForm;
import com.naosim.dddwork.kintaikanri.api.form.WorkTimeTotalForm;
import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalWorkTimeYearAndMonth;
import com.naosim.dddwork.kintaikanri.service.WorkTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@Slf4j
public class KintaiKanriController {

    @Autowired
    WorkTimeService workTimeService;

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public String input(@RequestBody WorkTimeInputForm workTimeInputForm) {
        //起動コマンド
        //curl -X POST -H 'Content-Type:application/json' -d '{"date":"20180207","start":"0900","end":"2030"}' http://localhost:8080/api/kintai/input --noproxy localhost
        log.debug(">>>>>>>>> input >>>>>>>>>> ");

        workTimeService.workTimeInput(workTimeInputForm.getValueObject());
        return "INPUT END";
    }

    @RequestMapping(value = "/total", method = RequestMethod.POST)
    public TotalWorkTimeYearAndMonth total(@RequestBody WorkTimeTotalForm workTimeTotalForm) {
        //起動コマンド
        //curl -X POST -H 'Content-Type:application/json' -d '{"yearMonth":"201802"}' http://localhost:8080/api/kintai/total --noproxy localhost
        log.debug(">>>>>>>>> total >>>>>>>>>> ");

        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth = workTimeService.workTimeTotal(workTimeTotalForm.getValueObject());

        return totalWorkTimeYearAndMonth;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public TotalWorkTimeYearAndMonth test(@RequestBody WorkTimeTotalForm workTimeTotalForm) {
        //起動コマンド
        //curl -X POST -H 'Content-Type:application/json' -d '{"yearMonth":"201802"}' http://localhost:8080/api/kintai/test --noproxy localhost
        long start = System.currentTimeMillis();
        log.debug(">>>>>>>>> test >>>>>>>>>> ");

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<CompletableFuture<TotalWorkTimeYearAndMonth>> completableFutureList = Arrays.asList(
                CompletableFuture.supplyAsync(() -> workTimeService.workTimeTotal(workTimeTotalForm.getValueObject()), executorService),
                CompletableFuture.supplyAsync(() -> workTimeService.workTimeTotal2(workTimeTotalForm.getValueObject()), executorService)
        );

        CompletableFuture.allOf(
                completableFutureList.toArray(new CompletableFuture[completableFutureList.size()])
        ).join();

        completableFutureList.forEach(
                s -> {
                    try {
                        log.debug(" >>> " + s.get().getTotalNormalWorkTimeYearAndMonth().getValue());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );

        //とりあえず、合計してみる。
        Integer sum = completableFutureList.stream()
                .mapToInt(s -> {
                    try {
                        return s.get().getTotalNormalWorkTimeYearAndMonth().getValue();
                    } catch (InterruptedException e) {
                        log.error("Error : " + e);
                        return 0;
                    } catch (ExecutionException e) {
                        log.error("Error : " + e);
                        return 0;
                    }
                })
                .sum();

        long end = System.currentTimeMillis();

        log.debug("sum : " + sum + " time : " + (end - start) + "ms");


//        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth =
//                workTimeService.workTimeTotal(workTimeTotalForm.getValueObject());
        return null;

    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public TotalWorkTimeYearAndMonth test2(@RequestBody WorkTimeTotalForm workTimeTotalForm) {
        //起動コマンド
        //curl -X POST -H 'Content-Type:application/json' -d '{"yearMonth":"201802"}' http://localhost:8080/api/kintai/test --noproxy localhost
        long start = System.currentTimeMillis();
        log.debug(">>>>>>>>> test2 >>>>>>>>>> ");

        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth =
                workTimeService.workTimeTotal(workTimeTotalForm.getValueObject());

        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth2 =
                workTimeService.workTimeTotal2(workTimeTotalForm.getValueObject());

        long end = System.currentTimeMillis();
        List<TotalWorkTimeYearAndMonth> totalWorkTimeYearAndMonthList = new ArrayList<TotalWorkTimeYearAndMonth>();
        totalWorkTimeYearAndMonthList.add(totalWorkTimeYearAndMonth);
        totalWorkTimeYearAndMonthList.add(totalWorkTimeYearAndMonth2);
        Integer sum = totalWorkTimeYearAndMonthList.stream().mapToInt(
                s -> s.getTotalNormalWorkTimeYearAndMonth().getValue()
        ).sum();

        log.debug("sum : " + sum + " time : " + (end - start) + "ms");


//        TotalWorkTimeYearAndMonth totalWorkTimeYearAndMonth =
//                workTimeService.workTimeTotal(workTimeTotalForm.getValueObject());
        return null;

    }
}
