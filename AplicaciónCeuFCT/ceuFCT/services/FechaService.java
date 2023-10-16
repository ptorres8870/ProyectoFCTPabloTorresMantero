package services;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.FechaDao;
import exception.EvalucianException;
import exception.FechaServiceException;
import model.Fecha;

public class FechaService {

	private OpenConnection openConn;
	private Integer anho;
	private Integer eva;

	public FechaService() {
		openConn = new OpenConnection();
	}

	public List<Fecha> consultarFechaActuales() throws FechaServiceException, EvalucianException {
		Connection conn = null;
		try {
			conn = openConn.getConection();
			List<Fecha> listaFechas = new ArrayList<Fecha>();
			FechaDao fecha = new FechaDao();
			anho = LocalDate.now().getYear();
			if (LocalDate.now().getMonthValue() >= 9 || LocalDate.now().getMonthValue() <= 11) {
				eva = 1;
			} else if (LocalDate.now().getMonthValue() >= 12 || LocalDate.now().getMonthValue() <= 2) {
				eva = 2;
			} else if (LocalDate.now().getMonthValue() >= 3 || LocalDate.now().getMonthValue() <= 6) {
				eva = 3;
			} else {
				throw new EvalucianException("No hay fechas para esa evaluación");
			}
			listaFechas = fecha.consultarFecha(conn, anho, eva);
			return listaFechas;

		} catch (Exception e) {
			System.out.println("Error en la base de datos" + e.getMessage());
			e.printStackTrace();
			throw new FechaServiceException("Error al consultar en la base de datos", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {

			}
		}
	}

}