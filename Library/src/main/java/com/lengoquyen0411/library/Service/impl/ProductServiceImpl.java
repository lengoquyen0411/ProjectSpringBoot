package com.lengoquyen0411.library.Service.impl;

import com.lengoquyen0411.library.DTO.ProductDTO;
import com.lengoquyen0411.library.Model.Product;
import com.lengoquyen0411.library.Repo.ProductRepo;
import com.lengoquyen0411.library.Service.ProductService;
import com.lengoquyen0411.library.Utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ImageUpload imageUpload;

    /*Admin*/
    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepo.findAll();
        List<ProductDTO> productDTOList = transfer(products);
        return productDTOList;
    }

    @Override
    public Product save(MultipartFile imageProduct, ProductDTO productDTO) {
        try {
            Product product = new Product();
            if (imageProduct == null) {
                product.setImage(null);
            } else {
                if (imageUpload.uploadImage(imageProduct)) {
                    System.out.println("Upload Successfully!");
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setCategory(productDTO.getCategory());
            product.setCostPrice(productDTO.getCostPrice());
            product.setCurrentQuantity(productDTO.getCurrentQuantity());
            product.set_activated(true);
            product.set_deleted(false);
            return productRepo.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product update(MultipartFile imageProduct, ProductDTO productDTO) {
        try {
            Product product = productRepo.getById(productDTO.getId());
            if (imageProduct == null) {
                product.setImage(product.getImage());
            } else {
                if (imageUpload.checkExisted(imageProduct) == false) {
                    imageUpload.uploadImage(imageProduct);
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setSalePrice(productDTO.getSalePrice());
            product.setCostPrice(productDTO.getCostPrice());
            product.setCurrentQuantity(productDTO.getCurrentQuantity());
            product.setCategory(productDTO.getCategory());
            return productRepo.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepo.getById(id);
        product.set_deleted(true);
        product.set_activated(false);
        productRepo.save(product);
    }

    @Override
    public void enableById(Long id) {
        Product product = productRepo.getById(id);
        product.set_activated(true);
        product.set_deleted(false);
        productRepo.save(product);
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepo.getById(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCurrentQuantity(product.getCurrentQuantity());
        productDTO.setCategory(product.getCategory());
        productDTO.setSalePrice(product.getSalePrice());
        productDTO.setCostPrice(product.getCostPrice());
        productDTO.setImage(product.getImage());
        productDTO.setDeleted(product.is_deleted());
        productDTO.setActivated(product.is_activated());
        return productDTO;
    }

    @Override
    public Page<ProductDTO> pageProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 6);
        List<ProductDTO> products = transfer(productRepo.findAll());
        Page<ProductDTO> productPages = toPage(products, pageable);
        return productPages;
    }

    @Override
    public Page<ProductDTO> searchProducts(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo, 6);
        List<ProductDTO> productDtoList = transfer(productRepo.searchProductsList(keyword));
        Page<ProductDTO> products = toPage(productDtoList, pageable);
        return products;
    }

    private Page toPage(List<ProductDTO> list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }


    private List<ProductDTO> transfer(List<Product> products) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setCurrentQuantity(product.getCurrentQuantity());
            productDTO.setCategory(product.getCategory());
            productDTO.setSalePrice(product.getSalePrice());
            productDTO.setCostPrice(product.getCostPrice());
            productDTO.setImage(product.getImage());
            productDTO.setDeleted(product.is_deleted());
            productDTO.setActivated(product.is_activated());
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    /*Customer*/
    @Override
    public List<Product> getAllProducts() {
        return productRepo.getAllProducts();
    }

    @Override
    public List<Product> listViewProducts() {
        return productRepo.listViewProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.getById(id);
    }

    @Override
    public List<Product> getRelatedProducts(Long categoryId) {
        return productRepo.getRelatedProducts(categoryId);
    }

    @Override
    public List<Product> getProductsInCategory(Long categoryId) {
        return productRepo.getProductsInCategory(categoryId);
    }

    @Override
    public List<Product> filterHighPrice() {
        return productRepo.filterHighPrice();
    }

    @Override
    public List<Product> filterLowPrice() {
        return productRepo.filterLowPrice();
    }
}
