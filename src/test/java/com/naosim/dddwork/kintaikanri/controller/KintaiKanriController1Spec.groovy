package com.naosim.dddwork.kintaikanri.controller

import com.naosim.dddwork.kintaikanri.api.form.WorkTimeInputForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class KintaiKanriController1Spec extends Specification {

    @Autowired
    TestRestTemplate testRestTemplate

    @LocalServerPort
    int port

    def 勤怠入力テスト() {
        setup:
        def workTimeInputForm = new WorkTimeInputForm("20180208", "0900", "1800", null)

        when:
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON)

        RequestEntity<?> req = new RequestEntity<>(workTimeInputForm, headers, HttpMethod.POST, "/api/kintai/input")
        HttpEntity<WorkTimeInputForm> entity = new HttpEntity<WorkTimeInputForm>(headers);

        ResponseEntity<String> response = testRestTemplate.exchange(req, )
        println("result >>>> " + response.getStatusCode())

        then:
        true

    }
}
