package com.example.communications.service;

import com.example.communications.domain.pvps.PvpsRepository;
import com.example.communications.dto.pvps.PvpsMainResponseDto;
import com.example.communications.dto.pvps.PvpsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PvpsService {

    private PvpsRepository pvpsRepository;

    // pvps 변수 모두 출력
    @Transactional(readOnly = true)
    public List<PvpsMainResponseDto> findAll() {
        return pvpsRepository.findAllBy()
                .map(PvpsMainResponseDto::new)
                .collect(Collectors.toList());
    }

    // 최초 전적 저장
    @Transactional
    public String save(PvpsSaveRequestDto pvpsSaveRequestDto) {
        return pvpsRepository.save(pvpsSaveRequestDto.toEntity()).getId();
    }

    // 전적 업데이트
    @Transactional
    public String update(PvpsSaveRequestDto pvpsSaveRequestDto) {
        String name = pvpsSaveRequestDto.getName();
        String id = pvpsRepository.findByName(name).get().getId();
        pvpsSaveRequestDto.setId(id);

        return pvpsRepository.save(pvpsSaveRequestDto.toEntity()).getId();
    }
}
