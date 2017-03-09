package com.solution.fromVC.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "THREAT")
public class Threat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
//    @Size(max = 1000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "threat")
    private List<Vulnerability> vulnerability;

    @JoinColumn(name = "ASSET_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Assets assets;

    public Threat() {
    }

    public Threat(String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Vulnerability> getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(List<Vulnerability> vulnerability) {
        this.vulnerability = vulnerability;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Threat threat = (Threat) o;

        return name != null ? name.equals(threat.name) : threat.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
