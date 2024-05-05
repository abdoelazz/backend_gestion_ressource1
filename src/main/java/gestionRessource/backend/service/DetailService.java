package gestionRessource.backend.service;

import gestionRessource.backend.model.Detail;

import java.util.List;

public interface DetailService {

	Detail saveDetail(Detail detail);
	List<Detail> getDetailByProposition(Long propositionId);


}
