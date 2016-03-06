package br.com.ajax.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.FormaDePagamento;
import br.com.ajax.service.CadastroFormaDePagamentoService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFormaDePagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFormaDePagamentoService cadastroFormaDePagamentoService;

	private FormaDePagamento formaDePagamento;

	public CadastroFormaDePagamentoBean() {
		limpar();
	}

	public void salvar() {
		this.cadastroFormaDePagamentoService.salvar(formaDePagamento);
		limpar();
		FacesUtil.addInfoMessage("Forma de pagamento slavo com sucesso!");
	}

	public void limpar() {
		formaDePagamento = new FormaDePagamento();
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public boolean isEditando() {
		return this.formaDePagamento.getId() != null;
	}

}
