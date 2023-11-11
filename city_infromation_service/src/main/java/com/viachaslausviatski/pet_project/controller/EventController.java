package com.viachaslausviatski.pet_project.controller;

import com.viachaslausviatski.pet_project.entity.Event;
import com.viachaslausviatski.pet_project.entity.MyObject;
import com.viachaslausviatski.pet_project.entity.Request;
import com.viachaslausviatski.pet_project.entity.User;
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

    @GetMapping("/events")
    public String allObjects(@RequestParam(name = "searchWord", required = false) String name, Model model,
                             Principal principal) {
        model.addAttribute("events", eventService.listOfEvents(name));
        model.addAttribute("user", eventService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", name);
        return "events";
    }

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

            Event event = new Event();
            event.setName(eventName);
            event.setType(eventType);
            event.setDescription(eventDescription);
            event.setStartDate(eventStartDate);
            event.setNumberOfVisitors(eventNumberOfVisitors);


            User user = userService.getUserByPrincipal(principal);


            MyObject object = myObjectService.getObjectById(objectId);

            event.setObject(object);

            Event savedEvent = eventService.saveEvent(principal, event, file1, file2, file3);


            Request request = new Request();
            request.setObjectName(requestObjectName);
            request.setStartDate(requestStartDate);
            request.setEventName(requestEventName);
            request.setEventType(requestEventType);
            request.setUserFullName(requestUserFullName);


            request.setEvent(savedEvent);


            request.getObjects().add(object);


            myObjectService.saveRequest(request);

            return "redirect:/";
        } catch (IOException e) {

            e.printStackTrace();
            return "error-page";
        }
    }

    @GetMapping("/my/events")
    public String userObjects(Principal principal, Model model) {
        User user = myObjectService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("events", user.getEvents());
        return "my-events";
    }

    @GetMapping("/event/edit/{eventId}")
    public String editEventForm(@PathVariable Long eventId, Model model, Principal principal) {
        Event event = eventService.getEventById(eventId);
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("event", event);
        model.addAttribute("user", user);
        return "edit-event";
    }

    @PostMapping("/event/update/{eventId}")
    public String updateEvent(@PathVariable Long eventId,
                              @RequestParam String eventName,
                              @RequestParam String eventType,
                              @RequestParam String eventDescription,
                              @RequestParam String eventStartDate,
                              @RequestParam int eventNumberOfVisitors,
                              @RequestParam MultipartFile file1,
                              @RequestParam MultipartFile file2,
                              @RequestParam MultipartFile file3,
                              Principal principal) {
        try {

            Event event = eventService.getEventById(eventId);


            event.setName(eventName);
            event.setType(eventType);
            event.setDescription(eventDescription);
            event.setStartDate(eventStartDate);
            event.setNumberOfVisitors(eventNumberOfVisitors);


            eventService.updateEvent(principal, event, file1, file2, file3);

            return "redirect:/events";
        } catch (IOException e) {

            e.printStackTrace();
            return "error-page";
        }
    }
}