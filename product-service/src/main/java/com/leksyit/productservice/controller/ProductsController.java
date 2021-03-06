package com.leksyit.productservice.controller;


import com.leksyit.productservice.dto.ProductDto;
import com.leksyit.productservice.entity.Product;
import com.leksyit.productservice.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    private final ProductServiceImpl productsService;
    private final UserController userController;

    private static final String PRODUCT = "product";
    private static final String PRODUCTS = "products";
    private static final String REDIRECT_PRODUCTS = "redirect:/products/?size=5&word=&minPrice=&maxPrice=";
    private static final String ID = "id";

    @GetMapping
    public String showProductsList(Model model,
                                   @RequestParam(value = "word", required = false) String word,
                                   @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                   @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                   ProductDto productDto,
                                   Pageable pageable) {

        Specification<Product> specification = productsService.settingSpecification(word, minPrice, maxPrice);
        Page<Product> modelsPages = productsService.getProductWithPagingAndFiltering(specification, pageable);
        List<ProductDto> productList = productsService.getListProductsFromPageable(specification, pageable);

        String login = userController.getLogin();

        model.addAttribute(PRODUCTS, productList);
        model.addAttribute(PRODUCT, productDto);
        model.addAttribute("word", word);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("pageNumbers", productsService.preparePageInt(pageable.getPageNumber(), modelsPages.getTotalPages()));
        model.addAttribute("login", login);
        return PRODUCTS;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = PRODUCT) ProductDto productDto, Model model) {
        productsService.updateProduct(productDto);

        model.addAttribute(PRODUCTS, productsService.getAllProducts());
        return REDIRECT_PRODUCTS;
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = ID) Long id) {
        productsService.addNewVisited(id);
        model.addAttribute(PRODUCT, productsService.getById(id));
        return "product-page";
    }

    //    @Secured(value="ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = ID) Long id) {
        productsService.deleteProduct(id);
        return REDIRECT_PRODUCTS;
    }

    //    @Secured(value="ROLE_ADMIN")
    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable(value = ID) Long id,
                                @ModelAttribute(value = PRODUCT) ProductDto productDto,
                                Model model) {
        productsService.addProductById(id, productDto);

        model.addAttribute(PRODUCTS, productsService.getAllProducts());
        return REDIRECT_PRODUCTS;
    }

    //    @Secured(value="ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String openUpdateStudentPage(@PathVariable(value = ID) Long id,
                                        Model model) {

        model.addAttribute(PRODUCT, productsService.getById(id));
        return "edit-product";
    }

    @GetMapping("/sorted")
    public String sortedMinToMax(Model model,
                                 @ModelAttribute(value = PRODUCT) ProductDto productDto,
                                 Pageable pageable) {

        Specification<Product> specification = Specification.where(null);
        Page<Product> modelsPages = productsService.getProductWithPagingAndFiltering(specification, pageable);

        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("pageNumbers", productsService.preparePageInt(pageable.getPageNumber(), modelsPages.getTotalPages()));
        model.addAttribute(PRODUCTS, productsService.getListProductsFromPageableAndNullSpecification(pageable));
        return PRODUCTS;
    }
}
