package s23.Harkkatyo.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface PerfumerRepository extends CrudRepository<Perfumer, Long> {
	
	LinkedHashSet<Perfumer> findAllByPerfumerName(String perfumerName);
	LinkedHashSet<Perfumer> findByPerfumerName(String perfumerName);
}
