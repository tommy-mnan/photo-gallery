package com.aia.test.service;

import com.aia.test.entity.Photo;
import com.aia.test.repository.PhotoRepository;
import com.aia.test.util.ArrayWithPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@Transactional
public class PhotoServiceImpl implements  PhotoService{
    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoServiceImpl.class);
    private static final String API_URL = "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1";
    private static final int PERPAGE = 5;

    @Autowired
    PhotoRepository photoRepository;
    @Override
    public ArrayWithPage callGetPhotoByTag(String tags, String id, int page) {
        ResponseEntity<JsonNode> result = getDataFromApi(tags,id);
        return convertToEntity(result,page);
    }

    @Override
    public ArrayList<Photo> savePhotoFromAPI(String tags, String id) {
        ResponseEntity<JsonNode> result = getDataFromApi(tags,id);
        return (ArrayList<Photo>) photoRepository.saveAll(converToList(result));
    }

    @Override
    public void deleteAllData() {
        photoRepository.deleteAll();
    }

    private ResponseEntity<JsonNode> getDataFromApi(String tags, String id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = validateParam(tags, id);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, JsonNode.class);
    }

    private UriComponentsBuilder validateParam(String tags, String id){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_URL);
        if(StringUtils.hasText(tags)) {
            builder.queryParam("tags", tags);
        }
        if (StringUtils.hasText(id)) {
            builder.queryParam("id", id);
        }
        return builder;
    }

    private ArrayWithPage convertToEntity(ResponseEntity<JsonNode> result, int page){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            JsonNode data = result.getBody();
            if(data != null) {
                data = data.get("items");
                photos = objectMapper.convertValue(data, new TypeReference<ArrayList<Photo>>() {
                });
            }
            int totalPage = (int) Math.ceil((double)photos.size()/PERPAGE);

            if(page == totalPage){
                photos =new ArrayList<> (photos.subList(((page-1)*PERPAGE), (photos.size())));
            }else{
                photos =new ArrayList<> (photos.subList(((page-1)*PERPAGE), (page*PERPAGE)));
            }
            return new ArrayWithPage(totalPage,page,photos);
        }catch (Exception e){
            LOGGER.debug(e.getMessage());
            int totalPage = (int) Math.ceil((double)photos.size()/PERPAGE);
            return new ArrayWithPage(totalPage,page,new ArrayList<Photo>());
        }
    }

    private ArrayList<Photo> converToList(ResponseEntity<JsonNode> result){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            JsonNode data = result.getBody();
            if(data != null) {
                data = data.get("items");
                photos = objectMapper.convertValue(data, new TypeReference<ArrayList<Photo>>() {
                });
            }
            return photos;
        }catch (Exception e){
            LOGGER.debug(e.getMessage());
            return photos;
        }
    }
}
