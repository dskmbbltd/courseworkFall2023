package s23.Harkkatyo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Perfumer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long perfumerId;
	private String perfumerName;
	
	public Perfumer() {
		
	}
	
	@ManyToMany(mappedBy = "perfumers")
	List<Perfume> perfumes;
	
	public Perfumer(String perfumerName) {
		this.perfumerName = perfumerName;
	}
	
	public Long getPerfumerId() {
		return perfumerId;
	}

	public void setPerfumerId(Long perfumerId) {
		this.perfumerId = perfumerId;
	}

	public void setPerfumerName(String perfumerName) {
		this.perfumerName = perfumerName;
	}
	
	public String getPerfumerName() {
		return perfumerName;
	}
	
	public List<Perfume> getPerfumes() {
		return perfumes;
	}
	
	public void setPerfumes(List<Perfume> perfumes) {
		this.perfumes = perfumes;
	}
}
