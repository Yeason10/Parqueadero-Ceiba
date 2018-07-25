package Estacionamiento.Estacionamiento.Model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.io.Serializable;

@Entity
@Table(name = "vehiculos")
@EntityListeners(AuditingEntityListener.class)


public class VehiculoEntidad implements Serializable 
{
    @Id
    private String placa;

    @NotBlank
    private String cilindraje;
    
    @NotBlank
    private String estado;
    
    @NotBlank
    private String tipo;

    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date fechaIngreso;

    public VehiculoEntidad(){}
    public VehiculoEntidad(String placa,String estado,String cilindraje,String tipo)
    {
      setPlaca(placa);
      this.estado = "INACTIVO";
      setCilindraje(cilindraje);
      setTipo(tipo);
      
    }
    
    public String getPlaca()
    {
      return placa;
    }
    
    public String getCilindraje()
    {
      return cilindraje;	
    }
    
    public String getEstado()
    {
      return estado;
    }
    
    public String getTipo()
    {
      return tipo;
    }
    
    public Date getFechaIngreso()
    {
      return fechaIngreso;
    }
    
    public void setPlaca(String placa)
    {
     this.placa = placa;
    }
    
    public void setCilindraje(String cilindraje)
    {
      this.cilindraje = cilindraje;
    }
    public void setEstado(String estado)
    {
      this.estado = estado;
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