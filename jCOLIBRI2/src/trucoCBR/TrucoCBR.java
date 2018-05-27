package trucoCBR;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.PlainTextConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntCosine;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.method.reuse.CombineQueryAndCasesMethod;
import jcolibri.method.reuse.NumericDirectProportionMethod;
import jcolibri.test.test5.Region;
import jcolibri.test.test5.TravelDescription;
import jcolibri.test.test5.TravelSolution;

public class TrucoCBR {
	
	Connector _connector;
	CBRCaseBase _caseBase;
	static TrucoDescription current_best_case = null;
	static TrucoDescription current_game_state = null;
	private static Scanner scanner = new Scanner( System.in );
	
	int current_tipo_consulta = 0;
	static final int DEFAULT = 0;
	static final int ENVIDO = 1;
	static final int TRUCO = 2;
	static final int CARTA = 3;
	
	public void configure() throws ExecutionException {
		try {
			_connector = new PlainTextConnector();
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("trucoCBR/plaintextconfig.xml"));
			_caseBase = new LinealCaseBase();
			
		} catch(Exception e) {
			throw new ExecutionException(e);
		}
	}
	
	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);
		Collection<CBRCase> cases = _caseBase.getCases();
		for(CBRCase c: cases)
			System.out.println(c);
		System.out.println("Base Carregada!");
		return _caseBase;
	}
	
	public List<Integer> retornaListaCarta(List<Integer> listaCartasJogador){
		List<Integer> listaCartasPorImportancia = new ArrayList<Integer>();
		Collections.sort(listaCartasJogador);
		
		for(Integer cartaJogador : listaCartasJogador) {
			listaCartasPorImportancia.add(cartaJogador);
		}
		
		return listaCartasPorImportancia;
	}
	
	/*
	public Collection<RetrievalResult> executeQuery(CBRQuery query) throws ExecutionException{
		
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		Attribute cartaRobo1 = new Attribute("cartaRobo1", TrucoDescription.class);
		simConfig.addMapping(cartaRobo1, new Interval(130)); 
		simConfig.setWeight(cartaRobo1, 1.0);
		
		Attribute cartaRobo2 = new Attribute("cartaRobo2", TrucoDescription.class);
		simConfig.addMapping(cartaRobo2, new Interval(130)); 
		simConfig.setWeight(cartaRobo2, 1.0);
		
		Attribute cartaRobo3 = new Attribute("cartaRobo3", TrucoDescription.class);
		simConfig.addMapping(cartaRobo3, new Interval(130)); 
		simConfig.setWeight(cartaRobo3, 1.0);
		
		Attribute ganhadorRodada1 = new Attribute("ganhadorRodada1", TrucoDescription.class);
		simConfig.addMapping(ganhadorRodada1, new Equal());
		simConfig.setWeight(ganhadorRodada1, 1.0);
		
		Attribute quemVenceuMao = new Attribute("quemVenceuMao", TrucoDescription.class);
		simConfig.addMapping(quemVenceuMao, new Interval(130));
		simConfig.setWeight(quemVenceuMao, 1.0);
		
		System.out.println("Query: "+query);
		
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);

		// Select k cases
		eval = SelectCases.selectTopKRR(eval, 1);


		return eval;
	}
	*/

	public void getJogadaRoboRodada1(CBRQuery query) throws ExecutionException{
		
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		Attribute cartaRobo1 = new Attribute("cartaRobo1", TrucoDescription.class);
		simConfig.addMapping(cartaRobo1, new Interval(130)); 
		simConfig.setWeight(cartaRobo1, 5.0);
		
		Attribute cartaRobo2 = new Attribute("cartaRobo2", TrucoDescription.class);
		simConfig.addMapping(cartaRobo2, new Interval(130)); 
		simConfig.setWeight(cartaRobo2, 5.0);
		
		Attribute cartaRobo3 = new Attribute("cartaRobo3", TrucoDescription.class);
		simConfig.addMapping(cartaRobo3, new Interval(130)); 
		simConfig.setWeight(cartaRobo3, 5.0);
		
		Attribute cartaRodada1Humano = new Attribute("cartaRodada1Humano", TrucoDescription.class);
		simConfig.addMapping(cartaRodada1Humano, new Interval(130));
		simConfig.setWeight(cartaRodada1Humano, 10.0);
		
		Attribute indJogadorMao = new Attribute("indJogadorMao", TrucoDescription.class);
		simConfig.addMapping(indJogadorMao, new Interval(20));
		simConfig.setWeight(indJogadorMao, 1.0);
		
		System.out.println("Query: "+query);
		
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);

		// Select k cases
		eval = SelectCases.selectTopKRR(eval, 1);
		
		System.out.println("Retrieved cases:");
		for(RetrievalResult nse: eval)
			System.out.println(nse);
		
		TrucoDescription best = (TrucoDescription) eval.iterator().next().get_case().getDescription();
		
		List<Integer> cartasOrdenadas = new ArrayList<Integer>();
		cartasOrdenadas.add(current_game_state.getCartaRobo1());
		cartasOrdenadas.add(current_game_state.getCartaRobo2());
		cartasOrdenadas.add(current_game_state.getCartaRobo3());
		cartasOrdenadas = retornaListaCarta(cartasOrdenadas);
		
		
		System.out.println("best carta rodada 1 robo: " + best.getCartaRodada1Robo());
		
		int alta = cartasOrdenadas.get(0);
		int media = cartasOrdenadas.get(1);
		int baixa = cartasOrdenadas.get(2);
		System.out.println(alta + " " + media + " " + baixa + " ");

		int dalta = Math.abs(best.getCartaRodada1Robo() - alta);
		int dmedia = Math.abs(best.getCartaRodada1Robo() - media);
		int dbaixa = Math.abs(best.getCartaRodada1Robo() - baixa);
		
		System.out.println("distancias das cartas");
		System.out.println(dalta + " " + dmedia + " " + dbaixa + " ");
		
		List<Integer> distanciaCartas = new ArrayList<Integer>();
		distanciaCartas.add(dalta);
		distanciaCartas.add(dmedia);
		distanciaCartas.add(dbaixa);
		
		distanciaCartas = retornaListaCarta(distanciaCartas);
		
		int menorDistancia = distanciaCartas.get(0);
		
		
		if(dalta == menorDistancia) {
			current_game_state.setCartaRodada1Robo(alta);
		}else if(dmedia == menorDistancia) {
			current_game_state.setCartaRodada1Robo(media);
		}else if(dbaixa == menorDistancia){
			current_game_state.setCartaRodada1Robo(baixa);
		}
		System.out.println("Carta jogada 1 rodada Robo");
		System.out.println(current_game_state.getCartaRodada1Robo());
		
	}
	
	//----------------------------------------------------------
	public void getJogadaRoboRodada2(CBRQuery query) throws ExecutionException{
		
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());
		Attribute cartaRobo1 = new Attribute("cartaRobo1", TrucoDescription.class);
		simConfig.addMapping(cartaRobo1, new Interval(130)); 
		simConfig.setWeight(cartaRobo1, 5.0);
		
		Attribute cartaRobo2 = new Attribute("cartaRobo2", TrucoDescription.class);
		simConfig.addMapping(cartaRobo2, new Interval(130)); 
		simConfig.setWeight(cartaRobo2, 5.0);
		
		Attribute cartaRobo3 = new Attribute("cartaRobo3", TrucoDescription.class);
		simConfig.addMapping(cartaRobo3, new Interval(130)); 
		simConfig.setWeight(cartaRobo3, 5.0);
		
		Attribute cartaRodada1Humano = new Attribute("cartaRodada1Humano", TrucoDescription.class);
		simConfig.addMapping(cartaRodada1Humano, new Interval(130));
		simConfig.setWeight(cartaRodada1Humano, 10.0);
		
		Attribute cartaRodada1Robo = new Attribute("cartaRodada1Robo", TrucoDescription.class);
		simConfig.addMapping(cartaRodada1Robo, new Interval(130));
		simConfig.setWeight(cartaRodada1Robo, 10.0);
		
		Attribute cartaRodada2Humano = new Attribute("cartaRodada2Humano", TrucoDescription.class);
		simConfig.addMapping(cartaRodada2Humano, new Interval(130));
		simConfig.setWeight(cartaRodada2Humano, 10.0);
		
		Attribute ganhadorRodada1 = new Attribute("ganhadorRodada1", TrucoDescription.class);
		simConfig.addMapping(ganhadorRodada1, new Interval(20));
		simConfig.setWeight(ganhadorRodada1, 1.0);
		
		Attribute indJogadorMao = new Attribute("indJogadorMao", TrucoDescription.class);
		simConfig.addMapping(indJogadorMao, new Interval(20));
		simConfig.setWeight(indJogadorMao, 1.0);
		
		System.out.println("Query: "+query);
		
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);

		// Select k cases
		eval = SelectCases.selectTopKRR(eval, 1);
		
		System.out.println("Retrieved cases:");
		for(RetrievalResult nse: eval)
			System.out.println(nse);
		
		TrucoDescription best = (TrucoDescription) eval.iterator().next().get_case().getDescription();
		
		List<Integer> cartasOrdenadas = new ArrayList<Integer>();
		cartasOrdenadas.add(current_game_state.getCartaRobo1());
		cartasOrdenadas.add(current_game_state.getCartaRobo2());
		cartasOrdenadas.add(current_game_state.getCartaRobo3());
		cartasOrdenadas.remove(current_game_state.cartaRodada1Robo);
		cartasOrdenadas = retornaListaCarta(cartasOrdenadas);
		
		
		System.out.println("best carta rodada 2 robo: " + best.getCartaRodada2Robo());
		
		int alta = cartasOrdenadas.get(0);
		int baixa = cartasOrdenadas.get(1);
		System.out.println("Cartas na mao");
		System.out.println(alta + " " + baixa + " ");

		int dalta = Math.abs(best.getCartaRodada2Robo() - alta);
		int dbaixa = Math.abs(best.getCartaRodada2Robo() - baixa);
		
		System.out.println("distancias das cartas rodada 2");
		System.out.println(dalta + " " + dbaixa + " ");
		
		List<Integer> distanciaCartas = new ArrayList<Integer>();
		distanciaCartas.add(dalta);
		distanciaCartas.add(dbaixa);
		
		distanciaCartas = retornaListaCarta(distanciaCartas);
		
		int menorDistancia = distanciaCartas.get(0);
		
		
		if(dalta == menorDistancia) {
			current_game_state.setCartaRodada2Robo(alta);
		}else if(dbaixa == menorDistancia){
			current_game_state.setCartaRodada2Robo(baixa);
		}
		
		System.out.println("Carta jogada 2 rodada Robo");
		System.out.println(current_game_state.getCartaRodada2Robo());
		
	}
	//------------------------------------------------------------
	
	public void postCycle() throws ExecutionException {
		_connector.close();

	}
	
	public void distribuirCartas() {
		System.out.println("Criando baralho...");
		List<Integer> baralho = new ArrayList<>();

		for(int i=0;i<4;i++) {
			baralho.add(i);
			baralho.add(i+10);
			baralho.add(i+20);
			baralho.add(i+40);
			baralho.add(i+50);
			baralho.add(i+60);
			baralho.add(i+80);
			baralho.add(i+90);
		}
		baralho.add(30);
		baralho.add(31);
		baralho.add(70);
		baralho.add(71);
		baralho.add(100);
		baralho.add(110);
		baralho.add(120);
		baralho.add(130);
		baralho.sort(null);
		/*
		for(Integer b: baralho) {
			System.out.print(b+" ");
		}*/
		System.out.println(" Embaralhando...");
		
		Collections.shuffle(baralho);
		
		/* Se quiser ver as cartas
		for(Integer b: baralho) {
			System.out.print(b+" ");
		}*/
		System.out.println("Distribuindo cartas...");
		current_game_state = new TrucoDescription();
		current_game_state.setCartaHumano1(baralho.get(0));
		current_game_state.setCartaRobo1(baralho.get(1));
		current_game_state.setCartaHumano2(baralho.get(2));
		current_game_state.setCartaRobo2(baralho.get(3));
		current_game_state.setCartaHumano3(baralho.get(4));
		current_game_state.setCartaRobo3(baralho.get(5));
	}
	
	public void defineMao() {
		// se for começo de jogo a mão é o player
		if(current_game_state.getIndJogadorMao() == null) { 
			current_game_state.setIndJogadorMao(10);
		// se a mao anterior o mao foi o jogador agora é o robo
		}else if(current_game_state.getIndJogadorMao() == 10) { 
			current_game_state.setIndJogadorMao(20);
		// se a mao anterior o robo foi o robo agora é o jogador
		}else if(current_game_state.getIndJogadorMao() == 20) {
			current_game_state.setIndJogadorMao(10);
		}
	}
	
	public void imprimeCartasHumano() {
		if(current_game_state.getCartaHumano1() != null){
			System.out.print(current_game_state.getCartaHumano1()+ " ");
		}
		if(current_game_state.getCartaHumano2() != null){
			System.out.print(current_game_state.getCartaHumano2()+ " ");
		}
		if(current_game_state.getCartaHumano3() != null){
			System.out.print(current_game_state.getCartaHumano3() + " ");
		}
	}
	
	public void imprimeCartasRobo() {
		if(current_game_state.getCartaRobo1() != null){
			System.out.print(current_game_state.getCartaRobo1()+ " ");
		}
		if(current_game_state.getCartaRobo2() != null){
			System.out.print(current_game_state.getCartaRobo2()+ " ");
		}
		if(current_game_state.getCartaRobo3() != null){
			System.out.print(current_game_state.getCartaRobo3() + " ");
		}
	}
	
	public void imprimeMesa(int rodada) {
		System.out.println("\nRodada 1: ");
		if(current_game_state.getCartaRodada1Humano() != null)
			System.out.print("H: " + current_game_state.getCartaRodada1Humano() + " ");
		if(current_game_state.getCartaRodada1Robo() != null)
			System.out.print("R: " + current_game_state.getCartaRodada1Robo() + " ");
		if(rodada > 1) {
			System.out.println("\nRodada 2: ");
			if(current_game_state.getCartaRodada2Humano() != null)
				System.out.print("H: " + current_game_state.getCartaRodada2Humano() + " ");
			if(current_game_state.getCartaRodada2Robo() != null)
				System.out.print("R: " + current_game_state.getCartaRodada2Robo() + " ");
		}
		if(rodada > 2) {
			System.out.println("\nRodada 3: ");
			if(current_game_state.getCartaRodada3Humano() != null)
				System.out.print("H: " + current_game_state.getCartaRodada3Humano() + " ");
			if(current_game_state.getCartaRodada3Robo() != null)
				System.out.print("R: " + current_game_state.getCartaRodada3Robo() + " ");
		}
		
	}
	
	public void getJogada1Humano() {
		System.out.println("Choose your card:");
		int num1 = scanner.nextInt();
		current_game_state.setCartaRodada1Humano(num1);
	}
	
	public void getJogada2Humano() {
		System.out.println("Choose your card:");
		int num1 = scanner.nextInt();
		current_game_state.setCartaRodada2Humano(num1);
	}
	
	
	/**
	 * @param args
	 * @throws ExecutionException 
	 */
	public static void main(String[] args) throws ExecutionException {
		TrucoCBR t = new TrucoCBR();
		boolean fimrodada = false;
		int rodada = 1; 
		CBRQuery query = new CBRQuery();
		
		try {
			t.configure();
			t.preCycle();
		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(TrucoCBR.class).error(e);
		}
		
		t.distribuirCartas();
		t.defineMao();
		while(!fimrodada){
			// se chegou ao fim da terceira rodada, verifica o vencedor e acaba rodada
			if(rodada == 4) {
				/* adicionar verificação aqui */
				fimrodada = true;
			}
			System.out.println("Suas cartas: ");
			t.imprimeCartasHumano();
			t.imprimeMesa(rodada);
			if(rodada == 1) {
				t.getJogada1Humano();
				query.setDescription(current_game_state);
				t.getJogadaRoboRodada1(query);
				if(current_game_state.getCartaRodada1Humano() > current_game_state.getCartaRodada1Robo()) {
					System.out.println("Ganhou Humano Rodada1");
					current_game_state.setGanhadorRodada1(10);
				}else {
					System.out.println("Ganhou Robo Rodada1");
					current_game_state.setGanhadorRodada1(20);
				}
				rodada++;
			}if(rodada == 2) {
				t.imprimeCartasHumano();
				t.getJogada2Humano();
				query.setDescription(current_game_state);
				t.getJogadaRoboRodada2(query);
				if(current_game_state.getCartaRodada2Humano() > current_game_state.getCartaRodada2Robo()) {
					System.out.println("Ganhou Humano Rodada2");
					current_game_state.setGanhadorRodada2(10);
				}else {
					System.out.println("Ganhou Robo Rodada2");
					current_game_state.setGanhadorRodada2(20);
				}
				rodada++;
			}
			
			fimrodada = true;
	
		}
			

	}
	
	
}
