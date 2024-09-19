package ar.edu.utn.frc.backend.infraestructura.repositorio;

import ar.edu.utn.frc.backend.dominio.modelo.Marca;
import ar.edu.utn.frc.backend.dominio.modelo.Modelo;
import ar.edu.utn.frc.backend.dominio.repositorio.ModeloRepository;
import ar.edu.utn.frc.backend.infraestructura.dao.ModeloDao;

import java.util.Collections;

public class ModeloRepositoryImpl implements ModeloRepository {
    ModeloDao modeloDao;

    public ModeloRepositoryImpl(ModeloDao modeloDao) {
        this.modeloDao = modeloDao;
    }

    @Override
    public Modelo get(Integer id) {
        return modeloDao.getModelo(id)
                .map((entity) ->
                        new Modelo(
                                entity.getId(),
                        entity.getNombre(),
                        entity.getAnio(),
                                new Marca(
                                        entity.getMarca().getId(),
                                        entity.getMarca().getNombre()
                                ),
                                Collections.emptyList()))
                .orElseThrow();
    }
}
