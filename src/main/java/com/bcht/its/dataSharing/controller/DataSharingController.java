package com.bcht.its.dataSharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by JXX on 2017/8/9.
 */
@Controller
public class DataSharingController {
   // @RequestMapping(name="BchtDataSharing",method = RequestMethod.GET)
    public String index(){
        return "error.html";
    }
}
