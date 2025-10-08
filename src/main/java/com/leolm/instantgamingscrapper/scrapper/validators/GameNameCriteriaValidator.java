package com.leolm.instantgamingscrapper.scrapper.validators;

import java.util.ArrayList;
import java.util.List;

public class GameNameCriteriaValidator implements CriteriaValidator {
    List<String> criterias = new ArrayList<>();

    public GameNameCriteriaValidator(List<String> criterias) {
        criterias.forEach(item -> this.criterias.add(item));
    }

    public boolean matches(String nomeJogo) {
        for (String criteria : criterias) {
            if (nomeJogo.contains(criteria)) {
                return true;
            }
        }
        return false;
    }

}
