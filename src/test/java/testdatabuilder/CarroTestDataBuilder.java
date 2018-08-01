package testdatabuilder;

import java.util.Date;

import Estacionamiento.Estacionamiento.Carro;

public class CarroTestDataBuilder 
{
    private String placa;
    private String tipo;
    private String cilindraje;
    private Date fechaIngreso;

    public CarroTestDataBuilder()
    {
     this.placa = "WXY-3345"; 
     this.tipo = "Carro";
     this.cilindraje="115";
     this.fechaIngreso= new Date();
    }
    
    public CarroTestDataBuilder withPlaca(String placa)
    {
    	this.placa = placa;
        return this;
    }
    
    public CarroTestDataBuilder withTipo(String tipo)
    {
    	this.tipo = tipo;
        return this;
    }
    
    public CarroTestDataBuilder withCilindraje(String cilindraje)
    {
    	this.cilindraje = cilindraje;
        return this;
    }
    
    public CarroTestDataBuilder withFechaIngreso(Date fechaIngreso)
    {
    	this.fechaIngreso = fechaIngreso;
        return this;
    }


    public Carro build()
    {
    	return new Carro(this.placa,this.tipo,this.cilindraje,this.fechaIngreso);
    }



}
