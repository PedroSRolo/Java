package Electronica_package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Shunt {
	int posX,posY;//coordenadas
	boolean montagem; //horiz/vertical
	String referencia;
	int comp = 200;
	

	public Shunt() {
		super();
	}
	public Shunt(int x, int y,String s, boolean b)
	{
		this.posX=x;
		this.posY=y;
		this.referencia=s;
		this.montagem=b;
	}
	
	//METODOS SET
	//METODOS SET
	//METODOS SET
	

	// @param posX the posX to set
	public void setPosX(int posX) {
		this.posX = posX;
	}

	//@param posY the posY to set
	public void setPosY(int posY) {
		this.posY = posY;
	}

	//@param montagem the montagem to set
	public void setMontagem(boolean montagem) {
		this.montagem = montagem;
	}

	//@param referencia the referencia to set
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	//@param comp the comp to set
	public void setComp(int comp) {
		this.comp = comp;
	}


	
	//METODOS GET
	//METODOS GET
	//METODOS GET
	
	//@return the posX
	public int getPosX() {
		return posX;
	}

	//@return the posY
	public int getPosY() {
		return posY;
	}

	//@return the montagem
	public boolean isMontagem() {
		return montagem;
	}

	//@return the referencia
	public String getReferencia() {
		return referencia;
	}

	//@return the comp
	public int getComp() {
		return comp;
	}

	public void paint(Graphics g){
			
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(1.8f));
		if(this.montagem)
		{
			g2D.setColor(Color.black);
			
			g2D.drawLine(this.posX, this.posY, this.posX+this.comp, this.posY);
		}
		else
		{
			g2D.setColor(Color.black);
			
			g2D.drawLine(this.posX, this.posY, this.posX, this.posY+this.comp);
		}
	}
	
	
	

}
