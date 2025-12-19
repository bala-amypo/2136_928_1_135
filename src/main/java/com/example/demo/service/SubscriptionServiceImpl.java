package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Subscription;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    List<Subscription> subs = new ArrayList<>();

    public Subscription subscribe(Subscription sub) {
        subs.add(sub);
        return sub;
    }

    public List<Subscription> getAll() {
        return subs;
    }
}
