package artisteja;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import artisteja.domain.Artisti;
import artisteja.domain.ArtistiRepository;

@DataJpaTest 
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE) 
public class ArtistiRepositoryTest {

    @Autowired 
    private ArtistiRepository artRepo;

    @Test 
    void saveArtist() {
        Artisti artist = new Artisti();
        artist.setName("Popeda");
        artist.setCountry("Finland");
        artist.setFounded(1977);
        Artisti saved = artRepo.save(artist);

        assertNotNull(saved.getId());
    }
}
