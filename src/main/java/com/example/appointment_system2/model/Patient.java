package com.example.appointment_system2.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity                     // Bu sınıf bir veritabanı tablosudur
@Data                       // Getter, setter, toString vb. otomatik oluştur
@NoArgsConstructor          // Parametresiz constructor
@AllArgsConstructor         // Tüm alanlar için constructor
public class Patient {

    @Id                     // Primary key tanımı
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //Id'nin otomatik olarak artmasi icin
    private Long id;   //Primary Key

    private String name;

    private String email;

    private String password;

    // Hasta geçmiş randevularını görür
    // Bir hasta birden cok randevuya sahip olabilir

    //mappedBy = "patient":
    //iliskinin sahip tarafi Appointment sinifidir
    //Appointment tablosundaki patient_id foreign key’e göre iliski kurar

    //cascade = CascadeType.ALL:
    //Hasta silinirse → tüm randevuları da otomatik silinir.
    //Hasta kaydedilirse → randevular da otomatik kaydedilir.

    //orphanRemoval = true:
    //appointments listesinden bir randevu kaldirilirsa, bu randevu veritabanindan da silinir.
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointments;
    //Lombok sayesinde setter/getter yazmaya gerek yok
}
