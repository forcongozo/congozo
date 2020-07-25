package com.congozo.service.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "ADVENTURE_PICTURE")
public class AdventurePicture extends BaseEntity {

    private Blob picture;

    private String filename;

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AdventurePicture that = (AdventurePicture) o;
        return Objects.equals(picture, that.picture) &&
                Objects.equals(filename, that.filename) &&
                Objects.equals(adventure, that.adventure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), picture, filename, adventure);
    }
}
