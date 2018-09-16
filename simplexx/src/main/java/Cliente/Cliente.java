package Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TipoEnvio.Envio;
import Simplex.Simplexx;

public class Cliente {
	
	
	String nombreYApellido;
	double dineroMaximo;
	
	private List<Envio> envios = new ArrayList<Envio>();

	/////////////////////////////////// CONSTRUCTORES /////////////////////////

	public Cliente(String nombreYApellido, double dineroMaximo, List<Envio> envios) {

		this.nombreYApellido = nombreYApellido;
		this.dineroMaximo = dineroMaximo;
		this.envios = envios;
	}
		
	public double getDineroMaximo() {return this.dineroMaximo;}
	
	//CONFIGURACION OPTIMA DE DISPOSITIVOS
	
	public HashMap<Envio, Double> configuracionOptima(){
		HashMap<Envio, Double> configuracionOptima = Simplexx.configuracionOptima(this.envios, this.dineroMaximo);
		return configuracionOptima;
	}
}


	