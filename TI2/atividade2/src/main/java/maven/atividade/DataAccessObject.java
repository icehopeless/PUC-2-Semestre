package maven.atividade;
import java.sql.*;

import javax.management.RuntimeErrorException;

public class DataAccessObject {
	private Connection con;
	
	public DataAccessObject() {
		this.con = null;
	}
	
	public boolean connect() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String databaseName = "atividade02";
		int port = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + port + "/" + databaseName;
		String username = "gabriel";
		String password = "12345";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			this.con = DriverManager.getConnection(url,username,password);
			status = (con == null);
			System.out.println("Conexao estabelecida");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver nao encontrado "+ e.getMessage());
		} catch(SQLException e) {
			System.err.println("Conexao nao efetuada "+ e.getMessage());
		}
		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			this.con.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	
	public boolean insertUser(User user) {
		boolean status = false;
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(
				    "INSERT INTO Users (id, login, senha, sexo) VALUES ("
				    + user.getId() + ", '"
				    + user.getLogin() + "', '"
				    + user.getSenha() + "', '"
				    + user.getSexo() + "')"
				);
			statement.close();
			status = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return status;
	}
	
	public boolean atualizarUsuario(User usuario) {
	    boolean status = false;
	    
	    try {
	        String sql = "UPDATE Users SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
	        
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, usuario.getLogin());
	        ps.setString(2, usuario.getSenha());
	        ps.setString(3, String.valueOf(usuario.getSexo()));
	        ps.setInt(4, usuario.getId());

	        ps.executeUpdate();
	        ps.close();
	        
	        status = true;
	        
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    
	    return status;
	}
	
	public boolean excluirUser(int id) {
		boolean status = false;
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("DELETE FROM Users where id = " + id);
			statement.close();
			status = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return status;
	}
	
	public User[] getUsuarios() {
	    User[] usuarios = null;

	    try {
	        Statement st = con.createStatement(
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY);

	        ResultSet rs = st.executeQuery("SELECT * FROM Users");

	        if (rs.next()) {

	            rs.last();
	            usuarios = new User[rs.getRow()];
	            rs.beforeFirst();

	            for (int i = 0; rs.next(); i++) {
	                usuarios[i] = new User(
	                        rs.getInt("id"),
	                        rs.getString("login"),
	                        rs.getString("senha"),
	                        rs.getString("sexo").charAt(0)
	                );
	            }
	        }

	        st.close();

	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }

	    return usuarios;
	}
	
	public User[] getUsuariosMasculinos() {
	    User[] usuarios = null;

	    try {
	        Statement st = con.createStatement(
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY);

	        ResultSet rs = st.executeQuery("SELECT * FROM Users WHERE sexo = 'M'");

	        if (rs.next()) {

	            rs.last();
	            usuarios = new User[rs.getRow()];
	            rs.beforeFirst();

	            for (int i = 0; rs.next(); i++) {
	                usuarios[i] = new User(
	                        rs.getInt("codigo"),
	                        rs.getString("login"),
	                        rs.getString("senha"),
	                        rs.getString("sexo").charAt(0)
	                );
	            }
	        }

	        st.close();

	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }

	    return usuarios;
	}
}
