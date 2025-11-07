package com.example.appointment_system2.dto;
//dto: data transfer object
import lombok.Data;
//Yani DTO = Client’e uygun paketleme. Service içinde Entity ↔ DTO dönüşümleri yapılır çoğunlukla.
@Data
public class AppointmentRequest {
    private String appointmentDate;
    //private String status;
    private String note;
    private Long patientId;
    private Long doctorId;
}
/*
Controller = kapı
Service = beyin
Repository = veritabanıyla bağlantı
DTO = dışarıya çıkacak veriyi temizleyen maske
*/