package Estacionamiento.Estacionamiento;

import java.util.Date;

public class Moto extends Vehiculo 
{
 private int cilindraje;
 
 
 public Moto(String placa,Date fechaIngreso,int cilindraje)
 {
  super(placa,fechaIngreso); 
  setCilindraje(cilindraje);
 }

 public int getCilindraje()
 {
  return cilindraje;
 }

 public void setCilindraje(int cilindraje)
 {
  this.cilindraje = cilindraje;
 }
}
