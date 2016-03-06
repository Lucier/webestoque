package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Banco;
import br.com.ajax.repository.BancoRepository;
import br.com.ajax.repository.filter.BancoFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaBancoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BancoRepository bancoRepository;

	private BancoFilter bancoFilter;
	private List<Banco> bancosFiltrados;
	private Banco bancoSelecionado;

	public PesquisaBancoBean() {
		bancoFilter = new BancoFilter();
		bancosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		bancosFiltrados = bancoRepository.bancosFiltrados(bancoFilter);
	}

	public void excluir() {
		bancoRepository.remover(bancoSelecionado);
		bancosFiltrados.remove(bancoSelecionado);

		FacesUtil.addInfoMessage("Banco " + bancoSelecionado.getNome() + " excluído com sucesso!");
	}

	public BancoFilter getBancoFilter() {
		return bancoFilter;
	}

	public void setBancoFilter(BancoFilter bancoFilter) {
		this.bancoFilter = bancoFilter;
	}

	public List<Banco> getBancosFiltrados() {
		return bancosFiltrados;
	}

	public void setBancosFiltrados(List<Banco> bancosFiltrados) {
		this.bancosFiltrados = bancosFiltrados;
	}

	public Banco getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(Banco bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}

}
