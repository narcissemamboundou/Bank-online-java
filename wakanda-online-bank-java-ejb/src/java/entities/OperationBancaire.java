/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author n.mamboundou
 */
@Entity
public class OperationBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String description;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateOperation;

    public OperationBancaire() {
    }

    public OperationBancaire(String description) {
        this.description = description;
        dateOperation= new Date();
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperationBancaire)) {
            return false;
        }
        OperationBancaire other = (OperationBancaire) object;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "entities.OperationBancaire[ id=" + id + " ]";
    }
    
}
