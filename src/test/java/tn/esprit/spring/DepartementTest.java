package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.DepartementServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementTest {

	@Autowired
	DepartementServiceImpl depService;
	@Autowired
	DepartementRepository deptRepoistory;
	
	@Test
	public void testRetrieveAllDepartement() {
		List<Departement> departements = depService.getAllDepartements(); 
		assertEquals(2, departements.size());
	}
	
	 @Test
	 public void testdeleteDepartement() {

depService.deleteDepartement(1);
		 Departement d=deptRepoistory.findById(1).get();
			assertEquals(1, d.getId());
		   
	 }
	 @Test
	 public void testdeleteDepartementTaille() {

depService.deleteDepartement(2);
		
		  
	  List<Departement> departements = depService.getAllDepartements(); 
		assertEquals(0, departements.size());
		   
	 }
	@Test
	public void testAddDepartement() {
		Departement u = new Departement("aa"); 
		Departement departementAdded = depService.addDepartement(u); 
		assertEquals(u.getName(), departementAdded.getName());
	}
	
	@Test
	public void testUpdateDepartement()  {
		Departement u = new Departement(3, "kk"); 
		Departement departementUpdated = depService.updateDepartement(u); 
		assertEquals(u.getName(), departementUpdated.getName());
	}
	
	@Test
	public void testRetrieveDepartement() {
		Departement departementRetrieved = depService.retrieveDepartement(3); 
		assertEquals(2, departementRetrieved.getId());
	}
	
	 
	 

}
