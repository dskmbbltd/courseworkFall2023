package s23.Harkkatyo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Perfume {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long perfumeId;
	private String perfumeName;
	private int publicationYear;
	private String genderSpec;
	
	@ManyToOne
	@JoinColumn(name="designerId")
	private Designer designer;
	
	
	@ManyToMany
	@JoinTable(
	name = "perfume_perfumer", 
	joinColumns = @JoinColumn(name = "perfumeId"), 
	inverseJoinColumns = @JoinColumn(name = "perfumerId"))
	private List<Perfumer> perfumers;
	
	public Perfume() {
		
	}
	
	public Perfume(String perfumeName, Designer designer) {
		this.perfumeName = perfumeName;
		this.designer = designer;
	}

	public Perfume(String perfumeName, Designer designer, List<Perfumer> perfumers) {
		this.perfumeName = perfumeName;
		this.designer = designer;
		this.perfumers = perfumers;
	}
	
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
	
	public List<Perfumer> getPerfumers() {
		return perfumers;
	}
	
	public void setPerfumers(List<Perfumer> perfumers) {
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
