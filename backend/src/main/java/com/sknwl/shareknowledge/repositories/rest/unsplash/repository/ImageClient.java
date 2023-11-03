package com.sknwl.shareknowledge.repositories.rest.unsplash.repository;

import com.sknwl.shareknowledge.repositories.rest.unsplash.model.ImageModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "photos", url = "https://api.unsplash.com/photos")
public interface ImageClient {

    @RequestMapping(method = RequestMethod.GET, value = "/random")
    ImageModel getRandomPhoto(@RequestParam String client_id,
                              @RequestParam String query,
                              @RequestParam String orientation,
                              @RequestParam String content_filter);
}
