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

import com.example.AptItSolutions.Entity.InternshipForm;
import com.example.AptItSolutions.Entity.RecieverEmails;
import com.example.AptItSolutions.service.InternshipService;
import com.example.AptItSolutions.service.RecieverEmailsService;

@RestController
@RequestMapping("/internships")
public class InternshipController {

    @Autowired
    private InternshipService internshipService;
    
	@Autowired
    private EmailService emailService;
	
	
	@Autowired
	private RecieverEmailsService recieverEmailsService;
	
	
	// ...

	@PostMapping("/inter")
	public ResponseEntity<?> saveInternshipForm(
	        @RequestParam String name,
	        @RequestParam String email,
	        @RequestParam String mobile,
	        @RequestParam String collegename,
	        @RequestParam String collegeid,
	        @RequestParam String collegLocation, // Fix the parameter name here
	        @RequestParam String choosedomain,
	        @RequestParam String timeduration,
	        @RequestParam String anyquiries,
	        @RequestParam MultipartFile certificates,
	        @RequestParam MultipartFile anyattachments) {

	    try {
	        // Create a new InternshipForm object and set its properties
	        InternshipForm internshipForm = new InternshipForm();
	        internshipForm.setName(name);
	        internshipForm.setEmail(email);
	        internshipForm.setMobile(mobile);
	        internshipForm.setCollegename(collegename);
	        internshipForm.setCollegeid(collegeid);
	        internshipForm.setCollegeLocation(collegLocation); // Corrected parameter name
	        internshipForm.setChoosedomain(choosedomain);
	        internshipForm.setTimeduration(timeduration);
	        internshipForm.setAnyquiries(anyquiries);

	        // Convert and set the byte arrays from MultipartFile
	        try {
	            internshipForm.setCertificates(certificates.getBytes());
	            internshipForm.setAnyattachments(anyattachments.getBytes());
	        } catch (IOException e) {
	            // Handle exception as needed
	            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        // Save the InternshipForm using the service
	        InternshipForm savedInternshipForm = internshipService.save(internshipForm);

	        // Send an email with additional information
	        List<RecieverEmails> mails = recieverEmailsService.getAllRecieverEmails();

	        if (!mails.isEmpty()) {
	            String emailContent = String.format(
	                    "Name: %s%nEmail: %s%nMobile: %s%nCollegename:  %s%nDomain: %s%nColleeLocation: %s%nTime Duration: %s%n",
	                    name, email, mobile, collegename, collegLocation, choosedomain, timeduration);

	            emailService.sendEmail(mails.get(0).getEmail(), email, emailContent);
	        } else {
	            String defaultEmail = "kannekantimahesh143@gmail.com";
	            String emailContent = String.format(
	                    "Name: %s%nEmail: %s%nMobile: %s%nCollegename:   %s%nDomain:%s%nColleeLocation: %s%nTime Duration: %s%n",
	                    name, email, mobile, collegename, collegLocation, choosedomain, timeduration);

	            emailService.sendEmail(defaultEmail, email, emailContent);
	        }

	        return new ResponseEntity<>(savedInternshipForm, HttpStatus.CREATED);
	    } catch (Exception e) {
	        // Log the error or return a specific error response
	        return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}





    @GetMapping("/none")
    public List<InternshipForm> getAll() {
        return internshipService.getAll();
    }

    @GetMapping("/{id}")
    public InternshipForm getById(@PathVariable long id) {
        return internshipService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        internshipService.deleteById(id);
    }

    // You can add more methods and customize the mappings as needed
}
