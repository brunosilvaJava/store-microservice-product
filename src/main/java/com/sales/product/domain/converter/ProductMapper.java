package com.sales.product.domain.converter;

import java.util.List;

import com.sales.product.dto.ProductDto;
import com.sales.product.domain.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto entityToDto(ProductEntity productEntity);

    List<ProductDto> entitysToDtos(List<ProductEntity> productEntities);

    ProductEntity dtoToEntity(ProductDto productDto);

    List<ProductEntity> dtosToEntities(List<ProductDto> productDtos);

}
