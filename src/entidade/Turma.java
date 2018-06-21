package entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "turma_codigo")
	private int codigo;

	@OneToOne
	private Disciplina disciplina;

	@OneToOne
	private Professor professor;

	@Column(name = "turma_descricao", length = 250)
	private String descricao;

	@ManyToMany
	@JoinTable(name = "turma_aluno", joinColumns = { @JoinColumn(name = "turma_codigo") }, inverseJoinColumns = {
			@JoinColumn(name = "aluno_matricula") })
	private List<Aluno> alunos = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "turma_material", joinColumns = { @JoinColumn(name = "turma_codigo") }, inverseJoinColumns = {
			@JoinColumn(name = "material_codigo") })
	private List<Material> material = new ArrayList<>();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getTurma() {
		return descricao;
	}

	public void setTurma(String descricao) {
		this.descricao = descricao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Material> getMaterial() {
		return material;
	}

	public void setMaterial(List<Material> material) {
		this.material = material;
	}
}
