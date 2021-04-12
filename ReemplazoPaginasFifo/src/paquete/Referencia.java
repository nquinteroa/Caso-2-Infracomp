package paquete;

import java.util.ArrayList;

public class Referencia extends Thread {

	private int[][] matriz;
	private String[] seguimiento;
	private ArrayList<Integer> ordenPaginas;
	private int marcosDePagina;
	private int numDePaginas;
	private int ultimaIteracion;
	private int fallas;
	private int Xd;
	private Envejecimiento env;

	public Referencia(int[][] matriz, String[] seguimiento, ArrayList<Integer> ordenPaginas, int marcos, int numPaginas,
			Envejecimiento env) {
		this.matriz = matriz;
		this.seguimiento = seguimiento;
		this.ordenPaginas = ordenPaginas;
		this.marcosDePagina = marcos;
		this.numDePaginas = numPaginas;
		ultimaIteracion = 0;
		fallas = 0;
		this.env = env;
	}

	public int darFallas() {
		return fallas;
	}

	public int darXd() {
		return Xd;
	}

	public void run() {
		cargarNuevaReferencia();
	}

	public synchronized void cargarNuevaReferencia() {
		// i es la columna donde nos pararemos - indica la iteracion en el array de
		// paginas
		int i = 0;
		for (i = ultimaIteracion; i < ordenPaginas.size() - 1; i++) {
			int marcoDisponible = buscarEspacio(i);
			if (marcoDisponible >= 0) // si hay marco disponible
			{
				if (!buscarActual(i, ordenPaginas.get(i))) {
					generarFalla(); // cuando se empiezan a llenar los espacios se consideran como fallas
					matriz[marcoDisponible][i] = ordenPaginas.get(i); // Se pone en el marco correspondiente el número
					agregarBitsDeReferencia(ordenPaginas.get(i));
					generarSiguienteColumna(i);
				} else {
					generarSiguienteColumna(i);
				}

			} else {
				try {
					if (!buscarActual(i, ordenPaginas.get(i))) {
						generarFalla();
						env.Envejecer(i);
						generarSiguienteColumna(i);
					} else {
						generarSiguienteColumna(i);
					}

				} catch (Exception e) {

				}

			}
			ultimaIteracion++;
		}
	}

	public int darUltimaIteracion() {
		return ultimaIteracion;
	}

	public int buscarEspacio(int j2) {
		// Busca en que marco de la iteracion hay un espacio libre - i2 es la iteracion
		int marcoDisponible = -2;
		for (int i = 0; i < marcosDePagina; i++) {
			marcoDisponible = matriz[i][j2] == -1 ? i : -2;
			if (marcoDisponible != -2) {
				break;
			}
		}
		return marcoDisponible;
	}

	public void generarFalla() {
		fallas++;
	}

	public boolean buscarActual(int columna, int pagina) {
		boolean rta = false;
		for (int i = 0; i < marcosDePagina; i++) {
			if (matriz[i][columna] == pagina) {
				rta = true;
			}
		}
		return rta;
	}

	public void generarSiguienteColumna(int iteracion) {
		for (int i = 0; i < marcosDePagina; i++) {
			matriz[i][iteracion + 1] = matriz[i][iteracion];
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
