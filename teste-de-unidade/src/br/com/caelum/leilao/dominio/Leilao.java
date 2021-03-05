package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void dobraLance(Usuario user) {
		if(podeDarLance(user)) {
			Lance prevLance = null;
			for(Lance i : this.lances) {
				if(i.getUsuario() == user) {
					prevLance = i;
				}
			}
			this.lances.add(new Lance(user, prevLance.getValor()*2));
		}
	}
	
	private int contaLances(Usuario user) {

		int count = 0;
		for(Lance i : lances) {
			if(i.getUsuario() == user)
				++count;
		}
		return count;
		
	}
	
	private boolean podeDarLance(Usuario user) {
		
		return (!(ultimoLanceDado().getUsuario() == user) && (contaLances(user) <5));
		
	}
	
	public void propoe(Lance lance) {
		
		if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}
	
	private Lance ultimoLanceDado() {
		return lances.get(lances.size()-1);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
