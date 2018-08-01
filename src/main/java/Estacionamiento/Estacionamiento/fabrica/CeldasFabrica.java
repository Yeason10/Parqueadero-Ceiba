package Estacionamiento.Estacionamiento.Fabrica;




public class CeldasFabrica 
{
  private CeldasFabrica(){}
  public static Celdas creacionEstacionamiento(String tipo)
  {
	 if(tipo.equals("moto"))
	 {
       return new CeldasMoto();
	 }
	 else 
	   return new CeldasCarro();
   }
}
