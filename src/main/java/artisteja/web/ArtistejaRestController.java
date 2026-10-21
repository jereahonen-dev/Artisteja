package artisteja.web;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import artisteja.domain.Artisti;
import artisteja.domain.ArtistiRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;






@RestController
@RequestMapping("/api/artists") 
public class ArtistejaRestController {

    private final ArtistiRepository artRepo;

    public ArtistejaRestController(ArtistiRepository artRepo) {
        this.artRepo = artRepo;
    }

    @GetMapping
    public List<Artisti> artistListRest(){
        return artRepo.findAll();
    }

    @GetMapping("/{id}")
    public Artisti getArtistById(@PathVariable Long id) {
        return artRepo.findById(id).orElseThrow();
    }
    
    
    @PostMapping
    public Artisti saveArtist(@RequestBody Artisti artisti) {
        return artRepo.save(artisti);
    }
    
    @PutMapping("/{id}")
    public Artisti editArtist(@PathVariable Long id, @RequestBody Artisti artistEdit) {
        artistEdit.setId(id);
        return artRepo.save(artistEdit);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artRepo.deleteById(id);
    } 
    
}
