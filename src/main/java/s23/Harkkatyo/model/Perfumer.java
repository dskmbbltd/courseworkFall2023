package s23.Harkkatyo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Perfumer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long perfumerId;
	
	@NotEmpty(message = "Perfumer name must be provided")
	@Size( min = 1, max = 200)
	private String perfumerName;
	
	public Perfumer() {
		
	}
	
	@ManyToMany(mappedBy = "perfumers")
	@JsonIgnore
	Set<Perfume> perfumes;
	
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
	
	public Set<Perfume> getPerfumes() {
		return perfumes;
	}
	
	public void setPerfumes(Set<Perfume> perfumes) {
		this.perfumes = perfumes;
	}
}
