package com.example.voziloservice.Service;


import com.example.voziloservice.model.ImageModel;
import org.springframework.stereotype.Service;

import java.util.Collection;


public interface ImageModelService {
    public ImageModel findByCarId(String id);
    public Collection<ImageModel> findImages(String id);

}
