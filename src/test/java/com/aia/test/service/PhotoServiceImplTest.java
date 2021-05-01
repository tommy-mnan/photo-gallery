package com.aia.test.service;

import com.aia.test.entity.Photo;
import com.aia.test.repository.PhotoRepository;
import com.aia.test.util.ArrayWithPage;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PhotoServiceImplTest {
    @Mock
    private PhotoRepository photoRepository;
    private PhotoService undeTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        undeTest = new PhotoServiceImpl(photoRepository);
    }

    @AfterEach()
    void tearDown() throws Exception{
        autoCloseable.close();
    }
    @Test
    void checkCallGetPhotoByTagHandleNull() {
        ArrayWithPage result = undeTest.callGetPhotoByTag(null, null,1);
        assertThat(result.getList().size()).isEqualTo(5);
        assertThat(result.getTotalPage()).isEqualTo(4);
        assertThat(result.getCurrentPage()).isEqualTo(1);
    }

    @Test
    void itShouldReturnEmptyArrayIfPageNotFound() {
        ArrayWithPage result = undeTest.callGetPhotoByTag(null, null,5);
        assertThat(result.getList().size()).isZero();
    }

    @Test
    void checkSaveFromApi() {
        ArrayList<Photo> result = undeTest.savePhotoFromAPI(null, null);
        verify(photoRepository).saveAll(result);
    }

    @Test
    void checkDeleteAll() {
        undeTest.deleteAllData();
        verify(photoRepository).deleteAll();
    }

}