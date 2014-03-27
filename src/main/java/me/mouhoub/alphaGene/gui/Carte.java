package me.mouhoub.alphaGene.gui;

/******************************************************************************************************
 * 																									  *
 * La classe Carte h?rit?e de Canvas																	  *
 * Elle repr?sente le graphe ou la carte o? on affiche les parcours									  *
 *																									  *
 ******************************************************************************************************/

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import me.mouhoub.alphaGene.genetics.Individu;
import me.mouhoub.alphaGene.genetics.*;

/**
 * La classe carte est la classe de gestion des villes. Elle contient les
 * instances des genes corespondants. Permet l'evaluation des distances et
 * l'affichage de la solution.
 */

public class Carte extends Canvas {

	private static final long serialVersionUID = 1L;

	/** abscisse et ordonnee entre -COORDONNEE_LIMITE et +COORDONNEE_LIMITE */
	private static final int COORDONNEE_LIMITE = 50;

	/** la taille des marges haut bas droite et gauche. */
	private static final int MARGES = 20;

	/** la taille des croix representant les villes. */
	private static final int TAILLE_CROIX = 5;

	/** de quoi generer du hasard. */
	private Random hasard = new Random();

	/** les villes geographiques. */
	private Coordonnee[] villes;

	/** le vecteur d'instance de GeneVille. */
	private VecteurGenes genesBase;

	/** le nombre de villes du probleme. */
	private int nombreVilles;

	/** la solution affichee. */
	private Individu solution;

	/**** constructeur ****/
	Carte(int leNombreVilles) {
		nombreVilles = leNombreVilles;
		villes = new Coordonnee[leNombreVilles];
		genesBase = new VecteurGenes();
		for (int i = 0; i < nombreVilles; i++) {
			villes[i] = new Coordonnee(hasard.nextDouble() * COORDONNEE_LIMITE
					* 2 - COORDONNEE_LIMITE, hasard.nextDouble()
					* COORDONNEE_LIMITE * 2 - COORDONNEE_LIMITE);
			genesBase.ajouterGene(new Gene(i));
		}
	}

	/** reinitialise le carte avec de nouvelles villes. */
	public void nouveauPays(int leNombreVilles) {
		nombreVilles = leNombreVilles;
		villes = new Coordonnee[nombreVilles];
		genesBase = new VecteurGenes();
		solution = null;
		for (int i = 0; i < nombreVilles; i++) {
			villes[i] = new Coordonnee(hasard.nextDouble() * COORDONNEE_LIMITE
					* 2 - COORDONNEE_LIMITE, hasard.nextDouble()
					* COORDONNEE_LIMITE * 2 - COORDONNEE_LIMITE);
			genesBase.ajouterGene(new Gene(i));
		}
		paint(getGraphics());
	}

	/**
	 * recuperer un clone du vecteur contenant les instances des genes GeneVille
	 */
	public VecteurGenes genesBase() {
		return genesBase.cloner();
	}

	/** le nombre de villes du carte. */
	int nombreVilles() {
		return nombreVilles;
	}

	/**
	 * dessine le carte: 1) dabort les arcs de la solution 2) puis les
	 * emplacements des villes.
	 */
	public void paint(Graphics g) {

		if (solution != null) {
			dessinDesArcs(g, solution.genes());
		} else {
			g.clearRect(0, 0, getSize().width, getSize().height);
		}
		dessinDeBase(g);
	}

	/** dessine les arcs du vecteur solution entre les villes */
	private void dessinDesArcs(Graphics g, VecteurGenes lesGenes) {
		int villeS, villeD;
		int xTempVS, yTempVS, xTempVD, yTempVD;

		g.clearRect(0, 0, getSize().width, getSize().height);
		for (int i = 0; i < solution.nombreGenes(); i++) {
			villeS = ((Gene) lesGenes.geneA(i)).ville();
			villeD = ((Gene) lesGenes.geneA((i + 1) % solution.nombreGenes()))
					.ville();
			xTempVS = villes[villeS].convertirX(this.getSize().width - MARGES
					* 2, COORDONNEE_LIMITE)
					+ MARGES;
			yTempVS = villes[villeS].convertirY(this.getSize().height - MARGES
					* 2, COORDONNEE_LIMITE)
					+ MARGES;
			xTempVD = villes[villeD].convertirX(this.getSize().width - MARGES
					* 2, COORDONNEE_LIMITE)
					+ MARGES;
			yTempVD = villes[villeD].convertirY(this.getSize().height - MARGES
					* 2, COORDONNEE_LIMITE)
					+ MARGES;
			g.drawLine(xTempVS, yTempVS, xTempVD, yTempVD);
		}
	}

	/** dessine une petite croix rouge a l'emplacement de chaque ville ! */
	private void dessinDeBase(Graphics g) {
		int xTemp, yTemp;

		g.setFont(new Font("Arial", Font.BOLD, 12));
		// FontMetrics fM = g.getFontMetrics();
		// On efface le precedent dessin
		g.setColor(Color.red);
		for (int i = 0; i < nombreVilles; i++) {
			xTemp = villes[i].convertirX(this.getSize().width - MARGES * 2,
					COORDONNEE_LIMITE) + MARGES;
			yTemp = villes[i].convertirY(this.getSize().height - MARGES * 2,
					COORDONNEE_LIMITE) + MARGES;

			// g.drawLine(xTemp-TAILLE_CROIX,yTemp,xTemp+TAILLE_CROIX,yTemp);
			// g.drawLine(xTemp,yTemp-TAILLE_CROIX,xTemp,yTemp+TAILLE_CROIX);
			g.fillOval(xTemp - (TAILLE_CROIX / 2), yTemp - (TAILLE_CROIX / 2),
					TAILLE_CROIX, TAILLE_CROIX);
		}
		g.setColor(Color.blue);
		for (int i = 0; i < nombreVilles; i++) {
			xTemp = villes[i].convertirX(this.getSize().width - MARGES * 2,
					COORDONNEE_LIMITE) + MARGES;
			yTemp = villes[i].convertirY(this.getSize().height - MARGES * 2,
					COORDONNEE_LIMITE) + MARGES;

			// g.drawLine(xTemp-TAILLE_CROIX,yTemp,xTemp+TAILLE_CROIX,yTemp);
			// g.drawLine(xTemp,yTemp-TAILLE_CROIX,xTemp,yTemp+TAILLE_CROIX);
			g.drawString("" + i, xTemp - (TAILLE_CROIX / 2) + 10, yTemp
					- (TAILLE_CROIX / 2) + 10);
		}

		g.setColor(Color.black);
	}

	/**
	 * mise a jour du graphique lors de la mise en place d'une nouvelle
	 * solution.
	 * 
	 * @param uneSolution
	 */
	public void nouvelleSolution(Individu uneSolution) {
		Graphics leg = getGraphics();
		solution = uneSolution;
		paint(leg);
	}

	/** retourne la solution qui est actuellement affichee. */
	public Individu solutionActuelle() {
		return solution;
	}

	/**
	 * permet de calculer la distance entre deux villes du carte a partir de
	 * leurs indices.
	 */
	public double distanceEntreVilles(int i, int j) {
		return villes[i].distance(villes[j]);
	}
}
