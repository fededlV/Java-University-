package ar.edu.utn.frc.backend.infraestructura.dao;

import java.util.List;
import java.util.Optional;

import ar.edu.utn.frc.backend.infraestructura.entidad.TipoAutoEntity;

public interface TipoAutoDao {
	Optional<TipoAutoEntity> getTipoAuto(Integer id);
	List<TipoAutoEntity> findAll();
}
