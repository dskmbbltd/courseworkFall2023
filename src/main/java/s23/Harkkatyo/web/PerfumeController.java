package s23.Harkkatyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("addperfume")
	public String addPerfume(Model model) {
		model.addAttribute("perfume", new Perfume());
		model.addAttribute("designers", dRepository.findAll());
		model.addAttribute("perfumers", perRepository.findAll());
		return "addperfume";
	}
	
	@PostMapping("save")
	public String savePerfume(Perfume perfume) {
		pRepository.save(perfume);
		return "redirect:perfumelist";
	}
}
