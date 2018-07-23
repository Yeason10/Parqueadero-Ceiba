package Estacionamiento.Estacionamiento;

public class Moto extends Vehiculo 
{
 private int cilindraje;
 
 public Moto(String placa,int cilindraje)
 {
  super(placa); 
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
