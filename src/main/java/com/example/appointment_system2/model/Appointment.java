
package com.example.appointment_system2.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Id'nin otomatik olarak artması için
    private Long id;

    private LocalDateTime appointmentDate; // Randevu tarihi ve saati

    private String status; // Beklemede, Onaylandı, Reddedildi gibi durumlar

    private String note; // Doktor notu

    @ManyToOne // Bir hasta, birden fazla randevu alabilir
    private Patient patient;

    @ManyToOne // Bir doktor, birden fazla randevuya sahip olabilir
    private Doctor doctor;
}
