package com.example.ktp_api.service;

import com.example.ktp_api.dto.KtpRequestDTO;
import com.example.ktp_api.dto.KtpResponseDTO;
import java.util.List;

public interface KtpService {

    KtpResponseDTO       create(KtpRequestDTO dto);
    List<KtpResponseDTO> getAll();
    KtpResponseDTO       getById(Integer id);
    KtpResponseDTO       update(Integer id, KtpRequestDTO dto);
    void                 delete(Integer id);
}
