package testdatabuilder;

import java.util.Date;

import Estacionamiento.Estacionamiento.Moto;

public class MotoTestDataBuilder 
{
        private String placa;
	    private String tipo;
	    private String cilindraje;
	    private Date fechaIngreso;

	    public MotoTestDataBuilder()
	    {
	     this.placa = "WXY-3345"; 
	     this.tipo = "Carro";
	     this.cilindraje="115";
	     this.fechaIngreso= new Date();
	    }
	    
	    public MotoTestDataBuilder withPlaca(String placa)
	    {
	    	this.placa = placa;
	        return this;
	    }
	    
	    public MotoTestDataBuilder withTipo(String tipo)
	    {
	    	this.tipo = tipo;
	        return this;
	    }
	    
	    public MotoTestDataBuilder withCilindraje(String cilindraje)
	    {
	    	this.cilindraje = cilindraje;
	        return this;
	    }
	    
	    public MotoTestDataBuilder withFechaIngreso(Date fechaIngreso)
	    {
	    	this.fechaIngreso = fechaIngreso;
	        return this;
	    }


	    public Moto build()
	    {
	    	return new Moto(this.placa,this.tipo,this.cilindraje,this.fechaIngreso);
	    }

}
