package com.solution.fromVC.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "PROCESSING")
public class Processing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROCESS_NAME")
    @Basic(optional = false)
    private String processName;

    @OneToMany(mappedBy = "processing")
    private List<Risk> riskList;

    public Processing() {
    }

    public Processing(String processName) {
        this.processName = processName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

        Processing that = (Processing) o;

        return processName != null ? processName.equals(that.processName) : that.processName == null;
    }

    @Override
    public int hashCode() {
        return processName != null ? processName.hashCode() : 0;
    }
}
