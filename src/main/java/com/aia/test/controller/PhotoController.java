package com.aia.test.controller;

import com.aia.test.entity.Photo;
import com.aia.test.service.PhotoServiceImpl;
import com.aia.test.util.ArrayWithPage;
import com.aia.test.util.ResponseModelSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class PhotoController {
    @Autowired
    private PhotoServiceImpl photoService;

    @GetMapping("/search")
    public ResponseEntity<ResponseModelSuccess> search(@RequestParam(required=false) String tags, @RequestParam(required=false) String id, @RequestParam(defaultValue = "1") int page){
        ArrayWithPage result = photoService.callGetPhotoByTag(tags,id,page);
        ResponseModelSuccess response = new ResponseModelSuccess(HttpStatus.OK.value(),"Success",result.getList(),result.getCurrentPage(),result.getTotalPage());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/save")
    public ResponseEntity<ResponseModelSuccess> responseEntitysave(@RequestParam(required=false) String tags, @RequestParam(required=false) String id){
        ArrayList<Photo> photos = photoService.savePhotoFromAPI(tags,id);
        ResponseModelSuccess response = new ResponseModelSuccess(HttpStatus.OK.value(),"Success",photos,1,1);
        return  ResponseEntity.ok(response);
    }
}
