package domain;

import lombok.Data;

@Data
public class UserRequest {

    private String password;
    private String email;
}
