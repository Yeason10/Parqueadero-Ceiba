package estacionaminento.unitarias;



import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import Estacionamiento.Estacionamiento.Repositorio.VehiculoRepositorioJPA;
import Estacionamiento.Estacionamiento.Servicio.PersistenciaVehiculos;
import Estacionamiento.Estacionamiento.Servicio.Vigilante;
import Estacionamiento.Estacionamiento.exception.ExcepcionDiaInvalido;
import Estacionamiento.Estacionamiento.exception.ExcepcionRangoVehiculos;
import Estacionamiento.Estacionamiento.fabrica.CeldasCarro;
import Estacionamiento.Estacionamiento.fabrica.CeldasMoto;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;




public class VigilanteTest 
{
	
	@Autowired
	@InjectMocks
	Vigilante vigilante;
	
	@InjectMocks
	@Spy
	Vigilante espiaVigilante;
    
    @Mock
    PersistenciaVehiculos persistenciaVehiculos;
    
    @Mock
    VehiculoRepositorioJPA vehiculoRepositorio;
    
     @Before public void initMocks()
     {
    	 MockitoAnnotations.initMocks(this);
     }

     @Test
     public void testRegistrarEntradaCarro() throws ExcepcionRangoVehiculos, ExcepcionDiaInvalido
     {
    	//Arrange
        Vehiculo vehiculo = new CarroTestDataBuilder().build();
    	CeldasCarro celdasCarro = new CeldasCarro();
    	Mockito.doReturn(true).when(espiaVigilante).verificacionCantidadVehiculos(Mockito.any(), Mockito.any());
    	Mockito.doReturn(true).when(espiaVigilante).verificacionPlaca(Mockito.any());
    	Mockito.doReturn(vehiculo).when(persistenciaVehiculos).insertar(Mockito.any());    	
    	
    	//Act
     	Vehiculo resultadoPruebaVehiculo = espiaVigilante.registroEntradaVehiculo(vehiculo, celdasCarro);
    	
    	//Assert
    	assertEquals(vehiculo,resultadoPruebaVehiculo);
      }
               
      @Test
      public void testVerifacionPlacaIngresaAlEstacionamientoLunesDomingo()throws ExcepcionDiaInvalido
      {
    	 //Arrange
    	  Vehiculo vehiculo = new CarroTestDataBuilder().withPlaca("AWX-345").build();
    	  when(espiaVigilante.verificacionFecha()).thenReturn(true);
    	  
    	  //Act
    	  boolean resultado = espiaVigilante.verificacionPlaca(vehiculo);
    	   
    	  //Assert
          assertTrue(resultado);  
      } 

      @Test(expected=ExcepcionDiaInvalido.class)
      public void testVerificacionPlacaNoIngresaAlEstacionamientoNoLunesDomingo()throws ExcepcionDiaInvalido
      {
    	//Arrange
    	Vehiculo vehiculo = new CarroTestDataBuilder().withPlaca("AWX-345").build();
    	when(espiaVigilante.verificacionFecha()).thenReturn(false);
      
        //Act
  	    boolean resultado = espiaVigilante.verificacionPlaca(vehiculo);
      
        //Assert
  	    assertFalse(resultado);
      }
      
      @Test
      public void testVerificacionPlacaIngresaAlEstacionamientoPlacaSinA()throws ExcepcionDiaInvalido
      {   
    	  //Arrange
    	  Vehiculo vehiculo = new CarroTestDataBuilder().withPlaca("YNP-3759").build(); 
    	  when(espiaVigilante.verificacionFecha()).thenReturn(false);
    	  
    	  //Act
    	  boolean resultado = espiaVigilante.verificacionPlaca(vehiculo);
    	  
    	  //Assert
          assertTrue(resultado); 
    	  
      }
      
    
      @Test
      public void testVerificarPlacaIngresaAlEstacionamientoPlacaSinANoLunesDomingo()throws ExcepcionDiaInvalido
      {
    	//Arrange
    	Vehiculo vehiculo = new CarroTestDataBuilder().withPlaca("YNP-3759").build(); 
    	when(espiaVigilante.verificacionFecha()).thenReturn(true);  
      
        //Act
    	boolean resultado = espiaVigilante.verificacionPlaca(vehiculo);
    	
    	//Assert
        assertTrue(resultado); 
      }
      
      @Test
      public void testVerificacionCantidadVehiculosCarros()throws ExcepcionRangoVehiculos
      {
    	//Arrange
        Vehiculo vehiculo = new CarroTestDataBuilder().build();
      	CeldasCarro celdasCarro = new CeldasCarro();
      	VehiculoEntidad vehiculoEntidad =  new VehiculoEntidad("XCV-234", "Inactivo", "34556", "carro");
      	List<VehiculoEntidad>  listVehiculoEntidad = new ArrayList<>();
      	listVehiculoEntidad.add(vehiculoEntidad);
      	Mockito.doReturn(listVehiculoEntidad).when(vehiculoRepositorio).findByTipo(Mockito.any());
      	
      	//Act
      	boolean resultado = vigilante.verificacionCantidadVehiculos(vehiculo, celdasCarro);
      	
      	//Assert
      	assertTrue(resultado);
       }


