package com.viachaslausviatski.pet_project.controller;

import com.viachaslausviatski.pet_project.entity.Event;
import com.viachaslausviatski.pet_project.entity.MyObject;
import com.viachaslausviatski.pet_project.entity.Request;
import com.viachaslausviatski.pet_project.entity.User;
import com.viachaslausviatski.pet_project.repositories.MyObjectRepository;
import com.viachaslausviatski.pet_project.services.EventService;
import com.viachaslausviatski.pet_project.services.MyObjectService;
import com.viachaslausviatski.pet_project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final UserService userService;

    private final MyObjectService myObjectService;

    @GetMapping("/my/events/request")
    public String requestEventForm(@RequestParam Long objectId, Model model,Principal principal) {
        MyObject object = myObjectService.getObjectById(objectId);
        User user = userService.getUserByPrincipal(principal); // Assuming you have a UserService to get the user
        model.addAttribute("object", object);
        model.addAttribute("user", user);
        return "request-event-form";
    }

    @PostMapping("/my/events/request")
    public String submitEventRequest(@RequestParam Long objectId,
                                     @RequestParam String eventName,
                                     @RequestParam String eventType,
                                     @RequestParam String eventDescription,
                                     @RequestParam String eventStartDate,
                                     @RequestParam int eventNumberOfVisitors,
                                     @RequestParam String requestObjectName,
                                     @RequestParam String requestStartDate,
                                     @RequestParam String requestEventName,
                                     @RequestParam String requestEventType,
                                     @RequestParam String requestUserFullName,
                                     @RequestParam MultipartFile file1,
                                     @RequestParam MultipartFile file2,
                                     @RequestParam MultipartFile file3,
                                     Principal principal) {
        try {
            // Create an Event object
            Event event = new Event();
            event.setName(eventName);
            event.setType(eventType);
            event.setDescription(eventDescription);
            event.setStartDate(eventStartDate);
            event.setNumberOfVisitors(eventNumberOfVisitors);

            // Get the authenticated user
            User user = userService.getUserByPrincipal(principal);

            // Get the object by ID
            MyObject object = myObjectService.getObjectById(objectId);

            event.setObject(object);
            // Save the event and get the saved event with images
            Event savedEvent = eventService.saveEvent(principal, event, file1, file2, file3);

            // Create a Request object
            Request request = new Request();
            request.setObjectName(requestObjectName);
            request.setStartDate(requestStartDate);
            request.setEventName(requestEventName);
            request.setEventType(requestEventType);
            request.setUserFullName(requestUserFullName);

            // Associate the request with the event using the savedEvent
            request.setEvent(savedEvent);

            // Associate the request with the object
            request.getObjects().add(object);

            // Save the request
            myObjectService.saveRequest(request);

            return "redirect:/";
        } catch (IOException e) {
            // Handle file-related exceptions here
            e.printStackTrace(); // Add proper error handling
            return "error-page"; // Redirect to an error page
        }
    }
}