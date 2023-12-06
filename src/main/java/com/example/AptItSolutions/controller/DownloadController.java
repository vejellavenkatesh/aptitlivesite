package com.example.AptItSolutions.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.AptItSolutions.Entity.Download;
import com.example.AptItSolutions.Repo.DownloadRepo;
import com.example.AptItSolutions.ServiceImpl.DownloadServiceImpl;

import jakarta.servlet.http.HttpServletResponse;


@RequestMapping("/api")
@RestController

public class DownloadController {

 @Autowired
 private DownloadServiceImpl downloadService;

 @Autowired
 private DownloadRepo downloadRepo;

 @GetMapping("/total-count")
 public Integer getTotalCount() {
     return downloadService.calculateTotalCount();
 }

 @PostMapping("/incrementDownloadCount/{id}")
 public ResponseEntity<String> incrementDownloadCount(@PathVariable("id") int id) throws NotFoundException {
     try {
         downloadService.incrementDownloadCount(id);
         return ResponseEntity.ok("Download count incremented successfully.");
     } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body("Error incrementing download count: " + e.getMessage());
     }
 }

 @PostMapping("/upload")
 public Download uploadFile(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("file") MultipartFile file) throws IOException {
     Download download = new Download();
     download.setTitle(title);
     download.setDescription(description);
     download.setFile(file.getBytes());
     download.setCount(0);

     String fileType = getFileExtension(file.getOriginalFilename());
     download.setFileType(fileType);

     return downloadService.saveDownload(download);
 }

 private String getFileExtension(String filename) {
     if (filename == null || filename.isEmpty() || !filename.contains(".")) {
         return "";
     }

     return filename.substring(filename.lastIndexOf(".")).toLowerCase();
 }

 @GetMapping("/getall")
 public List<Download> getAllDownloads() {
     return downloadService.getAllDownloads();
 }



 @PutMapping("/updatedownload/{id}")
 public Download updateFile(@PathVariable int id,
                            @RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
     Download download = downloadService.getDownloadById(id);

     if (download == null) {
         // Handle the case when the download is not found
         // You can throw an exception or return an appropriate response
         // For simplicity, it's left as a comment here
         // throw new DownloadNotFoundException("Download not found with id: " + id);
     }

     download.setTitle(title);
     download.setDescription(description);
     if (file != null) {
         download.setFile(file.getBytes());
     }

     return downloadService.updateDownload(id, download);
 }



 @DeleteMapping("/deletebydownload/{id}")
 public void deleteDownload(@PathVariable int id) {
     downloadService.deleteDownloadNews(id);
 }
 
 @GetMapping("/download/{id}")
 public Download getDownloadById(@PathVariable int id) {
     return downloadService.getDownloadById(id);
 }

 @GetMapping("/downloadthefile/{id}")
 public void downloadFile(@PathVariable long id, HttpServletResponse response) {
     try {
         byte[] fileBytes = downloadService.getDownloadById(id);

         if (fileBytes != null) {
             // Increment download count in the database
             downloadService.incrementDownloadCount((int) id);

             // Set response headers
             response.setContentType(MediaType.APPLICATION_PDF_VALUE);
             response.setHeader("Content-Disposition", "inline; filename=" + id + ".pdf");

             // Write file to response
             response.getOutputStream().write(fileBytes);
         } else {
             // Handle the case where the Download with the specified id is not found
             response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
         }
     } catch (IOException e) {
         // Handle IOException
         response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
     }
 }
}