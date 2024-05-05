package service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AppelDoffre;
import repository.AppelDoffreRepository;
import service.AppelDoffreService;

@Service
public class AppelDoffreServiceImpl implements AppelDoffreService {
	@Autowired
	private AppelDoffreRepository appelDoffreRepository;

	@Override
	public AppelDoffre AjouterAppelDoffre(AppelDoffre appelDoffre) {
		return appelDoffreRepository.save(appelDoffre);
	}

	@Override
	public List<AppelDoffre> getAllAppelDoffres() {
		return appelDoffreRepository.findAll();
	}

	@Override
	public List<AppelDoffre> getAppelDoffresNew() {
		return appelDoffreRepository.findByEtatDisponibilite(true);
	}

	@Override
	public AppelDoffre getAppelDoffreById(Long id) {
		Optional<AppelDoffre> appelDoffreOptional = appelDoffreRepository.findById(id);
		return appelDoffreOptional.get();
	}

}
