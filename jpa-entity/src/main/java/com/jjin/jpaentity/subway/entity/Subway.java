package com.jjin.jpaentity.subway.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_subway")
public class Subway {
    private Integer id;
    private String name;
    private List<Station> stations;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "subway")
    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
