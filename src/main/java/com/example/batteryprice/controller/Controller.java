package com.example.batteryprice.controller;


import com.example.batteryprice.datafetchers.BatteryDatafetcher;
import com.example.batteryprice.model.Battery;
import com.example.batteryprice.model.User;
import com.example.batteryprice.repository.UserRepo;
import com.example.batteryprice.service.BatteryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class BatteryController {
    @Autowired
    BatteryRepo batteryRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BatteryDatafetcher batteryDatafetcher;

    @GetMapping
    public @ResponseBody Battery findBattery(@RequestParam String name) {
        return  batteryRepo.find(name);
    }

    @GetMapping("/users")
    public @ResponseBody List<User> getUsers() {
        return userRepo.findAll();
    }



}
