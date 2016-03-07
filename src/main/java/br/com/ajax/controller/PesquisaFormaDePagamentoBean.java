package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

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

	private FormaDePagamentoFilter formaDePagamentoFilter = new FormaDePagamentoFilter();
	private FormaDePagamento formaDePagamentoSelecionada;
	private LazyDataModel<FormaDePagamento> formaDePagamentoModel;

	public PesquisaFormaDePagamentoBean() {
		formaDePagamentoModel = new LazyDataModel<FormaDePagamento>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<FormaDePagamento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				formaDePagamentoFilter.setPrimeiroRegistro(first);
				formaDePagamentoFilter.setQuantidadeRegistros(pageSize);
				formaDePagamentoFilter.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				formaDePagamentoFilter.setPropriedadeOrdenacao(sortField);

				setRowCount(formaDePagamentoRepository.quantidadeFormasDePagamentoFiltradas(formaDePagamentoFilter));

				return formaDePagamentoRepository.formasDePagamentoFiltradas(formaDePagamentoFilter);

			}
		};

	}

	public void excluir() {
		formaDePagamentoRepository.remover(formaDePagamentoSelecionada);

		FacesUtil.addInfoMessage(
				"Forma de Pagamento " + formaDePagamentoSelecionada.getDescricao() + " excluída com sucesso!");
	}

	public FormaDePagamentoFilter getFormaDePagamentoFilter() {
		return formaDePagamentoFilter;
	}

	public void setFormaDePagamentoFilter(FormaDePagamentoFilter formaDePagamentoFilter) {
		this.formaDePagamentoFilter = formaDePagamentoFilter;
	}

	public FormaDePagamento getFormaDePagamentoSelecionada() {
		return formaDePagamentoSelecionada;
	}

	public void setFormaDePagamentoSelecionada(FormaDePagamento formaDePagamentoSelecionada) {
		this.formaDePagamentoSelecionada = formaDePagamentoSelecionada;
	}

	public LazyDataModel<FormaDePagamento> getFormaDePagamentoModel() {
		return formaDePagamentoModel;
	}

}
