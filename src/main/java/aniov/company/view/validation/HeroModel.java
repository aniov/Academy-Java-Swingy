package aniov.company.view.validation;

import aniov.company.model.character.hero.HeroType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Input user model class to be used for validation for Front-End
 */
@Data
@AllArgsConstructor
public class HeroModel {

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{3,20}", message = "Name entered is not valid")
    private String name;

    @NotNull(message = "hero type cannot be null")
    private HeroType type;
}
