package com.solution.fromVC.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "MANAGER")
@NamedQueries({
        @NamedQuery(name = "Manager.findByName", query = "SELECT m FROM RiskManager m WHERE m.name = :name")
})
public class RiskManager implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", unique = true)
    @Basic(optional = false)
    private String name;

    @Column(name = "PASSWORD")
    @Basic(optional = false)
    private String password;

    @Column(name = "COMPANY")
    @Basic(optional = false)
    private String company;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private List<Risk> risk;

    public RiskManager() {
    }

    public RiskManager(String name) {
        this.name = name;
    }

    public RiskManager(String name, String password, String company) {
        this.name = name;
        this.password = password;
        this.company = company;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Risk> getRisk() {
        return risk;
    }

    public void setRisk(List<Risk> risk) {
        this.risk = risk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RiskManager that = (RiskManager) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return company != null ? company.equals(that.company) : that.company == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }
}
