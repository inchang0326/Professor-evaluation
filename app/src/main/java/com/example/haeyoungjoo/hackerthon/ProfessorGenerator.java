package com.example.haeyoungjoo.hackerthon;

/**
 * Created by Me on 2017-04-22.
 */

public abstract class ProfessorGenerator{
    protected abstract Professor selectProfessor(ProfessorData name);

    public final Professor professorGenerate(ProfessorData name){
        Professor professor = selectProfessor(name);
        return professor;
    }
}