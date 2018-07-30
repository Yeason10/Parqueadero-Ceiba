package Estacionamiento.Estacionamiento.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;



@Repository
public interface VehiculoRepositorio extends JpaRepository<VehiculoEntidad,String>
{
	List<VehiculoEntidad> findByTipo(String tipo);
}


