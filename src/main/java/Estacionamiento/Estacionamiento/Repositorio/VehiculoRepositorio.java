package Estacionamiento.Estacionamiento.Repositorio;

import Estacionamiento.Estacionamiento.Model.VehiculoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehiculoRepositorio extends JpaRepository<VehiculoEntidad,String>
{




}
