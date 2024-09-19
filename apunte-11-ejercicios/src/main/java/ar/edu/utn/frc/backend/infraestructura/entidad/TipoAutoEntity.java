package ar.edu.utn.frc.backend.infraestructura.entidad;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "tipoAuto")
public class TipoAutoEntity {
	@Id
	private Integer id;
	private String nombre;
	@ManyToMany(mappedBy = "tipoAuto")
	private List<ModeloEntity> modeloList;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
}
