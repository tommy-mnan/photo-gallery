package com.aia.test.service;

import com.aia.test.entity.Photo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements  PhotoService{
    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoService.class);
    private static final String API_URL = "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1";

    @Override
    public ArrayList<Photo> callGetPhotoByTag() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> result = restTemplate.exchange(API_URL, HttpMethod.GET, entity, JsonNode.class);

        return convertToEntity(result.getBody().get("items"));
    }

    private ArrayList<Photo> convertToEntity(JsonNode data){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            photos = objectMapper.convertValue(data, new TypeReference<ArrayList<Photo>>(){});
            return photos;
        }catch (Exception e){
            LOGGER.debug(e.getMessage());
            return photos;
        }
    }
}
