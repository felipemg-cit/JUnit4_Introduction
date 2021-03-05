package br.com.caelum.leilao.dominio;

import org.junit.Test;

import org.junit.Assert;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Assert.assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 2000.0));
		
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Macbook Pro 15");

		Usuario steveWoz = new Usuario("Steve Wozniak");
		Usuario steveJobs= new Usuario("Steve Jobs");
		
		leilao.propoe(new Lance(steveWoz, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		
		Assert.assertEquals(2, leilao.getLances().size());
		Assert.assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		Assert.assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		
		Leilao leilao = new Leilao("Macbook Pro 15");

		Usuario steveWoz = new Usuario("Steve Wozniak");
		
		leilao.propoe(new Lance(steveWoz, 2000.0));
		leilao.propoe(new Lance(steveWoz, 3000.0));
		
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQueCincoLancesDoMesmoUsuario() {
		
		Leilao leilao = new Leilao("Macbook Pro 15");

		Usuario steveWoz = new Usuario("Steve Wozniak");
		Usuario steveJobs = new Usuario("Steve Jobs");

		leilao.propoe(new Lance(steveWoz, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		
		leilao.propoe(new Lance(steveWoz, 3500.0));
		leilao.propoe(new Lance(steveJobs, 4000.0));
		
		leilao.propoe(new Lance(steveWoz, 5000.0));
		leilao.propoe(new Lance(steveJobs, 6000.0));
		
		leilao.propoe(new Lance(steveWoz, 7000.0));
		leilao.propoe(new Lance(steveJobs, 8000.0));
		
		leilao.propoe(new Lance(steveWoz, 9000.0));
		leilao.propoe(new Lance(steveJobs, 10000.0));
		
		leilao.propoe(new Lance(steveWoz, 11000.0));

		Assert.assertEquals(10, leilao.getLances().size());

		Assert.assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		Assert.assertEquals(10000.0, leilao.getLances().get(9).getValor(), 0.00001);
		
	}
	
	@Test
	public void deveDobrarOUltimoLanceDado() {
		

		Leilao leilao = new Leilao("Macbook Pro 15");

		Usuario steveWoz = new Usuario("Steve Wozniak");
		Usuario steveJobs = new Usuario("Steve Jobs");

		leilao.propoe(new Lance(steveWoz, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		
		leilao.dobraLance(steveWoz);
		leilao.dobraLance(steveJobs);

		Assert.assertEquals(4, leilao.getLances().size());

		Assert.assertEquals(4000.0, leilao.getLances().get(2).getValor(), 0.00001);
		Assert.assertEquals(6000.0, leilao.getLances().get(3).getValor(), 0.00001);
		

		leilao.dobraLance(steveWoz);
		leilao.dobraLance(steveJobs);
		leilao.dobraLance(steveWoz);
		leilao.dobraLance(steveJobs);
		leilao.dobraLance(steveWoz);
		leilao.dobraLance(steveJobs);
		

		Assert.assertEquals(32000.0, leilao.getLances().get(8).getValor(), 0.00001);
		Assert.assertEquals(48000.0, leilao.getLances().get(9).getValor(), 0.00001);
		
		leilao.dobraLance(steveJobs);
		
		Assert.assertEquals(10, leilao.getLances().size());
		Assert.assertEquals(48000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
