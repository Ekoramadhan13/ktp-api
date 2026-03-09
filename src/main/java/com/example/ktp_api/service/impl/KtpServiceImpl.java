package com.example.ktp_api.service.impl;

import com.example.ktp_api.dto.KtpRequestDTO;
import com.example.ktp_api.dto.KtpResponseDTO;
import com.example.ktp_api.entity.Ktp;
import com.example.ktp_api.mapper.KtpMapper;
import com.example.ktp_api.repository.KtpRepository;
import com.example.ktp_api.service.KtpService;
import com.example.ktp_api.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KtpServiceImpl implements KtpService {

    private final KtpRepository ktpRepository;
    private final KtpMapper     ktpMapper;

    @Override
    @Transactional
    public KtpResponseDTO create(KtpRequestDTO dto) {
        // Cek duplikat nomor KTP
        if (ktpRepository.existsByNomorKtp(dto.getNomorKtp())) {
            throw new IllegalArgumentException(ApiConstants.KTP_DUPLICATE);
        }
        Ktp saved = ktpRepository.save(ktpMapper.toEntity(dto));
        return ktpMapper.toResponseDTO(saved);
    }

    @Override
    public List<KtpResponseDTO> getAll() {
        return ktpRepository.findAll()
                .stream()
                .map(ktpMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KtpResponseDTO getById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(ApiConstants.KTP_NOT_FOUND));
        return ktpMapper.toResponseDTO(ktp);
    }

    @Override
    @Transactional
    public KtpResponseDTO update(Integer id, KtpRequestDTO dto) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(ApiConstants.KTP_NOT_FOUND));

        // Cek duplikat nomor KTP di ID lain
        if (ktpRepository.existsByNomorKtpAndIdNot(dto.getNomorKtp(), id)) {
            throw new IllegalArgumentException(ApiConstants.KTP_DUPLICATE);
        }

        ktpMapper.updateEntity(ktp, dto);
        return ktpMapper.toResponseDTO(ktpRepository.save(ktp));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!ktpRepository.existsById(id)) {
            throw new NoSuchElementException(ApiConstants.KTP_NOT_FOUND);
        }
        ktpRepository.deleteById(id);
    }
}
