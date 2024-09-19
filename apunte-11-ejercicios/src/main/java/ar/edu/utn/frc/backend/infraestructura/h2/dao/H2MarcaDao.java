package ar.edu.utn.frc.backend.infraestructura.h2.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import ar.edu.utn.frc.backend.infraestructura.dao.MarcaDao;
import ar.edu.utn.frc.backend.infraestructura.entidad.MarcaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class H2MarcaDao implements MarcaDao {

	@Override
	public Optional<MarcaEntity> getMarca(Integer id) {

		try(EntityManagerFactory emf =
					Persistence.createEntityManagerFactory("gestorPersi");
			EntityManager entityManager = emf.createEntityManager()) {

			MarcaEntity entity = entityManager.find(MarcaEntity.class, id);
			return Optional.ofNullable(entity);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override public List<MarcaEntity> findAll() {
		return Collections.emptyList();
	}
}
