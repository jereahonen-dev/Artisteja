package artisteja.web;

import org.springframework.web.bind.annotation.RestController;

import artisteja.domain.Label;
import artisteja.domain.LabelRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;





@RestController 
@RequestMapping("/api/labels") 
public class LabelRestController {

    private final LabelRepository labelRepo;

    public LabelRestController(LabelRepository labelRepo) {
        this.labelRepo = labelRepo;
    }

    @GetMapping
    public Iterable<Label> labelRestList() {
        return labelRepo.findAll();
    }

    @GetMapping("/{id}")
    public Label getLabelByID(@PathVariable Long id) {
        return labelRepo.findById(id).orElseThrow();
    }
    
    
    @PostMapping
    public Label saveLabel(@RequestBody Label label) {
        return labelRepo.save(label);
    }
    
    @DeleteMapping("/{id}")
    public void deleteLabel(@PathVariable Long id) {
        labelRepo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Label editLabel(@PathVariable Long id, @RequestBody Label lableEdit) {
        lableEdit.setLabelid(id);
        return labelRepo.save(lableEdit);
    }
}
