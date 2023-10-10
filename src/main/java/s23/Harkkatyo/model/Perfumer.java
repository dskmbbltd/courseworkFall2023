package s23.Harkkatyo.model;

import java.util.Set;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long perfumerId;
	
	@NotEmpty(message = "Perfumer name must be provided")
	@Size( min = 1, max = 200)
	@Column(nullable = false)
	private String perfumerName;
	
	public Perfumer() {
		
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "perfumers")
	@JsonIgnore
	private Set<Perfume> perfumes = new HashSet<>();
	
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
	
	public String toString() {
		return (this.perfumerName);
	}
	}
