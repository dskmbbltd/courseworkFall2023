package s23.Harkkatyo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DesignerRepository extends CrudRepository<Designer, Long> {
	
	List<Designer> findByDesignerName(String designerName);
}
