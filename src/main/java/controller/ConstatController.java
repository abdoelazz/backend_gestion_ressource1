package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.ConstatDTO;
import model.Constat;
import model.Panne;
import service.ConstatService;
import service.PanneService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/constat")
public class ConstatController {
	@Autowired
	private ConstatService constatService;

	@Autowired
	private PanneService panneService;

	@PostMapping("/addConstat")
	public Constat addConstat(@RequestBody ConstatDTO constatDto) {
		Panne panne = panneService.getPanneById(constatDto.getPanne_id());
		Constat constat = new Constat();
		constat.setExplication(constatDto.getExplication());
		constat.setFrequenceConstat(constatDto.getFrequenceConstat());
		constat.setOrdre(constatDto.getOrdre());
		constat.setDateApparition(constatDto.getDateApparition());
		constat.setPanne(panne);
		constatService.saveConstat(constat);
		return constat;
	}

	@GetMapping("/getConstatByPanne")
	public List<Constat> getConstatByPanne(@RequestParam Long panneId) {
		Panne panne = panneService.getPanneById(panneId);
		return constatService.getConstatByPanne(panne);
	}
}
