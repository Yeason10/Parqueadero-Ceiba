package Estacionamiento.Estacionamiento;

import java.util.Date;

public class Vehiculo 
{
  private String placa;
  Date fechaIngreso;
  
  public Vehiculo(String placa,Date fechaIngreso)
  {
	setPlaca(placa); 
	setFechaIngreso(fechaIngreso);
  }
  
  public String getPlaca()
  {
    return placa;
  }
  
  public void setPlaca(String placa)
  {
   this.placa = placa;
  }

  public Date getFechaIngreso()
  {
	return fechaIngreso;
  }
  
  public void setFechaIngreso(Date fechaIngreso)
  {
	this.fechaIngreso = fechaIngreso;
  }
  

}
