/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author n.mamboundou
 */
@Named(value = "imagesViewMB")
@ViewScoped
public class ImagesViewMB  implements Serializable{

    /**
     * Creates a new instance of ImagesViewMB
     */
    public ImagesViewMB() {
    }
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        for (int i = 1; i <7 ; i++) {
            images.add("wak"+i+".PNG");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}
