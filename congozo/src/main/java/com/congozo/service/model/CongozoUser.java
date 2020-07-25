package com.congozo.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table( name = "CONGOZO_USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class CongozoUser extends BaseEntity {

    @Size(max =50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    private String gender;

    @Size(max = 50)
    private String username;

    private Date dateOfBirth;

    private String language;

    private String city;
    private String country;
    private String hashtag;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 20)
    private String mobileNumber;

    @NotBlank
    @Size(max = 120)
    private String password;

    @OneToOne
    @JoinColumn(name = "USER_PICTURE_ID")
    private UserPicture userPicture;
    private String specialty;
    @Size(max = 2000)
    private String info;
    private boolean isAccountActive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "CONGOZO_USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<CongozoRole> congozoRoles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CongozoUser")
    @JsonIgnore
    private List<AccountActivationCode> aktivierungsCodeList;

    public CongozoUser(){

    }
    public CongozoUser(String firstName, String lastName, String gender, String username,
                       Date dateOfBirth, String email, String mobileNumber, String password,
                       String info, String city, String country, String hashtag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.info = info;
        this.city = city;
        this.country = country;
        this.hashtag = hashtag;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String vorname) {
        this.firstName = vorname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String nachname) {
        this.lastName = nachname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date geburtsdatum) {
        this.dateOfBirth = geburtsdatum;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String sprachen) {
        this.language = sprachen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPicture getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(UserPicture userPicture) {
        this.userPicture = userPicture;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String fachgebiet) {
        this.specialty = fachgebiet;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<CongozoRole> getCongozoRoles() {
        return congozoRoles;
    }

    public void setCongozoRoles(Set<CongozoRole> congozoRoles) {
        this.congozoRoles = congozoRoles;
    }

    public boolean isAccountActive() {
        return isAccountActive;
    }

    public void setAccountActive(boolean accountActive) {
        isAccountActive = accountActive;
    }

    public List<AccountActivationCode> getAktivierungsCodeList() {
        return aktivierungsCodeList;
    }

    public void setAktivierungsCodeList(List<AccountActivationCode> aktivierungsCodeList) {
        this.aktivierungsCodeList = aktivierungsCodeList;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String geschlecht) {
        this.gender = geschlecht;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String stadt) {
        this.city = stadt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String land) {
        this.country = land;
    }
}
