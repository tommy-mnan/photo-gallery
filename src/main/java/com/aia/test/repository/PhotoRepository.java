package com.aia.test.repository;

import com.aia.test.entity.Photo;
import org.springframework.data.repository.CrudRepository;


public interface PhotoRepository extends CrudRepository<Photo, Long> {
    Photo save(Photo newPhoto);
}
