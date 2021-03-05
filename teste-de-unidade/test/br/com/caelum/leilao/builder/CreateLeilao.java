package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CreateLeilao {
	
	private Leilao leilao;
	public CreateLeilao to(String description) {
		this.leilao = new Leilao(description);
		return this;
		
	}
	
	public CreateLeilao lance(Usuario user, double value) {
		
		leilao.propoe(new Lance(user, value	));
		return this;
		
	}
	
	public Leilao build() {
		return leilao;
	}

}
