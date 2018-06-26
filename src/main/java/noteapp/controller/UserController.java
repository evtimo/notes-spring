package noteapp.controller;

import noteapp.model.Note;
import noteapp.model.User;
import noteapp.service.NoteService;
import noteapp.service.SecurityService;
import noteapp.service.UserService;
import noteapp.validator.NoteValidator;
import noteapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final NoteService noteService;
    private final UserValidator userValidator;
    private final NoteValidator noteValidator;


    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator, NoteService noteService, NoteValidator noteValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.noteService = noteService;
        this.noteValidator = noteValidator;
    }

    @GetMapping("/")
    public String showUserNotes(Model model) {

        String username = securityService.findLoggedInUsername();

        User user = userService.findByUsername(username);

        List<Note> notes = user.getNotes();

        model.addAttribute("notes", notes);
        return "notes-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        model.addAttribute("note", new Note());

        return "note-form";
    }

    @PostMapping("/saveNote")
    public String saveNote(@ModelAttribute("note") Note note, BindingResult bindingResult) {

        noteValidator.validate(note, bindingResult);

        if (bindingResult.hasErrors())
            return "note-form";

        String username = securityService.findLoggedInUsername();

        User user = userService.findByUsername(username);

        user.addNote(note);

        noteService.saveNote(note);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForAddUpdate(@RequestParam("id") Long id, Model model){

        Note note = noteService.getNoteById(id);

        model.addAttribute("note", note);

        return "note-form";
    }

    @PostMapping("/updateNote")
    public String updateNote(@ModelAttribute("note") Note note, BindingResult bindingResult) {

        noteValidator.validate(note, bindingResult);

        if (bindingResult.hasErrors())
            return "note-form";

        noteService.saveNote(note);

        return "redirect:/";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam("id") Long id) {

        noteService.deleteNoteById(id);

        return "redirect:/";
    }


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Username or password is incorrect." + error);

        if (logout != null)
            model.addAttribute("message", "Logged out successfully.");

        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "registration";

        userService.register(user);

        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());

        return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
