package com.baas.service.service;


import com.baas.service.entity.BaaS;
import com.baas.service.entity.UserLink;
import com.baas.service.entity.repository.BaaSRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BaasService {

    @Autowired
    private BaaSRepo baaSRepo;

    @Transactional
    public BaaS createBaaS(String key, Boolean value, String userid) {
        BaaS baas = new BaaS();
        baas.setUserId(userid);
        baas.setKey(key);
        baas.setValue(value);
        return baaSRepo.save(baas);
    }

    @Transactional
    public void deleteBaaS(String id, String userid) {
        if (baaSRepo.findById(id).isPresent()) {
            if (baaSRepo.findById(id).get().getUserId().equals(userid)) {
                baaSRepo.deleteById(id);
            }
        }
    };

    @Transactional
    public Optional<BaaS> updateBaaS(BaaS baas, String userId, String id) {
        Optional<BaaS> existingBaaS = baaSRepo.findById(id);
        if (existingBaaS.isPresent() && existingBaaS.get().getUserId().equals(userId)) {
            BaaS entityToUpdate = existingBaaS.get();
            entityToUpdate.setKey(baas.getKey());
            entityToUpdate.setValue(baas.getValue());
            return Optional.of(baaSRepo.save(entityToUpdate));
        }
        return Optional.empty();
    }

    public Optional<BaaS> findBaaSByIds(String id, String userid) {
        if (baaSRepo.findById(id).isPresent()) {
            if (baaSRepo.findById(id).get().getUserId().equals(userid)) {
                return baaSRepo.findById(id);
            }
        }
        return null;
    }

    public Optional<UserLink> returnLink(Optional<BaaS> baas) {
        UserLink userLink = new UserLink();
        userLink.setKey(baas.get().getKey());
        userLink.setValue(baas.get().getValue());
        return Optional.of(userLink);
    }

}
