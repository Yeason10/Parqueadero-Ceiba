package Estacionamiento.Estacionamiento;

public class Vehiculo 
{
  private String placa;
  
  public Vehiculo(String placa)
  {
	setPlaca(placa); 
  }
  
  public String getPlaca()
  {
    return placa;
  }
  
  public void setPlaca(String placa)
  {
   this.placa = placa;
  }
}
