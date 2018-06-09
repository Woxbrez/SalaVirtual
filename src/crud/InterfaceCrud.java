package crud;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {

	//salva dados
	void  save(T obj) throws Exception;
	
	void persist(T obj)throws Exception;
	
	//salva ou atualiza
	void saveOrUpdate(T obj) throws Exception;
	
	//realiza atualização
	void update(T obj) throws Exception;
	
	//realiza delete de dados
	void delete(T obj) throws Exception;
	
	//salva ou atualiza e retorna objeto em estado persistente
	T merge (T obj) throws Exception;
	
	//carrega lista de dados
	List<T> findList(Class<T> objs) throws Exception;
	
	Object findById(Class<T> entidade, Long id) throws Exception;
		
	T findPorId(Class<T> entidade, Long id) throws Exception;
	
	List<T> findListByQueryDinamica(String s) throws Exception;
	
	//executar update com HQL
	void  executeUpdateQueryDinamica(String s)throws Exception;
	
	//executar update com SQL
	void  executeUpdateSQLDinamica(String s)throws Exception;

	//limpa a sessão do Hibernate
	void clearSession() throws Exception;
	
	//Retira um objeto da Sessão do Hibernate
	void  evict(Object objs) throws Exception;
	
	Session getSession() throws Exception;
	
	List<?> getListSQLDinamica(String sql) throws Exception;
	
	//JDBC do Spring
	JdbcTemplate getJdbcTemplate();
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	Long totalRegistro(String tabela) throws Exception;
	
	// talvez esteja errado aqui, quase certeza  =D
	Query obterQuery(String  query) throws Exception;
	
	//Carregamento Dinâmico
	List<T> findListByQueryDinamica(String query,int inicialNoRegistro,int maximoResultado) throws Exception;
	
}
