package maven.atividade;

public class User {
	private int id;
	private String login;
	private String senha;
	private char sexo;
	
	
	public User() {
		this.setId(-1);
		this.setLogin("");
		this.setSenha("");
		this.setSexo(' ');
	}
	
	public User(int id, String login, String senha, char sexo) {
		this.setId(id);
		this.setLogin(login);
		this.setSenha(senha);
		this.setSexo(sexo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	@Override
	public String toString() {
		return "Id = " + this.id + ", Login = " + this.login + ", Senha = " + this.senha + ", Sexo = " + this.sexo;
	}
	
}
