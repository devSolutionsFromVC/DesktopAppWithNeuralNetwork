package com.solution.fromVC.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ASSETS")
@NamedQueries({
        @NamedQuery(name = "Asset.findByNameAnd Description",
                query = "SELECT a FROM Assets a WHERE a.name = :name AND a.description = :description"),
        @NamedQuery(name = "Asset.getAsset", query = "SELECT a FROM Assets a")
})
public class Assets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    //@Size(max = 1000)
    private String description;

    //ценность
    @Basic(optional = false)
    @Column(name = "WORTH")
    //@Size(min = 1, max = 5)
    private int worth;

    @Column(name = "CONFIDENTIALITY")
    private Boolean confidentiality;

    @Column(name = "INTEGRITY")
    private Boolean integrity;

    @Column(name = "ACCESSIBILITY")
    private Boolean accessibility;

    @OneToMany(mappedBy = "assets", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Threat> threatList;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "assets")
    private List<Vulnerability> vulnerabilities;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "assets")
    private List<Risk> riskList;

    public Assets() {
    }

    public Assets(String name) {
        this.name = name;
    }

    public Assets(String name, int worth) {
        this.name = name;
        this.worth = worth;
    }

    public Assets(String name, String description, int worth, Boolean confidentiality, Boolean integrity, Boolean accessibility) {
        this.name = name;
        this.description = description;
        this.worth = worth;
        this.confidentiality = confidentiality;
        this.integrity = integrity;
        this.accessibility = accessibility;
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

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public boolean getConfidentiality() {
        return confidentiality;
    }

    public void setConfidentiality(boolean confidentiality) {
        this.confidentiality = confidentiality;
    }

    public boolean getIntegrity() {
        return integrity;
    }

    public void setIntegrity(boolean integrity) {
        this.integrity = integrity;
    }

    public boolean getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    public List<Threat> getThreatList() {
        return threatList;
    }

    public void setThreatList(List<Threat> threatList) {
        this.threatList = threatList;
    }

    public List<Vulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public List<Risk> getRiskList() {
        return riskList;
    }

    public void setRiskList(List<Risk> riskList) {
        this.riskList = riskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assets assets = (Assets) o;

        return name != null ? name.equals(assets.name) : assets.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return   name  ;
//                "description: " + description + " " +
//                "worth: " + worth + " " +
//                "conf: " + confidentiality + " " +
//                "integr: " + integrity + " " +
//                "access: " + accessibility;
    }
}
