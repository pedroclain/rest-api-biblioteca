package com.example.restapibiblioteca.util.gender;

import com.example.restapibiblioteca.dto.GenderRequestCreate;
import com.example.restapibiblioteca.dto.GenderView;

public class GenderRequestCreateCreator {

    public static GenderRequestCreate create() {
        GenderRequestCreate genderRequestCreate = new GenderRequestCreate();
        genderRequestCreate.setName("gender");
        return genderRequestCreate;
    }
}
