package com.example.AptItSolutions.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.AptItSolutions.Entity.OurClients;
import com.example.AptItSolutions.service.OurClientsService;

import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/api/clients")
public class OurClientsController {

    @Autowired
    private OurClientsService clientService;

    @GetMapping("/getallclients")
    public List<OurClients> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OurClients> getClientById(@PathVariable long id) {
        OurClients client = clientService.getClientById(id);
        return client != null
                ? ResponseEntity.ok(client)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/logo")
    public ResponseEntity<OurClients> saveClientLogo(
            @RequestParam("clientLogo") MultipartFile clientLogo
    ) {
        try {
            byte[] logoData = clientLogo.getBytes();

            // Compress the image if its size exceeds 500KB
            if (logoData.length > 500 * 1024) {
                BufferedImage originalImage = Thumbnails.of(clientLogo.getInputStream()).scale(1.0).asBufferedImage();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                String outputFormat = getImageOutputFormat(clientLogo.getContentType());
                Thumbnails.of(originalImage).size(250, 250).outputFormat(outputFormat).outputQuality(1.0)
                        .toOutputStream(outputStream);
                logoData = outputStream.toByteArray();

                // If the compressed image is still larger than 500KB, reduce its quality
                while (logoData.length > 500 * 1024) {
                    Thumbnails.of(new ByteArrayInputStream(logoData)).scale(0.9).outputQuality(0.9)
                            .toOutputStream(outputStream);
                    logoData = outputStream.toByteArray();
                    outputStream.reset();
                }
            }

            OurClients client = new OurClients();
            client.setClientLogo(logoData);

            OurClients savedClient = clientService.saveClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String getImageOutputFormat(String contentType) {
        if (contentType != null && !contentType.isEmpty()) {
            if (contentType.contains("jpeg") || contentType.contains("jpg")) {
                return "jpg";
            } else if (contentType.contains("png")) {
                return "png";
            }
        }
        return "jpg"; // Default to JPG format if the content type is not available or unrecognized
    }



    @PutMapping("/{id}")
    public ResponseEntity<OurClients> updateClient(@PathVariable long id, @RequestBody OurClients updatedClient) {
        OurClients result = clientService.updateClient(id, updatedClient);
        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
