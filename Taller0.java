package paquete;

import ucn.StdIn;
import ucn.StdOut;

public class Taller0 {

	public static void main(String[] args) {
		
		
		
		StdOut.println("Ingrese el rut");
		String RutIngresado = StdIn.readString();
		StdOut.println("Ingrese la contraseña");
		String ContraIngresada = StdIn.readString();
		//delete dots and hyphens from the rut
		
		while(true) {
			if(RutIngresado.equals("CERRAR") && ContraIngresada.equals("CERRAR")){
				break;
			}
			else{
				if(busqueda(RutIngresado) == true && busqueda(ContraIngresada) == true) {
					MenuUsuario();
				}
				if(RutIngresado == "ADMIN" && ContraIngresada == "ADMIN"){
					MenuAdmin();
				}
				else {
					StdOut.println("Registrese");
				}
				
			}
			StdOut.println("Ingrese el rut");
			RutIngresado = StdIn.readString();
			StdOut.println("Ingrese la contraseña");
			ContraIngresada = StdIn.readString();	
			
		}

	}
	
	public static boolean busqueda(String Buscado) {
		return false;
	}
	
	public static void MenuUsuario() {
		
	}
	
	public static void MenuAdmin() {
		
	}
	
	public static void RegistrarRut() {
		
	}
	
	
	
}

