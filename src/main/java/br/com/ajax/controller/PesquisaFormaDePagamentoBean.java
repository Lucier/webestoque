package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.FormaDePagamento;
import br.com.ajax.repository.FormaDePagamentoRepository;
import br.com.ajax.repository.filter.FormaDePagamentoFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFormaDePagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FormaDePagamentoRepository formaDePagamentoRepository;

	private FormaDePagamentoFilter formaDePagamentoFilter;
	private List<FormaDePagamento> formasDePagamentoFiltradas;
	private FormaDePagamento formaDePagamentoSelecionada;

	public PesquisaFormaDePagamentoBean() {
		formaDePagamentoFilter = new FormaDePagamentoFilter();
		formasDePagamentoFiltradas = new ArrayList<>();
	}

	public void pesquisar() {
		formasDePagamentoFiltradas = formaDePagamentoRepository.formasDePagamentoFiltradas(formaDePagamentoFilter);
	}

	public void excluir() {
		formaDePagamentoRepository.remover(formaDePagamentoSelecionada);
		formasDePagamentoFiltradas.remove(formaDePagamentoSelecionada);

		FacesUtil.addInfoMessage(
				"Forma de Pagamento " + formaDePagamentoSelecionada.getDescricao() + " excluída com sucesso!");
	}

	public FormaDePagamentoFilter getFormaDePagamentoFilter() {
		return formaDePagamentoFilter;
	}

	public void setFormaDePagamentoFilter(FormaDePagamentoFilter formaDePagamentoFilter) {
		this.formaDePagamentoFilter = formaDePagamentoFilter;
	}

	public List<FormaDePagamento> getFormasDePagamentoFiltradas() {
		return formasDePagamentoFiltradas;
	}

	public void setFormasDePagamentoFiltradas(List<FormaDePagamento> formasDePagamentoFiltradas) {
		this.formasDePagamentoFiltradas = formasDePagamentoFiltradas;
	}

	public FormaDePagamento getFormaDePagamentoSelecionada() {
		return formaDePagamentoSelecionada;
	}

	public void setFormaDePagamentoSelecionada(FormaDePagamento formaDePagamentoSelecionada) {
		this.formaDePagamentoSelecionada = formaDePagamentoSelecionada;
	}

}
