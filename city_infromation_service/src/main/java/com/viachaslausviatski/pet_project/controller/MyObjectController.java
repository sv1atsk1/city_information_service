package com.viachaslausviatski.pet_project.controller;

import com.viachaslausviatski.pet_project.entity.Event;
import com.viachaslausviatski.pet_project.entity.MyObject;
import com.viachaslausviatski.pet_project.entity.User;
import com.viachaslausviatski.pet_project.repositories.MyObjectRepository;
import com.viachaslausviatski.pet_project.services.MyObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class MyObjectController {

    private final MyObjectService myObjectService;

    @GetMapping("/")
    public String allObjects(@RequestParam(name = "searchWord", required = false) String name, Model model,
                            Principal principal) {
        model.addAttribute("objects", myObjectService.listOfObjects(name));
        model.addAttribute("user", myObjectService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", name);
        return "objects";
    }

    @PostMapping("/object/create")
    public String createEvent(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                              @RequestParam("number_of_seats") int numberOfSeats,
                              @RequestParam("file3") MultipartFile file3, MyObject object,
                              Principal principal) throws IOException {
        object.setNumberOfSeats(numberOfSeats);
        myObjectService.saveObject(principal, object, file1, file2, file3);
        return "redirect:/";
    }

    @GetMapping("/object/{id}")
    public String eventInfo(@PathVariable Long id, Model model, Principal principal) {
        MyObject myObject = myObjectService.getObjectById(id);
        model.addAttribute("user", myObjectService.getUserByPrincipal(principal));
        model.addAttribute("object", myObject);
        model.addAttribute("images", myObject.getImages());
        model.addAttribute("eventOwner", myObject.getOwner());
        return "event-info";
    }

    @PostMapping("/object/delete/{id}")
    public String deleteEvent(@PathVariable Long id, Principal principal) {
        myObjectService.deleteObject(myObjectService.getUserByPrincipal(principal), id);
        return "redirect:/my/objects";
    }

    @GetMapping("/static/css/style.css")
    public String css() {
        return"redirect:/";
    }

    @GetMapping("/my/objects")
    public String userEvents(Principal principal, Model model) {
        User user = myObjectService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("objects", user.getObjects());
        return "my-objects";
    }


}
