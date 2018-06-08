package bean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "indexBean")
@SessionScoped
public class IndexBean {
	private String opcao = "NADA";
	
	public String redirecionaPag() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		opcao =  params.get("disciplina"); 
		return "disciplina?faces-redirect=true";
	}

	public String getOpcao() {
		return opcao;
	}


	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
}
