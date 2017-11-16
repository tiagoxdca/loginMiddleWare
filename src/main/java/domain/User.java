package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String email;
    private String passaword;
}
