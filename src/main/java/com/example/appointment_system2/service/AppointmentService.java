package com.example.appointment_system2.service;

import com.example.appointment_system2.dto.AppointmentRequest;
import com.example.appointment_system2.model.Appointment;
import com.example.appointment_system2.model.Doctor;
import com.example.appointment_system2.model.Patient;
import com.example.appointment_system2.repository.AppointmentRepository;
import com.example.appointment_system2.repository.DoctorRepository;
import com.example.appointment_system2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired  //repository'ye baglanir
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    // Tüm randevuları getir
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // ID ile randevu getir
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
        //return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment createAppointment(AppointmentRequest request)
    {
        Patient patient = patientService.getPatientById(request.getPatientId());
        Doctor doctor = doctorService.getDoctorById(request.getDoctorId());

        if (patient == null || doctor == null)
        {
            throw new IllegalStateException("Patient or Doctor id not found.");
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(LocalDateTime.parse(request.getAppointmentDate()));
        appointment.setNote(request.getNote());
        appointment.setStatus("Beklemede");
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        // Aynı doktor ve aynı tarih-saat için mevcut randevuları kontrol et
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorIdAndAppointmentDate(doctor.getId(), appointment.getAppointmentDate());

        if (!existingAppointments.isEmpty()) {
            throw new IllegalStateException("Bu saatte zaten bir randevu var.");
        }


        return appointmentRepository.save(appointment);
    }


    // Yeni randevu oluştur veya mevcut olanı güncelle
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Randevu sil
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Belirli bir hastanın tüm randevuları
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Belirli bir doktorun randevuları
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Doktorun onay bekleyen randevuları
    public List<Appointment> getPendingAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorIdAndStatus(doctorId, "BEKLEMEDE");
    }

    // Doktorun randevulara not eklemesi
    public Appointment addNoteToAppointment(Long appointmentId, String note) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);;

        if (appointment == null) {
            throw new RuntimeException("Appointment not found with ID: " + appointmentId);
        }
        appointment.setNote(note); // Randevuya doktor notu ekle
        return appointmentRepository.save(appointment); // Güncellenmiş randevuyu kaydet
    }
    // Doktor statusu degistirir/randevuyu onaylar (onaylandı, reddedildi, beklemede)
    public Appointment updateAppointmentStatus(Long id, String status) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);;

        if (appointment == null) {
            throw new RuntimeException("Appointment not found with ID: " + id);
        }
        appointment.setStatus(status); // Randevuya doktor notu ekle
        return appointmentRepository.save(appointment); // Güncellenmiş randevuyu kaydet
    }

}
