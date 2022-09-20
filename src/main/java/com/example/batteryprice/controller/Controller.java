package com.example.batteryprice.controller;


import com.example.batteryprice.datafetchers.BatteryDatafetcher;
import com.example.batteryprice.model.Battery;
import com.example.batteryprice.model.User;
import com.example.batteryprice.repository.UserRepo;
import com.example.batteryprice.service.BatteryRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Data
@AllArgsConstructor

class BatteryController {
    private final BatteryRepo batteryRepo;
    private final UserRepo userRepo;
    private final BatteryDatafetcher batteryDatafetcher;

    @GetMapping
    public @ResponseBody Battery findBattery(@RequestParam String name) {
        return  batteryRepo.find(name);
    }

    @GetMapping("/users")
    public @ResponseBody List<User> getUsers() {
        return userRepo.findAll();
    }



}
