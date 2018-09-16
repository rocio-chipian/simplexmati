

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Cliente.Cliente;
import TipoEnvio.Envio;




public class TestsSimplex {


	Cliente cliente;
	Envio regular;
	Envio prioridad;
	Envio premium;
	
	
	@Before
	public void antesQueNada() {
		
		
		regular = new Envio( (double) 200, (double) 500, (double) 1000);
		prioridad = new Envio( (double) 300, (double) 200, (double) 500);
		premium = new Envio( (double) 500, (double) 100, (double) 700);

		List<Envio> listaEnvios = new ArrayList<Envio>();
		listaEnvios.add(regular);
		listaEnvios.add(prioridad);
		listaEnvios.add(premium);
		
		cliente = new Cliente("Mati M", 350000, listaEnvios);

	}


	@Test
	public void testSimplexAriel() {

		cliente.configuracionOptima();

		//Checkeado con http://www.phpsimplex.com/simplex
		assertEquals((double) 1000, regular.getPesoOptimo(), 0.1);
		assertEquals((double) 333.3, prioridad.getPesoOptimo(), 0.1);
		assertEquals((double) 100, premium.getPesoOptimo(), 0.1);
	}
}