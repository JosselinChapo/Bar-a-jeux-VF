package bar.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.exception.IdException;
import bar.exception.ReservationException;
import bar.model.Reservation;
import bar.repository.IReservationRepository;
import bar.repository.ITableRepository;

@Service
public class ReservationService {
	
	@Autowired
	private IReservationRepository resaRepo;

	@Autowired
	private ITableRepository tableRepo;
	
	
	public static Integer resaMaxJour4 = 30;
	public static Integer resaMaxJour6 = 12;
	public static Integer resaMaxJour8 = 12;
	
	
	
	public static Integer resaMaxHeure4 =4;
	public static Integer resaMaxHeure6 =2;
	public static Integer resaMaxHeure8 =2;
	
	
	// creation réservation
		public Reservation create(Reservation resa) {
			checkNotNull(resa);
			checkConstraint(resa);
			System.out.println(resa);
			return resaRepo.save(resa);
		}
		
		private void checkNotNull(Reservation resa) {
			if (resa == null) {
				//System.out.println("réservation obligatoire");
				throw new ReservationException("réservation obligatoire");
			}
		}
		
		private void checkConstraint(Reservation resa) {
			if (resa.getDateRes() == null) {
				throw new ReservationException("date obligatoire");
			}
			if (resa.getHeureRes() == null) {
				throw new ReservationException("heure obligatoire");
			}
//			if (resa.getClient() == null) {
//				throw new ReservationException("client obligatoire");
//			}
			if (resa.getNbPersonne() == 0) {
				throw new ReservationException("personne obligatoire");
			}
			if (resa.getTable() == null) {
				//System.out.println("table obligatoire");
				throw new ReservationException("table obligatoire");
			}
			// donner un jeu n'est pas obligatoire
		}
		
		private void checkId(Integer id) {
			if (id == null) {
				throw new IdException("id obligatoire");
			}
			if (!findAllId().contains(id)) {
				throw new IdException("id introuvable");
			}
		}

		private void checkExist(Reservation resa) {
			checkId(resa.getId());
			findById(resa.getId());
		}

		public void delete(Reservation resa) {
			checkExist(resa);
			resaRepo.delete(resa);
		}

		public void delete(Integer id) {
			delete(findById(id));
		}
		
		public Reservation update(Reservation resa) {
			checkNotNull(resa);
			checkExist(resa);
			checkConstraint(resa);
			Reservation resaEnBase = findById(resa.getId());
			resaEnBase.setDateRes(resa.getDateRes());
			resaEnBase.setHeureRes(resa.getHeureRes());
			resaEnBase.setClient(resa.getClient());
			resaEnBase.setNbPersonne(resa.getNbPersonne());
			// donner un jeu n'est pas obligatoire
			if (resa.getJeu() != null) {
				resaEnBase.setJeu(resa.getJeu());
			}
			return resaRepo.save(resaEnBase);
		}
		
		
		public Reservation findById(Integer id) {
			checkId(id);
			return resaRepo.findById(id).orElseThrow(IdException::new);
		}
		
		public List<Integer> findAllId(){
			return resaRepo.findAllId();
		}
		
		public List<Reservation> findAll(){
			return resaRepo.findAll();
		}
		
		//Cettte fonction renvoi toutes les dates completes (plus de réservation) pour un nombre de personne précis
		public List<LocalDate> findAllDisableDate(int nbPersonne){
			Collection<LocalDate> dates = resaRepo.findAllDate(nbPersonne);
//			System.out.println("bean "+resaMaxJour);
//			List<LocalDate> dateUnique = resaRepo.findUniqueDate(nbPersonne);
//			récupération des valeurs uniques des dates dans la variable dates provenant de l'ensemble des réservations
			List<LocalDate> dateUnique = dates.stream().distinct().collect(Collectors.toList());
			List<LocalDate> dateDisables = new ArrayList<>();
			for (var date : dateUnique) {
				// compte le nombre de fois que la date "date" apparait dans la liste "dates"
				int occurrences = Collections.frequency(dates, date);
				if (nbPersonne==4 && occurrences==resaMaxJour4) {
					dateDisables.add(date);
				} else if (nbPersonne==6 && occurrences==resaMaxJour6) {
					dateDisables.add(date);
					
				} else if (nbPersonne==8 && occurrences==resaMaxJour8){
					dateDisables.add(date);
				}
			}
			return dateDisables;
		}

