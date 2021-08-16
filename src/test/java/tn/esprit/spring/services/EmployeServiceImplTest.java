package tn.esprit.spring.services;

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
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	@Autowired
	EmployeServiceImpl employeServiceImpl;
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	MissionRepository missionRepository;

	@Test
	public void TestEmployeAuthenticate() {
		Employe authenticateEmploye = employeServiceImpl.authenticate("khaoula.mejri.1@esprit.tn", "khaoula");
		assertEquals(1, authenticateEmploye.getId());
	}



	@Test
	public void TestAddOrUpdateEmploye() {
		Employe employe = new Employe("loutcha", "loutch", "khaoula.mejri.1@esprit.tn", "loutcha", true, null);
		int idEmploye = employeServiceImpl.addOrUpdateEmploye(employe);

		assertEquals(5, idEmploye);
	}

	

	@Test
	public void TestmettreAjourEmailByEmployeId() {
		employeServiceImpl.mettreAjourEmailByEmployeId("khaoulaaaaa.mejri.1@esprit.tn", 1);
		Employe employe = employeRepository.findById(1).get();

		assertEquals("khaoulaaaaa.mejri.1@esprit.tn", employe.getEmail());

	}
	
	

	@Test
	public void testAffecterEmployeADepartement() {

		employeServiceImpl.affecterEmployeADepartement(5, 2);
		Employe employe = employeRepository.findById(5).get();
		int id1 = employe.getDepartements().get(0).getId();
		assertEquals(2, id1);
	}

	

	 @Test
	 public void desaffecterEmployeDuDepartementTest() {
	 employeServiceImpl.desaffecterEmployeDuDepartement(5, 2);
	 Employe employe = employeRepository.findById(5).get();
	 int z= employe.getDepartements().size();
	 assertEquals(0, z);
	 }

	

	@Test
	public void TestAjouterContrat() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2020-10-25");
		Contrat contrat = new Contrat(d, "CDI", 250);
		int idcontrat = employeServiceImpl.ajouterContrat(contrat);

		assertEquals(5, idcontrat);
	}

	

	@Test
	public void testAffecterContratAEmployet() {

		employeServiceImpl.affecterContratAEmploye(4, 2);
		Employe employe = employeRepository.findById(2).get();
		int idContrat = employe.getContrat().getReference();
		assertEquals(4, idContrat);

	}

	

	@Test
	public void TestgetEmployePrenomById() {
		String employe = employeServiceImpl.getEmployePrenomById(1);
		assertEquals("khaoula", employe);

	}

	
	@Test
	public void TestdeleteEmployeById() {
		employeServiceImpl.deleteEmployeById(3);
		List<Employe> empl = (List<Employe>) employeRepository.findAll();
		assertEquals(2, empl.size());
	}


	@Test
	public void TestDeleteContratById() {
		employeServiceImpl.deleteContratById(3);
		List<Contrat> contrat = (List<Contrat>) contratRepoistory.findAll();
		assertEquals(2, contrat.size());
	}

	

	@Test
	public void TestgetNombreEmployeJPQL() {
		int nbr = employeServiceImpl.getNombreEmployeJPQL();
		assertEquals(2, nbr);
	}


	@Test
	public void TestgetAllEmployeNamesJPQL() {
		List<String> emp = employeServiceImpl.getAllEmployeNamesJPQL();
		assertEquals(2, emp.size());
	}



	@Test
	public void TestmettreAjourEmailByEmployeIdJPQL() {
		employeServiceImpl.mettreAjourEmailByEmployeIdJPQL("loutcha.mejri.1@esprit.tn", 2);
		Employe employe = employeRepository.findById(2).get();
		assertEquals("loutcha.mejri.1@esprit.tn", employe.getEmail());
	}

	

	@Test
	public void TestdeleteAllContratJPQL() {
		employeServiceImpl.deleteAllContratJPQL();
		List<Contrat> contrat = (List<Contrat>) contratRepoistory.findAll();
		assertEquals(0, contrat.size());
	}



	@Test
	public void TestgetSalaireByEmployeIdJPQL() {

		float salaire = employeServiceImpl.getSalaireByEmployeIdJPQL(2);
		assertEquals(250, salaire, 0);
	}

	

	@Test
	public void TestgetSalaireMoyenByDepartementId() {

		double sal = employeServiceImpl.getSalaireMoyenByDepartementId(1);
		assertEquals(250, sal, 0);
	}



	@Test
	public void TestgetTimesheetsByMissionAndDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse("2020-10-07");
		Date dateFin = dateFormat.parse("2020-10-22");
		Employe emp = employeRepository.findById(2).get();
		Mission miss = missionRepository.findById(1).get();
		List<Timesheet> l = employeServiceImpl.getTimesheetsByMissionAndDate(emp, miss, dateDebut, dateFin);
		assertNotNull(l);
	}

	
	@Test
	public void testGetAllEmployes() {
		List<Employe> listEmploye = employeServiceImpl.getAllEmployes(); 
		assertEquals(2, listEmploye.size());
	}

}
