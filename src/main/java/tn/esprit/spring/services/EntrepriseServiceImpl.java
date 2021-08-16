package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	
	
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);
	 

	public Entreprise ajouterEntreprise(Entreprise entreprise) {
		l.info("In  addEntreprise : " + entreprise); 
		entrepriseRepoistory.save(entreprise);
		l.info("Out of  addEntreprise. "); 
		return entreprise;
	}
	
	public Departement ajouterDepartement(Departement dep) {
		l.info("In  addDepartement : " + dep); 
		deptRepoistory.save(dep);
		l.info("Out of  addDepartement. "); 
		return dep;
	}
	
	public List<Entreprise> retrieveAllEntreprises() {
		l.info("In  retrieveAllEntreprises : "); 
		List<Entreprise> entreprises =  (List<Entreprise>) entrepriseRepoistory.findAll();  
		for (Entreprise entreprise : entreprises) {
			l.debug("entreprise +++ : " + entreprise);
		}
		l.info("Out of retrieveAllEntreprises."); 
		return entreprises;
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		l.info("in  getEntreprise id = " + entrepriseId);

		Optional<Entreprise> e =entrepriseRepoistory.findById(entrepriseId);
		if (e.isPresent()) {
			Entreprise entreprise = e.get();
			l.info("entreprise returned : " + e);
			return entreprise;
		}
		return null;
	}
	
	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		l.info("in  deleteEntreprise id = " + entrepriseId);
		Optional<Entreprise> e =entrepriseRepoistory.findById(entrepriseId);
		if (e.isPresent()) {
			Entreprise entreprise = e.get();
			entrepriseRepoistory.delete(entreprise);

			l.info("entreprise deleted." +entreprise.getName() );
			l.info("out of  deleteentreprise"); 
			}
		
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		l.info("in  deleteDepartement id = " + depId);

		Optional<Departement>  d=deptRepoistory.findById(depId);
		if (d.isPresent()) {
			Departement dep = d.get();
			deptRepoistory.delete(dep);
			
			l.info("departement deleted." +dep.getName() );
			l.info("out of  deleteDepartement");
		}
	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.info("In  getAllDepartementByEntreprise : entrepriseId" + entrepriseId); 
		List<String> depNames = new ArrayList<>();
		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		if (entrepriseManagedEntity.isPresent()) {
			Entreprise entreprise = entrepriseManagedEntity.get();
			l.info("entreprise  : " + entreprise);
		
			for(Departement dep : entreprise.getDepartements()){
				l.info("departements of entreprise : " + entrepriseManagedEntity);
				depNames.add(dep.getName());
				l.debug("dep +++ : " + dep);
			}
			l.info("Out of getAllDepartementByEntreprise. " );
		}
		return depNames;
		
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		l.info("In entrepriseId  : " + entrepriseId);
		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);
		if (entrepriseManagedEntity.isPresent()) {
			
			Entreprise entreprise = entrepriseManagedEntity.get();
			l.info("entreprise  : " + entreprise);
		
		
			Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
			if (depManagedEntity.isPresent()) {
				Departement departement = depManagedEntity.get();
				l.info(" departementId "+ departement  ); 
	
				departement.setEntreprise(entreprise);
				deptRepoistory.save(departement);
			}
		
		
		l.info("departement  affecté"); 
		l.info("Out of affecterDepartementAEntreprise. " ); 
		
	}
	}

}
