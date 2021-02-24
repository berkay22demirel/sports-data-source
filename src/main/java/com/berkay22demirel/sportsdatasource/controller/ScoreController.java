package com.berkay22demirel.sportsdatasource.controller;

import com.berkay22demirel.sportsdatasource.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private DataSourceService iddaaDataSourceService;

    @RequestMapping(value = "/get-all")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(iddaaDataSourceService.getScores(), HttpStatus.OK);
    }

}
