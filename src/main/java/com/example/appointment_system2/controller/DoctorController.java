package com.example.appointment_system2.controller;

import com.example.appointment_system2.model.Appointment;
import com.example.appointment_system2.model.Doctor;
import com.example.appointment_system2.service.AppointmentService;
import com.example.appointment_system2.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/doctors") // Tüm endpointler /doctors ile başlar
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    // Tüm doktorları getir
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Yeni doktor ekle
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor)
    {
        return doctorService.saveDoctor(doctor);
    }

    // Doktor bilgilerini güncelle (PUT /doctors/{id})
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor)
    {
        Optional<Doctor> updated = doctorService.updateDoctor(id, doctor);
        return updated
                .map(ResponseEntity::ok) // varsa 200 + body
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody(); // yoksa 404

        // Gerçek projede burada 404 hatası döndürmen daha uygun olur
    }

    //http://localhost:8080/doctors/2
    // Belirli bir doktorun randevularını getir
    @GetMapping("/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    //http://localhost:8080/doctors/appointments/4/status?status=Beklemede
    // Randevu durumu güncelle (Onayla/Reddet)
    @PutMapping("/appointments/{appointmentId}/status")
    public Appointment updateAppointmentStatus(@PathVariable Long appointmentId, @RequestParam String status) {
        return appointmentService.updateAppointmentStatus(appointmentId, status);
    }

    //http://localhost:8080/doctors/appointments/5/note?note=tekrar muayene edilecek
    // Not ekle
    @PutMapping("/appointments/{appointmentId}/note")
    public Appointment addNote(@PathVariable Long appointmentId, @RequestParam String note) {
        return appointmentService.addNoteToAppointment(appointmentId, note);
    }

}