		// Cette fonction renvoie tous les crénaux complets pour une journée précise et un nombre de personne précis
		public List<LocalTime> findAllDisableHeureparDate(int nbPersonne, LocalDate dates){
			Collection<LocalTime> heures = resaRepo.findAllCrenauParDate(nbPersonne,dates);
//			List<LocalDate> dateUnique = resaRepo.findUniqueDate(nbPersonne);
			// récupération des valeurs uniques des dates dans la variable dates provenant de l'ensemble des réservations
			List<LocalTime> heureUnique = heures.stream().distinct().collect(Collectors.toList());
			List<LocalTime> heureDisables = new ArrayList<>();
			for (var heure : heureUnique) {
				// compte le nombre de fois que l'heure "heure" apparait dans la liste "heures"
				int occurrences = Collections.frequency(heures, heure);
				if (nbPersonne==4 && occurrences==resaMaxHeure4) {
					heureDisables.add(heure);
				} else if (nbPersonne==6 && occurrences==resaMaxHeure6) {
					heureDisables.add(heure);
					
				} else if (nbPersonne==8 && occurrences==resaMaxHeure8){
					heureDisables.add(heure);
				}
			}
			return heureDisables;
		}
		
		// Cette fonction renvoie la liste des réservations d'un client (par son id)
		public List<Reservation> findAllByClientId(Integer id){
			return resaRepo.findAllByClientId(id);
		}
		
		// Cette fonction renvoie la liste des réservations suivantes d'un client (par son id)
		public List<Reservation> findAllByAfterDateRes (Integer id){
			return resaRepo.findAllByAfterDateRes(LocalDate.now(), id);
		}
		
		// Cette fonction renvoie la liste des réservations passées d'un client (par son id)
		public List<Reservation> findAllByBeforeDateRes (Integer id){
			return resaRepo.findAllByBeforeDateRes(LocalDate.now(), id);
		}
		
		// Cette fonction renvoie la liste d'id des tables non disponible (l'attribut pas celui de la base)
		public List<Integer> findAllIdByDateResandHeureRes (int nbPersonne,LocalDate date,LocalTime heure){
			List<Integer> idTableDisable = resaRepo.findAllIdByDateResandHeureRes(date, heure);
			List<Integer> idTableDispo = tableRepo.findAllIdTablebyNbPersonne(nbPersonne);
			
			idTableDispo.removeIf(x -> idTableDisable.contains(x));
			
			return idTableDispo;
		}
		
		// Cette fonction renvoie la liste d'id des tables non disponible (l'attribut pas celui de la base)
		public List<Integer> findAllIdTablebyNbPersonne (int nbPersonne){
			List<Integer> idTableDisable = tableRepo.findAllIdTablebyNbPersonne(nbPersonne);
			return idTableDisable;
		}
		
		//Cette fonction donc la liste des heures disponible à une certaines dates et un autre de personne précis
		public List<LocalTime> AllEnableHeures (int nbPersonne, LocalDate dates) {
			List<LocalTime> heuresEnable = new ArrayList<>();
			heuresEnable.add(LocalTime.parse("18:00"));
			heuresEnable.add(LocalTime.parse("19:00"));
			heuresEnable.add(LocalTime.parse("20:00"));
			heuresEnable.add(LocalTime.parse("21:00"));
			heuresEnable.add(LocalTime.parse("22:00"));
			heuresEnable.add(LocalTime.parse("23:00"));
			
			List<LocalTime> heuresDisable = findAllDisableHeureparDate(nbPersonne,dates);
			
			heuresEnable.removeIf(x -> heuresDisable.contains(x));
			
			return heuresEnable;
		}

}
