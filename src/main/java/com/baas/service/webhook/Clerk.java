package com.baas.service.webhook;

import com.baas.service.entity.BaaS;
import com.baas.service.entity.repository.BaaSRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.Map;
@RestController
@RequestMapping("/webhook")
public class Clerk {
    @Autowired
    private BaaSRepo baasRepo;

    @PostMapping
    @Transactional
    public void handleClerkDelete(@RequestBody Map<String, Object> payload) {
        try {
            String eventType = (String) payload.get("type");

            if ("user.deleted".equals(eventType)) {
                Map<String, Object> data = (Map<String, Object>) payload.get("data");
                String userId = (String) data.get("id");

                if (userId != null) {
                    baasRepo.deleteByUserId(userId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
