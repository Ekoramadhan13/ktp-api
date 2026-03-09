package com.example.ktp_api.repository;

import com.example.ktp_api.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KtpRepository extends JpaRepository<Ktp, Integer> {

    // Cari data berdasarkan nomorKtp
    Optional<Ktp> findByNomorKtp(String nomorKtp);

    // Cek apakah nomorKtp sudah ada
    boolean existsByNomorKtp(String nomorKtp);

    // Cek nomorKtp sudah dipakai ID lain (validasi update)
    boolean existsByNomorKtpAndIdNot(String nomorKtp, Integer id);
}
