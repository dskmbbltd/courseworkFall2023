package s23.Harkkatyo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import s23.Harkkatyo.model.Designer;
import s23.Harkkatyo.model.DesignerRepository;

@Controller
public class DesignerController {

	@Autowired
	private DesignerRepository dRepository;

	// LISTING OF ALL DESIGNERS
	@GetMapping("designerlist")
	public String designerlist(Model model) {
		model.addAttribute("designers", dRepository.findAll());
		return "designerlist";
	}

	// ADDITION
	@GetMapping("adddesigner")
	public String addDesigner(Model model) {
		model.addAttribute("designer", new Designer());
		model.addAttribute("designers", dRepository.findAll());
		return "adddesigner";
	}

	@PostMapping("saveDesigner")
	public String saveDesigner(@Valid @ModelAttribute("designer") Designer designer, BindingResult bindingresult,
			Model model) {

		// Unique Constraint check
		List<Designer> optDes = dRepository.findByDesignerName(designer.getDesignerName());
		if (!optDes.isEmpty()) {
			bindingresult.rejectValue("designerName", "error.designerName", "Designer already exists in the database.");
		}

		// Regular errors
		if (bindingresult.hasErrors()) {
			model.addAttribute("designer", designer);
			model.addAttribute("designers", dRepository.findAll());
			return "adddesigner";
		}

		dRepository.save(designer);
		return "redirect:designerlist";

	}

	// EDIT
	@GetMapping("editdesigner/{designerId}")
	public String editDesigner(@PathVariable("designerId") Long designerId, Model model) {
		model.addAttribute("designer", dRepository.findById(designerId));
		model.addAttribute("designers", dRepository.findAll());
		return "editdesigner";
	}

	@PostMapping(value = "/editdesigner/saveEditedDesigner")
	public String saveEditedDesigner(@Valid Designer designer, BindingResult bindingresult, Model model) {

		// UNIQUE NAME CONSTRAINT ERROR
		List<Designer> optDes = dRepository.findByDesignerName(designer.getDesignerName());
		if (!optDes.isEmpty()) {
			bindingresult.rejectValue("designerName", "error.designerName",
					"Designer name already exists in the database. Did you edit the name?");
		}

		// Regular errors
		if (bindingresult.hasErrors()) {
			model.addAttribute("designer", designer);
			model.addAttribute("designers", dRepository.findAll());
			return "editdesigner";
		}

		dRepository.save(designer);
		return "redirect:../designerlist";
	}

	// DELETE
	@GetMapping(value = "/deletedesigner/{designerId}")
	public String deleteDesigner(@PathVariable("designerId") Long designerId, Model model) {
		dRepository.deleteById(designerId);
		return "redirect:../designerlist";
	}
}
