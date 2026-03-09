package com.example.ktp_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class KtpRequestDTO {

    @NotBlank(message = "Nomor KTP tidak boleh kosong")
    @Size(min = 16, max = 16, message = "Nomor KTP harus tepat 16 digit")
    @Pattern(regexp = "\\d{16}", message = "Nomor KTP harus berupa 16 angka")
    private String nomorKtp;

    @NotBlank(message = "Nama lengkap tidak boleh kosong")
    @Size(max = 255, message = "Nama lengkap maksimal 255 karakter")
    private String namaLengkap;

    @NotBlank(message = "Alamat tidak boleh kosong")
    @Size(max = 500, message = "Alamat maksimal 500 karakter")
    private String alamat;

    @NotNull(message = "Tanggal lahir tidak boleh kosong")
    private LocalDate tanggalLahir;

    @NotBlank(message = "Jenis kelamin tidak boleh kosong")
    @Pattern(regexp = "LAKI_LAKI|PEREMPUAN",
            message = "Jenis kelamin harus LAKI_LAKI atau PEREMPUAN")
    private String jenisKelamin;
}