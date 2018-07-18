package Estacionamiento.Estacionamiento;

import java.util.ArrayList;


public class Estacionamiento 
{

  final static int celdasExistentesCarro = 20;
  final static int celdasExistentesMoto = 10;
  private int celdasDisponiblesCarro;
  private int celdasDisponiblesMoto;
  private ArrayList<Vehiculo> vehiculos;
  
  public Estacionamiento(int celdasDisponiblesCarro,int celdasDisponiblesMoto, ArrayList<Vehiculo> vehiculos)
  {
	setCeldasDisponiblesCarro(celdasDisponiblesCarro);
	setCeldasDisponiblesMoto(celdasDisponiblesMoto);
	setVehiculos(vehiculos);
  }
  
  public int getCeldasDisponiblesCarro()
  {
	return celdasDisponiblesCarro;  
  }
  
  public int getCeldasDisponiblesMoto()
  {
	return celdasDisponiblesMoto;  
  }
  
  public ArrayList<Vehiculo> getVehiculos()
  {
	return vehiculos;
  }
  
  public void setVehiculos(ArrayList<Vehiculo> vehiculos)
  {
	this.vehiculos = vehiculos;
  }
  
  public void setCeldasDisponiblesCarro(int celdasDisponiblesCarro)
  {
	  this.celdasDisponiblesCarro = celdasDisponiblesCarro;
  }
  
  public void setCeldasDisponiblesMoto(int celdasDisponiblesMoto)
  {
	  this.celdasDisponiblesMoto = celdasDisponiblesMoto;
  }
  
}
