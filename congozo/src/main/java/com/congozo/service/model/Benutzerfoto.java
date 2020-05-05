package com.congozo.service.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "BENUTZER_FOTO")
public class Benutzerfoto extends BaseEntity {

    private Blob foto;

    private String filename;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Benutzerfoto that = (Benutzerfoto) o;
        return Objects.equals(foto, that.foto) &&
                Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foto, filename);
    }
}
