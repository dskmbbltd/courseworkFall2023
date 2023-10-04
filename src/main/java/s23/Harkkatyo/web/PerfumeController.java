package s23.Harkkatyo.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import s23.Harkkatyo.model.DesignerRepository;
import s23.Harkkatyo.model.Perfume;
import s23.Harkkatyo.model.PerfumeRepository;
import s23.Harkkatyo.model.PerfumerRepository;


@Controller
public class PerfumeController {
	@Autowired
	private PerfumeRepository pRepository;
	
	@Autowired
	private DesignerRepository dRepository;
	
	@Autowired
	private PerfumerRepository perRepository;
	
	
	@GetMapping("index")
	@ResponseBody
	public String showIndex() {
		return "loaded index page";
	}
	
	@GetMapping("perfumelist")
	public String perfumelist(Model model) {
		model.addAttribute("perfumes", pRepository.findByOrderByPerfumeNameAsc());
		return "perfumelist";
	}
	
	
	//ADDITION
	@GetMapping("addperfume")
	public String addPerfume(Model model) {
		model.addAttribute("perfume", new Perfume());
		model.addAttribute("designers", dRepository.findAll());
		model.addAttribute("perfumers", perRepository.findAll());
		return "addperfume";
	}
	
	@PostMapping("save")
	public String savePerfume(@Valid @ModelAttribute("perfume") Perfume perfume, BindingResult bindingresult, Model model) {
		if (bindingresult.hasErrors()) {
			model.addAttribute("perfume", perfume);
			model.addAttribute("designers", dRepository.findAll());
			model.addAttribute("perfumers", perRepository.findAll());
			return "addperfume";
		}
		
		pRepository.save(perfume);
		return "redirect:perfumelist";
	}
	
	//EDIT
	@GetMapping(value = "/edit/{perfumeId}")
    public String editPerfume(@PathVariable("perfumeId") Long perfumeId, Model model) {
		model.addAttribute("perfume", pRepository.findById(perfumeId));
		
		//get associated perfumers
    	model.addAttribute("existingPerfumers", pRepository.findById(perfumeId).orElseThrow().getPerfumers());
    	model.addAttribute("designers", dRepository.findAll());
    	model.addAttribute("perfumers", perRepository.findAll());
    	return "editperfume";
    }
	
	@PostMapping(value = "/edit/saveEditedPerfume")
	public String saveEditedPerfume(Perfume perfume) {
		pRepository.save(perfume);
		return "redirect:../perfumelist";
	}
}
