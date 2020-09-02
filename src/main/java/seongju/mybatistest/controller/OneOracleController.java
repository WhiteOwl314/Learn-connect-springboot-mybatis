package seongju.mybatistest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import seongju.mybatistest.service.OneOracleService;

@RestController
@RequestMapping(value="/one")
public class OneOracleController {

    @Autowired
    private OneOracleService oneOracleService;

    @RequestMapping(
            value = "/getHostname",
            method = RequestMethod.GET
    )
    private String getHostName(){
        return this.oneOracleService.getHostName();
    }
}
