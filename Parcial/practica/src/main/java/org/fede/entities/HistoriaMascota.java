package org.fede.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CollectionId;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class HistoriaMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Integer idHistorico;
    @Column(name = "tipo_historico")
    private String tipoHistorico;
    @Column(name = "energia_inicio")
    private Integer energiaInicio;
    @Column(name = "energia_fin")
    private Integer energiaFin;
    @Column(name = "humor_inicio")
    private Integer humorInicio;
    @Column(name = "humor_fin")
    private Integer humorFin;
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    @ManyToOne
    @JoinColumn(
            name = "mascota_id"
    )
    private Mascotas mascotas;


    public HistoriaMascota() {
    }

    public HistoriaMascota(String tipoHistorico, Integer energiaInicio, Integer energiaFin, Integer humorInicio, Integer humorFin, LocalDateTime fechaHora, Mascotas mascotas) {
        this.tipoHistorico = tipoHistorico;
        this.energiaInicio = energiaInicio;
        this.energiaFin = energiaFin;
        this.humorInicio = humorInicio;
        this.humorFin = humorFin;
        this.fechaHora = fechaHora;
        this.mascotas = mascotas;
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getTipoHistorico() {
        return tipoHistorico;
    }

    public void setTipoHistorico(String tipoHistorico) {
        this.tipoHistorico = tipoHistorico;
    }

    public Integer getEnergiaInicio() {
        return energiaInicio;
    }

    public void setEnergiaInicio(Integer energiaInicio) {
        this.energiaInicio = energiaInicio;
    }

    public Integer getEnergiaFin() {
        return energiaFin;
    }

    public void setEnergiaFin(Integer energiaFin) {
        this.energiaFin = energiaFin;
    }

    public Integer getHumorInicio() {
        return humorInicio;
    }

    public void setHumorInicio(Integer humorInicio) {
        this.humorInicio = humorInicio;
    }

    public Integer getHumorFin() {
        return humorFin;
    }

    public void setHumorFin(Integer humorFin) {
        this.humorFin = humorFin;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Mascotas getMascotas() {
        return mascotas;
    }

    public void setMascotas(Mascotas mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoriaMascota that = (HistoriaMascota) o;
        return Objects.equals(idHistorico, that.idHistorico) && Objects.equals(tipoHistorico, that.tipoHistorico) && Objects.equals(energiaInicio, that.energiaInicio) && Objects.equals(energiaFin, that.energiaFin) && Objects.equals(humorInicio, that.humorInicio) && Objects.equals(humorFin, that.humorFin) && Objects.equals(fechaHora, that.fechaHora) && Objects.equals(mascotas, that.mascotas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorico, tipoHistorico, energiaInicio, energiaFin, humorInicio, humorFin, fechaHora, mascotas);
    }

    @Override
    public String toString() {
        return "HistoriaMascota{" +
                "idHistorico=" + idHistorico +
                ", tipoHistorico='" + tipoHistorico + '\'' +
                ", energiaInicio=" + energiaInicio +
                ", energiaFin=" + energiaFin +
                ", humorInicio=" + humorInicio +
                ", humorFin=" + humorFin +
                ", fechaHora=" + fechaHora +
                ", mascotas=" + mascotas +
                '}';
    }
}
