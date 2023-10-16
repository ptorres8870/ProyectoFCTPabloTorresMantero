package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Fecha;

public class FechaDao {

	public List<Fecha> consultarFecha(Connection conn, Integer anho, Integer evaluacion) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			List<Fecha> listaFechas = new ArrayList<Fecha>();
			String sql = "select * from fechas where fecha = " + anho + " and evaluacion = " + evaluacion;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Fecha fechaConsultada = new Fecha();
				fechaConsultada.setFecha(rs.getDate("fecha").toLocalDate());
				fechaConsultada.setAnho(rs.getInt("año"));
				fechaConsultada.setEvaluacion(rs.getInt("evaluacion"));
				fechaConsultada.setDisponibilidad(rs.getBoolean("disponibilidad"));

				listaFechas.add(fechaConsultada);
			}

			return listaFechas;

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
	}

}