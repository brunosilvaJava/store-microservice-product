package com.sales.product.service;

import java.util.List;

import com.sales.product.domain.converter.ProductMapper;
import com.sales.product.dto.ProductDto;
import com.sales.product.domain.model.ProductEntity;
import com.sales.product.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public void save(ProductDto productDto){
        productDto.setCode(getNextCode());
        productRepository.save(ProductMapper.INSTANCE.dtoToEntity(productDto));
    }

    @Transactional
    public void update(ProductDto productDto){
        ProductDto productTransaction = findByProductCode(productDto.getCode());
        productTransaction.setDescription(productDto.getDescription());
        productTransaction.setValue(productDto.getValue());
    }

    @Transactional
    public void delete(Long productId){
        productRepository.deleteById(productId);
    }

    public List<ProductDto> findAll(){
        return ProductMapper.INSTANCE.entitysToDtos(productRepository.findAll());
    }

    public ProductDto findByProductCode (Long productCode){
        return ProductMapper.INSTANCE.entityToDto(getProductEntityByCode(productCode)); // TODO CRIAR PRODUCT NOT FOUND EXCEPTION
    }
    private Long getNextCode(){
        Long nextCode = 0l;
        ProductEntity productEntity = productRepository.findTopByOrderByCodeDesc().orElse(null);
        if(productEntity != null){
            ProductDto productDto = ProductMapper.INSTANCE.entityToDto(productEntity);
            nextCode = productDto.getCode();
        }
        return nextCode+1;
    }

    private ProductEntity getProductEntityByCode (final Long productCode) {
        return productRepository.findByCode(productCode).orElseThrow(RuntimeException::new);
    }

}
