package ifisolution.com.dao.ipml;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import ifisolution.com.dao.BookDAO;
import ifisolution.com.model.Book;

@Repository("BookDAO")
@Transactional
public class BookDAOIpml implements BookDAO {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public List<Book> getAllBook() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "FROM Book";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		session.close();
		if(list.isEmpty())
			return null;
		return list;
	}

	@Override
	public boolean saveBook(Book obj) {
		try {
			Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
			Transaction tx =  session.beginTransaction();
			session.saveOrUpdate(obj);
			tx.commit();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateBook(Book obj) {
		try {
			Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
			session.beginTransaction();
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteBook(int idBook) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.beginTransaction();
		System.out.println(idBook);
		Query query = session.createQuery("DELETE FROM Book c WHERE c.idbook =:idBook");
		query.setParameter("idBook",idBook);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public List<Book> getlistCatergory(String catergory, int page, int pageSize, String sortedColumn, Boolean desc) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		long maxsizepage=(page + 1) * pageSize;
		String hql = "select e from Book e where e.catergory= :catergory and ROWNUM <= :maxsizepage"; 
		if(sortedColumn != null && desc != null){
			String order = "";
			if(desc){
				order = "desc";
			}
			hql +=" ORDER BY "+sortedColumn + " " +  order;
		}
		Query query = session.createQuery(hql);
		query.setParameter("catergory", catergory);
		query.setParameter("maxsizepage",maxsizepage);
		query.setFirstResult(page * pageSize);
		List<Book> list = query.list();
		session.close();
		if(list.size() > pageSize){
			System.out.println("ERROR page:"+list.size());
			list = list.subList(0, pageSize);
		}
		
		return list;
	}

	@Override
	public Book getIdBook(int idBook) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		String hql = "Select e FROM Book e WHERE e.idbook = :idBook ";
		
		Query query = session.createQuery(hql);
		query.setParameter("idBook", idBook);
		List<Book> list = query.list();
		session.close();
		if(list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public List<Book> getListName(String name, int page, int pageSize, String sortedColumn, Boolean desc) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		long maxsizepage=(page + 1) * pageSize;
		String hql = "Select e FROM Book e WHERE e.namebook = :name and ROWNUM <= :maxsizepage";
		if(sortedColumn != null && desc != null){
			String order = "";
			if(desc){
				order = "desc";
			}
			hql +="ORDER BY "+sortedColumn + " " +  order;
		}
		Query query = session.createQuery(hql);
		query.setParameter("namebook", name);
		query.setParameter("maxsizepage",maxsizepage);
		query.setFirstResult(page * pageSize);
		List<Book> list = query.list();
		session.close();
		if(list.size() > pageSize){
			System.out.println("ERROR page:"+list.size());
			list = list.subList(0, pageSize);
		}
		return list;
	}

	@Override
	public List<Book> getListAuthor(String author, int page, int pageSize, String sortedColumn, Boolean desc) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		long maxsizepage=(page + 1) * pageSize;
		String hql = "Select e FROM Book e WHERE e.author = :author and ROWNUM <= :maxsizepage";
		if(sortedColumn != null && desc != null){
			String order = "";
			if(desc){
				order = "desc";
			}
			hql +="ORDER BY "+sortedColumn + " " +  order;
		}
		Query query = session.createQuery(hql);
		query.setParameter("author", author);
		query.setParameter("maxsizepage",maxsizepage);
		query.setFirstResult(page * pageSize);
		List<Book> list = query.list();
		session.close();
		if(list.size() > pageSize){
			System.out.println("ERROR page:"+list.size());
			list = list.subList(0, pageSize);
		}
		return list;
	}

	@Override
	public List<Book> getListPrice(float price, int page, int pageSize, String sortedColumn, Boolean desc) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		long maxsizepage=(page + 1) * pageSize;
		String hql = "Select e FROM Book e WHERE e.price = :price and ROWNUM <= :maxsizepage";
		if(sortedColumn != null && desc != null){
			String order = "";
			if(desc){
				order = "desc";
			}
			hql +="ORDER BY "+sortedColumn + " " +  order;
		}
		Query query = session.createQuery(hql);
		query.setParameter("price", price);
		query.setParameter("maxsizepage",maxsizepage);
		query.setFirstResult(page * pageSize);
		query.setParameter("maxsizepage",maxsizepage);
		List<Book> list = query.list();
		session.close();
		if(list.size() > pageSize){
			System.out.println("ERROR page:"+list.size());
			list = list.subList(0, pageSize);
		}
		return list;
	}

}
