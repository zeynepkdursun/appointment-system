package com.example.appointment_system2.service;

import com.example.appointment_system2.model.Patient;
import com.example.appointment_system2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Spring'e bu sınıfın bir servis olduğunu söylüyoruz
public class PatientService {

    @Autowired // Spring, PatientRepository nesnesini otomatik enjekte eder
    private PatientRepository patientRepository;

    // Tüm hastaları listele
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // ID ile hasta getir
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    // Yeni hasta ekle veya var olanı güncelle
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> updatePatient(Long id, Patient updatedPatient)
    {
        return patientRepository.findById(id).map(existingPatient ->
        {
            existingPatient.setName(updatedPatient.getName());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setPassword(updatedPatient.getPassword());
            return patientRepository.save(existingPatient);
        });
    }

    // Hasta sil
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    // Email ile hasta bul (girişte kullanılabilir)
    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }


}
