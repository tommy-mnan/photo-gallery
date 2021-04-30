package com.aia.test.controller;

import com.aia.test.service.PhotoServiceImpl;
import com.aia.test.util.ResponseModelSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class PhotoController {
    @Autowired
    private PhotoServiceImpl photoService;

    @GetMapping("/search")
    public ArrayList search(){
        ArrayList result = photoService.callGetPhotoByTag();
        return result;
    }
}
