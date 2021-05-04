package taller0;

import java.io.IOException;
import java.util.Scanner;

import ucn.*;
/**
 * <h1> Taller 0 </h1>
 * This is the code for the problem presented in taller 0.
 * @author <h1> LA BOTLINE </h1>
 * @author Gabriel Carvajal 
 * @author Pablo Rodriguez
 * @since 2021-05-03
 */
public class Tallerv4 {

	public static void main(String[] args) throws IOException {
		/*
		 * Arrays for personas.txt
		 */
		String[] nombre_completo = new String[1000]; // Revisar cantidad del vector
		String[] ruts = new String[1000];
		String[] contrasenas = new String[1000];
		int[] saldos = new int[1000];
		String[][] inventario = new String[20][1000]; // Revisar filas y columnas
		/**
		 *  Arrays for tiendas.txt
		 */
		String[] tiendas = new String[1000];
		int[] total_por_tienda = new int[1000];
		/*
		 * Arrays for productos.txt
		 */
		String[] mangas = new String[1000];
		String[] comics = new String[1000];
		int[][] inventario_tiendas = new int [1000][1000]; //stock por tienda
		int[] precio_x_unidad = new int[1000]; // precio por unidad por tienda
		String[] productos = new String[1000];
		String[] tipos = new String[1000];
		int[] intento = new int[4];
		
	
		/*
		 * All the files delivered are read and the variables are filled with the data of the stores
		 * people and products.
		 */
		
		Leer1(nombre_completo,ruts,contrasenas,saldos,inventario);
		Leer2(tiendas,total_por_tienda);
		Leer3(mangas,comics,inventario_tiendas,productos,tiendas,precio_x_unidad,tipos);
		
		// The program starts in the console
		IniciarSesion(nombre_completo,ruts,contrasenas,saldos,
				inventario,inventario_tiendas,productos,tiendas,
				precio_x_unidad, total_por_tienda, tipos,intento);
		
		// The program finishes and the files get updated
		CerrarSesion(nombre_completo,ruts,contrasenas,saldos,
				inventario,inventario_tiendas,productos,tiendas,
				precio_x_unidad, total_por_tienda, tipos);
		
	}
	/**
	 * <em>Leer1</em>. Gets as parameters all the arrays related to the personas.txt file
	 * and then fill them using the ucn library. 
	 * @param nombre_completo Array for the full names of the users.
	 * @param ruts Array for the ruts of the users.
	 * @param contrasenas Array for the passwords of the users.
	 * @param saldos Array for the balances of the users.
	 * @param inventario Array for the inventory per user.
	 * @throws IOException
	 */
	public static void Leer1(String[]nombre_completo,String[]ruts,String[]contrasenas,int[]saldos,String[][]inventario) throws IOException {
		ArchivoEntrada arch1 = new ArchivoEntrada("personas.txt");
		int indi = 0;
		while(!arch1.isEndFile()) {
			Registro regEnt = arch1.getRegistro();
			
			String nombre = regEnt.getString();
			String apellido = regEnt.getString();
			String rut = regEnt.getString();
			String rut1 = rut.replace(".", "");
			String rut_definitivo = rut1.replace("-", "");
			String contrasena = regEnt.getString();
			int saldo = regEnt.getInt();
			String[] inventario1 = new String[20];
			
			 try {								// Se leen todos los productos del usuario
				 for (int i = 0; i < 20; i++) {
			      String producto = regEnt.getString();
			      inventario1[i] = producto;
				 }
			    } catch (Exception e) {
			      System.out.println("Something went wrong.");
			   }
	
			nombre_completo[indi] = nombre + " " +  apellido;
			ruts[indi] = rut_definitivo;
			contrasenas[indi] = contrasena;
			saldos[indi] = saldo;
			
			for (int i = 0; i <20; i++) {
				inventario[i][indi] = inventario1[i];
			}
			indi++;
			
		}
	}
	/**
	 * <em>Leer2</em>. Gets as parameters all the arrays related to the tiendas.txt file
	 * and then fill them using the ucn library. 
	 * @param tiendas Array that contains all the stores.
	 * @param total_por_tienda This Array contains the earnings of each store.
	 * @throws IOException
	 */
	public static void Leer2(String[]tiendas,int[]total_por_tienda) throws IOException {
		ArchivoEntrada arch2 = new ArchivoEntrada("tiendas.txt");
		int indi = 0;
		while(!arch2.isEndFile()) {
			Registro regEnt2 = arch2.getRegistro();
			
			String tienda = regEnt2.getString();
			int monto_recaudado = regEnt2.getInt();
			
			tiendas[indi] = tienda;
			total_por_tienda[indi] = monto_recaudado;
		
			indi++;
			
		}
	}
	/**
	 * <em>Leer3</em>. Gets as parameters all the arrays related to the productos.txt file
	 * and then fill them using the ucn library. 
	 * @param mangas Array for the mangas.
	 * @param comics Array for the comics.
	 * @param inventario_tiendas Array for the stock per store.
	 * @param productos Array for the products.
	 * @param tiendas Array for the stores.
	 * @param precio_x_unidad Array for the price of each product.
	 * @param tipos Array for the tipes of product.
	 * @throws IOException
	 */
	public static void Leer3(String[]mangas,String[]comics,int[][]inventario_tiendas,String[]productos,String[]tiendas,int[]precio_x_unidad,String[]tipos) throws IOException {
		ArchivoEntrada arch3 = new ArchivoEntrada("productos.txt");
		int indi_mangas = 0;
		int indi_comics = 0;
		while(!arch3.isEndFile()) {
			Registro regEnt3 = arch3.getRegistro();
			
			String tipo = regEnt3.getString();
			String nom_producto = regEnt3.getString();
			int total_producto = regEnt3.getInt(); // Cantidad total de producto en la tienda
			int valor_por_unidad = regEnt3.getInt(); 
			String tienda = regEnt3.getString();
			
			int indi_productos = 0;
			for (int i = 0; i<1000; i++) {
				if(productos[i] == null || productos[i].equals(nom_producto)) {
					productos[i] = nom_producto;
					indi_productos = i;
					break;
				}
			}
			int indi_tiendas = 0;
			for(int j = 0; j <1000; j++) {
				if(tiendas[j].equals(tienda)) {
					indi_tiendas = j;
					break;
				}
			}
			inventario_tiendas[indi_productos][indi_tiendas] += total_producto;
			precio_x_unidad[indi_productos] += valor_por_unidad;
			tipos[indi_productos] = tipo;
			if (tipo.equals("Manga")) {
				mangas[indi_mangas] = nom_producto;
				indi_mangas++;
			}
			else if (tipo.equals("Comic")) {
				comics[indi_comics] = nom_producto;
				indi_comics ++;
			}
			else {}
			
		}
	}
	/**
	 * <em>IniciarSesion.</em>
	 * This subprogram is invoked in the main and receives as parameters the arrays declared in the main.
	 * First asks for the user's rut and password then enters an infinite while and
	 * depending on the user's input, MenuUsuario, MenuAdmin or Registrar will be activated otherwise the while loop will be closed
	 * ending the program.
	 * @param nombre_completo Array for the full names of the users.
	 * @param ruts Array for the ruts of the users.
	 * @param contras Array for the passwords of the users.
	 * @param saldos Array for the balance of the users.
	 * @param inventario Array for the inventory per user.
	 * @param inventario_tiendas Array for the inventory per store.
	 * @param productos Array for the products.
	 * @param tiendas Array for the stores.
	 * @param precio_x_unidad Array for the price per product.
	 * @param total_por_tienda Array for the earnings per store
	 * @param tipos Array for the type of product
	 * @param intento Array for all the info related to answering letter d of MenuAdmin.
	 */
	public static void IniciarSesion(String[]nombre_completo,String[]ruts, String[] contras, int[]saldos,
			String[][]inventario,int[][]inventario_tiendas,String[]productos,String[]tiendas,
			int[]precio_x_unidad, int[] total_por_tienda,String[] tipos,int[]intento){
		StdOut.println("Ingrese el rut");
		String RutIngresado = StdIn.readString();
		StdOut.println("Ingrese la contrasena");
		String ContraIngresada = StdIn.readString();
		String rut1 = RutIngresado.replace(".", "");
		String rut_definitivo = rut1.replace("-", "");
	
		
		while(true) {
			if(rut_definitivo.equals("CERRAR") && ContraIngresada.equals("CERRAR")){
				break;
			}
			else{
				if(busqueda(rut_definitivo, ruts) != -1 && busqueda(ContraIngresada, contras) != -1) {
					MenuUsuario(rut_definitivo,nombre_completo,ruts,saldos,
							inventario,inventario_tiendas,productos,tiendas,
							precio_x_unidad,total_por_tienda,tipos,intento);
				}
				else if(rut_definitivo.equals("ADMIN") && ContraIngresada.equals("ADMIN")){
					MenuAdmin(nombre_completo, ruts, saldos, tiendas,
							productos, total_por_tienda, inventario, inventario_tiendas,
							precio_x_unidad,intento);
				}
				else{
					StdOut.println("Registrese");
					StdOut.println("Registre su RUT: ");
					String RutNuevo = StdIn.readString();
					StdOut.println("Registre su contrasena: ");
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
						MenuUsuario(RutNuevo,nombre_completo,ruts,saldos,
								inventario,inventario_tiendas,productos,tiendas,
								precio_x_unidad,total_por_tienda, tipos,intento);
					}
					else {
						StdOut.println("Hubo un error al registrarse, intentelo denuevo");
					}
				}
				
			}
			StdOut.println("Ingrese el rut");
			RutIngresado = StdIn.readString();
			StdOut.println("Ingrese la contrasena");
			ContraIngresada = StdIn.readString();
			rut1 = RutIngresado.replace(".", "");
			rut_definitivo = rut1.replace("-", "");
		}

	}
	/**
	 *  <em>MenuUsuario</em>. This subprogram is invoked inside IniciarSesion and receives as parameters
	 * all the arrays related to the user info. Starts by opening the user menu on the console
	 * displays the name, balance and inventory of the user, then gives the option to add balance to the
	 * user account and finally lets buy a product from one of the company's stores. 
	 * @param rut_usuario Rut of the user
	 * @param nombre_completo Array that have all the full names of the users.
	 * @param ruts Array for the ruts
	 * @param saldos Array for the balances
	 * @param inventario Array for the inventory per user.
	 * @param inventario_tiendas Array for the inventory per store.
	 * @param productos Array for the products.
	 * @param tipos Array for the type of product.
	 * @param tiendas Array for the stores
	 * @param precio_x_unidad Array for the prices per product
	 * @param total_por_tienda Array for the earnings per store.
	 * @param tipos Array for the type of product
	 * @param intento Array for all the info related to answering letter d of MenuAdmin.
	 */
	public static void MenuUsuario(String rut_usuario,String[]nombre_completo,String[]ruts,int[]saldos,
			String[][]inventario,int[][]inventario_tiendas,String[]productos,String[]tiendas,
			int[]precio_x_unidad,int[]total_por_tienda, String []tipos,int[]intento) {
		int indi_a_usar = 0;
		for (int i = 0; i < 1000; i++) {
			if (ruts[i].equals(rut_usuario)) {
				indi_a_usar = i;
				break;
			}
		}
		StdOut.println("----------------------------");
		StdOut.println("a) Informacion del usuario: ");
		StdOut.println("Nombre completo: " + nombre_completo[indi_a_usar]);
		StdOut.println("Saldo disponible: " + saldos[indi_a_usar]);
		StdOut.println("Inventario del usuario: ");
		for (int j = 0; j<20; j++) {
			if (inventario[j][indi_a_usar] != null) {
				StdOut.print(inventario[j][indi_a_usar] + " ");
			}
		}
		StdOut.println("");
		StdOut.println("b) Agregar Saldo: ");
		StdOut.print("Cuanto saldo desea agregar: ");
		int saldo_agregar = StdIn.readInt();
		saldos[indi_a_usar] += saldo_agregar;
		StdOut.println("Saldo nuevo: " + saldos[indi_a_usar]);
		StdOut.println("c) Comprar producto: ");
		
	
		for(int i = 0; i<1000;i++) {		//Agregar un try para los null
			if (tiendas[i] != null) {
				StdOut.println(tiendas[i]);
			}	
		}
		StdOut.println("En que tienda desea comprar: ");
		Scanner sc = new Scanner(System.in);
		String tienda_a_comprar = sc.nextLine();
		StdOut.println(tienda_a_comprar);
		int indi_tiendas = busqueda(tienda_a_comprar, tiendas);
		StdOut.println("Listado de " + tiendas[indi_tiendas]);
		for(int l = 0; l <1000; l++) {
			if (inventario_tiendas[l][indi_tiendas] != 0) {
				StdOut.println("Nombre producto: " + 
						productos[l] + 
						"| Stock del producto: " + 
						inventario_tiendas[l][indi_tiendas] + 
						" | Precio por unidad del producto: " +
						precio_x_unidad[l]);	
			}
		}
		StdOut.println("Ingrese el nombre del producto que desea comprar: ");
		String producto_a_comprar = sc.nextLine();
		int indi_producto = busqueda(producto_a_comprar,productos);
		
		if (indi_producto == -1) {
			StdOut.println("El producto fue mal ingresado");
		}
		else if (saldos[indi_a_usar] < precio_x_unidad[indi_producto]) {
			StdOut.println("El saldo es insuficiente para realizar la compra");
		}
		else if(saldos[indi_a_usar] >= precio_x_unidad[indi_producto]) {
			// añadir producto al inventario de usuario 
			for(int b = 0; b <1000; b++) {
				if (inventario[b][indi_a_usar] == null) {
					inventario[b][indi_a_usar] = producto_a_comprar;
					break;
				}
			}
			//restarle al saldo actual del usuario el valor del producto
			saldos[indi_a_usar] -= precio_x_unidad[indi_producto];
			//quitarle a la tienda una unidad de su stock
			inventario_tiendas[indi_producto][indi_tiendas] -= 1;
			//Agregarle dinero a la recaudacion de la tienda
			total_por_tienda[indi_tiendas] += precio_x_unidad[indi_producto];
			//Sumar al contador de mangas o comics
			if (tipos[indi_producto].equals("Manga")) {
				
				intento[0] += 1;                              // index 0  == contador de los mangas
				intento[2] += precio_x_unidad[indi_producto]; // index 2 == suma de los mangas
				
			}
			if (tipos[indi_producto].equals("Comic")){
				
				intento[1] += 1;							   // index 1 == contador de los comics
				intento[3] += precio_x_unidad[indi_producto];  // index 3 == suma de los comics
			}
			// Mensaje de que la compra se realizo con exito
			StdOut.println("La compra se realizo con exito");
		}
		
		
	}
	/**
	 * <em>MenuAdmin</em>. This subprogram is invoked inside IniciarSesion and receives as parameters
	 * all the information that an admin needs. Starts asking for the action to perform from a to d.
	 * <p>Letter a: It allows adding stock of a product to a particular store.<p>
	 * Letter b: Shows the earning per store and earnings of the business.<p>
	 * Letter c: Shows the person who purchased the most products and the person who purchased the least.<p>
	 * Letter d: Displays results based on comic and manga data.
	 * @param nombres Array for the full names of the users.
	 * @param ruts Array for the ruts of the users.
	 * @param saldos Array for the balance per user.
	 * @param tiendas Array for the store.
	 * @param productos Array for the products.
	 * @param recaudado Array for the earnings.
	 * @param inventario Array for the inventory per user.
	 * @param Stocks Array for the stock per store.
	 * @param precios Array for the prices per product.
	 * @param intento Array for all the info related to answering letter d of MenuAdmin.
	 */
	public static void MenuAdmin(String[] nombres, String [] ruts,int [] saldos, String [] tiendas,
			String[] productos, int [] recaudado, String[][] inventario, int [][] Stocks,
			int[] precios,int[]intento) {
		StdOut.println("Ingrese por teclado: ");
		StdOut.println("a) Agregar stock");
		StdOut.println("b) Informacion de recaudacion");
		StdOut.println("c) Informacion compradores");
		StdOut.println("d) Comic vs Manga");
		String Opcion = StdIn.readString();
		Scanner sc = new Scanner(System.in);
		if(Opcion.equals("a")) {
			StdOut.println("Ingrese la tienda: ");
			StdOut.println(" ");
			for(int b = 0; b<1000; b++) {	//despliega la lista de tiendas
				if (tiendas[b] != null) {
					StdOut.println(tiendas[b]);
				}
			}
			
			String TiendaElegida = sc.nextLine();	
			int i = busqueda(TiendaElegida, tiendas);
			StdOut.println("Stock: ");
			for(int j = 0; j<1000; j++) {
				if (Stocks[j][i] > 0 ) {
					StdOut.println(productos[j] + " " + Stocks[j][i]);	//despliega la lista de productos y su stock
				}
			}
			StdOut.println("Ingrese el producto: ");
			String ProductoElegido = sc.nextLine();
			int k = busqueda(ProductoElegido, productos);
			StdOut.println("Stock a agregar:");
			int StockSumado = StdIn.readInt();
			Stocks[k][i] += StockSumado;				//agrega stock
			StdOut.println("Stock agregado exitosamente");
			
		}
		if(Opcion.equals("b")) {
			int suma = 0;
			for(int i = 0; i<1000; i++) {
				if (tiendas[i] != null) {
					StdOut.println("La tienda " + tiendas[i] + " recaudo "  + recaudado[i]);	//despliega las tiendas y su respectiva recaudacion
					suma += recaudado[i];
				}
			}
			StdOut.println("Total de la empresa: " + suma);
		}
		if(Opcion.equals("c")) {
			int mayor = 0;
			int indicemayor = 0;
			int menor = 9999999;
			int indicemenor = 0;
			int suma = 0;
			try {
			for(int i = 0; i<1000; i++){
				if (nombres[i] != null) {
					suma = 0;
					if (inventario[0][i] == null) {
						menor = suma;
						indicemenor = i;
					}
					for(int j = 0; j<1000; j++){
						if (inventario[j][i] == null) {	
							break;
						}
						else {	
							int indice_precio = busqueda(inventario[j][i], productos);
							suma += precios[indice_precio];
						}	
					}
					if(suma>mayor){
						mayor = suma;
						indicemayor = i;
					}
					if(suma<menor){
						menor = suma;
						indicemenor = i;
					}
				}
			}
			}catch(Exception e) {}
			StdOut.println("La persona que adquirio mas productos es: " + nombres[indicemayor] + " Rut: " + ruts[indicemayor] + " Saldo: " + 
				       saldos[indicemayor]);
			StdOut.println("Con una suma de: " + mayor);
			StdOut.println("La persona que adquirio menos productos es: " + nombres[indicemenor] + " Rut: " + ruts[indicemenor] + " Saldo: " + 
				       saldos[indicemenor]);
			StdOut.println("Con una suma de: " + menor);
		}
		if(Opcion.equals("d")) {
			StdOut.println("Mangas vendidos: " + intento[0]);
			StdOut.println("Recaudado: " + intento[2]);
			StdOut.println("Comics vendidos: " + intento[1]);
			StdOut.println("Recaudado: " + intento[3]);	
		}
		else {
		}
	}
	/**
	 * <em>busqueda</em>. It returns the index of a String that is in an array.
	 * @param buscado String that is used.
	 * @param vector Array that is going to be iterated.
	 * @return Returns the index of the String.
	 */
	public static int busqueda(String buscado, String[] vector) {
		
		try{	
			for(int i = 0; i<1000; i++) {
				if(vector[i].equals(buscado)){
					return i;
				}
			}
		}catch(Exception e) {
			
		}
		return -1;
	}
	/**
	 *  <em>Registrar.</em> It is used to add the data of a new user to the corresponding vectors.
	 * @param RutNuevo Rut of the new user.
	 * @param ContraNueva Password of the new user.
	 * @param Nombre Name of the new user.
	 * @param Apellido Lastname of the new user.
	 * @param ruts Array for the ruts.
	 * @param contras Array for the passwords.
	 * @param nombre_completo Array for the full names of the users.
	 * @return Returns true in case the subprogram was able to register the new user, returns false in case it did not.
	 */
	public static boolean Registrar(String RutNuevo, String ContraNueva, String Nombre, String Apellido, String []ruts, String[] contras, String[] nombre_completo) {
		if(RutNuevo.length() < 8 || RutNuevo.length() > 9) {
			return false;
		}
		else {
			for(int i=0; i<1000; i++) {
				if (ruts[i] == null) {
					ruts[i] = RutNuevo;
					contras[i] = ContraNueva;
					nombre_completo[i] = Nombre + " " +  Apellido;
					break;
				}
			}
			return true;
		}
	}
	/**
	 * <em>CerrarSesion</em>. This subprogram is invoked inside the main and is used after IniciarSesion its done.
	 * This program updates all the files (productos, personas and tiendas.txt) with the new data that was obtained 
	 * in IniciarSesion.
	 * @param nombre_completo Array for the full names of the users.
	 * @param ruts Array for the ruts of the users.
	 * @param contras Array for the passwords of the users.
	 * @param saldos Array for the balance per user.
	 * @param inventario Array for the inventory per user.
	 * @param inventario_tiendas Array for the inventory per store.
	 * @param productos Array for the products.
	 * @param tiendas Array for the stores.
	 * @param precio_x_unidad Array for the price per product.
	 * @param total_por_tienda Array for the earnings per store.
	 * @param tipos Array for the type of product (comic or manga).
	 * @throws IOException
	 */
	public static void CerrarSesion(String[]nombre_completo,String[]ruts, String[] contras, int[]saldos,
			String[][]inventario,int[][]inventario_tiendas,String[]productos,String[]tiendas,
			int[]precio_x_unidad, int[] total_por_tienda,String[] tipos) throws IOException{
		
		ArchivoSalida arch = new ArchivoSalida("personas.txt");		//Reescribir personas.txt
		for(int i = 0; i<1000; i++) {
			if(nombre_completo[i] == null) {
				break;
			}
			String [] nombre = nombre_completo[i].split(" ");
			Registro reg = new Registro(26);
			reg.agregarCampo(nombre[0]);
			reg.agregarCampo(nombre[1]);
			reg.agregarCampo(ruts[i]);
			reg.agregarCampo(contras[i]);
			reg.agregarCampo(saldos[i]);
			for(int j = 0; j<20; j++) {
				if(inventario[j][i] == null) {
					break;
				}
				reg.agregarCampo(inventario[j][i]);
			}
			arch.writeRegistro(reg);
		}
		arch.close();
		
		ArchivoSalida arch1 = new ArchivoSalida("tiendas.txt");		//Reescribir tiendas.txt
		for(int i = 0; i<10; i++) {
			if(tiendas[i] == null) {
				break;
			}
			Registro reg = new Registro(3);
			reg.agregarCampo(tiendas[i]);
			reg.agregarCampo(total_por_tienda[i]);
			arch1.writeRegistro(reg);
		}
		arch1.close();
		
		ArchivoSalida arch2 = new ArchivoSalida("productos.txt");
		for(int i = 0; i<1000; i++) {
			if(tipos[i] == null) {
				break;
			}
			Registro reg = new Registro(6);
			reg.agregarCampo(tipos[i]);
			reg.agregarCampo(productos[i]);
			reg.agregarCampo(inventario_tiendas[0][i]);
			reg.agregarCampo(precio_x_unidad[i]);
			reg.agregarCampo(tiendas[0]);
			arch2.writeRegistro(reg); 			
		}
		arch2.close();
		
		
	}
}