package service;

import java.util.List;

import model.AppelDoffre;

public interface AppelDoffreService {
	AppelDoffre AjouterAppelDoffre(AppelDoffre appelDoffre);

	List<AppelDoffre> getAllAppelDoffres();

	List<AppelDoffre> getAppelDoffresNew();

	AppelDoffre getAppelDoffreById(Long id);

}
