package com.example.parser.dao;

import com.example.parser.model.Doc;
import com.example.parser.util.DBUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class DocumentHibernateDao implements DocumentDao {
	private static SessionFactory sessionFactory = DBUtils.getDBUtils().getConfiguration();

    public List<Doc> getAll () {
		Session session = sessionFactory.openSession();
		List<Doc> docs = session.createQuery("FROM Doc", Doc.class).getResultList();
		session.close();
		return docs;
	}

    @Override
    public void addDocument(Doc doc) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        session.save(doc);
        transaction.commit();
        session.close();
    }

}
