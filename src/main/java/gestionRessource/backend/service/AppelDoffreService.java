package gestionRessource.backend.service;

import java.util.List;

import gestionRessource.backend.model.AppelDoffre;

public interface AppelDoffreService {
	AppelDoffre AjouterAppelDoffre(AppelDoffre appelDoffre);

	List<AppelDoffre> getAllAppelDoffres();

	List<AppelDoffre> getAppelDoffresNew();

	AppelDoffre getAppelDoffreById(Long id);

}
