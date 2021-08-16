package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {
	@Autowired
	ContratServiceImpl contratServiceimpl;
	@Autowired
	ContratRepository contratrep;
	
	  @Test public void testRetrieveAllContrats() { List<Contrat>
	  contrats=contratServiceimpl.getAllContrats(); assertEquals(1,
	  contrats.size());
	  
	  }
	 

	
	 @Test public void testAjoutContrat() throws ParseException { SimpleDateFormat
	  dateFormat = new SimpleDateFormat("yyyy-MM-dd"); Date d=
	 dateFormat.parse("2010-03-23"); Contrat contrat = new Contrat(d, "souha", 1100);
	  Contrat contratAdded = contratServiceimpl.ajouterContrat(contrat);
	 assertNotNull(contratAdded); }
	 

@Test
	public void testModifyContrat() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2022-03-22");
		Contrat c = new Contrat(d, "yy", 1250);
		Contrat contratUpdated = contratServiceimpl.updateContrat(c);
		assertEquals(c.getReference(), contratUpdated.getReference());
		// si elle est non nulle le test return false
		// assertNull(contratUpdated);
	}}

	/*
	  @Test public void testRetrieveContrat() { Contrat contratRetrieved =
	 contratServiceimpl.retrieveContrat(2); Contrat
	  c=contratrep.findById(2).get();
	  assertEquals(c.getReference(),contratRetrieved.getReference()); }
	 
}*/