      @Test
      public void testVerificacionCantidadVehiculosMotos()throws ExcepcionRangoVehiculos
      {
    	//Arrange 
        Vehiculo vehiculo = new MotoTestDataBuilder().build();
      	CeldasMoto celdasMoto = new CeldasMoto();
      	VehiculoEntidad vehiculoEntidad =  new VehiculoEntidad("XCV-234", "Inactivo", "34556", "moto");
      	List<VehiculoEntidad>  listVehiculoEntidad = new ArrayList<>();
      	listVehiculoEntidad.add(vehiculoEntidad);
      	Mockito.doReturn(listVehiculoEntidad).when(vehiculoRepositorio).findByTipo(Mockito.any());
      	
      	//Act
      	boolean resultado = vigilante.verificacionCantidadVehiculos(vehiculo, celdasMoto);
      	
      	//Assert
      	assertTrue(resultado);
      }

      @Test(expected=ExcepcionRangoVehiculos.class)
      public void testVerificacionCantidadVehiculosCarrosMayorAlLimite()throws ExcepcionRangoVehiculos
      {
    	//Arrange 
        Vehiculo vehiculo = new CarroTestDataBuilder().build();
      	CeldasCarro celdasCarro = new CeldasCarro();
      	VehiculoEntidad vehiculoEntidad1 =   new VehiculoEntidad("BCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad2 =   new VehiculoEntidad("CCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad3 =   new VehiculoEntidad("DCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad4 =   new VehiculoEntidad("ECV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad5 =   new VehiculoEntidad("FCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad6 =   new VehiculoEntidad("GCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad7 =   new VehiculoEntidad("HCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad8 =   new VehiculoEntidad("ICV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad9 =   new VehiculoEntidad("JCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad10 =  new VehiculoEntidad("KCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad11 =  new VehiculoEntidad("LCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad12 =  new VehiculoEntidad("MCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad13 =  new VehiculoEntidad("NCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad14 =  new VehiculoEntidad("OCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad15 =  new VehiculoEntidad("PCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad16 =  new VehiculoEntidad("QCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad17 =  new VehiculoEntidad("RCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad18 =  new VehiculoEntidad("SCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad19 =  new VehiculoEntidad("TCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad20 =  new VehiculoEntidad("UCV-234", "Inactivo", "34556", "carro");
      	VehiculoEntidad vehiculoEntidad21 =  new VehiculoEntidad("VCV-234", "Inactivo", "34556", "carro");
      	
      	List<VehiculoEntidad>  listVehiculoEntidad = new ArrayList<>();
      	listVehiculoEntidad.add(vehiculoEntidad1);
      	listVehiculoEntidad.add(vehiculoEntidad2);
      	listVehiculoEntidad.add(vehiculoEntidad3);
      	listVehiculoEntidad.add(vehiculoEntidad4);
      	listVehiculoEntidad.add(vehiculoEntidad5);
      	listVehiculoEntidad.add(vehiculoEntidad6);
      	listVehiculoEntidad.add(vehiculoEntidad7);
      	listVehiculoEntidad.add(vehiculoEntidad8);
      	listVehiculoEntidad.add(vehiculoEntidad9);
      	listVehiculoEntidad.add(vehiculoEntidad10);
      	listVehiculoEntidad.add(vehiculoEntidad11);
      	listVehiculoEntidad.add(vehiculoEntidad12);
      	listVehiculoEntidad.add(vehiculoEntidad13);
      	listVehiculoEntidad.add(vehiculoEntidad14);
      	listVehiculoEntidad.add(vehiculoEntidad15);
      	listVehiculoEntidad.add(vehiculoEntidad16);
      	listVehiculoEntidad.add(vehiculoEntidad17);
      	listVehiculoEntidad.add(vehiculoEntidad18);
      	listVehiculoEntidad.add(vehiculoEntidad19);
      	listVehiculoEntidad.add(vehiculoEntidad20);
      	listVehiculoEntidad.add(vehiculoEntidad21);
      	
      	Mockito.doReturn(listVehiculoEntidad).when(vehiculoRepositorio).findByTipo(Mockito.any());
      	
      	//Act
      	boolean resultado = vigilante.verificacionCantidadVehiculos(vehiculo, celdasCarro);
      	
      	//Assert
      	assertTrue(resultado);
      }









}
