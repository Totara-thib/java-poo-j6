package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws ReflectionException {


		List<Ville> villes = rec.getVilles();
		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		if (!NumberUtils.isDigits(saisieMin)) {
			throw new ReflectionException("Vous devez renseigner un chiffre");
		} else if (Integer.parseInt(saisieMin) < 0) {
			throw new ReflectionException("Le chiffre doit être supérieur à 0");
		} else {
			System.out.println("Choississez une population maximum (en milliers d'habitants): ");
			String saisieMax = scanner.nextLine();
			if (!NumberUtils.isDigits(saisieMax)) {
				throw new ReflectionException("Vous devez renseigner un chiffre");
			} else if (Integer.parseInt(saisieMax) < 0) {
				throw new ReflectionException("Le chiffre doit être supérieur à 0");
			} else if (Integer.parseInt(saisieMin) > Integer.parseInt(saisieMax)) {
				throw new ReflectionException("Le chiffre max doit être supérieur au chiffre min");
			} else {

				int min = Integer.parseInt(saisieMin) * 1000;
				int max = Integer.parseInt(saisieMax) * 1000;
				boolean checker = false;
				for (Ville ville : villes) {
					if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
						
						if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
							checker = true;
							System.out.println(ville);
						}
						
					} 
				}
				if (checker == false) {
					throw new ReflectionException("Le département renseigné n'existe pas");
				}
			}
		}
	}

}
