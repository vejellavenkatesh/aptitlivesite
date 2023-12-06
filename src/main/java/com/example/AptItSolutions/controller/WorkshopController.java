package com.example.AptItSolutions.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.AptItSolutions.Entity.RecieverEmails;
import com.example.AptItSolutions.Entity.WorkshopReg;
import com.example.AptItSolutions.service.InternshipService;
import com.example.AptItSolutions.service.RecieverEmailsService;
import com.example.AptItSolutions.service.WorkshopService;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {

	    @Autowired
         private WorkshopService workshopService;
	
	    @Autowired
	    private RecieverEmailsService recieverEmailsService;
	    
		@Autowired
	    private EmailService emailService;
	
	
	

    @Autowired
    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @PostMapping("/home")
    public ResponseEntity<?> saveWorkshopReg(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String mobile,
            @RequestParam String collegename,
            @RequestParam String collegeid,
            @RequestParam String collegLocation,
            @RequestParam String choosedomain,
            @RequestParam String timeduration,
            @RequestParam String anyquiries,
            @RequestParam MultipartFile certificates,
            @RequestParam MultipartFile anyattachments) {

        try {
            // Create a WorkshopReg object and set its properties
            WorkshopReg form = new WorkshopReg();
            form.setName(name);
            form.setEmail(email);
            form.setMobile(mobile);
            form.setCollegename(collegename);
            form.setCollegeid(collegeid);
            form.setCollegLocation(collegLocation);
            form.setChoosedomain(choosedomain);
            form.setTimeduration(timeduration);
            form.setAnyquiries(anyquiries);

            // Convert and set the byte arrays from MultipartFile
            try {
                form.setCertificates(certificates.getBytes());
                form.setAnyattachments(anyattachments.getBytes());
            } catch (IOException e) {
                // Handle exception as needed
                return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Save the WorkshopReg using the service
            WorkshopReg savedWorkshopReg = workshopService.save(form);

            // Send an email with additional information
            List<RecieverEmails> mails = recieverEmailsService.getAllRecieverEmails();

            if (!mails.isEmpty()) {
                String emailContent = String.format(
                        "Name: %s%nEmail: %s%nMobile: %s%nCollegename: %s%nDomain:%s%ncollegLocation: %s%nTime Duration: %s%n",
                        name, email, mobile, collegename, choosedomain, collegLocation, timeduration);

                emailService.sendEmail(mails.get(0).getEmail(), email, emailContent);
            } else {
                String defaultEmail = "kannekantimahesh143@gmail.com";
                String emailContent = String.format(
                        "Name: %s%nEmail: %s%nMobile: %s%nCollegename: %s%nDomain: %s%ncollegLocation:%s%nTime Duration: %s%n",
                        name, email, mobile, collegename, choosedomain, collegLocation,timeduration);

                emailService.sendEmail(defaultEmail, email, emailContent);
            }

            return new ResponseEntity<>(savedWorkshopReg, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error or return a specific error response
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getworks")
    public List<WorkshopReg> getAll() {
        return workshopService.getAll();
    }


    @GetMapping("/{id}")
    public WorkshopReg getById(@PathVariable long id) {
        return workshopService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
    	workshopService.deleteById(id);
    }

    // You can add more methods and customize the mappings as needed
}
