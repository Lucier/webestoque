package br.com.ajax.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.ajax.model.FormaDePagamento;
import br.com.ajax.repository.filter.FormaDePagamentoFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class FormaDePagamentoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manger;

	public FormaDePagamento buscarPorId(Long id) {
		return this.manger.find(FormaDePagamento.class, id);
	}

	public List<FormaDePagamento> formasDePagamento() {
		return this.manger.createQuery("from FormaDePagamento", FormaDePagamento.class).getResultList();
	}

	public FormaDePagamento salvar(FormaDePagamento formaDePagamento) {
		return this.manger.merge(formaDePagamento);
	}

	@Transactional
	public void remover(FormaDePagamento formaDePagamento) {
		try {
			formaDePagamento = buscarPorId(formaDePagamento.getId());
			manger.remove(formaDePagamento);
			manger.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("A Forma de Pagamento selecionada não pode ser excluída!");
		}
	}

	public FormaDePagamento buscarPorDescricao(String descricao) {
		try {
			return this.manger
					.createQuery("from FormaDePagamento where upper(descricao) = :descricao", FormaDePagamento.class)
					.setParameter("descricao", descricao.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FormaDePagamento> formasDePagamentoFiltradas(FormaDePagamentoFilter formaDePagamentoFilter) {
		Criteria criteria = criarCriteriaParaFiltro(formaDePagamentoFilter);
		
		criteria.setFirstResult(formaDePagamentoFilter.getPrimeiroRegistro());
		criteria.setMaxResults(formaDePagamentoFilter.getQuantidadeRegistros());
		
		if(formaDePagamentoFilter.isAscendente() && formaDePagamentoFilter.getPropriedadeOrdenacao() != null){
			criteria.addOrder(Order.asc(formaDePagamentoFilter.getPropriedadeOrdenacao()));
		}else if (formaDePagamentoFilter.getPropriedadeOrdenacao() != null){
			criteria.addOrder(Order.desc(formaDePagamentoFilter.getPropriedadeOrdenacao()));
		}
		
		return criteria.list();
	}
	
	public int quantidadeFormasDePagamentoFiltradas(FormaDePagamentoFilter formaDePagamentoFilter){
		Criteria criteria = criarCriteriaParaFiltro(formaDePagamentoFilter);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
		
	}
	
	private Criteria criarCriteriaParaFiltro(FormaDePagamentoFilter formaDePagamentoFilter){
		Session session = manger.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FormaDePagamento.class);
		
		if(StringUtils.isNotEmpty(formaDePagamentoFilter.getDescricao())){
			criteria.add(Restrictions.ilike("descricao", formaDePagamentoFilter.getDescricao(), MatchMode.ANYWHERE));
		}
		
		return criteria;
	}

}
