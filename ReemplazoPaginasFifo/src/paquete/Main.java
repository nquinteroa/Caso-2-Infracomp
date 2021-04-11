package paquete;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Main {
	
	private final static String RUTA_ARCHIVO="./data/referencia1.txt";
	private static int cantidadPaginas,marcosDePaginas;
	private static double nivelLocalidad;
	private static ArrayList<Integer> paginas=new ArrayList();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cargarArchivo();

		/*Fifo fifo=new Fifo();
		fifo.setCantidadFrames(cantidadFrames);
		fifo.setCantidadPaginas(cantidadPaginas);
		fifo.setPaginas(paginas);
		fifo.fifo();*/
		
		/*LRU lru=new LRU();
		lru.setCantidadPaginas(cantidadPaginas);
		lru.setCantidadFrames(cantidadFrames);
		lru.setPaginas(paginas);
		lru.lru();*/
	}
	private static void cargarArchivo() {
		try {
			BufferedReader br=new BufferedReader(new FileReader(RUTA_ARCHIVO));
			marcosDePaginas=Integer.parseInt(br.readLine());
			System.out.println(marcosDePaginas);
			cantidadPaginas=Integer.parseInt(br.readLine());
			System.out.println(cantidadPaginas);
			nivelLocalidad=Double.parseDouble(br.readLine());
			System.out.println(nivelLocalidad);
			String pagina=br.readLine();
			while(pagina!=null)
			{
				paginas.add(Integer.parseInt(pagina));
				pagina=br.readLine();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
