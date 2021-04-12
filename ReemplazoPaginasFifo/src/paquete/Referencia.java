package paquete;

import java.util.ArrayList;

public class Referencia extends Thread {

	private Matriz matriz;
	private String[] seguimiento;
	private ArrayList<Integer> ordenPaginas;
	private int marcosDePagina;
	private int numDePaginas;
	private int ultimaIteracion;
	private int fallas;
	private int Xd;
	private Envejecimiento env;

	public Referencia(Matriz matriz, String[] seguimiento, ArrayList<Integer> ordenPaginas, int marcos, int numPaginas) {
		this.matriz = matriz;
		this.seguimiento = seguimiento;
		this.ordenPaginas = ordenPaginas;
		this.marcosDePagina = marcos;
		this.numDePaginas = numPaginas;
		fallas = 0;
	}

	public int darFallas() {
		return fallas;
	}

	public void run() {
		cargarNuevaReferencia();
	}

	public  void cargarNuevaReferencia() {
		// i es la columna donde nos pararemos - indica la iteracion en el array de
		// paginas
		int i = 0;
		for (i = matriz.darUltimaIteracion(); i < ordenPaginas.size(); i++) {
			int marcoDisponible = buscarEspacio(i);
			if (marcoDisponible >= 0) // si hay marco disponible
			{
				if (!buscarActual(i, ordenPaginas.get(i))) {
					matriz.modificarNumero(marcoDisponible, i, ordenPaginas.get(i)); // Se pone en el marco correspondiente el nï¿½mero
					agregarBitsDeReferencia(ordenPaginas.get(i));
					generarFalla(); // cuando se empiezan a llenar los espacios se consideran como fallas
					generarSiguienteColumna(i);
				} else {
					agregarBitsDeReferencia(ordenPaginas.get(i));
					generarSiguienteColumna(i);
				}

			}else {
				synchronized(matriz)
				{
					if (!buscarActual(i, ordenPaginas.get(i))) {
						matriz.notify();
						try {
							matriz.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						generarFalla();
						generarSiguienteColumna(i);
					}else {
						generarSiguienteColumna(i);
					}
				}
			}
			matriz.aumentarIteracion();
//			System.out.println(matriz.darUltimaIteracion()-1+"-");
//			System.out.println(ordenPaginas.size()-1+"*");
		}
	}

	public int buscarEspacio(int j2) {
		// Busca en que marco de la iteracion hay un espacio libre - i2 es la iteracion
		int marcoDisponible = -2;
		for (int i = 0; i < marcosDePagina; i++) {
			marcoDisponible = matriz.retornarNumero(i, j2) == -1 ? i : -2;
			if (marcoDisponible != -2) {
				break;
			}
		}
		return marcoDisponible;
	}

	public void generarFalla() {
		fallas++;
	}

	/*
	 * Retorna un boolean que dice si la página en la lista ordenPaginas ya esta en la matriz
	 */
	public boolean buscarActual(int columna, int pagina) {
		boolean rta = false;
		for (int i = 0; i < marcosDePagina; i++) {
			if (matriz.retornarNumero(i, columna)== pagina) {
				rta = true;
			}
		}
		return rta;
	}

	public void generarSiguienteColumna(int iteracion) {
		for (int i = 0; i < marcosDePagina; i++) {
			if(iteracion!=ordenPaginas.size()-1)
			{
				matriz.modificarNumero(i, iteracion +1, matriz.retornarNumero(i, iteracion));
			}
		}
	}

	public void agregarBitsDeReferencia(int numeroPaginaReferenciada) {
		for (int i = 0; i < seguimiento.length; i++) {
			if (i == numeroPaginaReferenciada) {
				String paginaReferenciada = seguimiento[numeroPaginaReferenciada];
				paginaReferenciada = "1" + paginaReferenciada.substring(0, paginaReferenciada.length() - 1);
				seguimiento[numeroPaginaReferenciada] = paginaReferenciada;
			} else {
				String paginaActual = seguimiento[i];
				paginaActual = "0" + paginaActual.substring(0, paginaActual.length() - 1);
				seguimiento[i] = paginaActual;
			}
		}
	}

}
