package com.aia.test.controller;

import com.aia.test.entity.Photo;
import com.aia.test.service.PhotoServiceImpl;
import com.aia.test.util.ArrayWithPage;
import com.aia.test.util.ResponseModel;
import com.aia.test.util.ResponseModelPaginate;
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
    public ResponseEntity<ResponseModelPaginate> search(@RequestParam(required=false) String tags, @RequestParam(required=false) String id, @RequestParam(defaultValue = "1") int page){
        ArrayWithPage result = photoService.callGetPhotoByTag(tags,id,page);
        ResponseModelPaginate response = new ResponseModelPaginate(HttpStatus.OK.value(),"Success",result.getList(),result.getCurrentPage(),result.getTotalPage());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/save")
    public ResponseEntity<ResponseModelPaginate> responseEntitysave(@RequestParam(required=false) String tags, @RequestParam(required=false) String id){
        ArrayList<Photo> photos = photoService.savePhotoFromAPI(tags,id);
        ResponseModelPaginate response = new ResponseModelPaginate(HttpStatus.OK.value(),"Success",photos,1,1);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/delete")
    public ResponseEntity<ResponseModel> responseEntitysave(){
        photoService.deleteAllData();
        ResponseModel response = new ResponseModel(HttpStatus.OK.value(),"Success Delete All Data",new ArrayList<>());
        return  ResponseEntity.ok(response);
    }
}
