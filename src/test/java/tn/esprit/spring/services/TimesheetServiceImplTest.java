package tn.esprit.spring.services;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {
	@Autowired 
	ITimesheetService tM; 
	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	@Test
	public void testAjouterMission() {
	Mission mission = new Mission("test", "facile");
	int id=	tM.ajouterMission(mission);
	
		
		assertEquals(8,id);
	}
/*@Test
public void testAffecterMission() {
	
 tM.affecterMissionADepartement(4,1);
 Optional<Mission> value= missionRepository.findById(4);
 if(value.isPresent()) {
	 Mission mission=value.get();  
	 int iddept=mission.getDepartement().getId();
	 assertEquals(1,iddept);
 }


 
	
	
	
}
	
	@Test
	public void testAjouterTimesheet() throws ParseException {
SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date db=dataFormat.parse("2020-10-23");
		Date df =dataFormat.parse("2020-10-25");
		
	 TimesheetPK timesheetPK = new TimesheetPK(5,1, db, df);
	tM.ajouterTimesheet(5, 1, db, df);
	
	int idm=timesheetPK.getIdMission();
	
		assertEquals(5,idm);
	}


	@Test
	public void testAllEmployeByMission() {
		
		List<Employe> listEmploye = tM.getAllEmployeByMission(1);
	 assertEquals(1,listEmploye.size());
	}  
	
	
	@Test
	public void testAllMissionByEmploye() {
		
		List<Mission> listMission = tM.findAllMissionByEmployeJPQL(1);
	 assertEquals(4,listMission.size());
	}  
	
	@Test
	public void valider() throws ParseException {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date db=dataFormat.parse("2020-10-23");
		Date df =dataFormat.parse("2020-10-25");
		Optional <Employe> value =employeRepository.findById(1);
		if (value.isPresent()) {
			Employe validateur=value.get();
			TimesheetPK timesheetPK = new TimesheetPK(3,1,db,df);
			Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
	validateur.setRole(Role.CHEF_DEPARTEMENT);
			
			tM.validerTimesheet(3,1,db,df,1);
			timesheet.setValide(true);
			
			int idv=validateur.getId();
			
		 assertEquals(1,idv);

		}
		
		
	
		
	}  */
	
}