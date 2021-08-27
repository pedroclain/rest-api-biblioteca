package com.example.restapibiblioteca.util.gender;

import com.example.restapibiblioteca.domain.Book;
import com.example.restapibiblioteca.domain.Gender;

import java.util.Collection;
import java.util.List;

public class GenderCreator {

    public static Gender newGender() {
        return new Gender("gender");
    }

    public static Gender createdGender() {
        Gender gender = new Gender("gender");
        gender.setId(1L);
        return gender;
    }

    public static List<Gender> genderList() {
        Gender gender1 = new Gender("gender");
        gender1.setId(1L);
        Gender gender2 = new Gender("gender 2");
        gender2.setId(2L);
        return List.of(gender1, gender2);
    }

    public static Gender toUpdateGender() {
        Gender gender = new Gender("gender");
        gender.setId(1L);
        return gender;
    }
}
