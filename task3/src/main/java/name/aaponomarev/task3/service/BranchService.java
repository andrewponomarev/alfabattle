package name.aaponomarev.task3.service;

import name.aaponomarev.task3.domain.branch.Branch;
import name.aaponomarev.task3.domain.branch.BranchRepository;
import name.aaponomarev.task3.domain.queuelog.QueueLog;
import name.aaponomarev.task3.domain.queuelog.QueueLogRepository;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final QueueLogRepository queueLogRepository;

    public BranchService(BranchRepository branchRepository, QueueLogRepository queueLogRepository) {
        this.branchRepository = branchRepository;
        this.queueLogRepository = queueLogRepository;
    }

    public Branch findById(Long id) {
         return branchRepository.findById(id).orElseThrow(BranchNotFoundException::new);
    }

    public Branch getNearest(Double lat, Double lon) {
        DistanceUtil distanceUtil = new DistanceUtil(lat, lon);
        List<Branch> all = branchRepository.findAll();
        all.stream().forEach(x -> x.setDistance(distanceUtil.distance(x)));
        return all.stream()
                .min(Comparator.comparing(Branch::getDistance))
                .orElseThrow(BranchNotFoundException::new);
    }

    public Branch predict(Long id, Integer dayOfWeek, Integer hourOfDay) {
        Branch branch = findById(id);

        List<QueueLog> logs = queueLogRepository.getByBranchesId(id);
        logs.removeIf(x -> x.getData().getDayOfWeek().getValue() != dayOfWeek);
        logs.removeIf(x -> x.getStartTimeOfWait().getHour() > hourOfDay);
        logs.removeIf(x -> x.getEndTimeOfWait().getHour() < hourOfDay);
        Long[] waitingTimes = logs.stream()
                .map(x ->  x.getStartTimeOfWait().until(x.getEndTimeOfWait(), SECONDS)).toArray(Long[]::new);
        double[] dWaitingTimes = new double[waitingTimes.length];
        int i = 0;
        for (var wt : waitingTimes) {
            dWaitingTimes[i++] = wt.doubleValue();
        }
        long median = Math.round(new Median().evaluate(dWaitingTimes));

        branch.setDayOfWeek(dayOfWeek);
        branch.setHourOfDay(hourOfDay);
        branch.setPredicting(median);

        return branch;
    }
}
