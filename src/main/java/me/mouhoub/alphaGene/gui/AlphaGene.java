package me.mouhoub.alphaGene.gui;

import java.awt.Graphics;

import me.mouhoub.alphaGene.core.AlgoGenetique;

/****************************************************************************************************
 * La fenetre principale du programme appele AlphaGene !!!
 ***************************************************************************************************/

public class AlphaGene extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Declarations Necessaires
	 */

	public Carte carte;
	Graphics g;

	/** Nombre d'individus */
	private final int NB_INDIVIDUS = 200;
	private int nbIndividus = NB_INDIVIDUS;

	/** Nombre de generation maximum */
	private final int NB_GENERATIONS_MAX = 500;
	private int nbGenerationsMax = NB_GENERATIONS_MAX;

	/** Nombre de Genes */
	private final int NOMBRE_DE_GENES = 40;
	private int nombreDeGenes = NOMBRE_DE_GENES;

	// II) Les outils de calcul
	// la resoultion par algorithme genetique
	AlgoGenetique resolutionAG;
	// le thread pour les calcul
	public Thread leThread = null;

	/**
	 * Fin
	 * //////////////////////////////////////////////////////////////////////
	 * //////////////////
	 */

	/** lorsque le programme est demarree, initialisation des graphiques. */
	public void start() {
		carte.paint(carte.getGraphics());
	}

	/** Fin du Programme, arret du thread en cours */
	@SuppressWarnings("deprecation")
	public void stop() {
		if (leThread != null) {
			leThread.stop();
			leThread = null;
		}
	}

	/** Retourne le nombre d'individus que devra comporter la population */
	public int nbIndividus() {
		return nbIndividus;
	}

	/**
	 * Retourne le nombre maximum de generations que devra comporter la
	 * resolution
	 */
	public int nbGenerationsMax() {
		return nbGenerationsMax;
	}

	/**
	 * Retourne le nombre de genes qui devar etre utilise par le generateur de
	 * graphes
	 */
	public int nombreDeGenes() {
		return nombreDeGenes;
	}

	/** Creates new form AlphaGene */
	public AlphaGene() {
		initComponents();
	}

	private void initComponents() {

		/*
		 * Initialisations necessaires
		 * /////////////////////////////////////////////////////////////////
		 */

		g = getGraphics();
		carte = new Carte(this.nombreDeGenes());

		/*
		 * Declaration des composants
		 * //////////////////////////////////////////////////////////////////
		 */

		conteneur = new javax.swing.JPanel();
		panneauDeControl = new javax.swing.JPanel();
		depart = new javax.swing.JButton();
		fin = new javax.swing.JButton();
		nouveau = new javax.swing.JButton();
		parametres = new javax.swing.JPanel();
		texteNombreDeGenes = new javax.swing.JTextField();
		texteNbIndividus = new javax.swing.JTextField();
		texteNbGenerationMax = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		afficheur = new javax.swing.JPanel();
		generation = new javax.swing.JTextField();
		distance = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		grapheur = new javax.swing.JPanel();
		logo = new javax.swing.JLabel();

		/*
		 * Declaration de la fenetre principale
		 * //////////////////////////////////////////////////////////////////
		 */

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("AlphaGene");
		setResizable(false);

		conteneur.setBackground(new java.awt.Color(204, 204, 255));
		conteneur.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		/*
		 * Declaration de la table des parametres
		 * //////////////////////////////////////////////////////////////////
		 */

		panneauDeControl.setBackground(new java.awt.Color(217, 224, 231));
		panneauDeControl.setBorder(javax.swing.BorderFactory
				.createEtchedBorder());
		panneauDeControl.setPreferredSize(new java.awt.Dimension(170, 144));

		/*
		 * Declaration des bouttons
		 */

		depart.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"depart1.png")));
		depart.setToolTipText("Demarrer le parcours");
		depart.setBorder(null);
		depart.setBorderPainted(false);
		depart.setContentAreaFilled(false);
		depart.setDisabledIcon(new javax.swing.ImageIcon(getClass()
				.getResource("depart7.png")));
		depart.setFocusPainted(false);
		depart.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource(
				"depart5.png")));
		depart.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("depart2.png")));
		depart.setSelectedIcon(new javax.swing.ImageIcon(getClass()
				.getResource("depart2.png")));
		depart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				departActionPerformed(evt);
			}
		});

		fin.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("fin1.png")));
		fin.setToolTipText("Terminer le parcour en cours");
		fin.setBorder(null);
		fin.setBorderPainted(false);
		fin.setContentAreaFilled(false);
		fin.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource(
				"fin7.png")));
		fin.setFocusPainted(false);
		fin.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource(
				"fin5.png")));
		fin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(
				"fin2.png")));
		fin.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource(
				"fin2.png")));
		fin.setEnabled(false);
		fin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				finActionPerformed(evt);
			}
		});

		nouveau.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"nouveau1.png")));
		nouveau.setToolTipText("Demarrer un nouveau voyage avec une nouvelle carte");
		nouveau.setBorder(null);
		nouveau.setBorderPainted(false);
		nouveau.setContentAreaFilled(false);
		nouveau.setDisabledIcon(new javax.swing.ImageIcon(getClass()
				.getResource("nouveau7.png")));
		nouveau.setFocusPainted(false);
		nouveau.setPressedIcon(new javax.swing.ImageIcon(getClass()
				.getResource("nouveau5.png")));
		nouveau.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("nouveau2.png")));
		nouveau.setSelectedIcon(new javax.swing.ImageIcon(getClass()
				.getResource("nouveau2.png")));
		nouveau.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nouveauActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout panneauDeControlLayout = new javax.swing.GroupLayout(
				panneauDeControl);
		panneauDeControl.setLayout(panneauDeControlLayout);
		panneauDeControlLayout
				.setHorizontalGroup(panneauDeControlLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panneauDeControlLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												panneauDeControlLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																depart,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																141,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																nouveau,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																142,
																Short.MAX_VALUE)
														.addComponent(
																fin,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		panneauDeControlLayout
				.setVerticalGroup(panneauDeControlLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panneauDeControlLayout
										.createSequentialGroup()
										.addComponent(
												depart,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												41,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												nouveau,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												fin,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												43,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		parametres.setBackground(new java.awt.Color(217, 224, 231));
		parametres.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		texteNombreDeGenes.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
		texteNombreDeGenes
				.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
		texteNombreDeGenes.setText("40");

		texteNbIndividus.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
		texteNbIndividus
				.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
		texteNbIndividus.setText("200");

		texteNbGenerationMax.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
		texteNbGenerationMax
				.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
		texteNbGenerationMax.setText("500");

		jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 18));
		jLabel1.setText("Villes");

		jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 18));
		jLabel2.setText("Generations");

		jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 18));
		jLabel3.setText("Individus");

		javax.swing.GroupLayout parametresLayout = new javax.swing.GroupLayout(
				parametres);
		parametres.setLayout(parametresLayout);
		parametresLayout
				.setHorizontalGroup(parametresLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								parametresLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												parametresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																parametresLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel3)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				44,
																				Short.MAX_VALUE)
																		.addComponent(
																				texteNbIndividus,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				55,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																parametresLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				69,
																				Short.MAX_VALUE)
																		.addComponent(
																				texteNombreDeGenes,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				55,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																parametresLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				22,
																				Short.MAX_VALUE)
																		.addComponent(
																				texteNbGenerationMax,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				55,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		parametresLayout
				.setVerticalGroup(parametresLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								parametresLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												parametresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																texteNombreDeGenes,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												parametresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																texteNbGenerationMax,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel2))
										.addGap(11, 11, 11)
										.addGroup(
												parametresLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																texteNbIndividus,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		/*
		 * Declaration de l'afficheur
		 * //////////////////////////////////////////////////////////////////
		 */

		afficheur.setBackground(new java.awt.Color(217, 224, 231));
		afficheur.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		generation.setEditable(false);
		generation.setFont(new java.awt.Font("Arial Narrow", 1, 18));
		generation.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

		distance.setEditable(false);
		distance.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
		distance.setForeground(new java.awt.Color(0, 102, 153));
		distance.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

		jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 18));
		jLabel4.setText("Meilleure distance");

		jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 18));
		jLabel5.setText("Generation en cours");

		javax.swing.GroupLayout afficheurLayout = new javax.swing.GroupLayout(
				afficheur);
		afficheur.setLayout(afficheurLayout);
		afficheurLayout
				.setHorizontalGroup(afficheurLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								afficheurLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												afficheurLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																afficheurLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel5)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				generation,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				97,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																afficheurLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel4)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				distance,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				112,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		afficheurLayout
				.setVerticalGroup(afficheurLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								afficheurLayout
										.createSequentialGroup()
										.addGap(27, 27, 27)
										.addGroup(
												afficheurLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																distance,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																35,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4))
										.addGap(18, 18, 18)
										.addGroup(
												afficheurLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																generation,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																35,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5))
										.addContainerGap(25, Short.MAX_VALUE)));

		/**
		 * Declaration du Graphe
		 * //////////////////////////////////////////////////////////////////
		 */

		grapheur.setBackground(new java.awt.Color(0, 102, 102));
		grapheur.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		carte.setBackground(new java.awt.Color(153, 204, 255));

		javax.swing.GroupLayout grapheurLayout = new javax.swing.GroupLayout(
				grapheur);
		grapheur.setLayout(grapheurLayout);
		grapheurLayout.setHorizontalGroup(grapheurLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				grapheurLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(carte,
								javax.swing.GroupLayout.PREFERRED_SIZE, 944,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		grapheurLayout.setVerticalGroup(grapheurLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				grapheurLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(carte,
								javax.swing.GroupLayout.PREFERRED_SIZE, 492,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		logo.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("pvc.png"))); // NOI18N
		logo.setPreferredSize(new java.awt.Dimension(254, 144));

		javax.swing.GroupLayout conteneurLayout = new javax.swing.GroupLayout(
				conteneur);
		conteneur.setLayout(conteneurLayout);
		conteneurLayout
				.setHorizontalGroup(conteneurLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								conteneurLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												conteneurLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																conteneurLayout
																		.createSequentialGroup()
																		.addComponent(
																				panneauDeControl,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				parametres,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				afficheur,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				logo,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				271,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																grapheur,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		conteneurLayout
				.setVerticalGroup(conteneurLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								conteneurLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												conteneurLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																logo,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																144,
																Short.MAX_VALUE)
														.addComponent(
																afficheur,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																parametres,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																panneauDeControl,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																144,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												grapheur,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(conteneur,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(conteneur,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	/*
	 * Declaration des evenements
	 * //////////////////////////////////////////////////////////////////
	 */

	@SuppressWarnings("deprecation")
	private void departActionPerformed(java.awt.event.ActionEvent evt) {

		nbIndividus = Integer.parseInt(texteNbIndividus.getText());
		if (nbIndividus < 1) {
			nbIndividus = NB_INDIVIDUS;
		}

		nombreDeGenes = Integer.parseInt(texteNombreDeGenes.getText());
		if (nombreDeGenes < 2) {
			nombreDeGenes = NOMBRE_DE_GENES;
		}

		nbGenerationsMax = Integer.parseInt(texteNbGenerationMax.getText());
		if (nbGenerationsMax < 1) {
			nbGenerationsMax = NB_GENERATIONS_MAX;
			texteNbGenerationMax.setText("500");
		}

		if (leThread != null) {
			leThread.stop();
		}
		if (nombreDeGenes() != carte.nombreVilles()) {
			carte.nouveauPays(nombreDeGenes());
		}
		resolutionAG = new AlgoGenetique(this);
		leThread = new Thread(resolutionAG);
		leThread.start();
		depart.setEnabled(false);
		nouveau.setEnabled(false);
		fin.setEnabled(true);

	}

	@SuppressWarnings("deprecation")
	private void finActionPerformed(java.awt.event.ActionEvent evt) {
		// arret force du thread
		leThread.stop();
		leThread = null;
		// donc reactivation forcee de l'interface.
		depart.setEnabled(true);
		nouveau.setEnabled(true);
		fin.setEnabled(false);
	}

	private void nouveauActionPerformed(java.awt.event.ActionEvent evt) {
		carte.nouveauPays(nombreDeGenes());
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		AlphaGene f = new AlphaGene();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	// Variables declaration - do not modify
	private javax.swing.JPanel afficheur;
	private javax.swing.JPanel conteneur;
	public javax.swing.JButton depart;
	public javax.swing.JTextField distance;
	public javax.swing.JButton fin;
	public javax.swing.JTextField generation;
	private javax.swing.JPanel grapheur;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel logo;
	public javax.swing.JButton nouveau;
	private javax.swing.JPanel panneauDeControl;
	private javax.swing.JPanel parametres;
	private javax.swing.JTextField texteNbGenerationMax;
	private javax.swing.JTextField texteNbIndividus;
	private javax.swing.JTextField texteNombreDeGenes;
	// End of variables declaration

}
