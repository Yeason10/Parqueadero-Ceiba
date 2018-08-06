package estacionaminento.unitarias;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import Estacionamiento.Estacionamiento.Vehiculo;
import Estacionamiento.Estacionamiento.Servicio.Factura;
import Estacionamiento.Estacionamiento.Servicio.Fecha;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;

public class FacturaTest 
{
	 @Spy
	 @InjectMocks	 
	 Factura factura;
	 
	 @Mock
	 Fecha fecha;
	
	@Before 
	public void initMocks(){
    	 MockitoAnnotations.initMocks(this); 
     }
   
	
	
	@Test
	public void testCobroSalidaDeMotoMayorAnueveHorasMenosDeUnDiaCilindrajeAlto() throws Throwable
	{
		//Arrange
		DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	    dt1.toDate();
        DateTime dt2 = new DateTime(2000, 1, 1, 11, 1);
        dt2.toDate();
        
        
        Duration du = new Duration(dt1, dt2);
        
	    
	    Vehiculo vehiculo = new MotoTestDataBuilder().withCilindraje("650").build(); 
        vehiculo.setEstado("");
       
        Mockito.when(fecha.obtenertCantDeTiempoEnParqueadero(Mockito.any(), Mockito.any())).thenReturn(du); 
        Mockito.doReturn((long)6000).when(factura).cobroMotoMayorANueveHoras(Mockito.any());
       
        //Act
        long dineroAcobrarse = factura.cobroSalidaDeVehiculo(vehiculo);
		
        //assert
        assertEquals(6000,dineroAcobrarse); 
		 
	}
	
	@Test
	public void testCobroSalidaDeMotoMayorAnueveHorasMasDeUnDiaNoExactosCilindrajeAlto() throws Throwable
	{
		//Arrange
		DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	    dt1.toDate();
        DateTime dt2 = new DateTime(2000, 1, 4, 5, 1);
        dt2.toDate();
        
        
        Duration du = new Duration(dt1, dt2);
       
	    
	    
        Vehiculo vehiculo = new MotoTestDataBuilder().withCilindraje("650").build();
        vehiculo.setEstado("");
        
        Mockito.when(fecha.obtenertCantDeTiempoEnParqueadero(Mockito.any(), Mockito.any())).thenReturn(du);
        Mockito.doReturn((long)18000).when(factura).cobroMotoMayorANueveHoras(Mockito.any());
        
        //Act
        long dineroAcobrarse = factura.cobroSalidaDeVehiculo(vehiculo);
		
        //assert
        assertEquals(18000,dineroAcobrarse); 
		  
	}
	
	@Test
	public void testCobroSalidaDeMotoMayorAnueveHorasMasDeUnDiaExactosCilindrajeAlto() throws Throwable
	{
		//Arrange
		
	    DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	    dt1.toDate();
        DateTime dt2 = new DateTime(2000, 1, 4, 1, 1);
        dt2.toDate();
        
        
        Duration du = new Duration(dt1, dt2);
        
	    
	    //Arrange
        Vehiculo vehiculo = new MotoTestDataBuilder().withCilindraje("650").build();
        vehiculo.setEstado("");
    
        Mockito.when(fecha.obtenertCantDeTiempoEnParqueadero(Mockito.any(), Mockito.any())).thenReturn(du);
        Mockito.doReturn((long)14000).when(factura).cobroMotoMayorANueveHoras(Mockito.any());
       
        //Act
        long dineroAcobrarse = factura.cobroSalidaDeVehiculo(vehiculo);
		
        //assert
        assertEquals(14000,dineroAcobrarse);  
		 
	}
	
	@Test
	public void testCobroSalidaDeMotoMayorAnueveHorasNoExactasCilindrajeBajo() throws Throwable
	{
		//Arrange
		DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	    dt1.toDate();
        DateTime dt2 = new DateTime(2000, 1, 2, 8, 1);
        dt2.toDate();
        
        
        Duration du = new Duration(dt1, dt2);
       
	    
        Vehiculo vehiculo = new MotoTestDataBuilder().build();  
        vehiculo.setEstado("");
        
        Mockito.when(fecha.obtenertCantDeTiempoEnParqueadero(Mockito.any(), Mockito.any())).thenReturn(du);
        Mockito.doReturn((long)10000).when(factura).cobroMotoMayorANueveHoras(Mockito.any());
        
        //Act
        long dineroAcobrarse = factura.cobroSalidaDeVehiculo(vehiculo);
		
        //assert
        assertEquals(8000,dineroAcobrarse); 
		 
	} 
	
	@Test
	public void testCobroSalidaDeMotoMenorAnueveHorasNoExactasCilindrajeBajo() throws Throwable
	{
		//Arrange
		DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	    dt1.toDate();
        DateTime dt2 = new DateTime(2000, 1, 1, 8, 5);
        dt2.toDate();
        
        
        Duration du = new Duration(dt1, dt2);
       
       
	    
        Vehiculo vehiculo = new MotoTestDataBuilder().build();  
        vehiculo.setEstado("");
        
        Mockito.when(fecha.obtenertCantDeTiempoEnParqueadero(Mockito.any(), Mockito.any())).thenReturn(du);
        Mockito.doReturn((long)6000).when(factura).cobroMotoMenorANueveHoras(Mockito.any());
        
        //Act
        long dineroAcobrarse = factura.cobroSalidaDeVehiculo(vehiculo);
		
        //assert
        assertEquals(4000,dineroAcobrarse); 
		 
	} 
	
	@Test
	public void testCobroSalidaDeMotoMenorAnueveHorasNoExactasCilindrajeAlto() throws Throwable
	{
		//Arrange
		DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	    dt1.toDate();
        DateTime dt2 = new DateTime(2000, 1, 1, 8, 5);
        dt2.toDate();
        
        
        Duration du = new Duration(dt1, dt2);
        
       
	    
        Vehiculo vehiculo = new MotoTestDataBuilder().withCilindraje("615").build();  
        vehiculo.setEstado("");
        
        Mockito.when(fecha.obtenertCantDeTiempoEnParqueadero(Mockito.any(), Mockito.any())).thenReturn(du);
        Mockito.doReturn((long)6000).when(factura).cobroMotoMenorANueveHoras(Mockito.any());
        
        //Act
        long dineroAcobrarse = factura.cobroSalidaDeVehiculo(vehiculo);
		
        //assert
        assertEquals(6000,dineroAcobrarse); 
		 
	} 
    
	@Test
	public void testCobroSalidaDeCarroMayorAnueveHorasNoExactasMenosDeUnDia() throws Throwable
	{
		//Arrange
		DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	    dt1.toDate();
        DateTime dt2 = new DateTime(2000, 1, 1, 13, 5);
        dt2.toDate();
        
        
        Duration du = new Duration(dt1, dt2);
        
       
	    
        Vehiculo vehiculo = new CarroTestDataBuilder().build();  
        vehiculo.setEstado("");
        
        Mockito.when(fecha.obtenertCantDeTiempoEnParqueadero(Mockito.any(), Mockito.any())).thenReturn(du);
        Mockito.doReturn((long)13000).when(factura).cobroCarroMayorANueveHoras(Mockito.any()); 
        
        //Act
        long dineroAcobrarse = factura.cobroSalidaDeVehiculo(vehiculo);
		
        //assert
        assertEquals(13000,dineroAcobrarse); 
		 
	} 




}
