package me.mouhoub.alphaGene.genetics;

import java.util.Random;

import me.mouhoub.alphaGene.gui.Carte;

/***********************************************************************
 * La classe population est représente l'ensemble des Individus, Elle contient
 * les classes de gestion d'une population d'individus. Elle fait : L'évaluation
 * et L'évolution de la population
 ***********************************************************************/

public class Population {

	/****
	 * VARIABLES
	 * ////////////////////////////////////////////////////////////////
	 * ///////////
	 */

	/** Sert à créer quelque chose du hasard */
	protected static Random hasard = new Random();

	/** La population est un ensemble (vecteur) d'individus */
	VecteurIndividus population;

	/**
	 * Cette population a un nombre maximal d'individus
	 * "nombreMaxIndividus"(int)
	 */
	protected int nombreMaxIndividus;

	/**
	 * Lorsque des individus meurrent c'est selon un taux de mortalit? (la
	 * moiti?)
	 */
	private final double MORTALITE = 0.5;

	/** un individu mute avec la probabilit? "probaMutation" (double) */
	private final double PROBA_MUTATION_INDIVIDU = 0.5;

	/**
	 * La population evolue mais selon certaines limites _ Stopper l'?volution
	 * lorsqu'elle stagne sur plus de "stagneMax" (int) g?n?ration _ Stopper
	 * l'?volution lorsqu'elle depasse la g?n?ration "generationsMax" (int)
	 */

	/** private final int NB_GENERATIONS_STAG = 300; */
	protected int generationsMax;

	// Pour se souvenir :

	/** nombre de generations sans evolution du meilleur individu */
	protected int nombreGenerationsStagnantes = 0;
	/** valeur d'adaptation du meilleur individu */
	protected double meilleureAdaptation = 0;
	/** le nombre de generations effectues */
	protected int numGeneration = 0;

	// CONSTRUCTEUR /////////////////////////////////

	/** constuire la population */
	public Population(int leNombreMaxIndividus, int leGenerationMax,
			Carte laConfigurationVilles) {
		// initialisation des variables
		nombreMaxIndividus = leNombreMaxIndividus;
		numGeneration = 0;
		generationsMax = leGenerationMax;

		// cr?ation d'une population initiale appel?e "generation 0"
		population = new VecteurIndividus();
		for (int i = 0; i < leNombreMaxIndividus; i++) {
			population.insererIndividuTrier(new Individu(0,
					laConfigurationVilles));
		}
	}

	// METHODES /////////////////////////////////

	/*
	 * I) Evaluation de la population !!! _____________________________
	 */

	/** retourne le meilleur individu */
	public Individu meilleurIndividu() {
		return population.premierIndividu();
	}

	/** retourne le pire individu */
	public Individu pireIndividu() {
		return population.dernierIndividu();
	}

	/** retourne la valeur d'adaptation du meilleur individu */
	public double meilleureValeurAdaptation() {
		return meilleurIndividu().valeurAdaptation();
	}

	/** retourne le nombre d'individus de la population */
	public int nombreIndividus() {
		return population.taille();
	}

	/** retourne le nombre de g?n?ration effectu?es */
	public int generationEnCours() {
		return numGeneration;
	}

	/** test si le calcul est fini */
	public boolean testFinDeCalcul() {
		return /* testFinStagnation() || */testFinDerniereGeneration();

	}

	/* test si on a atteint le nombre de stagnation maximum */
	/*
	 * public boolean testFinStagnation(){ if ( meilleureValeurAdaptation() >=
	 * meilleureAdaptation ) { nombreGenerationsStagnantes++; if (
	 * nombreGenerationsStagnantes >= NB_GENERATIONS_STAG){ return true; }else{
	 * return false; } }else{ nombreGenerationsStagnantes = 0;
	 * meilleureAdaptation = meilleureValeurAdaptation(); return false; } }
	 */

	/** test si ont a atteint le nombre maximum de g?n?rations */
	public boolean testFinDerniereGeneration() {
		return (numGeneration >= generationsMax);
	}

	/*
	 * II) SELECTION ::: ?liminer ou consever la moiti? individus de la
	 * population
	 * ________________________________________________________________________
	 */

	/** retourne le nombre d'individus remplac?s */
	public int nombreIndividusRemplace() {
		return (int) Math.floor(MORTALITE * nombreMaxIndividus);
	}

	/** retourne le nombre d'individus conserv?s */
	public int nombreIndividusConserve() {
		return nombreMaxIndividus - nombreIndividusRemplace();
	}

	/** selectionnner l'individu qui va mourir */
	private int choisirUnTuer() {
		return population.taille() - 1;
	}

	/**
	 * tuer les individus ind?sirables : élimination d'une partie de la
	 * population selon taux de mortalite
	 */
	public void selection() {
		while (population.taille() > nombreIndividusConserve()) {
			if (population.taille() > 1) {
				population.enleverIndividuA(choisirUnTuer());
			}
		}
	}

	/*
	 * III) Reproduction ::: CROISEMENT MUTATION reg?n?ration des individus
	 * Manquant par croisement-mutation
	 * _____________________________________________________________________________________
	 */

	/**
	 * s?l?ctionner des Parents al?atoirement et les mettre dans un vecteur
	 * "couple" de deux ?l?ments
	 */
	private VecteurIndividus selectionParents() {
		VecteurIndividus couple = new VecteurIndividus();
		// choix totalement al?atoire.
		couple.insererIndividuTrier(population.individuA((int) Math
				.floor(hasard.nextDouble() * this.population.taille())));
		couple.insererIndividuTrier(population.individuA((int) Math
				.floor(hasard.nextDouble() * this.population.taille())));
		return couple;
	}

	// croisement de la popultation

	/**
	 * definition d'une m?thode de croisement limit? par la taille maximale de
	 * la population. les nouveaux individus sont instantan?ment mut?s et
	 * directement reinject?s dans la population. ils peuvent alors de nouveau
	 * ?tre selectionn?es en tant que g?niteur.
	 */
	private void croisement(int leNombreIndividusFinal) {
		VecteurIndividus couple;
		VecteurIndividus enfants = new VecteurIndividus();

		while (nombreIndividus() < leNombreIndividusFinal) {
			// s?l?ction de parents
			couple = selectionParents();
			// croisement.
			enfants = couple.individuA(0).croiser(couple.individuA(1),
					generationEnCours());
			// mutation des enfants.
			for (int i = 0; i < enfants.taille(); i++) {
				if (hasard.nextDouble() < PROBA_MUTATION_INDIVIDU) {
					(enfants.individuA(i)).mutation();
				}
			}
			// insertion des enfants dans la population
			this.population.insererPlusieursIndividusTrier(enfants);
		}
	}

	/*
	 * // pas de m?thode mutation, la mutation ?tant faite en m?me temps que le
	 * croisement. public void mutation(){ }
	 */

	/*
	 * VI) Evolution d'une generation !! _________________________________
	 */

	/** incrementer le numero de generation */
	protected void generationSuivante() {
		numGeneration++;
	}

	/** effectuer une evolution complette */
	public void evolution() {
		generationSuivante();
		selection();
		croisement(nombreMaxIndividus);
	}
}
