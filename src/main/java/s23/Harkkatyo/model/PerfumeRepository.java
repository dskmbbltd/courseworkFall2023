package s23.Harkkatyo.model;
import java.util.List;

import org.springframework.data.repository.CrudRepository;	

public interface PerfumeRepository extends CrudRepository<Perfume, Long> {

	List<Perfume> findByOrderByPerfumeNameAsc();
	List<Perfume> findByPerfumeName(String perfumeName);
	Perfume findOneByPerfumeName(String perfumerName);
}
