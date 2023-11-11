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
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyObjectService {

    private final MyObjectRepository myObjectRepository;

    private final UserRepository userRepository;

    private final RequestRepository requestRepository;

    private final EventService eventService;
    private List<MyObject> objects = new ArrayList<>();


    public List<MyObject> listOfObjects(String name) {
        if (name != null) return myObjectRepository.findByName(name);
        return myObjectRepository.findAll();
    }

    public void saveObject(Principal principal, MyObject myObject, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        myObject.setOwner(getUserByPrincipal(principal));
        Images image1;
        Images image2;
        Images image3;
        if(file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            myObject.addImageToObject(image1);
        }
        if(file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            myObject.addImageToObject(image2);
        }
        if(file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            myObject.addImageToObject(image3);
        }
        log.info("Saving new Object. Name: {}; Type: {} ",myObject.getName(),myObject.getType());
        MyObject objectFromDB = myObjectRepository.save(myObject);
        objectFromDB.setPreviewImageId(objectFromDB.getImages().get(0).getId());
        myObjectRepository.save(myObject);
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

    public void deleteObject(User user, Long id) {
        MyObject object = myObjectRepository.findById(id)
                .orElse(null);
        if (object != null) {
            if (object.getOwner().getId().equals(user.getId())) {
                myObjectRepository.delete(object);
                log.info("Event with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this event with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Event with id = {} is not found", id);
        }
    }

    public MyObject getObjectById(Long id){

        return myObjectRepository.findById(id).orElse(null);
    }

    public void updateObject(Principal principal, MyObject updatedObject,
                             MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {

        User user = getUserByPrincipal(principal);


        MyObject existingObject = getObjectById(updatedObject.getId());
        if (existingObject.getOwner().getId().equals(user.getId())) {

            existingObject.setName(updatedObject.getName());
            existingObject.setAddress(updatedObject.getAddress());
            existingObject.setOpeningDate(updatedObject.getOpeningDate());
            existingObject.setWorkStatus(updatedObject.getWorkStatus());
            existingObject.setPopularity(updatedObject.getPopularity());
            existingObject.setType(updatedObject.getType());
            existingObject.setNumberOfSeats(updatedObject.getNumberOfSeats());




            saveObject(principal, existingObject, file1, file2, file3);
        } else {

            throw new AccessDeniedException("You do not have permission to update this object.");
        }
    }

    public void saveRequest(Request request) {
        requestRepository.save(request);
    }
}
