package com.naosim.dddwork.kintaikanri.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WorkTimeServiceSpec extends Specification {

    @Autowired
    WorkTimeService workTimeService

    def てすとです() {
        when:
        println(">>>> " + workTimeService)

        then:
        true
    }
}
