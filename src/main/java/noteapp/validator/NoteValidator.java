package noteapp.validator;

import noteapp.model.Note;
import noteapp.model.User;
import noteapp.service.SecurityService;
import noteapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class NoteValidator implements Validator {

    private final SecurityService securityService;
    private final UserService userService;

    @Autowired
    public NoteValidator(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Note.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Note note = (Note) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Required");

        if(!errors.hasFieldErrors("title")){

            String username = securityService.findLoggedInUsername();

            User user = userService.findByUsername(username);

            List<Note> notes = user.getNotes();

            for (Note tempNote : notes) {
                if(tempNote.getTitle().equals(note.getTitle())){
                    if(note.getId() != null && note.getId().equals(tempNote.getId())) continue;
                    errors.rejectValue("title", "Duplicate.note.title");
                }

            }

        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "Required");

    }
}
