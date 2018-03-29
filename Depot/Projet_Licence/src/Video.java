import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.Controller;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.DurationUpdateEvent;
import javax.media.GainControl;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.AudioDevice;
//import javazoom.jl.player.FactoryRegistry;
//import javazoom.jl.player.advanced.AdvancedPlayer;
public class Video extends JFrame implements ControllerListener, Runnable
{
	// Attributs de la classe
	private JTextField fldAffichageDuree;
	private JTextField fldAffichagePosition;
	private JLabel lbVolume;
    private JButton bPlay = null;
    private JButton bPause = null;
    private JButton bStop = null;
    private JSlider sSlider;
    private JSlider sVolume;
	private Player player;
	private String repertoire = "file:///D:\\Depot\\Depot\\Projet_Licence\\src\\video\\";
	private String urlFilm;
	private Time duree;
	private JPanel videoPanel;
	private float t0;
	private float t1;
	private boolean intervalle;
	protected GainControl gain = null;
	boolean running;
	static Thread t;
 
 
 
	/**
        * WMediaPlayer : Constructeur qui permet de cr�er l'interface graphique
        * 
        * @_nomFichier String  : Nom du fichier du film � visualiser
        * @_t0         float   : Temps (en secondes) depuis o� l'on veut visualiser la vid�o
        *                                                S'il est vide, voir depuis le d�but
        * @_t1         float   : Temps (en secondes) jusqu'o� o� l'on veut visualiser la vid�o
        *                                                S'il est vide, voir depuis le d�but
        * @_intervalle boolean : Soit on visualise toute la vid�o, soit un intervalle
        */	
	public Video(String _nomFichier, float _t0, float _t1, boolean _intervalle)
	{
		// Appel du constructeur de JFrame
        super();
 
        // R�cup�rer les param�tres
        t0 = _t0;
        t1 = _t1;
        intervalle = _intervalle;
 
        // V�rifier que le nom du fichier pass� en param�tre ne soit pas nul
        // Si c'est en ordre, chargement du film
        if (_nomFichier != null)
        	fChargerFilm(_nomFichier);
 
        // Ev�nement lors de la fermeture du player
    	addWindowListener
    	(
    	    new WindowAdapter() 
    	    {
    	    	public void windowClosing(WindowEvent we) 
    	    	{
    	    		// Fermer le player
    	    		if (player != null) 
    	    		{
    	    			player.close();
    	    		}
    	    	}
    	    }
    	);
	}
 
 
	/**
        * fChargerFilm : Cette m�thode permet de charger le film pass� en param�tre
        * 
        * @_nomFichier String : Nom du fichier du film � visualiser
        */	
	public void fChargerFilm(String _nomFichier)
	{
	
		// D�finir l'URL en fonction du nom du fichier
		urlFilm = repertoire +_nomFichier; //Enlever Darkcity pour mettre en fonction de la bdd
 
		// Cr�ation du player
		try
		{
		    player = Manager.createPlayer(new MediaLocator(urlFilm));
		    player.addControllerListener(this);
 
		    // Si la cr�ation du player est en ordre, attributs de la fen�tre
	        setLocation(400, 200);
	        setTitle("Player"); 
	        setVisible(true);
 
		    // Cette fonction permet au player d'acqu�rir toutes les informations
		    // et toutes les ressources qui lui sont n�cessaires sur le m�dia
	        //JOptionPane.showMessageDialog(this,
	    			//"Acqu�sition des ressources et des informations m�dia en cours. \nPatientez S'il vous plait. : ", "Information", JOptionPane.WARNING_MESSAGE);   
		    player.realize();
		    player.start();
		    //fGestionTemps();
		}
		catch (Exception error)
		{		    
		    // Afficher un message d'erreur si n�cessaire
			JOptionPane.showMessageDialog(this,
			"Erreur lors de la cr�ation du player !\nMessage d'erreur : " +error.getMessage(), "Attention", JOptionPane.WARNING_MESSAGE);   
			error.printStackTrace();
 
			return;
		}
	}
 
 
	/**
        * controllerUpdate : Cette fonction intercepte tous les �v�nements en  
        *                                        provenance du playser (gestionnaire d'�v�nements)
        *
        * @_event ControllerEvent : Un �v�nement est intervenu sur un des composants
        */
	public void controllerUpdate(ControllerEvent _event) 
	{		
		// Donner la dur�e du film 
		if  (_event instanceof DurationUpdateEvent)
		{ 
			duree = ((DurationUpdateEvent) _event).getDuration();  
		} 
 
	    // Cr�er les composants et les �v�nements 
        if (_event instanceof RealizeCompleteEvent )
        {         	
            // Cr�ation des composants
    		bPlay  		 		 = new JButton("Play");
            bPause 		 		 = new JButton("Pause");
            bStop  		 	     = new JButton("Stop");
            fldAffichageDuree    = new JTextField();
            fldAffichagePosition = new JTextField();
            lbVolume			 = new JLabel("Volume");
 
            // R�cup�rer le temps du film pour sp�cifier la barre de progression
            // et l'afficher
            duree = player.getDuration();
            sSlider = new JSlider(JSlider.HORIZONTAL,0,(int)(duree.getSeconds()),0);
            sVolume = new JSlider(JSlider.HORIZONTAL,0,10,10);
 
            // Gestionnaire d'�v�nement sur le slider
            sSlider.addChangeListener
            (
                new ChangeListener() 
                {
                	// Lorsque le curseur se d�place
					public void stateChanged(ChangeEvent arg0) 
					{
						// D�placer la position du film
						float l_tempCourant = sSlider.getValue();
						player.setMediaTime(new Time(l_tempCourant));
					}
				}
            );
 
            // Param�tres de la barre de progression
            sSlider.setMajorTickSpacing((int) duree.getSeconds()/10);
            sSlider.setMinorTickSpacing(2);
            sSlider.setPaintTicks(true);
            sSlider.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));           
 
