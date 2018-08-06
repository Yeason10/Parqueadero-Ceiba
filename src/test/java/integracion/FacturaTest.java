package integracion;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import estacionamiento.EstacionamientoApplication;
import estacionamiento.servicio.Factura;


@SpringBootTest(classes=EstacionamientoApplication.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
public class FacturaTest 
{
   @Test
   public void testCobroMotoMayorANueveHorasNoExactasMenosDeUnDia()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 1, 15, 5);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroMotoMayorANueveHoras(du);
	 
	 //assert
	 assertEquals(6000,dineroAcobrarse);
	         
   }
   
   @Test
   public void testCobroMotoMayorANueveHorasMasDeUnDiaNoExactos()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 4, 3, 1);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroMotoMayorANueveHoras(du);
	 
	 //assert
	 assertEquals(18000,dineroAcobrarse);
	         
   }
   
   @Test
   public void testCobroMotoMayorANueveHorasMasDeUnDiaExactos()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 4, 1, 1);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroMotoMayorANueveHoras(du);
	 
	 //assert
	 assertEquals(14000,dineroAcobrarse);
	         
   }
   
   @Test
   public void testCobroMotoMenorANueveHorasNoExactas()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 1, 8, 5);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroMotoMenorANueveHoras(du);
	 
	 //assert
	 assertEquals(6000,dineroAcobrarse);
	         
   }
   
   @Test
   public void testCobroMotoMenorANueveHorasExactas()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 1, 8, 1);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroMotoMenorANueveHoras(du);
	 
	 //assert
	 assertEquals(5500,dineroAcobrarse);
	         
   }
   
   @Test
   public void testCobroCarroMayorANueveHorasMenosDeUnDia()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 1, 8, 1);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroCarroMayorANueveHoras(du);
	 
	 //assert
	 assertEquals(8000,dineroAcobrarse);
	         
   }
   
   @Test
   public void testCobroCarroMayorANueveHorasMasDeUnDiaNoExactos()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 5, 8, 1);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroCarroMayorANueveHoras(du);
	 
	 //assert
	 assertEquals(40000,dineroAcobrarse);
	         
   }
   
   @Test
   public void testCobroCarroMayorANueveHorasMasDeUnDiaExactos()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 5, 1, 1);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroCarroMayorANueveHoras(du);
	 
	 //assert
	 assertEquals(32000,dineroAcobrarse);
	         
   }
   
   @Test
   public void testCobroCarroMenorANueveHorasNoExactas()
   {
	 //Arrange
	 DateTime dt1 = new DateTime(2000, 1, 1, 1, 1);
	 DateTime dt2 = new DateTime(2000, 1, 1, 7, 4);
	 Duration du = new Duration(dt1, dt2);
	 Factura factura = new Factura();
	 
	 //Act
	 long dineroAcobrarse = factura.cobroCarroMenorANueveHoras(du);
	 
	 //assert
	 assertEquals(7000,dineroAcobrarse);
	         
   }



}
