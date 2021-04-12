package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

	private final static String RUTA_ARCHIVO = "./data/referencias4_16.txt";
	private static int cantidadPaginas, marcosDePaginas;
	private static double nivelLocalidad;
	private static ArrayList<Integer> paginas = new ArrayList<Integer>();
	private static Matriz matriz;
	private static String[] seguimientoPaginas;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cargarArchivo();
		matriz = new Matriz(marcosDePaginas, paginas.size());
		seguimientoPaginas = new String[cantidadPaginas];
		iniciarSeguimientoPaginas();

		Referencia r = new Referencia(matriz, seguimientoPaginas, paginas, marcosDePaginas, cantidadPaginas);
		Envejecimiento e = new Envejecimiento(matriz, seguimientoPaginas, paginas, marcosDePaginas, cantidadPaginas, r);

		
		e.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		r.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < marcosDePaginas; i++) {
			System.out.println(" ");
			System.out.print(i + "|");
			for (int j = 0; j < paginas.size(); j++) {
				if (matriz.retornarNumero(i, j) == 0 || matriz.retornarNumero(i, j) == 1 || 
						matriz.retornarNumero(i, j) == 2 || matriz.retornarNumero(i, j)== 3
						||matriz.retornarNumero(i, j) == 4 || matriz.retornarNumero(i, j) == 5 ||
								matriz.retornarNumero(i, j) == 6 ||matriz.retornarNumero(i, j)== 7
						|| matriz.retornarNumero(i, j) == 8 || matriz.retornarNumero(i, j)== 9) {
					System.out.print("  0" + matriz.retornarNumero(i, j));
				} else {
					System.out.print("  " + matriz.retornarNumero(i, j));
				}
			}
		}
		System.out.println(" ");

		for (int i = 0; i < seguimientoPaginas.length; i++) {
			System.out.println("Pagina numero " + i + " : " + seguimientoPaginas[i]);
		}

		System.out.println("Numero de fallas: " + r.darFallas());
		/*
		 * LRU lru=new LRU(); lru.setCantidadPaginas(cantidadPaginas);
		 * lru.setCantidadFrames(marcosDePaginas); lru.setPaginas(paginas); lru.lru();
		 */
	}

	private static void cargarArchivo() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO));
			marcosDePaginas = Integer.parseInt(br.readLine());
			System.out.println(marcosDePaginas);
			cantidadPaginas = Integer.parseInt(br.readLine()) + 1;
			System.out.println(cantidadPaginas);
			nivelLocalidad = Double.parseDouble(br.readLine());
			System.out.println(nivelLocalidad);
			String pagina = br.readLine();
			while (pagina != null) {
				paginas.add(Integer.parseInt(pagina));
				pagina = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void iniciarSeguimientoPaginas() {
		for (int i = 0; i < seguimientoPaginas.length; i++) {
			seguimientoPaginas[i] = "000000000000000000000000000000";
		}
	}
}
