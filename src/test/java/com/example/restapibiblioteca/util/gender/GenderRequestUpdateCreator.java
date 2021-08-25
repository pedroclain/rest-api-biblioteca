package com.example.restapibiblioteca.util.gender;

import com.example.restapibiblioteca.dto.GenderRequestCreate;
import com.example.restapibiblioteca.dto.GenderRequestUpdate;

public class GenderRequestUpdateCreator {

    public static GenderRequestUpdate create() {
        GenderRequestUpdate genderRequestUpdate = new GenderRequestUpdate();
        genderRequestUpdate.setName("gender");
        genderRequestUpdate.setId(1);
        return genderRequestUpdate;
    }
}
