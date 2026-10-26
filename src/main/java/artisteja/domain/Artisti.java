package artisteja.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity 
@Table(name="artisti")
public class Artisti {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    @Min (value = 1950, message = "Too old")
    private int founded;

    @JsonIgnoreProperties("artisti")
    @ManyToOne
    @JoinColumn (name = "genreid")
    private Genre genre;

    @JsonIgnoreProperties("signed") 
    @ManyToMany 
    @JoinTable(
        name = "label_signed",
        joinColumns = @JoinColumn(name="id"),
        inverseJoinColumns = @JoinColumn(name = "labelid"))
    private Set<Label> signedLabel;

    public Artisti() {
    }

    public Artisti(String name, String country, int founded, Genre genre, Set<Label> signedLabel) {
        super();
        this.name = name;
        this.country = country;
        this.founded = founded;
        this.genre = genre;
        this.signedLabel = signedLabel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Label> getSignedLabel() {
        return signedLabel;
    }

    public void setSignedLabel(Set<Label> signedLabel) {
        this.signedLabel = signedLabel;
    }

    @Override
    public String toString() {
        return "Artisti [id=" + id + ", name=" + name + ", country=" + country + ", founded=" + founded + "]";
    }

    
}
