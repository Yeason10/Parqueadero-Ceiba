package Estacionamiento.Estacionamiento.fabrica;




public class CeldasFabrica 
{
  public CeldasFabrica(){}
  
  public Celdas creacionEstacionamiento(String tipo)
  {
	 if(tipo.equals("moto"))
	 {
       return new CeldasMoto(); 
	 }
	 else  
	   return new CeldasCarro();
   }
}
