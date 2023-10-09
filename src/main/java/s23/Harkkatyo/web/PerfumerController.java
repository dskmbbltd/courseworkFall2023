package s23.Harkkatyo.web;

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
import s23.Harkkatyo.model.Perfumer;
import s23.Harkkatyo.model.PerfumerRepository;

@Controller
public class PerfumerController {
	
	@Autowired
	private PerfumerRepository perRepository;
	
	// LISTING OF ALL PERFUMERS
	@GetMapping("perfumerlist")
	public String perfumerlist(Model model) {
		model.addAttribute("perfumers", perRepository.findAll());
		return "perfumerlist";
	}
	
	// ADDITION
		@GetMapping("addperfumer")
		public String addPerfumer(Model model) {
			model.addAttribute("perfumer", new Perfumer());
			model.addAttribute("perfumers", perRepository.findAll());
			return "addperfumer";
}
	@PostMapping("savePerfumer")
	public String savePerfumer(@Valid @ModelAttribute("perfumer") Perfumer perfumer, BindingResult bindingresult, Model model) {
		
		if (bindingresult.hasErrors()) {
			model.addAttribute("perfumer", perfumer);
			model.addAttribute("perfumers", perRepository.findAll());
			return "addperfumer";
		}
		
		perRepository.save(perfumer);
		return "redirect:perfumerlist";
	}
	
	// EDIT
	@GetMapping("editperfumer/{perfumerId}")
	public String editperfumer(@PathVariable("perfumerId") Long perfumerId, Model model) {
		model.addAttribute("perfumer", perRepository.findById(perfumerId));
		model.addAttribute("perfumers", perRepository.findAll());
		return "editperfumer";
}
	@PostMapping("editperfumer/saveEditedPerfumer")
	public String saveEditedPerfumer(@Valid @ModelAttribute("perfumer") Perfumer perfumer, BindingResult bindingresult, Model model) {
		
		if (bindingresult.hasErrors()) {
			model.addAttribute("perfumer", perfumer);
			model.addAttribute("perfumers", perRepository.findAll());
			return "editedperfumer";
		}
		
		perRepository.save(perfumer);
		return "redirect:../perfumerlist";
	}
}
