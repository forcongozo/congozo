package com.congozo.service.view;

import com.congozo.service.model.CongozoUser;
import org.springframework.stereotype.Component;

@Component
public class Profile {
    private CongozoUser CongozoUser;

    public CongozoUser getCongozoUser() {
        return CongozoUser;
    }

    public void setCongozoUser(CongozoUser congozoUser) {
        this.CongozoUser = congozoUser;
    }
}
