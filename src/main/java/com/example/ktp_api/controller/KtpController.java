package com.example.ktp_api.controller;

import com.example.ktp_api.dto.KtpRequestDTO;
import com.example.ktp_api.dto.KtpResponseDTO;
import com.example.ktp_api.model.ApiResponse;
import com.example.ktp_api.service.KtpService;
import com.example.ktp_api.util.ApiConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ktp")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class KtpController {

    private final KtpService ktpService;

    // ── POST /ktp ── Tambah data KTP baru
    @PostMapping
    public ResponseEntity<ApiResponse<KtpResponseDTO>> create(
            @Valid @RequestBody KtpRequestDTO dto) {

        KtpResponseDTO result = ktpService.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(ApiConstants.KTP_CREATED, result));
    }

    // ── GET /ktp ── Ambil semua data KTP
    @GetMapping
    public ResponseEntity<ApiResponse<List<KtpResponseDTO>>> getAll() {
        return ResponseEntity.ok(
                ApiResponse.success(ApiConstants.KTP_FETCH_ALL, ktpService.getAll()));
    }

    // ── GET /ktp/{id} ── Ambil data KTP by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KtpResponseDTO>> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                ApiResponse.success(ApiConstants.KTP_FETCH_ONE, ktpService.getById(id)));
    }

    // ── PUT /ktp/{id} ── Update data KTP
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<KtpResponseDTO>> update(
            @PathVariable Integer id,
            @Valid @RequestBody KtpRequestDTO dto) {

        return ResponseEntity.ok(
                ApiResponse.success(ApiConstants.KTP_UPDATED, ktpService.update(id, dto)));
    }

    // ── DELETE /ktp/{id} ── Hapus data KTP
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(
            @PathVariable Integer id) {

        ktpService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success(ApiConstants.KTP_DELETED, null));
    }
}
