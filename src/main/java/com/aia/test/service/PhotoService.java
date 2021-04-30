package com.aia.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface PhotoService {
    ArrayList callGetPhotoByTag();
}
