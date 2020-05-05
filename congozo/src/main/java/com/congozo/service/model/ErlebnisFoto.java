package com.congozo.service.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "ERLEBNIS_FOTO")
public class ErlebnisFoto extends BaseEntity {

    private Blob foto;

    private String filename;

    @ManyToOne
    @JoinColumn(name = "erlebnis_id")
    private Erlebnis erlebnis;

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Erlebnis getErlebnis() {
        return erlebnis;
    }

    public void setErlebnis(Erlebnis erlebnis) {
        this.erlebnis = erlebnis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ErlebnisFoto that = (ErlebnisFoto) o;
        return Objects.equals(foto, that.foto) &&
                Objects.equals(filename, that.filename) &&
                Objects.equals(erlebnis, that.erlebnis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foto, filename, erlebnis);
    }
}
