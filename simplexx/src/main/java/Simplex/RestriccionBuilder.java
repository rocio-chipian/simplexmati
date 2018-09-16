package Simplex;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;

import TipoEnvio.Envio;

public class RestriccionBuilder {
	
	private static int i;
	private static int posicion;
	double dineroMaxiomo;
	
	public static ArrayList<LinearConstraint> getRestricciones(List<Envio> envios, double dineroMaximo){
		
		ArrayList<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();
		
		ArrayList<LinearConstraint> restriccionesPesos = crearRestriccionPesos(envios);
		LinearConstraint restriccionPrincipal = crearRestriccionPrincipal(envios, dineroMaximo);
		
		restricciones.addAll(restriccionesPesos);
		restricciones.add(restriccionPrincipal);
		
		return restricciones;
	}
	
	private static LinearConstraint crearRestriccionPrincipal(List<Envio> envios, double dineroMaximo){

		i = 0;
		double coeficientes[] = new double[envios.size()];	
		
		envios.forEach(envio -> {
							coeficientes[i]=envio.getCosto();
							i++;
							});
		
		LinearConstraint restriccionPrincipal = new LinearConstraint(coeficientes, Relationship.LEQ, dineroMaximo);

		return restriccionPrincipal;
	}

	private static ArrayList<LinearConstraint> crearRestriccionPesos(List<Envio> envios) {
		
		posicion = 0;
		ArrayList<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();
		
		envios.forEach(envio -> {
			LinearConstraint restriccionMayorA = new LinearConstraint(prepararArray(envio, posicion, envios), Relationship.GEQ, envio.getPesoMinimo());
			LinearConstraint restriccionMenorA = new LinearConstraint(prepararArray(envio, posicion, envios), Relationship.LEQ, envio.getPesoMaximo());
			restricciones.add(restriccionMenorA);
			restricciones.add(restriccionMayorA);
			posicion++;
		});
		
		return restricciones;
	}
	

	private static double[] prepararArray(Envio envio, int posicion, List<Envio> envios) {
		double array[] = new double[envios.size()];
		array[posicion] = 1;
		return array;
	}
	
}