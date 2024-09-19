package ar.edu.utn.frc.backend.infraestructura.dao;

import java.util.List;
import java.util.Optional;

import ar.edu.utn.frc.backend.dominio.modelo.Marca;
import ar.edu.utn.frc.backend.infraestructura.entidad.MarcaEntity;

public interface MarcaDao {
	Optional<MarcaEntity> getMarca(Integer id);
	List<MarcaEntity> findAll();
}
