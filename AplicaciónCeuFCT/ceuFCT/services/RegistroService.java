package services;

import java.sql.Connection;
import java.util.List;

import dao.RegistroDao;
import exception.RegistroServiceException;
import model.Registro;

public class RegistroService {

	private OpenConnection openConn;

	public RegistroService() {
		openConn = new OpenConnection();
	}

	public List<Registro> consultarTodosRegistrosUsuario(Long idUsuario) throws RegistroServiceException {
		Connection conn = null;
		try {
			conn = openConn.getConection();
			RegistroDao registro = new RegistroDao();

			return registro.consultarTodosRegistro(conn, idUsuario);

		} catch (Exception e) {
			System.out.println("Error en la base de datos" + e.getMessage());
			e.printStackTrace();
			throw new RegistroServiceException("Error al consultar en la base de datos", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {

			}
		}
	}

	public void insertarRegistro(Registro regis) throws RegistroServiceException {
		Connection conn = null;
		try {
			conn = openConn.getConection();
			RegistroDao registroDao = new RegistroDao();
			if (registroDao.consultarRegistroFechaId(conn, regis.getId_usuario(), regis.getFecha()) != null) {
				throw new RegistroServiceException("Registro existente en la base de datos");
			} else {
				System.out.println("Registro aceptado");
				registroDao.insertarRegistro(conn, regis);
			}

		} catch (Exception e) {
			System.out.println("Error en la base de datos" + e.getMessage());
			e.printStackTrace();
			throw new RegistroServiceException("Error al consultar en la base de datos", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {

			}
		}
	}

}