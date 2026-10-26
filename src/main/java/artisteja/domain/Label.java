package artisteja.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="label")
public class Label {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long labelid;
    private String name;

    @ManyToMany(mappedBy = "signedLabel")
    @JsonIgnore 
    private Set<Artisti> signed = new HashSet<>();

    public Label() {}

    public Label(String name, Set<Artisti> signed) {
        super();
        this.name = name;
        this.signed = signed;
    }

    public Long getLabelid() {
        return labelid;
    }

    public void setLabelid(Long labelid) {
        this.labelid = labelid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Artisti> getSigned() {
        return signed;
    }

    public void setSigned(Set<Artisti> signed) {
        this.signed = signed;
    }
}
