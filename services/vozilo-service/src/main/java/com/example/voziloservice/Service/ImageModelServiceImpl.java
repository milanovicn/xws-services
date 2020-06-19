package com.example.voziloservice.Service;
import com.example.voziloservice.model.ImageModel;
import com.example.voziloservice.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ImageModelServiceImpl implements ImageModelService {

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public ImageModel findByCarId(String id) {
        String searchBy = new String("vozilo"+id);

        for(ImageModel im : imageRepository.findAll()) {
            if(im.getName().contains(searchBy)) {

                return im;
                //dodavace se u kolekciju i vraca ce kolekciju kad bude vise slika
            }
        }
        return null;
    }

    @Override
    public Collection<ImageModel> findImages(String id) {
        Collection<ImageModel> ret = imageRepository.findAll();
        String searchBy = new String("vozilo"+id);

        for(ImageModel im : ret) {
            if(!im.getName().contains(searchBy)) {

                ret.remove(im);
            }
        }


        return ret;
    }
}
