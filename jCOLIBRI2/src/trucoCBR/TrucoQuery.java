package trucoCBR;

import jcolibri.exception.ExecutionException;

public class TrucoQuery {
	
	public int primeiraCartaMao(TrucoDescription jogo_atual) {
		int result;
		TrucoCBR t = new TrucoCBR();
		try {
			t.configure();
			t.preCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
		result = 0;
		
		return result;
	}

}
