package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	private static final Logger l = Logger.getLogger(TimesheetServiceImpl.class);
	
	public int ajouterMission(Mission mission) {
		l.info("In  addMission : " + mission); 
		missionRepository.save(mission);
		l.info("Out of  addMission. "); 
		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		l.info("In  FindMission : " + missionId); 
		l.info("In  FindDepartment : " + depId); 
		
		Optional<Mission> value= missionRepository.findById(missionId);
		
	
		Optional<Departement> value1 =deptRepoistory.findById(depId);
		
		if(value.isPresent() && value1.isPresent()) {
		
			
				Departement dep=value1.get();
				Mission mission=value.get();  
				 mission.setDepartement(dep);
			
		
		missionRepository.save(mission);}
		l.info("Out of  MissionToDepartment. "); 
		    
	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {

		TimesheetPK timesheetPK = new TimesheetPK();
		
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		l.info("In  addTimesheet : " + timesheet); 
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		l.info("Out of  addTimesheet. "); 
		
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
	
		l.info("In valider Timesheet");
		Optional <Employe> value =employeRepository.findById(validateurId);
		
		
		Optional <Mission> value1 =missionRepository.findById(missionId);
		if (value.isPresent() && value1.isPresent()) {
			Employe validateur=value.get();
			Mission mission =value1.get();
		
		
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			l.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
		
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.getDepartements()){
			if(dep.getId() == mission.getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			l.debug("l'employe doit etre chef de departement de la mission en question");
			return;
		}
//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		l.info("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		l.info("OUT of valider Timesheet");
	}
	}
	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		l.info("in GetMissionByEmploye = " + employeId);
		
		List<Mission> m= timesheetRepository.findAllMissionByEmployeJPQL(employeId);
		l.info("Mission Returned: " +m);
		return m;
	}

	
	
	public List<Employe> getAllEmployeByMission(int missionId) {
		l.info("in GetEmploye By Mission id = " + missionId);
		List<Employe> e= timesheetRepository.getAllEmployeByMission(missionId);
		
		l.info("Employees Returned : " +e );
		return e;
	}

}
