package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.DepartementServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {

	@Autowired
	EntrepriseServiceImpl es;
	
	@Autowired
	DepartementServiceImpl departementServiceImpl;
	
	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	
	@Autowired
	DepartementRepository deptRepoistory;
	
	@Test
	public void testAddEntreprise() throws ParseException {
		
		  Entreprise  e = new Entreprise("Teamwill","sssss") ;
          Entreprise entrepriseAdded = es.ajouterEntreprise(e);
		  assertEquals(e.getName(), entrepriseAdded.getName());
	}
	
	
	
	@Test
	public void testAddDepartement() throws ParseException {
		
		 Departement  dep = new Departement("yasminet") ;
          Departement departementAdded = es.ajouterDepartement(dep);
		  assertEquals(dep.getName(), departementAdded.getName());
		}
	
	
	@Test
	public void testgetEntrepriseById() {
		
		  Entreprise entrepriseRetrieved =es.getEntrepriseById(3); 
	      assertEquals(3, entrepriseRetrieved.getId());
	}
	
	
	@Test
	public void testgetAllDepartementsNamesByEntreprise() {
		
		List<String>listDepartments = es.getAllDepartementsNamesByEntreprise(3); 
		// if there are 5 departements in DB : 
		assertEquals(5, listDepartments.size());
	}
	
	
	
	@Test
	public void testRetrieveAllEntreprises() {
		
		List<Entreprise> listEntreprises = es.retrieveAllEntreprises(); 
		
		// if there are 5 users in DB : 
		assertEquals(2, listEntreprises.size());
	}
	

	
	 @Test
	 public void testdeleteEntrepriseById() {
		 
		  es.deleteEntrepriseById(5); 
		  Entreprise e =entrepriseRepoistory.findById(5).get();
		  assertEquals(5, e.getId()); 
	}
	

	
	@Test
	public void testdeleteDepartementById() {
	  
		Optional<Departement> d=deptRepoistory.findById(6);
		if (d.isPresent()) {
			Departement departement = d.get();
			  es.deleteDepartementById(6);
			  assertEquals(6, departement.getId());
		}
	}
	
	
	@Test 
	public void testaffecterDepartementAEntreprise() {
		
		Optional<Entreprise> e =entrepriseRepoistory.findById(7);
		if (e.isPresent()) {
			
			Entreprise entreprise = e.get();
			es.affecterDepartementAEntreprise(6, 7); 
			assertEquals(1, entreprise.getDepartements().size());		
	}
	}
	
}
