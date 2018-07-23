package Estacionamiento.Estacionamiento.Model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.io.Serializable;

@Entity
@Table(name = "vehiculos")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)

public class VehiculoEntidad implements Serializable 
{
    @Id
    private String placa;

    @NotBlank
    private String cilindraje;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    public String getPlaca()
    {
      return placa;
    }
    
    public String getCilindraje()
    {
      return cilindraje;	
    }
    
    public Date getCreatedAt()
    {
      return createdAt;
    }
    
    public void setPlaca(String placa)
    {
     this.placa = placa;
    }
    
    public void setCilindraje(String cilindraje)
    {
      this.cilindraje = cilindraje;
    }
    
    public void setCreatedAt(Date createdAt)
    {
      this.createdAt = createdAt;
    }
}