package me.mouhoub.alphaGene.gui;

/** la classe de coordonnees dans un plan. */
class Coordonnee {

	private double x, y;

	Coordonnee(double leX, double leY) {
		x = leX;
		y = leY;
	}

	/** calcul de la distance entre cette coordonnee et une autre. */
	public double distance(Coordonnee b) {
		return Math.sqrt((this.x - b.x) * (this.x - b.x) + (this.y - b.y)
				* (this.y - b.y));
	}

	/** convertion des coordonnees en coordonnee graphique pour x */
	public int convertirX(int largeur, int abscisseMax) {
		return (int) Math.floor(((this.x + abscisseMax) * largeur)
				/ (2 * abscisseMax));
	}

	/** pour y */
	public int convertirY(int hauteur, int ordonneeMax) {
		return (int) Math.floor(((this.y + ordonneeMax) * hauteur)
				/ (2 * ordonneeMax));
	}
}
