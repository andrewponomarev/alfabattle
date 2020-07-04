package name.aaponomarev.task3.controller;

import name.aaponomarev.task3.domain.branch.Branch;
import name.aaponomarev.task3.service.BranchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping()
    public Branch get(@RequestParam Double lat, @RequestParam Double lon) {
        return branchService.getNearest(lat, lon);
    }

    /**
     * Gets information of a specific branch by ID.
     * @param id the id number of the given branch
     * @return all information for the requested branch
     */
    @GetMapping("/{id}")
    public Branch get(@PathVariable Long id) {
        return branchService.findById(id);
    }

    @GetMapping("/{id}/predict")
    public Branch get(@PathVariable Long id,
                      @RequestParam Integer dayOfWeek,
                      @RequestParam Integer hourOfDay) {
        return branchService.predict(id, dayOfWeek, hourOfDay);
    }

}
