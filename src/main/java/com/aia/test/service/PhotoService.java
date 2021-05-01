package com.aia.test.service;

import com.aia.test.util.ArrayWithPage;

public interface PhotoService {
    ArrayWithPage callGetPhotoByTag(String tags, String ids, int page);
}
