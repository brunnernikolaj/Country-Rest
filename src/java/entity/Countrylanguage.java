/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nikolaj
 */
@Entity
@Table(name = "countrylanguage")
@NamedQueries({
    @NamedQuery(name = "Countrylanguage.findAll", query = "SELECT c FROM Countrylanguage c"),
    @NamedQuery(name = "Countrylanguage.findByCountryCode", query = "SELECT c FROM Countrylanguage c WHERE c.countrylanguagePK.countryCode = :countryCode"),
    @NamedQuery(name = "Countrylanguage.findByLanguage", query = "SELECT c FROM Countrylanguage c WHERE c.countrylanguagePK.language = :language"),
    @NamedQuery(name = "Countrylanguage.findByIsOfficial", query = "SELECT c FROM Countrylanguage c WHERE c.isOfficial = :isOfficial"),
    @NamedQuery(name = "Countrylanguage.findByPercentage", query = "SELECT c FROM Countrylanguage c WHERE c.percentage = :percentage")})
public class Countrylanguage implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CountrylanguagePK countrylanguagePK;
    @Basic(optional = false)
    @Column(name = "IsOfficial")
    private String isOfficial;
    @Basic(optional = false)
    @Column(name = "Percentage")
    private float percentage;

    public Countrylanguage() {
    }

    public Countrylanguage(CountrylanguagePK countrylanguagePK) {
        this.countrylanguagePK = countrylanguagePK;
    }

    public Countrylanguage(CountrylanguagePK countrylanguagePK, String isOfficial, float percentage) {
        this.countrylanguagePK = countrylanguagePK;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    public Countrylanguage(String countryCode, String language) {
        this.countrylanguagePK = new CountrylanguagePK(countryCode, language);
    }

    public CountrylanguagePK getCountrylanguagePK() {
        return countrylanguagePK;
    }

    public void setCountrylanguagePK(CountrylanguagePK countrylanguagePK) {
        this.countrylanguagePK = countrylanguagePK;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countrylanguagePK != null ? countrylanguagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Countrylanguage)) {
            return false;
        }
        Countrylanguage other = (Countrylanguage) object;
        if ((this.countrylanguagePK == null && other.countrylanguagePK != null) || (this.countrylanguagePK != null && !this.countrylanguagePK.equals(other.countrylanguagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "servlet.Countrylanguage[ countrylanguagePK=" + countrylanguagePK + " ]";
    }
    
}
