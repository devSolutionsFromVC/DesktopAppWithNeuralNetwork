package com.solution.fromVC.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RISK_STATUS")
public class RiskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;

    @Column(name = "DESCRIPTION")
//    @Size(max = 1000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "riskStatus")
    private List<Risk> riskList;

    public RiskStatus() {
    }

    public RiskStatus(Long id) {
        this.id = id;
    }

    public RiskStatus(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        RiskStatus that = (RiskStatus) o;

        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        return status != null ? status.hashCode() : 0;
    }
}
