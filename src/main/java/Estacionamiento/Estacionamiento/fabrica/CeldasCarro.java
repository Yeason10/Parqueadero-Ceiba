package Estacionamiento.Estacionamiento.fabrica;



public class CeldasCarro extends Celdas
{ 
  private static final int CANT_DE_CELDAS_DISPONIBLES=20;
  
  @Override
  public int getCantidadCeldasDisponibles()
  { 
	 return CANT_DE_CELDAS_DISPONIBLES;
  }
}
