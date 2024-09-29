package org.example.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "backend.user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Repository> repositories = new HashSet<>();


    public User(String name) {
        this.name = name;
        
    } 

    public Set<Repository> getRepositories() {
        return repositories;
    }
    

    public void setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
    }

    public void addRepository(Repository repository) {
        repositories.add(repository);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    /* public void addRepository(Repository repository) {
        repositories.add(repository);
    }

    public int getSizeRepository() {
        return repositories.size();
    }

    public double getStars() {
        return repositories.stream().mapToDouble(Repository::getStars).sum();
    } */

    

    
}
