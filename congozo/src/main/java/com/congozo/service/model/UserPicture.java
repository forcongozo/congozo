package com.congozo.service.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "USER_PICTURE")
public class UserPicture extends BaseEntity {

    private Blob picture;

    private String filename;

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

}
