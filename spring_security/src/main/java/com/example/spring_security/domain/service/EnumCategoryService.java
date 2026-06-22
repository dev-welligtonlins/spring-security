package com.example.spring_security.domain.service;

public enum EnumCategoryService {
    BARBA("barba"),
    CABELO("cabelo"),
    SOBRANCELHA("sobrancelha"),
    COMBO("combo"),
    LIMPEZA_FACIAL("limpeza_facial");

    private String category;
    
    EnumCategoryService(String category){
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }
}

