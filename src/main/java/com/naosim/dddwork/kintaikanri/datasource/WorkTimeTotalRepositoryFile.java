package com.naosim.dddwork.kintaikanri.datasource;

import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalNormalWorkMinutes;
import com.naosim.dddwork.kintaikanri.domain.worktotal.TotalOverWorkMinutes;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkDateAndTimeTotal;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkMinutesPerYMD;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkTimeTotal;
import com.naosim.dddwork.kintaikanri.domain.worktotal.WorkTimeTotalRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class WorkTimeTotalRepositoryFile implements WorkTimeTotalRepository {
    @Override
    public WorkTimeTotal doWorktimeTaskExecute(WorkDateAndTimeTotal workDateAndTimeTotal) {
        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            TotalNormalWorkMinutes totalNormalWorkMinutes = new TotalNormalWorkMinutes();
            TotalOverWorkMinutes totalOverWorkMinutes = new TotalOverWorkMinutes();

            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(workDateAndTimeTotal.getWorkTotalYeaAndMonth().getYearMonth())) {
                    continue;
                }
                totalNormalWorkMinutes.getTotalWorkMinutesMap().put(new WorkMinutesPerYMD(columns[0]), Integer.valueOf(columns[3]));
                totalOverWorkMinutes.getOverWorkMinutesMap().put(new WorkMinutesPerYMD(columns[0]), Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            WorkTimeTotal workTimeTotal = new WorkTimeTotal(totalNormalWorkMinutes, totalOverWorkMinutes);

            return workTimeTotal;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
