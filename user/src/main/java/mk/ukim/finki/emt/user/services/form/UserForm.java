package mk.ukim.finki.emt.user.services.form;

import lombok.Data;
import mk.ukim.finki.emt.user.domain.valueobjects.Address;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserForm {

    @NotBlank
    private String name;

    @Min(0)
    private int embg;

    @NotBlank
    private String phoneNumber;

    @Email
    @NotBlank
    private String email;

    @Valid
    private Address address;

}
