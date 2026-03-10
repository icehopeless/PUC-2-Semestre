package maven.atividade;

public class Principal {
	public static void main(String[] args) {
		DataAccessObject dao = new DataAccessObject();
		dao.connect();
		
		User usuario = new User(15, "daniel", "daniel", 'M');
		if(dao.insertUser(usuario)== true) {
			System.out.println("Inserido com sucesso -> " + usuario.toString());
		}
		
		System.out.println("Usuarios do sexo masculino");
		User[] usuariosMasc = dao.getUsuariosMasculinos();
		for (User user : usuariosMasc) {
			System.out.println(user.toString());
		}
		
		usuario.setSenha("nova senha");
		dao.atualizarUsuario(usuario);
		
		System.out.println("Usuarios");
		User[] usuarios = dao.getUsuarios();
		for (User user : usuarios) {
			System.out.println(user.toString());
		}
		
		dao.excluirUser(usuario.getId());
		
		System.out.println("Usuarios");
		usuarios = dao.getUsuarios();
		for (User user : usuarios) {
			System.out.println(user.toString());
		}
		
		dao.close();
		
	}
}
