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

import s23.Harkkatyo.model.Designer;
import s23.Harkkatyo.model.DesignerRepository;

@RestController
public class RestDesignerController {
	@Autowired
	private DesignerRepository dRepository;
	
	//GET
	@GetMapping(value="rest/designers")
	public List<Designer> restDesignerList() {
		return (List<Designer>) dRepository.findAll();
	}
	
	//POST
	@PostMapping(value="rest/designers/post")
	Designer newDesigner(@RequestBody Designer newDesigner) {
		return dRepository.save(newDesigner);
	}
	//PUT
	@PutMapping(value="rest/designers/{designerId}")
	Designer editDesigner(@PathVariable Long designerId, @RequestBody Designer editableDesigner) {
		editableDesigner.setDesignerId(designerId);
		return dRepository.save(editableDesigner);
	}
	
	//DELETE
	@DeleteMapping(value="rest/designers/{designerId}")
	public void deleteDesigner(@PathVariable Long designerId) {
		dRepository.deleteById(designerId);
	}
	
}
