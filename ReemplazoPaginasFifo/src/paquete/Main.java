package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

	private final static String RUTA_ARCHIVO = "./data/referencia4_16.txt";
	private static int cantidadPaginas, marcosDePaginas;
	private static double nivelLocalidad;
	private static ArrayList<Integer> paginas = new ArrayList();
	private static int[][] matriz;
	private static String[] seguimientoPaginas;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cargarArchivo();
		matriz = new int[marcosDePaginas][paginas.size()];
		seguimientoPaginas = new String[cantidadPaginas];
		iniciarMatriz();
		iniciarSeguimientoPaginas();

		Envejecimiento e = new Envejecimiento(matriz, seguimientoPaginas, paginas, marcosDePaginas, cantidadPaginas);
		Referencia r = new Referencia(matriz, seguimientoPaginas, paginas, marcosDePaginas, cantidadPaginas, e);

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
				if (matriz[i][j] == 0 || matriz[i][j] == 1 || matriz[i][j] == 2 || matriz[i][j] == 3
						|| matriz[i][j] == 4 || matriz[i][j] == 5 || matriz[i][j] == 6 || matriz[i][j] == 7
						|| matriz[i][j] == 8 || matriz[i][j] == 9) {
					System.out.print("  0" + matriz[i][j]);
				} else {
					System.out.print("  " + matriz[i][j]);
				}
			}
		}
		System.out.println(" ");

		for (int i = 0; i < seguimientoPaginas.length; i++) {
			System.out.println("Pagina numero " + i + " : " + seguimientoPaginas[i]);
		}

		System.out.println("Numero de fallas: " + r.darFallas());

		System.out.println("Dar Xd: " + r.darXd());
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void iniciarMatriz() {
		for (int i = 0; i < marcosDePaginas; i++) {
			for (int j = 0; j < paginas.size(); j++) {
				matriz[i][j] = -1;
			}
		}
	}

	private static void iniciarSeguimientoPaginas() {
		for (int i = 0; i < seguimientoPaginas.length; i++) {
			seguimientoPaginas[i] = "000000000000000000000000000000";
		}
	}
}
