package com.hanzelkajan.bookborrowing.mappers.impl;

import com.hanzelkajan.bookborrowing.domain.dto.ReservationDto;
import com.hanzelkajan.bookborrowing.domain.entities.ReservationEntity;
import com.hanzelkajan.bookborrowing.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl implements Mapper<ReservationEntity, ReservationDto> {

    private ModelMapper modelMapper;

    public ReservationMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public ReservationDto mapTo(ReservationEntity reservationEntity) {
        return modelMapper.map(reservationEntity, ReservationDto.class);
    }

    @Override
    public ReservationEntity mapFrom(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, ReservationEntity.class);
    }
}