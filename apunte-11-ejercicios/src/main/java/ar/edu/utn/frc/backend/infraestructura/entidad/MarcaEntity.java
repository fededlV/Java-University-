package ar.edu.utn.frc.backend.infraestructura.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "marca")
public class MarcaEntity {
	@Id
	private Integer id;
	private String nombre;
	@OneToMany(mappedBy = "marca")
	private List<ModeloEntity> modelos;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
}
