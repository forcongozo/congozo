package com.congozo.service.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT_ACTIVATION_CODE")
public class AccountActivationCode extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "CONGOZO_USER_ID")
    private CongozoUser CongozoUser;

    private String activationCode;

    private Date validUntil;

    public CongozoUser getCongozoUser() {
        return CongozoUser;
    }

    public void setCongozoUser(CongozoUser congozoUser) {
        this.CongozoUser = congozoUser;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String aktivierungsCode) {
        this.activationCode = aktivierungsCode;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date gueltigBis) {
        this.validUntil = gueltigBis;
    }

}
