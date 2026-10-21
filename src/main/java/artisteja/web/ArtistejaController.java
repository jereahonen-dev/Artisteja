package artisteja.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import artisteja.domain.Artisti;
import artisteja.domain.ArtistiRepository;
import artisteja.domain.GenreRepository;
import artisteja.domain.Label;
import artisteja.domain.LabelRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class ArtistejaController {

    private static final Logger log = LoggerFactory.getLogger(ArtistejaController.class);
    private final ArtistiRepository artRepo;
    private final GenreRepository genRepo;
    private final LabelRepository labelRepo;

    public ArtistejaController(ArtistiRepository artRepo, GenreRepository genRepo, LabelRepository labelRepo) {
        this.artRepo = artRepo;
        this.genRepo = genRepo;
        this.labelRepo = labelRepo;
    }

    @GetMapping("/artistList")
    public String showAllArtist(Model model) {
        model.addAttribute("artists", artRepo.findAll());
        return "artistList";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')") 
    public String addArtist(Model model) {
        model.addAttribute("artist", new Artisti());
        model.addAttribute("genres", genRepo.findAll());
        model.addAttribute("labels", labelRepo.findAll());
        return "/addArtist";
    }
    
    @PostMapping("/saveArtist")
    public String saveArtist(@Valid @ModelAttribute Artisti artisti, @RequestParam(required = false) List<Long> labelIds, BindingResult verifyResult, Model model) {
        log.info("CONTROLLER: Validate artist info and attempt to save: " + artisti);
        if (verifyResult.hasErrors()){
            log.error("Problem occured during validation for: " + artisti);
            model.addAttribute("artist", artisti);
            model.addAttribute("genres", genRepo.findAll());
            model.addAttribute("labels", labelRepo.findAll());
            return "addArtist";
        }
        Set<Label> labels = new HashSet<>();
        if (labelIds != null) {
            for (Long id : labelIds) {
                labelRepo.findById(id).ifPresent(labels::add);
            }
        }
        artisti.setSignedLabel(labels);
        log.info("Save artist: " + artisti);
        artRepo.save(artisti);
        return "redirect:/artistList";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") 
    public String deleteArtist(@PathVariable Long id, Model model) {
        log.info("Attempt deleting: " + artRepo.findById(id));
        artRepo.deleteById(id);
        return "redirect:../artistList";
    }
    
    @GetMapping("/editArtist/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") 
    public String editArtist(@PathVariable Long id, Model model) {
        log.info("Edited artist: " + artRepo.findById(id));
        model.addAttribute("artist", artRepo.findById(id).orElseThrow());
        model.addAttribute("genres", genRepo.findAll());
        model.addAttribute("labels", labelRepo.findAll());
        return "editArtist";
    }

    @GetMapping("/labelList")
    public String showAllLabels(Model model) {
        model.addAttribute("labels", labelRepo.findAll());
        return "labelList";
    }

    @GetMapping("/addLabel")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addLabel(Model model) {
        model.addAttribute("label", new Label());
        return "/labelForm";
    }
    
    @GetMapping("/editLabel/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editLabel(@PathVariable Long id, Model model) {
        log.info("Edited label: " + labelRepo.findById(id));
        model.addAttribute("label", labelRepo.findById(id).orElseThrow());
        return "labelForm";
    }

    @PostMapping("/saveLabel")
    public String saveLabel(@ModelAttribute Label label) {
        labelRepo.save(label);
        return "redirect:/labelList";
    }

    @GetMapping("/deleteLabel/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") 
    public String deleteLabel(@PathVariable Long id, Model model) {
        log.info("Attempt deleting: " + labelRepo.findById(id));
        Label label = labelRepo.findById(id).orElseThrow();
        for (Artisti artist : label.getSigned()) {
            artist.getSignedLabel().remove(label);
            artRepo.save(artist);
        }
        labelRepo.delete(label);
        return "redirect:../labelList";
    }
    
    
    
    
}
