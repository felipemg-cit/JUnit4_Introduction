
package br.com.caelum.leilao.teste;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import br.com.caelum.leilao.builder.CreateLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	private Avaliador leiloeiro;
	private Usuario felipe;
	private Usuario aquino;
	private Usuario viktorAdm;
	private Usuario guilherme;
	private Usuario thiago;
	private Usuario flavio;
	private Usuario joao;
	
	@BeforeEach
	public void criarAvaliador(){
		this.leiloeiro = new Avaliador();
		this.aquino = new Usuario("Aquino");
		this.felipe = new Usuario("Felipe");
		this.flavio = new Usuario("Flavio");
		this.guilherme = new Usuario("Guilherme");
		this.joao = new Usuario("Joao");
		this.thiago = new Usuario("Thiago");
		this.viktorAdm = new Usuario("Viktor");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		//Parte 1: Cenário
			
		Leilao leilao = new CreateLeilao().to("PlayStation 5 Novo")
				.lance(felipe, 260.0)
				.lance(aquino, 300.0)
				.lance(viktorAdm, 400.0)
				.build();
		
		//Parte 2: Ação
		leiloeiro.avalia(leilao);

		//Parte 3: Validação
		double maiorEsperado = 400.0;
		double menorEsperado = 260.0;
		double mediaEsperado = 320.0;
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		Assert.assertEquals(mediaEsperado, leiloeiro.getMediaLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderUmLance() {
		
		Leilao leilao = new CreateLeilao().to("PS5 Novo")
				.lance(guilherme, 500.0)
				.build();
		
		leiloeiro.avalia(leilao);
		
		double maiorEsperado, menorEsperado, mediaEsperado;
		maiorEsperado = menorEsperado = mediaEsperado = 500.0;
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		Assert.assertEquals(mediaEsperado, leiloeiro.getMediaLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemAleatoria() {
		
		//Parte 1: Cenário
		
		Leilao leilao = new CreateLeilao().to("PlayStation 5 novo")
				.lance(felipe, 260.0)
				.lance(viktorAdm, 400.0)
				.lance(aquino, 300.0)
				.lance(felipe, 240.0)
				.lance(viktorAdm, 500.0)
				.build();
		
		//Parte 2: Ação
		leiloeiro.avalia(leilao);

		//Parte 3: Validação
		double maiorEsperado = 500.0;
		double menorEsperado = 240.0;
		double mediaEsperado = 340.0;
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		Assert.assertEquals(mediaEsperado, leiloeiro.getMediaLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		
		//Parte 1: Cenário
		
		Leilao leilao = new CreateLeilao().to("PlayStation 5 novo")
				.lance(viktorAdm, 400.0)
				.lance(aquino, 300.0)
				.lance(felipe, 260.0)
				.build();
		
		//Parte 2: Ação
		leiloeiro.avalia(leilao);

		//Parte 3: Validação
		double maiorEsperado = 400.0;
		double menorEsperado = 260.0;
		double mediaEsperado = 320.0;
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		Assert.assertEquals(mediaEsperado, leiloeiro.getMediaLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderOsTresMaioresLances() {
		
		//Parte 1: Cenário
				
				Leilao leilao = new CreateLeilao().to("PlayStation 5 novo")
						.lance(felipe, 260.0)
						.lance(viktorAdm, 400.0)
						.lance(aquino, 300.0)
						.lance(felipe, 240.0)
						.lance(viktorAdm, 500.0)
						.build();
				
				//Parte 2: Ação
				leiloeiro.avalia(leilao);
				List<Lance> maiores = leiloeiro.getTresMaiores();

				//Parte 3: Validação
				double maiorEsperado[] = {500.0, 400.0, 300.0};;
				double menorEsperado = 240.0;
				double mediaEsperado = 340.0;
				Assert.assertEquals(3, maiores.size());
				Assert.assertEquals(maiorEsperado[0], maiores.get(0).getValor(), 0.00001);
				Assert.assertEquals(maiorEsperado[1], maiores.get(1).getValor(), 0.00001);
				Assert.assertEquals(maiorEsperado[2], maiores.get(2).getValor(), 0.00001);
				Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
				Assert.assertEquals(mediaEsperado, leiloeiro.getMediaLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderCasoNaoHajaNoMinimoTres() {
		
		//Parte 1: Cenário
				Usuario thiago = new Usuario("Thiago");
				Usuario flavio = new Usuario("Flavio");
				
				Leilao leilao = new Leilao("PlayStation 5 novo");

				leilao.propoe(new Lance(flavio, 400.0));
				leilao.propoe(new Lance(thiago, 300.0));
				
				//Parte 2: Ação
				leiloeiro.avalia(leilao);
				List<Lance> maiores = leiloeiro.getTresMaiores();

				//Parte 3: Validação
				double maiorEsperado[] = {400.0, 300.0};
				double menorEsperado = 300.0;
				double mediaEsperado = 350.0;
				Assert.assertEquals(2, maiores.size());
				Assert.assertEquals(maiorEsperado[0], maiores.get(0).getValor(), 0.00001);
				Assert.assertEquals(maiorEsperado[1], maiores.get(1).getValor(), 0.00001);
				Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
				Assert.assertEquals(mediaEsperado, leiloeiro.getMediaLance(), 0.00001);
		
	}
	
	@Test
	public void deveEntenderCasoNaoHajaLances() {
		
		//Parte 1: Cenário
				
				Leilao leilao = new Leilao("PlayStation 5 tralha");
				
				//Parte 2: Ação
				leiloeiro.avalia(leilao);
				List<Lance> maiores = leiloeiro.getTresMaiores();

				//Parte 3: Validação
				double maiorEsperado = Double.NEGATIVE_INFINITY;
				double menorEsperado = Double.POSITIVE_INFINITY;
				double mediaEsperado = 0.0;
				Assert.assertEquals(0, maiores.size());
				Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
				Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
				Assert.assertEquals(mediaEsperado, leiloeiro.getMediaLance(), 0.00001);
		
	}

}