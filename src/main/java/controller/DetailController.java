package controller;

import model.Detail;
import service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/detail")
public class DetailController {
	@Autowired
	private DetailService detailService;

	@GetMapping("/getDetailByProposition")
	public List<Detail> getDetailByProposition(@RequestParam Long propositionId) {
		List<Detail> details = detailService.getDetailByProposition(propositionId);
		return details;
	}
}
