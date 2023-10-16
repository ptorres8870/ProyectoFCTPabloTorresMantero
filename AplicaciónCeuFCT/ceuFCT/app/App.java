package app;

import java.math.BigDecimal;
import java.time.LocalDate;

import model.Registro;
import model.Usuario;
import services.RegistroService;
import services.UsuarioService;

public class App {

	public static void main(String[] args) {

		try {
			UsuarioService usuarioService = new UsuarioService();
			RegistroService registroService = new RegistroService();
			
			Usuario usuario1 = new Usuario();
			usuario1.setEmail("asdasd@gmail.com");
			usuario1.setPassword("12345");
			usuario1.setId_usuario(1L);
			usuario1.setNombre("Pablo");
			usuario1.setApellidos("Torres Mantero");
			usuario1.setCiclo("DAM");

			System.out.println(usuario1);
			usuarioService.altaUsuario(usuario1);

			Registro registro1 = new Registro();
			registro1.setId_registro(1L);
			registro1.setId_usuario(usuario1.getId_usuario());
			registro1.setDescripcion("Hacer el pastel");
			registro1.setNum_horas(new BigDecimal(1));
			registro1.setFecha(LocalDate.now());
			registro1.setId_usuario(usuario1.getId_usuario());

			System.out.println(registro1);
			registroService.insertarRegistro(registro1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
