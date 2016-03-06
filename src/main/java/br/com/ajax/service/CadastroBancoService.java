package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Banco;
import br.com.ajax.repository.BancoRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroBancoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BancoRepository bancoRepository;

	@Transactional
	public Banco salvar(Banco banco) {
		Banco bancoNumeroExistente = bancoRepository.buscarPorNumero(banco.getNumero());

		if (bancoNumeroExistente != null && !bancoNumeroExistente.equals(banco)) {
			throw new NegocioException("Já existe um Bnaco registrado com o número informado!");
		}

		return bancoRepository.salvar(banco);
	}

}
