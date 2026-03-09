package com.example.ktp_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class KtpResponseDTO {
    private Integer id;
    private String  nomorKtp;
    private String  namaLengkap;
    private String  alamat;
    private LocalDate tanggalLahir;
    private String  jenisKelamin;
}