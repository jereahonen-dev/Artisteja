package artisteja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;

import artisteja.domain.Application_user;
import artisteja.domain.Application_userRepository;
import artisteja.domain.Artisti;
import artisteja.domain.ArtistiRepository;
import artisteja.domain.Genre;
import artisteja.domain.GenreRepository;
import artisteja.domain.Label;
import artisteja.domain.LabelRepository;

@SpringBootApplication
public class ArtistejaApplication {

	private static final Logger log = LoggerFactory.getLogger(ArtistejaApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(ArtistejaApplication.class, args);
	}

 
	@Bean
	public CommandLineRunner demo(
		Application_userRepository userRepo,
		ArtistiRepository artRepo,
		GenreRepository genreRepo,
		LabelRepository labelRepo) {
		return (args) -> {

			if (userRepo.count()==0) {
			userRepo.save(new Application_user("admin", "$2a$12$s3spzn7imeVp5o0f6zv6AuDWSOjHJmdRRZXo8O0ibOiVcz2UAZtHi", "ADMIN"));
			userRepo.save(new Application_user("user", "$2a$12$m3HNDX/8FOovrv8jvbWXneZXm5.yK4/SDMColosEVy.IBWCCUCBD6", "USER"));
			}

			if (labelRepo.count()==0){
			Label spinefarm = labelRepo.save(new Label("Spinefarm", null));
			Label nuclearBlast = labelRepo.save(new Label("Nuclear Blast", null));
			Label atlantic = labelRepo.save(new Label("Atlantic", null));
			Label fueledByRamen = labelRepo.save(new Label("Fueled by Ramen", null));
			Label elektra = labelRepo.save(new Label("Elektra", null));

			Set<Label> labels1 = new HashSet<>();
			labels1.add(spinefarm);
			labels1.add(nuclearBlast);

			Set<Label> labels2 = new HashSet<>();
			labels2.add(atlantic);
			labels2.add(fueledByRamen);

			Set<Label> labels3 = new HashSet<>();
			labels3.add(elektra);
			labels3.add(atlantic);

			Genre altRock = genreRepo.save(new Genre("Alt-rock"));
			Genre metal = genreRepo.save(new Genre("Metal"));
			Genre rock = genreRepo.save(new Genre("Rock"));

			Artisti nightwish = new Artisti(
						"Nightwish",
						"Finland",
						1996,
						metal,
						labels1
			);

			Artisti pilots21 = new Artisti(
					"21 Pilots",
					"USA",
					2009,
					altRock,
					labels2
			);

			Artisti acdc = new Artisti(
					"AC/DC",
					"Australia",
					1973,
					rock,
					labels3
			);
            
			artRepo.save(nightwish);
			artRepo.save(pilots21);
			artRepo.save(acdc);

			log.info("fetch all artist");
			for (Artisti artisti : artRepo.findAll()) {
				log.info(artisti.toString());
			}
		}
		};
	}

}
