package com.example.appointment_system2.repository;

import com.example.appointment_system2.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByEmail(String email); // örneğin giriş için kullanılabilir
}
