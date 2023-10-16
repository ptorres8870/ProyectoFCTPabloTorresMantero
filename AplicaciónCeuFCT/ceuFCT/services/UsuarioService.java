package services;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UsuarioDao;
import exception.AutenticarException;
import exception.UsuarioServiceException;
import model.Usuario;

public class UsuarioService {

	private OpenConnection openConn;

	public UsuarioService() {
		openConn = new OpenConnection();
	}

	public Usuario loginUsuario(String email, String pass) throws UsuarioServiceException, AutenticarException {
		Connection conn = null;
		try {
			conn = openConn.getConection();
			UsuarioDao userDao = new UsuarioDao();
			Usuario usuarioCorrecto = userDao.consultarUsuario(conn, email);
			if (usuarioCorrecto == null) {
				throw new AutenticarException("El email indicado no existe");
			} else if (!usuarioCorrecto.getPassword().equals(pass)) {
				throw new AutenticarException("La contraseña indicada es incorrecta");

			} else {
				return usuarioCorrecto;
			}
		} catch (SQLException e) {
			System.out.println("Error en la base de datos" + e.getMessage());
			e.printStackTrace();
			throw new UsuarioServiceException("Error al consultar en la base de datos", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {

			}
		}

	}

	public void altaUsuario(Usuario user) throws UsuarioServiceException {
		Connection conn = null;
		try {
			conn = openConn.getConection();
			UsuarioDao userDao = new UsuarioDao();
			Usuario usuarioCorrecto = userDao.consultarUsuario(conn, user.getEmail());
			if (usuarioCorrecto != null) {
				throw new UsuarioServiceException("El email indicado existe");
			} else {
				System.out.println("Usuario aceptado");
				user.setActivo(true);
				userDao.insertarUsuario(conn, user);
			}
 
		} catch (SQLException e) {
			System.out.println("Error en la base de datos" + e.getMessage());
			e.printStackTrace();
			throw new UsuarioServiceException("Error al consultar en la base de datos", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {

			}
		}
	}

}
