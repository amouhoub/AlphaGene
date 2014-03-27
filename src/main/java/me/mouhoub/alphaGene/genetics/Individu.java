package me.mouhoub.alphaGene.genetics;

import java.util.Random;

import me.mouhoub.alphaGene.gui.Carte;

/************************************************************************************
 * La classe Individu Un individu est represente comme un vecteur de genes (Code
 * G?n?tique) en plus de quelques autres attributs Elle contient aussi les
 * m?thodes de gestion d'un Individu.
 ************************************************************************************/

public class Individu {

	/** Sert à créer quelque chose au hasard */
	private static Random hasard = new Random();

	/** le code génétique de l'individu */
	private VecteurGenes lesGenes = new VecteurGenes();

	/** la g?n?ration de naissance de l'individu */
	private int numGeneration;

	/** la valeur d'adaptation de l'individu */
	private double adaptation;

	/** Carte contenant la configuration des villes */
	private Carte configVilles;

	/** Création à partir d'un heritage d'un parent !!! */
	Individu(int leNumeroGeneration, Carte laConfigurationVilles,
			VecteurGenes heritage) {
		numGeneration = leNumeroGeneration;
		configVilles = laConfigurationVilles;
		lesGenes = heritage;
		calculerAdaptation();
	}

	/** Création aléatoire !!! */
	Individu(int leNumeroGeneration, Carte laConfigurationVilles) {
		numGeneration = leNumeroGeneration;
		configVilles = laConfigurationVilles;
		creerCodeGenetique();
		calculerAdaptation();
	}

	/** retourne sa valeur d'adaptation [[[ La longeur du chemin ]]] */
	public double valeurAdaptation() {
		return adaptation;
	}

	/** retourne son num?ro de g?n?ration de naissance */
	public int generation() {
		return numGeneration;
	}

	/** retourne son nombre de g?nes */
	public int nombreGenes() {
		return lesGenes.taille();
	}

	/** retourne son code g?n?tique */
	public VecteurGenes genes() {
		return lesGenes;
	}

	/* LE CROISEMENT /////////////////////////////////////////////////// */

