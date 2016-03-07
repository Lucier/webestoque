package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

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

	private BancoFilter bancoFilter = new BancoFilter();
	private Banco bancoSelecionado;
	private LazyDataModel<Banco> bancoModel;

	public PesquisaBancoBean() {
		bancoModel = new LazyDataModel<Banco>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Banco> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				bancoFilter.setPrimeiroRegistro(first);
				bancoFilter.setQuantidadeRegistros(pageSize);
				bancoFilter.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				bancoFilter.setPropriedadeOrdenacao(sortField);

				setRowCount(bancoRepository.quantidadeBancosFiltrados(bancoFilter));

				return bancoRepository.bancosFiltrados(bancoFilter);
			}
		};

	}

	public void excluir() {
		bancoRepository.remover(bancoSelecionado);

		FacesUtil.addInfoMessage("Banco " + bancoSelecionado.getNome() + " excluído com sucesso!");
	}

	public BancoFilter getBancoFilter() {
		return bancoFilter;
	}

	public void setBancoFilter(BancoFilter bancoFilter) {
		this.bancoFilter = bancoFilter;
	}

	public Banco getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(Banco bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}

	public LazyDataModel<Banco> getBancoModel() {
		return bancoModel;
	}

}
