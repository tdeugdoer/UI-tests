package formData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationFormData {
    private String username;
    private String password;

}
