package com.viachaslausviatski.pet_project.controller;

import com.viachaslausviatski.pet_project.entity.Event;
import com.viachaslausviatski.pet_project.entity.User;
import com.viachaslausviatski.pet_project.services.EventService;
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

    }