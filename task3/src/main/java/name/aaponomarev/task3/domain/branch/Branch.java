package name.aaponomarev.task3.domain.branch;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity(name = "branches")
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Branch {

    private String address;

    @Id
    @GeneratedValue
    private Long id;

    private Double lat;

    private Double lon;

    private String title;

    @Transient
    private Long distance;

    @Transient
    private Integer dayOfWeek;

    @Transient
    private Integer hourOfDay;

    @Transient
    private Long predicting;

}
