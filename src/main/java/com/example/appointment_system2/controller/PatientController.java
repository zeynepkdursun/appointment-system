package com.example.appointment_system2.controller;

import com.example.appointment_system2.model.Patient;
import com.example.appointment_system2.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Bu sınıfın REST Controller olduğunu belirtir, @Controller + @ResponseBody birleşimidir
@RequestMapping("/patients") // Tüm endpoint'ler /patients ile başlar
public class PatientController {

    @Autowired // Spring, PatientService nesnesini otomatik olarak enjekte eder
    private PatientService patientService;

    // Tüm hastaları getir (GET /patients)
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // ID ile hasta getir (GET /patients/{id})
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // Yeni hasta ekle (POST /patients)
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    // Hasta bilgilerini güncelle (PUT /patients/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Optional<Patient> updated = patientService.updatePatient(id, patient);
        /*if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get()); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404
        }*/
        return updated
                .map(ResponseEntity::ok) // varsa 200 + body
                .orElseGet(() -> ResponseEntity.notFound().build()); // yoksa 404

    }

    // Hasta sil (DELETE /patients/{id})
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id)
    {
        patientService.deletePatient(id);
    }

    //http://localhost:8080/patients/email/can33@gmail.com
    // Email ile hasta getir (örnek: login) (GET /patients/email/{email})
    @GetMapping("/email/{email}")
    public Patient getPatientByEmail(@PathVariable String email)
    {
        return patientService.getPatientByEmail(email);
    }
}
