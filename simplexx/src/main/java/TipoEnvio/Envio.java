package TipoEnvio;

public class Envio {

	double costoPorKg;
	double pesoMinimo;
	double pesoMaximo;
	double pesoOptimo;
	
	public Envio(double costo, double pesoMinimo, double pesoMaximo) {
		this.costoPorKg = costo;
		this.pesoMinimo = pesoMinimo;
		this.pesoMaximo = pesoMaximo;
		this.pesoOptimo = 0;
	}
	
	public double getCosto() {return this.costoPorKg;}
	public double getPesoMaximo() {return this.pesoMaximo;}
	public double getPesoMinimo() {return this.pesoMinimo;}
	public double getPesoOptimo() {return this.pesoOptimo;}
	
	public void setPesoOptimo(double peso) {this.pesoOptimo = peso;}
}
