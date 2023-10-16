package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Usuario;

public class UsuarioDao {

	public Usuario consultarUsuario(Connection conn, String email) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from usuarios where email = '" + email + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			Usuario usuarioConsultado = new Usuario();
			if (rs.next()) {
				usuarioConsultado.setId_usuario(rs.getLong("id_usuario"));
				usuarioConsultado.setEmail(rs.getString("email"));
				usuarioConsultado.setPassword(rs.getString("password"));
				usuarioConsultado.setNombre(rs.getString("nombre"));
				usuarioConsultado.setApellidos(rs.getString("apellidos"));
				usuarioConsultado.setCiclo(rs.getString("ciclo"));
				usuarioConsultado.setActivo(rs.getBoolean("activo"));

				return usuarioConsultado;
			} else {
				return null;
			}

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

	}

	public void insertarUsuario(Connection conn, Usuario usuario) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(
					"insert into usuarios (email, password, nombre, apellidos, ciclo, activo) values (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getPassword());
			stmt.setString(3, usuario.getNombre());
			stmt.setString(4, usuario.getApellidos());
			stmt.setString(5, usuario.getCiclo());
			stmt.setBoolean(6, usuario.getActivo());

			stmt.execute();
			rs = stmt.getGeneratedKeys();
			rs.next();
			Long id = rs.getLong(1);
			usuario.setId_usuario(id);
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
	}

}
