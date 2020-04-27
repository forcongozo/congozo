package com.congozo.service.test;

import com.congozo.service.model.TestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

    @GetMapping(path = "/")
    public TestModel testRest(){

        return new TestModel("Test", 30, "123456789");
    }
}
