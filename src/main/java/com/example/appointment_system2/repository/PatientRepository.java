package com.example.appointment_system2.repository;

import com.example.appointment_system2.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // İsteğe bağlı özel sorgular buraya eklenebilir
    Patient findByEmail(String email); // örnek: login kontrolü için
}
