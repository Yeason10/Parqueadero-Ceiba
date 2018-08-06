package integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import Estacionamiento.Estacionamiento.EstacionamientoApplication;
import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorioJPA;
import Estacionamiento.Estacionamiento.Servicio.Factura;
import Estacionamiento.Estacionamiento.Servicio.PersistenciaVehiculos;
import Estacionamiento.Estacionamiento.Servicio.Vigilante;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;
import Estacionamiento.Estacionamiento.exception.ExcepcionVehiculoNoEncontrado;
import Estacionamiento.Estacionamiento.fabrica.CeldasCarro;
import Estacionamiento.Estacionamiento.fabrica.CeldasMoto;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;

@SpringBootTest(classes=EstacionamientoApplication.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class VigilanteTest 
{

  @Autowired //Inyeccion de dependencias.
  PersistenciaVehiculos persistenciaVehiculos;
  
  @Autowired
  VehiculoRepositorioJPA vehiculoRepositorio;
  
  @Autowired
  Factura factura;
  
  @Test
  public void testRegistroEntradaCarro() throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido
  {
	  //Arrange
	  Vigilante vigilante = new Vigilante(persistenciaVehiculos,vehiculoRepositorio);
	  Vehiculo vehiculo = new CarroTestDataBuilder().build();
	  CeldasCarro celdasCarro = new CeldasCarro(); 
	  
	  //Act 
	  Vehiculo resultado = vigilante.registroEntradaVehiculo(vehiculo, celdasCarro);
	   
	  //assert
	  assertEquals(vehiculo.getPlaca(),resultado.getPlaca());
  }
   
  @Test
  public void testVerificacionCantidadVehiculosMotos() throws ExcepcionRangoVehiculos
  {
	  //Arrange
	  Vehiculo vehiculo = new MotoTestDataBuilder().build();
      CeldasMoto celdasMoto = new CeldasMoto();
      Vigilante vigilante = new Vigilante(persistenciaVehiculos,vehiculoRepositorio);
      
      //Act
      boolean resultado = vigilante.verificacionCantidadVehiculos(vehiculo, celdasMoto);
      
      //assert
      assertTrue(resultado);
      
  } 

  @Test
  public void testVerificacionPlaca() throws ExcepcionDiaInvalido
  {
	  //Arrange
	  Vehiculo vehiculo = new CarroTestDataBuilder().withPlaca("WXY-234").build();
	  Vigilante vigilante = new Vigilante();
	  
	  //Act
	  boolean resultado = vigilante.verificacionPlaca(vehiculo); 
	  
	  //assert
	  assertTrue(resultado);
	  
  }
  
 /* @Test(expected=ExcepcionDiaInvalido.class)
  public void testVerificacionPlacaNoIngresa () throws ExcepcionDiaInvalido
  {
	//Arrange
	  Vehiculo vehiculo = new CarroTestDataBuilder().withPlaca("AXY-234").build();
	  Vigilante vigilante = new Vigilante();
	 
	  //Act
	  boolean resultado = vigilante.verificacionPlaca(vehiculo);
	
	 //assert
	 assertFalse(resultado);
   }*/

  @Test
  public void testRegistroSalidaCarro() throws ExcepcionVehiculoNoEncontrado
  {
	  //Arrange
	  
	  Vigilante vigilante = new Vigilante(persistenciaVehiculos,vehiculoRepositorio,factura);
	  Vehiculo vehiculo = new CarroTestDataBuilder().build();
	  VehiculoEntidad ve = new VehiculoEntidad(vehiculo.getPlaca(), vehiculo.getEstado(), vehiculo.getCilindraje(), vehiculo.getTipo());
	  String vehiculoPlaca = vehiculo.getPlaca();
	  vehiculoRepositorio.save(ve);
	  
	  //Act 
	  long resultado = vigilante.registroSalidaVehiculo(vehiculoPlaca); 
	   
	  //assert
	  assertEquals(0,resultado);
  }

}
