package ar.edu.utn.frc.backend.infraestructura.dao;

import java.util.Optional;

import ar.edu.utn.frc.backend.dominio.modelo.Modelo;
import ar.edu.utn.frc.backend.infraestructura.entidad.ModeloEntity;

public interface ModeloDao {
	Optional<ModeloEntity> getModelo(Integer id);
}
