package com.example.parser.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doc")
public class Doc implements Comparable<Doc> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    public Doc() {}

    public Doc(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Doc o) {
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doc)) return false;
        Doc doc = (Doc) o;
        return Objects.equals(getId(), doc.getId()) &&
                Objects.equals(getName(), doc.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
