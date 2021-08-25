package com.example.restapibiblioteca.util.autor;

import com.example.restapibiblioteca.dto.AutorRequestCreate;

public class AutorRequestCreateCreator {

    public static AutorRequestCreate create() {
        AutorRequestCreate autorRequestCreate = new AutorRequestCreate();
        autorRequestCreate.setName("autor");
        return autorRequestCreate;
    }
}
