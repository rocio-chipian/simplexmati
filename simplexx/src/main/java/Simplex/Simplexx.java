package Simplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import TipoEnvio.Envio;


public class Simplexx {
	
	private static int posicion;
	private static double horasCorrientes;
	static List<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();

	public static HashMap<Envio, Double> configuracionOptima(List<Envio> envios, double dineroMaximo) {

		SimplexSolver simplex = new SimplexSolver();		
		
		HashMap<Envio, Double> configuracionOptima = new HashMap<Envio, Double>();
		
		double array[] = new double[envios.size()];
		Arrays.fill(array, 1);
		LinearObjectiveFunction funcionAOptimizar = new LinearObjectiveFunction(array, 0);
		
		restricciones = RestriccionBuilder.getRestricciones(envios, dineroMaximo);

		PointValuePair resultado = simplex.optimize(new MaxIter(100), funcionAOptimizar,
				new LinearConstraintSet(restricciones), GoalType.MAXIMIZE, new NonNegativeConstraint(true));

		double horasTotales = resultado.getValue();
		
		double[] arrayEnviosOptimos = resultado.getPoint();
		
		posicion = 0;
		
		envios.forEach(envio -> {
							double pesoOptimo = arrayEnviosOptimos[posicion];
							envio.setPesoOptimo(pesoOptimo);
							configuracionOptima.put(envios.get(posicion), (Double) pesoOptimo);
							posicion++;
							});

		return configuracionOptima;
		
	}
}