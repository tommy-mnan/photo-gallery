package com.aia.test.service;

import com.aia.test.entity.Photo;
import com.aia.test.util.ArrayWithPage;

import java.util.ArrayList;

public interface PhotoService {
    ArrayWithPage callGetPhotoByTag(String tags, String ids, int page);
    ArrayList<Photo> savePhotoFromAPI(String tags, String ids);
}
