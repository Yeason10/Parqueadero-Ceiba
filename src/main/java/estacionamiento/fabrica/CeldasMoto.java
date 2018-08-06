package estacionamiento.fabrica;



public class CeldasMoto extends Celdas
{
   
   private static final int CANT_DE_CELDAS_DISPONIBLES=10;
	  
   @Override
   public int getCantidadCeldasDisponibles()
   {
	  return CANT_DE_CELDAS_DISPONIBLES;
   }
} 
