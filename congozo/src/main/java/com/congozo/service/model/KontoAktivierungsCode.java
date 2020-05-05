package com.congozo.service.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "KONTO_AKTIVIERUNGS_CODE")
public class KontoAktivierungsCode extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "benutzer_id")
    private Benutzer benutzer;

    private String aktivierungsCode;

    private Date gueltigBis;

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public String getAktivierungsCode() {
        return aktivierungsCode;
    }

    public void setAktivierungsCode(String aktivierungsCode) {
        this.aktivierungsCode = aktivierungsCode;
    }

    public Date getGueltigBis() {
        return gueltigBis;
    }

    public void setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        KontoAktivierungsCode that = (KontoAktivierungsCode) o;
        return Objects.equals(benutzer, that.benutzer) &&
                Objects.equals(aktivierungsCode, that.aktivierungsCode) &&
                Objects.equals(gueltigBis, that.gueltigBis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), benutzer, aktivierungsCode, gueltigBis);
    }
}
