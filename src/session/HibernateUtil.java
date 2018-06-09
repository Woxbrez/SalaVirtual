package session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/* Respons�vel por estabelecer conex�o com Hibernate*/

public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	/*Respons�vel por ler o arquivo de configura��o Hibernate.cfg.xml*/
	private static SessionFactory buildSessionFactory() {
		
		try {
			if(sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			
			return sessionFactory;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conex�o Session Factory");
		}
	}
	
	/*retorna Session Factory corrente*/
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/*retorna a sess�o do Session Factory*/
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	/*Abre uma nova sess�o do Session Factory*/
	public static Session openSession(){
		if(sessionFactory == null) {
			buildSessionFactory();
		}
		
		return sessionFactory.openSession();
	}
	
	/**/
	public static Connection getConnectionProvider() throws SQLException{
		
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider();
	}
}
