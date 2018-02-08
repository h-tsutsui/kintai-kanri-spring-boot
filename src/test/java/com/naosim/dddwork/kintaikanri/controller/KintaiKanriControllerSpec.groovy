package com.naosim.dddwork.kintaikanri.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.naosim.dddwork.kintaikanri.api.form.WorkTimeInputForm
import com.naosim.dddwork.kintaikanri.api.form.WorkTimeTotalForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class KintaiKanriControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper mapper

    def 勤怠入力テスト() {
        setup:
        def workTimeInputForm = new WorkTimeInputForm("20180208", "0900", "1800", null)
        def json = mapper.writeValueAsString(workTimeInputForm)

        when:
        def result = this.mockMvc.perform(post("/api/kintai/input")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isOk())
    }

    def 勤怠合計時間テスト() {
        setup:
        def workTimeTotalForm = new WorkTimeTotalForm("201802")
        def json = mapper.writeValueAsString(workTimeTotalForm)

        when:
        def result = this.mockMvc.perform(post("/api/kintai/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isOk())

    }

    def cleanupSpec() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
