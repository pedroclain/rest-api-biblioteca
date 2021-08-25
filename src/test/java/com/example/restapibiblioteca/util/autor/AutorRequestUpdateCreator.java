package com.example.restapibiblioteca.util.autor;

import com.example.restapibiblioteca.dto.AutorRequestUpdate;

public class AutorRequestUpdateCreator {

    public static AutorRequestUpdate create() {
        AutorRequestUpdate autorRequestUpdate = new AutorRequestUpdate();
        autorRequestUpdate.setName("autor");
        autorRequestUpdate.setId(1);
        return autorRequestUpdate;
    }
}
