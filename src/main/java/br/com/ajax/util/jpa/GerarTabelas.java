package br.com.ajax.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GerarTabelas {
	
	public static void main(String[] args){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("webestoquePU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		trx.commit();
	}

}
