package noteapp.validator;

import noteapp.model.User;
import noteapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator{

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        Pattern pattern = Pattern.compile("^\\w*$");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if(!errors.hasFieldErrors("username")){

            String username = user.getUsername();

            if (username.length() < 4 || username.length() > 16)
                errors.rejectValue("username", "Size.userForm.username");
            else if (!pattern.matcher(username).matches())
                errors.rejectValue("username", "Letters.userForm.username");
            else if (userService.findByUsername(username) != null)
                errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if(!errors.hasFieldErrors("password")) {

            String userPassword = user.getPassword();

            if (userPassword.length() < 8 || userPassword.length() > 32)
                errors.rejectValue("password", "Size.userForm.password");
            if (!user.getConfirmPassword().equals(userPassword))
                errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

    }
}
