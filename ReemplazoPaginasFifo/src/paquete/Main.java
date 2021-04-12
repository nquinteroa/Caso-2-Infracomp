package paquete;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument.LeafElement;

public class Main {
	
	private final static String RUTA_ARCHIVO="./data/referencia1.txt";
	private static int cantidadPaginas,marcosDePaginas;
	private static double nivelLocalidad;
	private static ArrayList<Integer> paginas=new ArrayList();
	private static int[][] matriz;
	private static String[] seguimientoPaginas;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cargarArchivo();
		matriz=new int[marcosDePaginas][paginas.size()];
		seguimientoPaginas=new String[cantidadPaginas];
		iniciarMatriz();
		iniciarSeguimientoPaginas();
		Envejecimiento e=new Envejecimiento(matriz, seguimientoPaginas, paginas, marcosDePaginas, cantidadPaginas);
	
		/*for(int i=0;i<marcosDePaginas;i++)
		{
			System.out.println(" ");
			System.out.print(i+"|");
			for(int j=0;j<paginas.size();j++)
			{
				System.out.print("  "+matriz[i][j]);
			}
		}*/
		/*LRU lru=new LRU();
		lru.setCantidadPaginas(cantidadPaginas);
		lru.setCantidadFrames(marcosDePaginas);
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
	private static void iniciarMatriz(){
		for(int i=0;i<marcosDePaginas;i++){
			for(int j=0;j<paginas.size();j++){
				matriz[i][j]=-1;
			}
		}
	}
	private static void iniciarSeguimientoPaginas(){
		for(int i=0;i<seguimientoPaginas.length;i++){
				seguimientoPaginas[i]="000000000000000000000000000000";
			}
		}
}
