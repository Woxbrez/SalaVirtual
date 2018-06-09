package session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;

/* Responsável por estabelecer conexão com Hibernate*/

@ApplicationScoped
public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	/*Responsável por ler o arquivo de configuração Hibernate.cfg.xml*/
	private static SessionFactory buildSessionFactory() {
		
		try {
			if(sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			
			return sessionFactory;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão Session Factory");
		}
	}
	
	/*retorna Session Factory corrente*/
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/*retorna a sessão do Session Factory*/
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	/*Abre uma nova sessão do Session Factory*/
	public static Session openSession(){
		if(sessionFactory == null) {
			buildSessionFactory();
		}
		
		return sessionFactory.openSession();
	}
	
	/*Obtem a connection do  provedor de conexões configurado*/
//	public static Connection getConnectionProvider() throws SQLException{
//		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
//	}
	
	
	public static Connection getConnection() throws Exception{
		InitialContext context = new InitialContext();
		DataSource ds  = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		
		return ds.getConnection();
	}
	
	public static DataSource getDataSourceJndi() throws NamingException{
		InitialContext context = new InitialContext();
		return (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
	}
}
