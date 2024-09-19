package ar.edu.utn.frc.backend.infraestructura.entidad;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "modelo")
public class ModeloEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nombre;
	private Integer anio;
	@ManyToOne
	@JoinColumn(name = "ID_MARCA", nullable = false)
	private MarcaEntity marca;
	@ManyToMany
	private List<TipoAutoEntity> tipoAuto;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	public Integer getAnio() {
		return anio;
	}

	public MarcaEntity getMarca() {
		return marca;
	}
}
