package com.example.AptItSolutions.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.AptItSolutions.Entity.NewsEvent;
import com.example.AptItSolutions.Repo.NewsEventRepository;
import com.example.AptItSolutions.service.NewsEventService;

@RestController
@RequestMapping("/api")
public class NewsEventController {

    @Autowired
    private NewsEventRepository newsEventRepository;

    @Autowired
    private NewsEventService newsEventService;

    @Autowired
    public NewsEventController(NewsEventService newsEventService) {
        this.newsEventService = newsEventService;
    }

    @GetMapping("/getallnews")
    public List<NewsEvent> getAllNewsEvents() {
        return newsEventService.getAllNewsEvents();
    }

    @PostMapping("/save")
    public NewsEvent createNewsEvent(
        @RequestParam("date") Date newsDate,
        @RequestParam("newsEvent") String newsText,
        @RequestParam("link") String link,
        @RequestPart("imageFile") MultipartFile imageFile) 
    {

        // Create a new NewsEvent
        NewsEvent newsEvent = new NewsEvent();
        newsEvent.setDate(newsDate);
        newsEvent.setLink(link);
        newsEvent.setNewsEvent(newsText);

        try {
            // Check if an image file was provided
            if (imageFile != null) {
                // Set the image data as bytes
                newsEvent.setImage(imageFile.getBytes());
            }
        } catch (IOException e) {
            // Handle the exception, e.g., log an error
        }

        // Save the NewsEvent with the image data
        return newsEventRepository.save(newsEvent);
    }


    @GetMapping("/get/{id}")
    public NewsEvent getNewsEventById(@PathVariable("id") Long id) {
        return newsEventService.getNewsEventById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NewsEvent> updateNewsEvent(
            @PathVariable("id") Long id,
            @RequestParam("newsEvent") String newsText,
            @RequestParam("link") String link,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {

        try {
            // Get the existing NewsEvent
            NewsEvent newsEvent = newsEventService.getNewsEventById(id);

            if (newsEvent == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Update NewsEvent properties
            newsEvent.setNewsEvent(newsText);
            newsEvent.setLink(link);

            // Check if a new image file is provided
            if (imageFile != null && !imageFile.isEmpty()) {
                newsEvent.setImage(imageFile.getBytes());
            }

            // Save and return the updated NewsEvent
            NewsEvent updatedNewsEvent = newsEventService.saveNewsEvent(id, newsEvent);
            return new ResponseEntity<>(updatedNewsEvent, HttpStatus.OK);

        } catch (IOException e) {
            // Log or handle the exception appropriately
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/deletenews/{id}")
    public void deleteNewsEvent(@PathVariable("id") Long id) {
        NewsEvent newsEvent = newsEventService.getNewsEventById(id);
        if (newsEvent != null) {
            newsEventService.deleteNewsEvent(newsEvent);
        }
    }
}