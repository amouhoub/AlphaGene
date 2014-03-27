package me.mouhoub.alphaGene.genetics;

/***********************************************************************
 * La classe Gene est le noyeau de la structure de nos classes Elle sert à
 * représenter la ville comme une partie de la solution
 ***********************************************************************/

public class Gene {

	int numVille;

	public Gene(int leNumero) {
		numVille = leNumero;
	}

	/** récupération de la valeur qu'il contient. */
	public int ville() {
		return numVille;
	}

}