package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Registro;

public class RegistroDao {

	public List<Registro> consultarTodosRegistro(Connection conn, Long idUsuario) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from registros where id_usuario = " + idUsuario;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Registro> listaRegistro = new ArrayList<Registro>();
			while (rs.next()) {

				Registro registroConsultado = new Registro();
				registroConsultado.setId_registro(rs.getLong("id_registro"));
				registroConsultado.setId_usuario(rs.getLong("id_usuario"));
				registroConsultado.setFecha(rs.getDate("fecha").toLocalDate());
				registroConsultado.setNum_horas(rs.getBigDecimal("num_horas"));
				registroConsultado.setDescripcion(rs.getString("descripcion"));
				listaRegistro.add(registroConsultado);

			}

			return listaRegistro;

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public Registro consultarRegistroFechaId(Connection conn, Long idUsuario, LocalDate fecha) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Registro registroConsultado = new Registro();
		try {
			{
				stmt = conn.prepareStatement("select * from registros where id_usuario = ? and fecha =  ?");
				stmt.setLong(1, idUsuario);
				stmt.setDate(2, Date.valueOf(fecha));
				rs = stmt.executeQuery();
				if (rs.next()) {

					registroConsultado.setId_registro(rs.getLong("id_registro"));
					registroConsultado.setId_usuario(rs.getLong("id_usuario"));
					registroConsultado.setFecha(rs.getDate("fecha").toLocalDate());
					registroConsultado.setNum_horas(rs.getBigDecimal("num_horas"));
					registroConsultado.setDescripcion(rs.getString("descripcion"));

					return registroConsultado;
				} else {
					return null;
				}
			}

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public Long insertarRegistro(Connection conn, Registro registro) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(
					"insert into registros (id_usuario, fecha, num_horas, descripcion) values (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, registro.getId_usuario());
			stmt.setDate(2, Date.valueOf(registro.getFecha()));
			stmt.setBigDecimal(3, registro.getNum_horas());
			stmt.setString(4, registro.getDescripcion());

			stmt.execute();
			rs = stmt.getGeneratedKeys();
			rs.next();
			Long id = rs.getLong(1);
			return id;
		} finally {

			if (stmt != null) {

				stmt.close();
			}

		}
	}

}
