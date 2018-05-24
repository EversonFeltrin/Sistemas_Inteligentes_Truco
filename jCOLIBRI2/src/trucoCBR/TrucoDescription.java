package trucoCBR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class TrucoDescription implements CaseComponent {
	
	Integer IDCaso;	
	Integer indJogadorMao;
	Integer cartaRobo1;
	Integer cartaRobo2;
	Integer cartaRobo3;
	Integer cartaRodada1Robo;
	Integer cartaRodada1Humano;
	Integer ganhadorRodada1;
	Integer cartaRodada2Robo;
	Integer cartaRodada2Humano;
	Integer ganhadorRodada2;
	Integer cartaRodada3Robo;
	Integer cartaRodada3Humano;
	Integer ganhadorRodada3;
	Integer cartaHumano1;
	Integer cartaHumano2;
	Integer cartaHumano3;
	Integer quemVenceuMao;
	
	public void setIDCaso(Integer iDCaso) {
		IDCaso = iDCaso;
	}

	public void setIndJogadorMao(Integer indJogadorMao) {
		this.indJogadorMao = indJogadorMao;
	}	
	
	public void setCartaRobo1(Integer cartaRobo1) {
		this.cartaRobo1 = cartaRobo1;
	}

	public void setCartaRobo2(Integer cartaRobo2) {
		this.cartaRobo2 = cartaRobo2;
	}

	public void setCartaRobo3(Integer cartaRobo3) {
		this.cartaRobo3 = cartaRobo3;
	}

	public void setCartaRodada1Robo(Integer cartaRodada1Robo) {
		this.cartaRodada1Robo = cartaRodada1Robo;
	}

	public void setCartaRodada1Humano(Integer cartaRodada1Humano) {
		this.cartaRodada1Humano = cartaRodada1Humano;
	}

	public void setGanhadorRodada1(Integer ganhadorRodada1) {
		this.ganhadorRodada1 = ganhadorRodada1;
	}

	public void setCartaRodada2Robo(Integer cartaRodada2Robo) {
		this.cartaRodada2Robo = cartaRodada2Robo;
	}

	public void setCartaRodada2Humano(Integer cartaRodada2Humano) {
		this.cartaRodada2Humano = cartaRodada2Humano;
	}

	public void setGanhadorRodada2(Integer ganhadorRodada2) {
		this.ganhadorRodada2 = ganhadorRodada2;
	}

	public void setCartaRodada3Robo(Integer cartaRodada3Robo) {
		this.cartaRodada3Robo = cartaRodada3Robo;
	}

	public void setCartaRodada3Humano(Integer cartaRodada3Humano) {
		this.cartaRodada3Humano = cartaRodada3Humano;
	}

	public void setGanhadorRodada3(Integer ganhadorRodada3) {
		this.ganhadorRodada3 = ganhadorRodada3;
	}

	public void setCartaHumano1(Integer cartaHumano1) {
		this.cartaHumano1 = cartaHumano1;
	}

	public void setCartaHumano2(Integer cartaHumano2) {
		this.cartaHumano2 = cartaHumano2;
	}

	public void setCartaHumano3(Integer cartaHumano3) {
		this.cartaHumano3 = cartaHumano3;
	}

	public void setQuemVenceuMao(Integer quemVenceuMao) {
		this.quemVenceuMao = quemVenceuMao;
	}

	public Integer getIDCaso() {
		return IDCaso;
	}

	public Integer getIndJogadorMao() {
		return indJogadorMao;
	}

	public Integer getCartaRobo1() {
		return cartaRobo1;
	}

	public Integer getCartaRobo2() {
		return cartaRobo2;
	}

	public Integer getCartaRobo3() {
		return cartaRobo3;
	}

	public Integer getCartaRodada1Robo() {
		return cartaRodada1Robo;
	}

	public Integer getCartaRodada1Humano() {
		return cartaRodada1Humano;
	}

	public Integer getGanhadorRodada1() {
		return ganhadorRodada1;
	}

	public Integer getCartaRodada2Robo() {
		return cartaRodada2Robo;
	}

	public Integer getCartaRodada2Humano() {
		return cartaRodada2Humano;
	}

	public Integer getGanhadorRodada2() {
		return ganhadorRodada2;
	}

	public Integer getCartaRodada3Robo() {
		return cartaRodada3Robo;
	}

	public Integer getCartaRodada3Humano() {
		return cartaRodada3Humano;
	}

	public Integer getGanhadorRodada3() {
		return ganhadorRodada3;
	}

	public Integer getCartaHumano1() {
		return cartaHumano1;
	}

	public Integer getCartaHumano2() {
		return cartaHumano2;
	}

	public Integer getCartaHumano3() {
		return cartaHumano3;
	}

	public Integer getQuemVenceuMao() {
		return quemVenceuMao;
	}
	
	public Attribute getIdAttribute() {
		return new Attribute("IDCaso", this.getClass());
	}
	
	public String toString()
	{
		return IDCaso+","+indJogadorMao+","+cartaRobo1+","+cartaRobo2+","+cartaRobo3+","+cartaRodada1Robo+","+cartaRodada1Humano+","+ganhadorRodada1+","+cartaRodada2Robo+","+cartaRodada2Humano+","+ganhadorRodada2+","+cartaRodada3Robo+","+cartaRodada3Humano+","+ganhadorRodada3+","+cartaHumano1+","+cartaHumano2+","+cartaHumano3+","+quemVenceuMao;
	}
	
}