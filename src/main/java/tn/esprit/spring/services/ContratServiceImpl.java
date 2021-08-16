package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {

	@Autowired
	ContratRepository contratRepository;

	private static final Logger l = Logger.getLogger(ContratServiceImpl.class);

	public List<Contrat> getAllContrats()

	{
		l.info("In  retrieveAllContrats : ");
		List<Contrat> contrats = (List<Contrat>) contratRepository.findAll();
		for (Contrat c : contrats) {
			l.debug("c +++ : " + c);
		}
		l.info("Out of retrieveAllContrats.");
		return contrats;
	}

	public Contrat ajouterContrat(Contrat contrat) {
		l.info("ajout contrat : " + contrat);
		Contrat contratSaved = contratRepository.save(contrat);
		l.info("Out of  ajoutContrat. ");
		return contratSaved;
	}

	public Contrat updateContrat(Contrat c) {
		l.info("ajout contrat : " + c);
		Contrat contratSaved = contratRepository.save(c);
		l.info("Out of  ajoutContrat. ");
		return contratSaved;
	}
	
	 public Contrat retrieveContrat(int id) { l.info("in  retrieveContrat id = " +
	  id); 
	 Contrat c = contratRepository.findById(id).orElse(null);
	 
	  l.info("Contrat   returned : " + c); return c; }
	

	
	
	 

}