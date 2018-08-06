package Estacionamiento.Estacionamiento;

import java.util.Date;

public class Carro extends Vehiculo
{
    public Carro(String placa,String estado,String cilindraje,String tipo,Date fechaIngreso)
    {
     super(placa,estado,cilindraje,tipo,fechaIngreso); 
    } 

    public Carro(String placa,String tipo,String cilindraje,Date fechaIngreso)
    {
      super(placa,tipo,cilindraje,fechaIngreso);
    }

}
