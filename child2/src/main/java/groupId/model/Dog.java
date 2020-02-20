package groupId.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
    @NotBlank
    String name;
    @Positive
    @NonNull
    Double height;
    @PastOrPresent
    LocalDate dateOfBirth;
    Integer age;
    Double weight;

    @Positive
    Integer id;

    Boolean deleted = false;
}
