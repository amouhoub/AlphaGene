package me.mouhoub.alphaGene.genetics;

/*
 * VecteurIndividus.java
 *  
 *  R?alis? par :
 *  			 Mouhoub Mohammed Amine
 *  
 *  Centre Universitaire de Bordj Bou Arreridj - Alg?rie
 *  3?me ann?e LMD d?cisionnel
 *  Janvier 2009
 */

import java.util.Vector;

/****************************************************************************************************************
 * * La classe VecteurIndividu repr?sente la population sous forme d'un vecteur
 * * Les individus sont tri?s en ordre d?scendant du meilleur au pire,(en
 * foction de leurs valeurs d'adaptation) * Elle contient les m?thodes de
 * gestion du vecteur de la population * Le vecteur ne peut contenir que des
 * objets Individu * *
 ****************************************************************************************************************/

class VecteurIndividus extends Vector<Individu> {

	private static final long serialVersionUID = 1L;

	// METHODES /////////////////////////////////

	/* retourne le nombre d'individus que contient le vecteur */
	int taille() {
		return this.size();
	}

	/* retourne l'individu d'indicie "leIndice" */
	Individu individuA(int leIndice) {
		return (Individu) this.elementAt(leIndice);
	}

	/*
	 * retourne le premier individu du vecteur (celui qui a la plus petite
	 * valeur d'adaptation)
	 */
	Individu premierIndividu() {
		return (Individu) this.firstElement();
	}

	/*
	 * retourne le dernier individu du vecteur (celui qui a la plus grande
	 * valeur d'adaptation)
	 */
	Individu dernierIndividu() {
		return (Individu) this.lastElement();
	}

	/* enleve l'individu se trouvant a l'indice "leIndice" */
	void enleverIndividuA(int leIndice) {
		this.removeElementAt(leIndice);
	}

	/* retourne une copie de ce vecteur */
	VecteurIndividus cloner() {
		return (VecteurIndividus) this.clone();
	}

	/*
	 * insertion tri? des individus dans la population par ordre croissant de
	 * leurs valeurs d'adaptation recherche dicotomique on ne sait pas a quel
	 * indice l'individu est insere
	 */
	void insererIndividuTrier(Individu leIndividu) {
		int inf = 0;
		int sup;

		sup = this.taille() - 1;
		if (sup == -1) {
			this.addElement(leIndividu);
		} else if (leIndividu.valeurAdaptation() >= (this.dernierIndividu())
				.valeurAdaptation()) {
			this.addElement(leIndividu);
		} else if (leIndividu.valeurAdaptation() <= (this.premierIndividu())
				.valeurAdaptation()) {
			this.insertElementAt(leIndividu, 0);
		} else {
			while ((sup - inf) > 1) {
				if ((this.individuA((int) Math.floor((sup + inf) / 2)))
						.valeurAdaptation() > leIndividu.valeurAdaptation())
					sup = (int) Math.floor((sup + inf) / 2);
				else
					inf = (int) Math.floor((sup + inf) / 2);
			}
			this.insertElementAt(leIndividu, inf + 1);
		}
	}

	/*
	 * reclassement de l'individu place a l'indice "leIndice" (cas ou sa valeur
	 * d'adaptation a chang?e)
	 */
	void reclasserIndividuA(int leIndice) {
		Individu temp;
		temp = this.individuA(leIndice); /* memorisation */
		this.enleverIndividuA(leIndice); /* supression */
		this.insererIndividuTrier(temp); /* reinsertion */
	}

	/* fusion du vecteur d'individus "source" dans celui ci. */
	void insererPlusieursIndividusTrier(VecteurIndividus source) {
		for (int i = 0; i < source.taille(); i++) {
			insererIndividuTrier(source.individuA(i));
		}
	}

}
