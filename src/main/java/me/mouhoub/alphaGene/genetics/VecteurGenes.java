package me.mouhoub.alphaGene.genetics;

import java.util.Vector;

/************************************************************************************
 * La classe VecteurGenes sert ? repr?senter les genes de l'individu dans un
 * vecteur Ce vecteur ne peut contenir que des objets Gene La classe contient
 * les m?thodes de gestion du vecteur
 ************************************************************************************/

public class VecteurGenes extends Vector<Gene> {

	private static final long serialVersionUID = 1L;

	// METHODES /////////////////////////////////

	/** retourne le nombre de g?nes dans le vecteur */
	int taille() {
		return this.size();
	}

	/** retourne le g?ne a l'indice "leIndice" */
	public Gene geneA(int leIndice) {
		return (Gene) this.elementAt(leIndice);
	}

	/** retourne le premier g?ne du vecteur */
	Gene premierGene() {
		return (Gene) this.firstElement();
	}

	/** retourne le dernier g?ne du vecteur */
	Gene dernierGene() {
		return (Gene) this.lastElement();
	}

	/** ajoute un g?ne "leGene" a la fin de ce VecteurGenes */
	public void ajouterGene(Gene leGene) {
		this.addElement(leGene);
	}

	/** supprime le g?ne situe ? l'indice "leIndice" */
	void enleverGeneA(int leIndice) {
		this.removeElementAt(leIndice);
	}

	/** enlever le g?ne d'instance "leGene" si pr?sent dans ce vecteur */
	void enleverGene(Gene leGene) {
		this.removeElement(leGene);
	}

	/** insere le G?ne "leGene" a l'indice "leIndice" */
	void insererGeneA(Gene leGene, int index) {
		this.insertElementAt(leGene, index);
	}

	/** retourne l'indice du g?ne d'instance "leGene" dans ce vecteur */
	int indiceGene(Gene leGene) {
		return this.indexOf(leGene);
	}

	/** retourne une copie de ce vecteur */
	public VecteurGenes cloner() {
		return (VecteurGenes) this.clone();
	}
}
