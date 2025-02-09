package com.ltd.coders.software.firestore_with_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() {
        if (FirebaseApp.getApps().isEmpty()) {
        	//using eclipse variables
            FirebaseApp.initializeApp();
        }
        return FirestoreClient.getFirestore();
    }
}
