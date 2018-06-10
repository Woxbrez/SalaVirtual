package crud;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import session.HibernateUtil;


@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;
	private static SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImpl jdbcTemplate;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;
	
	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplateImpl;
	
	private SimpleJdbcTemplate getSimpleJdbcTemplateImpl() {
		return simpleJdbcTemplateImpl;
	}

	@Override
	public void save(Object obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
		
	}

	@Override
	public void persist(Object obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
		
	}

	@Override
	public void saveOrUpdate(Object obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();

	}

	@Override
	public void update(Object obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();

	}

	@Override
	public void delete(Object obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@Override
	public Object merge(Object obj) throws Exception {
		validaSessionFactory();
		obj  = (T) sessionFactory.getCurrentSession().merge(obj);	
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append("select distinct(entity)")
		.append(entidade.getSimpleName())
		.append(" entity ");
		
		List<T>  lista = sessionFactory.getCurrentSession()
				.createQuery(query.toString()).list();
		return lista;
	}

	@Override
	public Object findById(Class entidade, Long id) throws Exception {
		validaSessionFactory();
		Object obj = sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	@Override
	public T findPorId(Class <T> entidade, Long id) throws Exception {
		validaSessionFactory();
		T obj = (T)sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	@Override
	public List findListByQueryDinamica(String s) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(s).list();
		return lista;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearSession() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void evict(Object objs) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Session getSession() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getListSQLDinamica(String sql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		// TODO Auto-generated method stub
		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		// TODO Auto-generated method stub
		return simpleJdbcInsert;
	}

	@Override
	public Long totalRegistro(String tabela) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findListByQueryDinamica(String query, int inicialNoRegistro, int maximoResultado) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validaTransaction(){
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	private void validaSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}	
		
		validaTransaction();
	}
	
	private void commitProcessoAjax(){
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	private void rollBackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}
}
