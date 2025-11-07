package com.example.appointment_system2.controller;

import com.example.appointment_system2.dto.AppointmentRequest;
import com.example.appointment_system2.model.Appointment;
import com.example.appointment_system2.model.Doctor;
import com.example.appointment_system2.model.Patient;
import com.example.appointment_system2.service.AppointmentService;
import com.example.appointment_system2.service.DoctorService;
import com.example.appointment_system2.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    // Tüm randevuları getir
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Yeni randevu oluştur (Hasta tarafından)
    /*@PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }*/

    //Id ile appointment getir
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }
    //appointment olustur
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.createAppointment(request));
        //return appointmentService.createAppointment(request);
    }



    // Hasta ID'ye göre randevuları getir (geçmiş görüntüleme)
    //http://localhost:8080/appointments/patient/7
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    // Appointment sil (DELETE /appointment/{id})
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
