package Estacionamiento.Estacionamiento.exception;


public class ExcepcionRangoVehiculos extends Exception 
{ 
	
	private static final long serialVersionUID = -8901983612232852661L;
	public ExcepcionRangoVehiculos() {}
	public ExcepcionRangoVehiculos(String msg)
	{
		super(msg);
	}

}
