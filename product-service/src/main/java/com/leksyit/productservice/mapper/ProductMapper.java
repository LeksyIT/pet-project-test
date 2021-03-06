package com.leksyit.productservice.mapper;

import com.leksyit.productservice.dto.ProductDto;
import com.leksyit.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productTitle", source = "product.title")
    @Mapping(target = "productPrice", source = "product.price")
    @Mapping(target = "productVisited", source = "product.visit")
    ProductDto productToProductDto(Product product);

    @Mapping(target = "id", source = "productDto.productId")
    @Mapping(target = "title", source = "productDto.productTitle")
    @Mapping(target = "price", source = "productDto.productPrice")
    @Mapping(target = "visit", source = "productDto.productVisited")
    Product productDtoToProduct(ProductDto productDto);

    List<ProductDto> listOfProductsToListOfProductsDto(List<Product> listOfProducts);

}
