package com.file.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    private static String UPLOADED_PATH = "E:\\Java Spring-Boot\\storage\\";
    
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes){

        if(multipartFile.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:status";
        }

        try {
            
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(UPLOADED_PATH + multipartFile.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "Success Upload File "+ multipartFile.getOriginalFilename());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:status";
    }

    @GetMapping("/status")
    public String uploadStatus(){
        return "status";
    }
}
