package br.com.ajax.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Banco;
import br.com.ajax.service.CadastroBancoService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroBancoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroBancoService cadastroBancoService;

	private Banco banco;

	public CadastroBancoBean() {
		limpar();
	}

	public void salvar() {
		this.cadastroBancoService.salvar(banco);
		limpar();
		FacesUtil.addInfoMessage("Banco salvo com sucesso!");
	}

	public void limpar() {
		banco = new Banco();
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public boolean isEditando() {
		return this.banco.getId() != null;
	}

}
