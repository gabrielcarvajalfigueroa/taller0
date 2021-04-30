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
	
	public static void IniciarSesion(String[]nombre_completo,String[]ruts, String[] contras, int[]saldos,String[][]inventario,int[][]inventario_tiendas,String[]productos,String[]tiendas,int[]precio_x_unidad, int[] total_por_tienda){
		StdOut.println("Ingrese el rut");
		String RutIngresado = StdIn.readString();
		StdOut.println("Ingrese la contraseña");
		String ContraIngresada = StdIn.readString();
		RutIngresado = RutIngresado.replace(".", "");
		RutIngresado = RutIngresado.replace("-", "");
		while(true) {
			if(RutIngresado.equals("CERRAR") && ContraIngresada.equals("CERRAR")){
				break;
			}
			else{
				if(busqueda(RutIngresado, ruts) != -1 && busqueda(ContraIngresada, contras) != -1) {
					MenuUsuario(RutIngresado,nombre_completo,ruts,saldos,inventario,inventario_tiendas,productos,tiendas,precio_x_unidad);
				}
				else if(RutIngresado.equals("ADMIN") && ContraIngresada.equals("ADMIN")){
					MenuAdmin(nombre_completo, ruts, saldos, tiendas, productos, total_por_tienda, inventario, inventario_tiendas, precio_x_unidad);
				}
				else{
					StdOut.println("Registre su RUT: ");
					String RutNuevo = StdIn.readString();
					StdOut.println("Registre su contraseña: ");
					String ContraNueva = StdIn.readString();
					RutNuevo = RutNuevo.replace(".", "");
					RutNuevo = RutNuevo.replace("-", "");
					StdOut.println("Primer nombre: ");
					String Nombre = StdIn.readString();
					StdOut.println("Primer apellido: ");
					String Apellido = StdIn.readString();
					boolean o = Registrar(RutNuevo, ContraNueva, Nombre, Apellido, ruts, contras, nombre_completo);
					if(o == true) {
						StdOut.println("Registrado exitosamente!");
						StdOut.println(ruts[3]);
					}
					else {
						StdOut.println("Hubo un error al registrarse, intentelo denuevo");
					}
				}
				
			}
			StdOut.println("Ingrese el rut");
			RutIngresado = StdIn.readString();
			StdOut.println("Ingrese la contraseña");
			ContraIngresada = StdIn.readString();
			RutIngresado = RutIngresado.replace(".", "");
			RutIngresado = RutIngresado.replace("-", "");
			
		}

	}
	public static boolean Registrar(String RutNuevo, String ContraNueva, String Nombre, String Apellido, String []ruts, String[] contras, String[] nombre_completo) {
		if(RutNuevo.length() < 8 || RutNuevo.length() > 9) {
			return false;
		}
		else {
			for(int i=0; i<ruts.length; i++) {
				if (ruts[i] == null) {
					ruts[i] = RutNuevo;
					contras[i] = ContraNueva;
					nombre_completo[i] = Nombre + Apellido;
					break;
				}
			}
			return true;
		}
	}
	
	
	
}

