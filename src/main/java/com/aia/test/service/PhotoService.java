package com.aia.test.service;

import com.aia.test.entity.Photo;
import com.aia.test.util.ArrayWithPage;

import java.util.ArrayList;

public interface PhotoService {
    ArrayWithPage callGetPhotoByTag(String tags, String id, int page);
    ArrayList<Photo> savePhotoFromAPI(String tags, String id);
    void deleteAllData();
}
