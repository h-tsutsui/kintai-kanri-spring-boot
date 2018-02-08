package com.naosim.dddwork.kintaikanri.datasource;


import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkDateAndTime;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeInputRepository;
import com.naosim.dddwork.kintaikanri.domain.workdateandtime.WorkTimeMinutes;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component("com.naosim.dddwork.kintaikanri.datasource.WorkTimeInputRepositoryFile")
public class WorkTimeInputRepositoryFile implements WorkTimeInputRepository {
    @Override
    public void registerWork_time(WorkDateAndTime workDateAndTime, WorkTimeMinutes workTimeMinutes) {

        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    workDateAndTime.getWorkDate().getValue(),
                    workDateAndTime.getWorkTimeStart().getValue(),
                    workDateAndTime.getWorkTimeEnd().getValue(),
                    workTimeMinutes.getAllWorkTimeMinutes().getValue(),
                    workTimeMinutes.getOverWorkTimeMinutes().getValue(),
                    workDateAndTime.getWorkTimeNow().getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
