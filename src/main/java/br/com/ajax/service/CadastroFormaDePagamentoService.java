package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.FormaDePagamento;
import br.com.ajax.repository.FormaDePagamentoRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroFormaDePagamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FormaDePagamentoRepository formaDePagamentoRepository;

	@Transactional
	public FormaDePagamento salvar(FormaDePagamento formaDePagamento) {
		FormaDePagamento formaDePagamentoExistente = formaDePagamentoRepository
				.buscarPorDescricao(formaDePagamento.getDescricao());

		if (formaDePagamentoExistente != null && !formaDePagamentoExistente.equals(formaDePagamento)) {
			throw new NegocioException("Já existe uma forma de pagamento registrada com a descrição informada!");
		}

		return formaDePagamentoRepository.salvar(formaDePagamento);
	}

}
