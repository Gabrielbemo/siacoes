package br.edu.utfpr.dv.siacoes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    void getName() {
        Department d = new Department();
        d.setName("Ana");
        assertEquals(d.getName(), "Ana");
    }
}