package name.aaponomarev.task3.domain.queuelog;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
public class QueueLog {

    @Id
    Long id;

    LocalDate data;

    LocalTime startTimeOfWait;

    LocalTime endTimeOfWait;

    LocalTime endTimeOfService;

    Long branchesId;

}
