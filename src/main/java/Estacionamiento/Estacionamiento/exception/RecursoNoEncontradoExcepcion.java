package Estacionamiento.Estacionamiento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoExcepcion extends RuntimeException {
	
	private static final long serialVersionUID = 997477375338771076L;
	private String nombreRecurso;
	private String campoNombre;
	private Object campoValor;

	public RecursoNoEncontradoExcepcion(String nombreRecurso, String campoNombre, Object campoValor) {
		super(String.format("% not found with %s : '%s'", nombreRecurso, campoNombre, campoValor));
		this.nombreRecurso = nombreRecurso;
		this.campoNombre = campoNombre;
		this.campoValor = campoValor;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public String getCampoNombre() {
		return campoNombre;
	}

	public Object getCampoValor() {
		return campoValor;
	}

}