            // Gestionnaire d'�v�nement sur le slider du volume
            sVolume.addChangeListener
            (
                new ChangeListener() 
                {
                	// Lorsque le curseur se d�place
					public void stateChanged(ChangeEvent arg0) 
					{
						// D�s que l'utilisateur modifie la position du slider
						// ==> modifier la valeur du volume en cons�quence
						// R�cup�rer la valeur du slider (de 0 � 10)
						int l_valeurVolume = sVolume.getValue();
						// Le gain est compris entre 0 et 1, donc multiplication par 0.1
						gain.setLevel((float) (l_valeurVolume * 0.1));
					}
				}
            );
 
            // Panel regroupant les informations pour le volume
            JPanel pVolume = new JPanel();
            pVolume.add(lbVolume);
            pVolume.add(sVolume);
 
        	// Panel regroupant les boutons
        	JPanel pBouton = new JPanel(new FlowLayout());
        	pBouton.add(bPlay);
        	pBouton.add(bPause);
        	pBouton.add(bStop);
 
        	// Panel regroupant les afficheurs
        	JPanel pAfficheur = new JPanel(new BorderLayout());
        	pAfficheur.add(fldAffichageDuree, BorderLayout.NORTH);
        	pAfficheur.add(fldAffichagePosition, BorderLayout.CENTER);
        	pAfficheur.add(pVolume, BorderLayout.SOUTH);
 
        	// Panel regroupant la barre de progression, les boutons et l'afficheur
            JPanel pControle = new JPanel(new BorderLayout());
            pControle.add(sSlider, BorderLayout.NORTH);
            pControle.add(pAfficheur, BorderLayout.CENTER);
            pControle.add(pBouton, BorderLayout.SOUTH);
 
            // Param�tres de l'afficheur
            fldAffichageDuree.setEditable(false);
            fldAffichagePosition.setEditable(false);
            fldAffichageDuree.setText("Dur�e du film : "+(float)duree.getSeconds()/60 + " minutes");
 
            if (videoPanel == null)
            { 
            	// Cr�ation du panel
            	videoPanel = new JPanel();
                videoPanel.setLayout(new BorderLayout());
                getContentPane().add(videoPanel, BorderLayout.CENTER);
            }
            else
                videoPanel.removeAll();
 
            // R�cup�rer le composant contenant l'image provenant du player
            Component vis = player.getVisualComponent();
            if (vis != null)
            { 
            	// Ok donc ajout de l'aspect visuel du m�dia
            	videoPanel.add(vis, BorderLayout.CENTER);
            	videoPanel.add(pControle, BorderLayout.SOUTH);
            	videoPanel.setVisible(true);
                this.pack();
            }
 
            // Ev�nements sur les boutons
            // Bouton "play"
            bPlay.addActionListener
            (
            	new ActionListener()
                {
            		public void actionPerformed(ActionEvent e)
                    {
            			player.start();
            			// FAIRE QUE LE THREAD CONTINUE
                    }
                }
            );
 
            // Bouton "pause"
            bPause.addActionListener
            (
            	new ActionListener()
                {
            		public void actionPerformed(ActionEvent e)
                    {
            			// METTRE LE THREAD EN PAUSE
            			try 
            			{
 							t.wait();
 						}
            			catch (InterruptedException e1) 
            			{
 							e1.printStackTrace();
 						}
 
            			player.stop();
            			player.deallocate();
                    }
                }
            );
 
            // Bouton "stop"
            bStop.addActionListener
            (
            	new ActionListener()
                {
            		public void actionPerformed(ActionEvent e)
                    {
                         player.stop();
                         player.deallocate();
                         // Remettre la vid�o au d�but
                         player.setMediaTime(new Time(0));
                         // Remettre � la barre de progression � 0
                         sSlider.setValue(0);
                         if (player.getTargetState() < Player.Started)
                        	 player.prefetch();
 
                         // ARRETER LE THREAD
                     }
                }
            );
 
            // Placer la vid�o � l'endroit appropri�
    		player.setMediaTime(new Time(t0));
    		sSlider.setValue((int) t0);
        }
 
        if (_event instanceof PrefetchCompleteEvent) 
        {
    	    // R�cup�rer le gain du player
    	    gain = (GainControl) player.getControl("javax.media.GainControl");
        }
	}
 
//	public static void main(String[] args) 
//	{
//		//new test("Issco-35", 0, 0, false);
// 
//        t = new Thread(new test("Issco-35", 0, 0, false));
//        t.start();
//	}
 
 
 
	public void run() 
	{	
		// Etat du player
    	int state = player.getState();
 
        while (state != Controller.Started)
        {
        	try
        	{
        		t.sleep(100);
        		state = player.getState();
        	}
        	catch( Exception e )
        	{
        		e.printStackTrace();
        	}
        }
 
        while(t.isAlive()) 
        {
			try
			{
				Time time = player.getMediaTime();
				state = player.getState();
				double seconds = time.getSeconds();
				if (seconds >= 0.0d)
				{
					System.out.println( "Time: " + seconds);
				}
				if (state != Controller.Started && state != Controller.Prefetching && state != Controller.Realizing )
				{
					running = false;
				}
				t.sleep(1000);
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
        }
	}
}