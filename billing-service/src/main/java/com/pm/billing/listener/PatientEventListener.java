package com.pm.billing.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;
import com.google.protobuf.InvalidProtocolBufferException;

@Service
public class PatientEventListener {

    @KafkaListener(topics = "patient", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(byte[] message) {
        try {
            PatientEvent event = PatientEvent.parseFrom(message);
            System.out.println("Received Patient Event: " + event);
            System.out.println("Patient ID: " + event.getPatientId());
            System.out.println("Event Type: " + event.getEventType());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            System.err.println("Failed to parse Protobuf message");
        }
    }
}
