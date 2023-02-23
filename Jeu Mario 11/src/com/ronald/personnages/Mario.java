package com.ronald.personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.ronald.jeu.Main;
import com.ronald.objets.Objet;

public class Mario extends Personnage{

	
	//**** VARIABLES ****//
	private Image imgMario;
	private ImageIcon icoMario;
    private boolean saut; // vrai si mario saute
    private int compteurSaut; // dur�e du saut et hauteur du saut
	
	//**** CONSTRUCTEUR	****//	
	public Mario(int x, int y) {

	super(x, y, 28, 50);		
	this.icoMario = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
	this.imgMario = this.icoMario.getImage();
	
	this.saut = false;
	this.compteurSaut = 0;
	}

		
	//**** GETTERS ****//		
	public Image getImgMario() {return imgMario;}

	public boolean isSaut() {return saut;}
	
		
	//**** SETTERS ****//
	public void setSaut(boolean saut) {this.saut = saut;}

	//**** METHODES ****//	
    public Image saute(){
		
	    ImageIcon ico;
	    Image img;
	    String str;
	
		this.compteurSaut++;
		// Mont�e du saut		
		if(this.compteurSaut <= 40){
			if(this.getY() > Main.scene.getHautPlafond()){this.setY(this.getY() - 4);}
			else{this.compteurSaut = 41;}			
			if(this.isVersDroite() == true){str = "/images/marioSautDroite.png";}
			else{str = "/images/marioSautGauche.png";}	
			
		// Retomb�e du saut
		}else if(this.getY() + this.getHauteur() < Main.scene.getySol()){this.setY(this.getY() + 1);
			if(this.isVersDroite() == true){str = "/images/marioSautDroite.png";}
			else{str = "/images/marioSautGauche.png";}
			
		// Saut termin�	
		}else{				
			if(this.isVersDroite() == true){str = "/images/marioArretDroite.png";}
			else{str = "/images/marioArretGauche.png";}	
			this.saut = false;
			this.compteurSaut = 0;
		}
		// Affichage de l'image de mario
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img;
	}
    
    public void contact(Objet objet) {
    	if((super.contactAvant(objet) == true && this.isVersDroite() == true || (super.contactArriere(objet) == true && this.isVersDroite() == false))){
    	 	Main.scene.setDx(0);
    	 	this.setMarche(false);
    	}

    
    if(super.contactDessous(objet) == true && this.saut == true) {
    	Main.scene.setySol(objet.getY());
    }else if(super.contactDessous(objet) == false) {
    	Main.scene.setySol(293);
    	if(this.saut == false) {this.setY(243);}
    }
    
    if(super.contactDessus(objet) == true) {
    	Main.scene.setHautPlafond(objet.getY() + objet.getHauteur());
    }else if(super.contactDessus(objet) == false && this.saut == false) {
    	Main.scene.setHautPlafond(0);
    }
    }
}
