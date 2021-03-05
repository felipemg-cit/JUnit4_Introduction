package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.caelum.matematica.MatematicaMaluca;

public class TesteDoMatematicaMaluca {
	
	@Test
	public void deveEntenderMatematicaMaluca() {
		
		MatematicaMaluca math = new MatematicaMaluca();
		
		Assert.assertEquals(90, math.contaMaluca(30));
		Assert.assertEquals(124, math.contaMaluca(31));
		Assert.assertEquals(20, math.contaMaluca(10));
		Assert.assertEquals(33, math.contaMaluca(11));
		Assert.assertEquals(12, math.contaMaluca(6));
		
	}

}
