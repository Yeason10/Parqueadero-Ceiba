package estacionamiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import estacionamiento.model.VehiculoEntidad;



@Repository
public interface VehiculoRepositorioJPA extends JpaRepository<VehiculoEntidad,String>
{
	List<VehiculoEntidad> findByTipo(String tipo);
}


