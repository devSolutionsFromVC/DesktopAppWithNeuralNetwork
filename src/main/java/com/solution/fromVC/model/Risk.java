package com.solution.fromVC.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "RISK")
@NamedQueries({
        @NamedQuery(name = "Risk.findByName", query = "SELECT r FROM Risk r WHERE r.name = :name"),
        @NamedQuery(name = "Risk.getRisk", query = "SELECT r FROM Risk r")}
)
public class Risk implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @Basic(optional = false)
    private String name;

    @Column(name = "DESCRIPTION")
//    @Size(max = 1000)
    private String description;

    @Column(name = "LIKELIHOOD")
//    @Size(min = 1, max = 5)
    private int likelihood;

    @Column(name = "LOSS")
//    @Size(min = 1, max = 5)
    private int loss;

    @Column(name = "VALUE")
    private int value;

    @JoinColumn(name = "PROCESS_ID", referencedColumnName = "ID")
    @ManyToOne
    private Processing processing;

    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
    @ManyToOne
    private Groups group;

    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    @ManyToOne
    private RiskStatus riskStatus;

    @JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")
    @ManyToOne
    private RiskManager manager;

    public RiskManager getManager() {
        return manager;
    }

    public void setManager(RiskManager manager) {
        this.manager = manager;
    }

    public Risk() {
    }

    public Risk(String name) {
        this.name = name;
    }

    public Risk(String name, int likelihood, int loss, int value, Processing processing) {
        this.name = name;
        this.likelihood = likelihood;
        this.loss = loss;
        this.value = value;
        this.processing = processing;
    }

    public Risk(String name, String description, int likelihood, int loss) {
        this.name = name;
        this.description = description;
        this.likelihood = likelihood;
        this.loss = loss;
    }

    public Risk(String name, String description, int likelihood, int loss, int value) {
        this.name = name;
        this.description = description;
        this.likelihood = likelihood;
        this.loss = loss;
        this.value = value;
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

    public int getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(int likelihood) {
        this.likelihood = likelihood;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Processing getProcessing() {
        return processing;
    }

    public void setProcessing(Processing processing) {
        this.processing = processing;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RiskStatus getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(RiskStatus riskStatus) {
        this.riskStatus = riskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Risk risk = (Risk) o;

        return name != null ? name.equals(risk.name) : risk.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
