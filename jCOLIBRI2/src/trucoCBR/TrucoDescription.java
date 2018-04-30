package trucoCBR;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class TrucoDescription implements CaseComponent {

	/* por enquanto estão somente os atributos da tabela apenastruco.csv */
	Integer IDCaso;	// atributo criado para os IDS IdPartida e IdMao virar um ID unico.
	// 10 = humano e 20 = robo
	Integer indJogadorMao;
	Integer cartaRobo1;
	Integer cartaRobo2;
	Integer cartaRobo3;
	Integer cartaRodada1Robo;
	Integer cartaRodada1Humano;
	Integer ganhadorRodada1;
	Integer cartaRodada2Robo;
	Integer caraRodada2Humano;
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

	public void setCaraRodada2Humano(Integer caraRodada2Humano) {
		this.caraRodada2Humano = caraRodada2Humano;
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

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
	
}