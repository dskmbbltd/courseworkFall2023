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

import s23.Harkkatyo.model.Perfume;
import s23.Harkkatyo.model.PerfumeRepository;

@RestController
public class RestPerfumeController {
	@Autowired
	private PerfumeRepository pRepository;
	
	
	//GET
	@GetMapping(value="/rest/perfumes")
	public List<Perfume> restPerfumeList() {
		return (List<Perfume>) pRepository.findAll();
	}
	
	//POST
	@PostMapping(value="/rest/perfumes/post")
	Perfume newPerfume(@RequestBody Perfume newPerfume) {
		return pRepository.save(newPerfume);
	}
	
	//PUT
	@PutMapping(value="/rest/perfumes/{perfumeId}")
	Perfume editPerfume(@PathVariable Long perfumeId, @RequestBody Perfume ePerfume) {
		ePerfume.setPerfumeId(perfumeId);
		return pRepository.save(ePerfume);
	}
	
	//DELETE
	@DeleteMapping(value="/rest/perfumes/{perfumeId}")
	public void deletePerfume(@PathVariable Long perfumeId) {
		pRepository.deleteById(perfumeId);
	}
	
}
