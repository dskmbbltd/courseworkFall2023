package s23.Harkkatyo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PerfumerRepository extends CrudRepository<Perfumer, Long> {
	
	List<Perfumer> findAllByPerfumerName(String perfumerName);
	Perfumer findByPerfumerName(String perfumerName);
}
