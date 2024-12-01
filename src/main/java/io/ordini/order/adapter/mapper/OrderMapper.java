package io.ordini.order.adapter.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ClientAddressMapper.class)
public interface OrderMapper {
}
