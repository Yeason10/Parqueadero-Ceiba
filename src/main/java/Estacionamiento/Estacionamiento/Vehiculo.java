package Estacionamiento.Estacionamiento;

import java.util.Date;

public class Vehiculo 
{
  private String placa;
  private String estado;
  private String cilindraje;
  private String tipo;
  private Date fechaIngreso;
  
  public Vehiculo(){}
  public Vehiculo(String placa,String estado,String cilindraje,String tipo,Date fechaIngreso)
  {
	setPlaca(placa); 
	this.estado="INACTIVO";
	setTipo(tipo);
	setCilindraje(cilindraje);
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

  public String getEstado()
  {
	return estado;
  }
  
  public void setEstado(String estado)
  {
	this.estado = estado;
  }
  
  public String getCilindraje()
  {
   return cilindraje;
  }

  public void setCilindraje(String cilindraje)
  {
   this.cilindraje = cilindraje;
  }
  
  public String getTipo()
  {
	return tipo;
  }
 
  public Date getFechaIngreso()
  {
	return fechaIngreso;
  }
  
  public void setTipo(String tipo)
  {
	this.tipo = tipo;
  }
  
  public void setFechaIngreso(Date fechaIngreso)
  {
	this.fechaIngreso = fechaIngreso;
  }
  

}
