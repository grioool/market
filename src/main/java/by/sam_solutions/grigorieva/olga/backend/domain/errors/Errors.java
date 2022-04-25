package by.sam_solutions.grigorieva.olga.backend.domain.errors;

import java.util.ArrayList;

public class Errors extends ArrayList<String> {

    public boolean hasErrors() {
        return !isEmpty();
    }
}
