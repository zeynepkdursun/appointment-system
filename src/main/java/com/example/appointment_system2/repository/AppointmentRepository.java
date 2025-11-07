package com.example.appointment_system2.repository;

import com.example.appointment_system2.model.Appointment;
import com.example.appointment_system2.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Belirli bir hastaya ait randevuları getir
    List<Appointment> findByPatientId(Long patientId);

    // Belirli bir doktora ait randevuları getir
    List<Appointment> findByDoctorId(Long doctorId);

    // Belirli bir doktora ait belirli durumdaki randevular (örneğin: BEKLEMEDE)
    List<Appointment> findByDoctorIdAndStatus(Long doctorId, String status);

    // Belirli bir doktora ait belirli zamandaki randevular
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

    // findById metoduna gerek yok, JpaRepository zaten içeriyor
}
