package me.mouhoub.alphaGene.core;

import me.mouhoub.alphaGene.genetics.Individu;
import me.mouhoub.alphaGene.genetics.Population;
/*
 * AlgoGenetique.java
 *  
 *  R?alis? par :
 *  			 Mouhoub Mohammed Amine
 *  
 *  Centre Universitaire de Bordj Bou Arreridj - Alg?rie
 *  3?me ann?e LMD d?cisionnel
 *  Janvier 2009
 */
import me.mouhoub.alphaGene.gui.AlphaGene;

/********************************************************************************************
 * la classe AlgoGenetique est la classe executable effectuant le calcul par la
 * methode stochastique qui consiste a trouver toutes les solutions existantes
 * afin de ne retenir que la meilleure. cette classe utilise une interface
 * AlphaGene
 ********************************************************************************************/

public class AlgoGenetique implements Runnable {

	/** la population de voyageur, c'est elle que l'on va faire evoluer */
	private Population population;

	/** l'interface d'affichage des resultats. */
	private AlphaGene interfacePVC;

	/** constructeur initialisant l'interface. */
	public AlgoGenetique(AlphaGene laInterface) {
		interfacePVC = laInterface;
	}

	/** le moteur de calcule avec mise a jour des divers affichages. */
	public void run() {
		// creation de la population
		population = new Population(interfacePVC.nbIndividus(),
				interfacePVC.nbGenerationsMax(), interfacePVC.carte);
		// initialisation de l'interface
		interfacePVC.carte.nouvelleSolution((Individu) population
				.meilleurIndividu());

		// Evolution jusqu'a rencontre d'un crit?re d'arr?t.
		while (!population.testFinDeCalcul()) {

			// passage a la nouvelle generation
			population.evolution();

			/*
			 * eventuelle mise a jour de l'affichage (dessin) du meilleur
			 * parcour////////////
			 */
			if (population.meilleurIndividu().valeurAdaptation() != interfacePVC.carte
					.solutionActuelle().valeurAdaptation()) {
				interfacePVC.carte.nouvelleSolution((Individu) population
						.meilleurIndividu());
			}
			/*
			 * mise a jour des differente valeurs affichees
			 * ////////////////////////////////
			 */

			// la plus courte distance (correspondant a la meilleure valeur)
			interfacePVC.distance.setText(String.valueOf((population
					.meilleureValeurAdaptation())));
			// le numero de la generation actuelle
			interfacePVC.generation.setText(String.valueOf(population
					.generationEnCours()));

		}// fin d'evolution, critere d'arret rencontre

		// reactivation de l'interface.
		interfacePVC.depart.setEnabled(true);
		interfacePVC.nouveau.setEnabled(true);
		interfacePVC.fin.setEnabled(false);
		interfacePVC.leThread = null;
	}

}
