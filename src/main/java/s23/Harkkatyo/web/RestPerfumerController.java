package s23.Harkkatyo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s23.Harkkatyo.model.Perfumer;
import s23.Harkkatyo.model.PerfumerRepository;

@RestController
public class RestPerfumerController {
	@Autowired
	private PerfumerRepository perfRepository;
	
	
	//GET
	@GetMapping(value="/rest/perfumers")
	public List<Perfumer> restPerfumerList() {
		return (List<Perfumer>) perfRepository.findAll();
	}
	
	//POST
	@PostMapping(value="/rest/perfumers/post")
	Perfumer newPerfumer(@RequestBody Perfumer newPerfumer) {
		return perfRepository.save(newPerfumer);
	}
	
	//PUT
	@PutMapping(value="/rest/perfumers/{perfumerId}")
	Perfumer editPerfumer(@PathVariable Long perfumerId, @RequestBody Perfumer editablePerfumer) {
		editablePerfumer.setPerfumerId(perfumerId);
		return perfRepository.save(editablePerfumer);
	}
	
	//DELETE
	@DeleteMapping(value="/rest/perfumers/{perfumerId}")
	public void deletePerfumer(@PathVariable Long perfumerId) {
		perfRepository.deleteById(perfumerId);
	}
	
}
