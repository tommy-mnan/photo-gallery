package com.aia.test.controller;

import com.aia.test.service.PhotoServiceImpl;
import com.aia.test.util.ResponseModelSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity search(){
        ArrayList result = photoService.callGetPhotoByTag();
        ResponseModelSuccess response = new ResponseModelSuccess(HttpStatus.OK.value(),"Success",result);
        return ResponseEntity.ok(response);
    }
}
