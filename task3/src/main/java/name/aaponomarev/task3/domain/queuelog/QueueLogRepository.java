package name.aaponomarev.task3.domain.queuelog;

import name.aaponomarev.task3.domain.branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueLogRepository extends JpaRepository<QueueLog, Long> {

    List<QueueLog> getByBranchesId(Long id);

}
