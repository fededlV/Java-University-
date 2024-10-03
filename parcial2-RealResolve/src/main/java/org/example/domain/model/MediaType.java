package org.example.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "media_types")
public class MediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_type_id")
    private Integer id;
    private String name;

    public MediaType(String name) {
        this.name = name;
    }

    public  MediaType() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MediaType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaType mediaType = (MediaType) o;
        return Objects.equals(id, mediaType.id) && Objects.equals(name, mediaType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
