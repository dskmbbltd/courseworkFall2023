package s23.Harkkatyo.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Perfume {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long perfumeId;
	@NotEmpty(message = "Name must be provided")
	@Size( min = 1, max = 200)
	private String perfumeName;
	@Min(1900)
	private int publicationYear = 1900;
	@Size( min = 1, max = 1)
	private String genderSpec = "U";
	
	//SUHTEET
	@ManyToOne
	@JoinColumn(name="designerId")
	private Designer designer;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@OrderBy("perfumerName ASC")
	@JoinTable(
	name = "perfume_perfumer", 
	joinColumns = @JoinColumn(name = "perfumeId"), 
	inverseJoinColumns = @JoinColumn(name = "perfumerId"))
	private Set<Perfumer> perfumers = new HashSet<>();
	
	//CONSTR
	public Perfume() {
		
	}
	
	public Perfume(String perfumeName, Designer designer) {
		this.perfumeName = perfumeName;
		this.designer = designer;
	}

	public Perfume(String perfumeName, Designer designer, Set<Perfumer> perfumers) {
		this.perfumeName = perfumeName;
		this.designer = designer;
		this.perfumers = perfumers;
	}
	
    public void addPerfumer(Perfumer perfumer) {
        perfumers.add(perfumer);
        perfumer.getPerfumes().add(this);
    }
	
	//GET SET
	public String getPerfumeName() {
		return perfumeName;
	}

	public void setPerfumeName(String perfumeName) {
		this.perfumeName = perfumeName;
	}
	
	public Long getPerfumeId() {
		return perfumeId;
	}
	
	public void setPerfumeId(Long perfumeId) {
		this.perfumeId = perfumeId;
	}
	
	public Designer getDesigner() {
		return designer;
	}
	
	public void setDesigner(Designer designer) {
		this.designer = designer;
	}
	
	public Set<Perfumer> getPerfumers() {
		return perfumers;
	}
	
	public void setPerfumers(Set<Perfumer> perfumers) {
		this.perfumers = perfumers;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getGenderSpec() {
		return genderSpec;
	}

	public void setGenderSpec(String genderSpec) {
		this.genderSpec = genderSpec;
	}
}
