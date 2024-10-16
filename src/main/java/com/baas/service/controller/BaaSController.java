package com.baas.service.controller;

import com.baas.service.entity.BaaS;
import com.baas.service.entity.UserLink;
import com.baas.service.entity.repository.BaaSRepo;
import com.baas.service.service.BaasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class BaaSController {

    @Autowired
    private BaasService baasService;

    @Autowired
    private BaaSRepo baaSRepo;

    @GetMapping
    public List<BaaS> findAll(){
        return baaSRepo.findAll();
    }

    @GetMapping("/{userId}")
    public List<BaaS> findByUserid(@PathVariable String userId){
        return baaSRepo.findByUserId(userId);
    }

    @GetMapping("/{userId}/{id}")
    public Optional<BaaS> findById(@PathVariable String userId, @PathVariable String id){
        return baasService.findBaaSByIds(id, userId);
    }

    @GetMapping("/data/{id}")
    public Optional<UserLink> findById(@PathVariable String id) {
        return baasService.returnLink(baaSRepo.findById(id));
    }

    @PostMapping("/{userId}/create")
    public ResponseEntity create(@RequestBody BaaS entity, @PathVariable String userId) {
        BaaS createdBaas = baasService.createBaaS(entity.getKey(), entity.getValue(), userId);
        return ResponseEntity.ok(createdBaas);
    }

    @PutMapping("/{userId}/update/{id}")
    public ResponseEntity<?> update(@RequestBody BaaS entity, @PathVariable String userId, @PathVariable String id) {
        Optional<BaaS> updatedBaaS = baasService.updateBaaS(entity, userId, id);
        if (updatedBaaS.isPresent()) {
            return ResponseEntity.ok(updatedBaaS.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BaaS entry not found or user ID does not match.");
        }
    }

    @DeleteMapping("/{userId}/delete/{id}")
    public void delete(@PathVariable String userId, @PathVariable String id) {
        baasService.deleteBaaS(id, userId);
    }


}
