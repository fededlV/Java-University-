package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
        @NamedQuery(name = "Actor.findByName", query = "SELECT a FROM Actor a WHERE a.firstName == :name")
})
public class Actor {

    @Id
    @Column(name = "actor_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastUpdate;

    //Hibernate necesita un constructor si o si sin parametros para crear un objeto de este, hibernate le agrega todo.
    public Actor() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
