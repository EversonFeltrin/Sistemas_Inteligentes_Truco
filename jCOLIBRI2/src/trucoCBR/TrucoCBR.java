package trucoCBR;

import java.util.Collection;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.PlainTextConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.selection.SelectCases;

public class TrucoCBR {
	
	Connector _connector;
	CBRCaseBase _caseBase;
	TrucoDescription current_best_case = null;
	TrucoDescription current_game_state = null;
	
	int current_tipo_consulta = 0;
	static final int DEFAULT = 0;
	static final int ENVIDO = 1;
	static final int TRUCO = 2;
	static final int CARTA = 3;
	
	public void configure() throws ExecutionException {
		System.out.println("Entrei no configure");
		try {
			_connector = new PlainTextConnector();
			System.out.println("Entrei no configure 2");
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("trucoCBR/plaintextconfig.xml"));
			System.out.println("Entrei no configure 3");
			_caseBase = new LinealCaseBase();
			
		} catch(Exception e) {
			throw new ExecutionException(e);
		}
		System.out.println("Entrei no configure 4");
	}
	
	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		for(CBRCase c: cases)
			System.out.println(c);
		System.out.println("Base Carregada!");
		return _caseBase;
	}
	
	public Collection<RetrievalResult> executeQuery(CBRQuery query) throws ExecutionException{
		NNConfig simConfig = new NNConfig();
		
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
		
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 5);
		
		return eval;
	}
	
	
	
	public void postCycle() throws ExecutionException {
		_connector.close();

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrucoCBR test = new TrucoCBR();
		try {
			test.configure();
			test.preCycle();
			test.executeQuery(null);
			test.postCycle();
		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(TrucoCBR.class).error(e);
		}

	}
	
	
}
