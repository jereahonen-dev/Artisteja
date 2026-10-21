package artisteja.domain;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="genre")
public class Genre {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genreid;
    private String name;
    
    @JsonIgnoreProperties("genre") 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private List<Artisti> artists;

    public Genre() {}

    public Genre(String name) {
        super();
        this.name = name;
    }

    public Long getGenreid() {
        return genreid;
    }

    public void setGenreid(Long genreid) {
        this.genreid = genreid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artisti> getArtisti() {
        return artists;
    }

    public void setArtisti(List<Artisti> artisti) {
        this.artists = artisti;
    }

    @Override
    public String toString() {
        return "Genre [id=" + genreid + ", name=" + name + ", artisti=" + artists + "]";
    } 
    
}
