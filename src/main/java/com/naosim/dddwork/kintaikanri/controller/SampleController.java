package com.naosim.dddwork.kintaikanri.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @RequestMapping("/sample")
    public String sample() {
        return "これはサンプルです。";
    }
}
