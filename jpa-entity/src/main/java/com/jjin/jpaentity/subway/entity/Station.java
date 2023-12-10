package com.jjin.jpaentity.subway.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_station")
public class Station {
    private Integer id;
    private String name;
    private String code;
    private String freeCode;
    private Boolean isMainLine;
    private Subway subway;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFreeCode() {
        return freeCode;
    }

    public void setFreeCode(String freeCode) {
        this.freeCode = freeCode;
    }

    @Column(name = "is_main_line")
    public Boolean getMainLine() {
        return isMainLine;
    }

    public void setMainLine(Boolean mainLine) {
        isMainLine = mainLine;
    }

    @JoinColumn(name = "subway_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    public Subway getSubway() {
        return subway;
    }

    public void setSubway(Subway subway) {
        this.subway = subway;
    }
}