	/**
	 * Croisement de type "glouton". Les meilleurs caract?ristiques de chaque
	 * parent sont conserv?es dans le fils.
	 */
	public VecteurIndividus croiser(Individu partenaire, int leNumeroGeneration) {
		VecteurIndividus fils = new VecteurIndividus();
		VecteurGenes genesFils = new VecteurGenes();
		int premier;
		int indiceGenePere1avant, indiceGenePere2avant;
		int indiceGenePere1apres, indiceGenePere2apres;
		double distance1, distance2, distance3, distance4, distanceMin;

		// choix al?atoire de la ville de d?part.
		premier = (int) Math.floor(hasard.nextDouble() * nombreGenes()); // premier=
																			// un
																			// indice
																			// qui
																			// est
																			// un
																			// num
																			// d'une
																			// ville
		genesFils.ajouterGene(this.lesGenes.geneA(premier)); // Placer le g?ne
																// indic? par
																// "premier"
																// dans le
																// genesFils

		// algorithme de croisement glouton.
		for (int i = 0; i < (nombreGenes() - 1); i++) {

			// Initialise les indices des lesGenes entourant la l'indice du
			// dernier genesFils

			indiceGenePere1avant = (nombreGenes()
					+ this.lesGenes.indiceGene(genesFils.dernierGene()) - 1)
					% nombreGenes();
			indiceGenePere1apres = (this.lesGenes.indiceGene(genesFils
					.dernierGene()) + 1) % nombreGenes();
			indiceGenePere2avant = (nombreGenes()
					+ partenaire.lesGenes.indiceGene(genesFils.dernierGene()) - 1)
					% nombreGenes();
			indiceGenePere2apres = (partenaire.lesGenes.indiceGene(genesFils
					.dernierGene()) + 1) % nombreGenes();

			// Cherche si le GeneA(indiceParentavap) existe dans le genesFils

			while (genesFils.indiceGene(this.lesGenes
					.geneA(indiceGenePere1avant)) != -1) {
				indiceGenePere1avant = (nombreGenes() + indiceGenePere1avant - 1)
						% nombreGenes();
			}
			while (genesFils.indiceGene(this.lesGenes
					.geneA(indiceGenePere1apres)) != -1) {
				indiceGenePere1apres = (indiceGenePere1apres + 1)
						% nombreGenes();
			}
			while (genesFils.indiceGene(partenaire.lesGenes
					.geneA(indiceGenePere2avant)) != -1) {
				indiceGenePere2avant = (nombreGenes() + indiceGenePere2avant - 1)
						% nombreGenes();
			}
			while (genesFils.indiceGene(partenaire.lesGenes
					.geneA(indiceGenePere2apres)) != -1) {
				indiceGenePere2apres = (indiceGenePere2apres + 1)
						% nombreGenes();
			}
			distance1 = configVilles.distanceEntreVilles(
					((Gene) genesFils.dernierGene()).ville(),
					((Gene) this.lesGenes.geneA(indiceGenePere1avant)).ville());
			distance2 = configVilles.distanceEntreVilles(
					((Gene) genesFils.dernierGene()).ville(),
					((Gene) this.lesGenes.geneA(indiceGenePere1apres)).ville());
			distance3 = configVilles.distanceEntreVilles(((Gene) genesFils
					.dernierGene()).ville(), ((Gene) partenaire.lesGenes
					.geneA(indiceGenePere2avant)).ville());
			distance4 = configVilles.distanceEntreVilles(((Gene) genesFils
					.dernierGene()).ville(), ((Gene) partenaire.lesGenes
					.geneA(indiceGenePere2apres)).ville());

			// Ins?re la plus proche ville ? la ville actuelle dans genesFils

			distanceMin = Math.min(distance1,
					Math.min(distance2, Math.min(distance3, distance4)));
			if (distance1 == distanceMin) {
				genesFils
						.ajouterGene(this.lesGenes.geneA(indiceGenePere1avant));
			} else if (distance2 == distanceMin) {
				genesFils
						.ajouterGene(this.lesGenes.geneA(indiceGenePere1apres));
			} else if (distance3 == distanceMin) {
				genesFils.ajouterGene(partenaire.lesGenes
						.geneA(indiceGenePere2avant));
			} else {
				genesFils.ajouterGene(partenaire.lesGenes
						.geneA(indiceGenePere2apres));
			}
		}
		fils.insererIndividuTrier(new Individu(leNumeroGeneration,
				this.configVilles, genesFils));
		return fils;
	}

	/**
	 * Cr?ation du code g?n?tique de fa?on al?atoire. le code g?n?tique de base
	 * est defini par le carte de fa?on ? avoir une instance unique de chaque
	 * g?ne.
	 */
	public void creerCodeGenetique() {
		lesGenes = configVilles.genesBase();

		for (int i = 0; i < nombreGenes(); i++) {
			int index = (int) Math.floor(hasard.nextDouble()
					* (nombreGenes() - i));
			lesGenes.ajouterGene(lesGenes.geneA(index));
			lesGenes.enleverGeneA(index);
		}
	}

	/**
	 * La mutation de l'individu, un simple ?change de deux lesGenes (ville)
	 * dans l'ordre du parcours.
	 */
	public void mutation() {
		Gene tempon1;
		Gene tempon2;
		int index1, index2;

		index1 = (int) Math.floor(hasard.nextDouble() * nombreGenes());
		tempon1 = (Gene) lesGenes.geneA(index1);
		index2 = (int) Math.floor(hasard.nextDouble() * nombreGenes());
		tempon2 = (Gene) lesGenes.geneA(index2);
		lesGenes.enleverGeneA(index1);
		lesGenes.insererGeneA(tempon2, index1);
		lesGenes.enleverGeneA(index2);
		lesGenes.insererGeneA(tempon1, index2);

		calculerAdaptation();
	}

	/**
	 * calcul de la distance parcourue par le voyageur (distance entre les
	 * villes)
	 */
	public void calculerAdaptation() {
		double distance;

		distance = 0;
		for (int i = 0; i < nombreGenes(); i++) {
			distance += configVilles.distanceEntreVilles(
					((Gene) lesGenes.geneA(i)).ville(),
					((Gene) lesGenes.geneA((i + 1) % nombreGenes())).ville());
		}
		this.adaptation = distance;
	}

}
