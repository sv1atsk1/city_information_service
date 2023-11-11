package com.viachaslausviatski.pet_project.services;

import com.viachaslausviatski.pet_project.entity.*;
import com.viachaslausviatski.pet_project.repositories.EventRepository;
import com.viachaslausviatski.pet_project.repositories.MyObjectRepository;
import com.viachaslausviatski.pet_project.repositories.RequestRepository;
import com.viachaslausviatski.pet_project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {


    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final MyObjectRepository myObjectRepository;

    private final RequestRepository requestRepository;

    private List<Event> events = new ArrayList<>();


    public List<Event> listOfEvents(String name) {
        if (name != null) return eventRepository.findByName(name);
        return eventRepository.findAll();
    }

    public Event saveEvent(Principal principal, Event event, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        event.setOwner(getUserByPrincipal(principal));
        Images image1;
        Images image2;
        Images image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            event.addImageToEvent(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            event.addImageToEvent(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            event.addImageToEvent(image3);
        }
        log.info("Saving new Event. Description: {}; Name: {} ", event.getDescription(), event.getName());
        Event eventFromDB = eventRepository.save(event);
        eventFromDB.setPreviewImageId(eventFromDB.getImages().get(0).getId());
        eventRepository.save(event);
        return eventFromDB; // Return the saved Event
    }
    public User getUserByPrincipal(Principal principal)
    {
        if(principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Images toImageEntity(MultipartFile file) throws IOException {
        Images image = new Images();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteEvent(User user, Long id) {
        Event event = eventRepository.findById(id)
                .orElse(null);
        if (event != null) {
            if (event.getOwner().getId().equals(user.getId())) {
                eventRepository.delete(event);
                log.info("Event with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this event with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Event with id = {} is not found", id);
        }    }

    public Event getEventById(Long id){

        return eventRepository.findById(id).orElse(null);
    }
    public class ObjectNotFoundException extends RuntimeException {
        public ObjectNotFoundException(String message) {
            super(message);
        }
    }


    public Event getObjectById(Long eventId) throws ObjectNotFoundException {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            return optionalEvent.get();
        } else {
            throw new ObjectNotFoundException("Event not found with ID: " + eventId);
        }
    }
}
