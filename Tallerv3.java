package paquete;

import java.io.IOException;

import ucn.*;

public class Tallerv3 {

	public static void main(String[] args) throws IOException {
		/*
		 * Arrays for personas.txt
		 */
		String[] nombre_completo = new String[1000]; // Revisar cantidad del vector
		String[] ruts = new String[1000];
		String[] contrasenas = new String[1000];
		int[] saldos = new int[1000];
		String[][] inventario = new String[20][1000]; // Revisar filas y columnas
		/*
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
		
		Leer1(nombre_completo,ruts,contrasenas,saldos,inventario);
		Leer2(tiendas,total_por_tienda);
		Leer3(mangas,comics,inventario_tiendas,productos,tiendas,precio_x_unidad);
		
		IniciarSesion(nombre_completo,ruts,contrasenas,saldos,inventario,inventario_tiendas,productos,tiendas,precio_x_unidad, total_por_tienda);
		
	}

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
	
	public static void Leer3(String[]mangas,String[]comics,int[][]inventario_tiendas,String[]productos,String[]tiendas,int[]precio_x_unidad) throws IOException {
		ArchivoEntrada arch3 = new ArchivoEntrada("productos.txt");
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
		}
	}
	// Leer3 is uncompleted
	public static void IniciarSesion(String[]nombre_completo,String[]ruts, String[] contras, int[]saldos,String[][]inventario,int[][]inventario_tiendas,String[]productos,String[]tiendas,int[]precio_x_unidad, int[] total_por_tienda){
		StdOut.println("Ingrese el rut");
		String RutIngresado = StdIn.readString();
		StdOut.println("Ingrese la contraseña");
		String ContraIngresada = StdIn.readString();
		String rut1 = RutIngresado.replace(".", "");
		String rut_definitivo = rut1.replace("-", "");
		while(true) {
			if(rut_definitivo.equals("CERRAR") && ContraIngresada.equals("CERRAR")){
				break;
			}
			else{
				if(busqueda(rut_definitivo, ruts) != -1 && busqueda(ContraIngresada, contras) != -1) {
					MenuUsuario(rut_definitivo,nombre_completo,ruts,saldos,inventario,inventario_tiendas,productos,tiendas,precio_x_unidad);
				}
				else if(rut_definitivo == "ADMIN" && ContraIngresada == "ADMIN"){
					MenuAdmin(nombre_completo, ruts, saldos, tiendas, productos, total_por_tienda, inventario, inventario_tiendas, precio_x_unidad);
				}
				else{
					StdOut.println("Registrese");
				}
				
			}
			StdOut.println("Ingrese el rut");
			RutIngresado = StdIn.readString();
			StdOut.println("Ingrese la contraseña");
			ContraIngresada = StdIn.readString();	
			
		}

	}
	
	public static void MenuUsuario(String rut_usuario,String[]nombre_completo,String[]ruts,int[]saldos,String[][]inventario,int[][]inventario_tiendas,String[]productos,String[]tiendas,int[]precio_x_unidad ) {
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
		StdOut.println(saldos[indi_a_usar]);
		StdOut.println("c) Comprar producto: ");
		
	
		int indi_tiendas = 0;
		for(int i = 0; i<3;i++) {		//Agregar un try para los null
			StdOut.println(tiendas[i]);
		}
		StdOut.println("En que tienda desea comprar: ");
		String tienda_a_comprar = StdIn.readString();
		StdOut.println(tienda_a_comprar);
		indi_tiendas = busqueda(tienda_a_comprar, tiendas);
		StdOut.println("Listado de " + tiendas[indi_tiendas]);
		for(int l = 0; l <productos.length; l++) {
			if (inventario_tiendas[l][indi_tiendas] != 0) {
				StdOut.println("Nombre producto: " 
			+ productos[l] 
					+ "| Stock del producto: " 
			+ inventario_tiendas[l][indi_tiendas] + " | Precio por unidad del producto: " + precio_x_unidad[l]);
	
			}
		}
		StdOut.println("Ingrese el nombre del producto que desea comprar: ");
		String producto_a_comprar = StdIn.readString();
		int indi_producto = 0;
		for (int a = 0; a < 1000; a++) {
			try {
			if (productos[a].equals(producto_a_comprar)) {
				indi_producto = a;
				break;
			}
			}catch(Exception e) {}
		}
		
		if (saldos[indi_a_usar] < precio_x_unidad[indi_producto]) {
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
			// Mensaje de que la compra se realizo con exito
			StdOut.println("La compra se realizo con exito");
		}
		
		
	}
	public static void MenuAdmin(String[] nombres, String [] ruts,int [] saldos, String [] tiendas, String[] productos, int [] recaudado, String[][] inventario, int [][] Stocks, int[] precios) {
		StdOut.println("a) Agregar stock");
		StdOut.println("b) Informacion de recaudacion");
		StdOut.println("c) Informacion compradores");
		StdOut.println("d) Comic vs Manga");
		String Opcion = StdIn.readString();
		if(Opcion.equals("a")) {
			for(int i = 0; i<tiendas.length; i++) {		//despliega la lista de tiendas
				StdOut.println(tiendas[i]);
			}
			StdOut.println("Ingrese la tienda");
			String TiendaElegida = StdIn.readString();	
			int i = busqueda(TiendaElegida, tiendas);
			StdOut.println("Stock: ");
			for(int j = 0; j<productos.length; j++) {
				StdOut.println(productos[j] + Stocks[i][j]);	//despliega la lista de productos y su stock
			}
			StdOut.println("Ingrese el producto");
			String ProductoElegido = StdIn.readString();
			int k = busqueda(ProductoElegido, productos);
			StdOut.println("Stock a agregar:");
			int StockSumado = StdIn.readInt();
			Stocks[i][k] += StockSumado;				//agrega stock
			StdOut.println("Stock agregado exitosamente");
			
		}
		if(Opcion.equals("b")) {
			int suma = 0;
			for(int i = 0; i<tiendas.length; i++) {
				StdOut.println(tiendas[i] + recaudado[i]);	//despliega las tiendas y su respectiva recaudacion
				suma += recaudado[i];
			}
			StdOut.println("Total de la empresa: " + suma);
		}
		if(Opcion.equals("c")) {
			int mayor = 0;
			int indicemayor = 0;
			int menor = 9999999;
			int indicemenor = 0;
			for(int i = 0; i<nombres.length; i++){
				int suma = 0;
				for(int j = 0; j<productos.length; j++){
					int indice_precio = busqueda(inventario[j][i], productos);
					suma += precios[indice_precio];
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
			StdOut.println("La persona que adquirió mas productos es: " + nombres[indicemayor] + "Rut: " + ruts[indicemayor] + "Saldo: " + 
				       saldos[indicemayor]);
			StdOut.println("Con una suma de: " + mayor);
			StdOut.println("La persona que adquirió menos productos es: " + nombres[indicemenor] + "Rut: " + ruts[indicemenor] + "Saldo: " + 
				       saldos[indicemenor]);
			StdOut.println("Con una suma de: " + menor);
		}
		if(Opcion.equals("d")) {
			//comic vs manga
		}
		else {
			
		}
		
		
		
	}

	public static int busqueda(String buscado, String[] vector) {
		
		try{	
			for(int i = 0; i<vector.length; i++) {
				if(vector[i].equals(buscado)){
					return i;
				}
			}
		}catch(Exception e) {
			
		}
		return -1;
	}



}