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
import org.hibernate.criterion.Restrictions;

import br.com.ajax.model.Banco;
import br.com.ajax.repository.filter.BancoFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class BancoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Banco buscarPorId(Long id) {
		return this.manager.find(Banco.class, id);
	}

	public List<Banco> bancos() {
		return this.manager.createQuery("from Banco", Banco.class).getResultList();
	}

	public Banco salvar(Banco banco) {
		return this.manager.merge(banco);
	}

	@Transactional
	public void remover(Banco banco) {
		try {
			banco = buscarPorId(banco.getId());
			manager.remove(banco);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O Banco selecionado não pode ser excluído!");
		}
	}

	public Banco buscarPorNome(String nome) {
		try {
			return manager.createQuery("from Banco where upper(nome) = :nome", Banco.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Banco buscarPorNumero(String numero) {
		try {
			return manager.createQuery("from Banco where upper(numero) = :numero", Banco.class)
					.setParameter("numero", numero).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Banco> bancosFiltrados(BancoFilter bancoFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Banco.class);

		if (StringUtils.isNotBlank(bancoFilter.getNome())) {
			criteria.add(Restrictions.ilike("nome", bancoFilter.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
