package com.Quang.demo.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Quang.demo.domain.Product;
import com.Quang.demo.service.ProductService;
import com.Quang.demo.service.UploadService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

  private final ProductService productService;
  private final UploadService uploadService;

  public ProductController(ProductService productService, UploadService uploadService) {
    this.productService = productService;
    this.uploadService = uploadService;
  }

  // @RequestParam: 1. lấy queryString 2. lấy data của input theo name của input
  // trong form gửi lên
  @GetMapping("/admin/product")
  public String getProducts(Model model, @RequestParam("page") Optional<String> pageOptional) {

    int page = 1;
    try {
      if (pageOptional.isPresent()) {
        page = Integer.parseInt(pageOptional.get());
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

    // tính số trang
    // long totalPrd = this.productService.handleCountAllProducts();
    // long pages = totalPrd / 5;

    Pageable pageable = PageRequest.of(page - 1, 5);
    Page<Product> prds = this.productService.handleGetProducts(pageable);
    long pages = prds.getTotalPages();

    List<Product> products = prds.getContent(); // convert từ Page sang List để view có thể forEach được
    model.addAttribute("productsList", products);
    model.addAttribute("currentPage", page);
    model.addAttribute("pages", pages);
    return "admin/product/show";
  }

  @GetMapping("/admin/product/create")
  public String createProductPage(Model model) {
    model.addAttribute("newProduct", new Product());
    return "admin/product/create";
  }

  @PostMapping("/admin/product/create")
  // modelAttribute: lấy newUser từ form trong view
  // requestParam: lấy input name: avatarFileUpload trong view
  public String createUser(Model model, @ModelAttribute("newProduct") @Valid Product newProduct,
      BindingResult productResult,
      @RequestParam("imageFileUpload") MultipartFile file) {

    // validate
    List<FieldError> errors = productResult.getFieldErrors();
    for (FieldError error : errors) {
      System.out.println(">>>>>" + error.getField() + " - " + error.getDefaultMessage());
    }

    if (productResult.hasErrors()) {
      return "admin/product/create";
    }

    // image
    String image = this.uploadService.handleSaveUploadFile(file, "products");
    newProduct.setImage(image);

    // save
    this.productService.handleSaveProduct(newProduct);
    // redirect: chuyển hướng đến link khác
    return "redirect:/admin/product";
  }

  @GetMapping("/admin/product/{id}")
  public String getMethodName(Model model, @PathVariable long id) {
    Product product = this.productService.handleGetByID(id);
    model.addAttribute("product", product);
    return "admin/product/detail";
  }

  @GetMapping("/admin/product/delete/{id}")
  public String getProductDeletePage(Model model, @PathVariable long id) {
    model.addAttribute("id", id);
    Product product = new Product();
    product.setId(id);
    model.addAttribute("product", product);
    return "admin/product/delete";
  }

  @PostMapping("/admin/product/delete")
  public String deleteproduct(Model model, @ModelAttribute("product") Product product) {
    this.productService.handleDeleteProduct(product.getId());
    System.out.println("show:" + product.getId());
    // redirect: chuyển hướng đến link khác
    return "redirect:/admin/product";
  }

  @GetMapping("/admin/product/update/{id}")
  public String getProductUpdatePage(Model model, @PathVariable long id) {
    Product product = this.productService.handleGetByID(id);
    model.addAttribute("product", product);
    return "admin/product/update";
  }

  @PostMapping("/admin/product/update/")
  public String updateProduct(Model model, @ModelAttribute("product") Product product,
      @RequestParam("imageFileUpload") MultipartFile file) {
    Product currentProduct = this.productService.handleGetByID(product.getId());
    if (currentProduct != null) {
      // sửa và lưu user do ko có hàm update :((
      currentProduct.setName(product.getName());
      currentProduct.setPrice(product.getPrice());
      currentProduct.setDetailDesc(product.getDetailDesc());
      currentProduct.setShortDesc(product.getShortDesc());
      currentProduct.setQuantity(product.getQuantity());
      currentProduct.setFactory(product.getFactory());
      currentProduct.setTarget(product.getTarget());

      String image = this.uploadService.handleSaveUploadFile(file, "products");
      currentProduct.setImage(image);

      this.productService.handleSaveProduct(currentProduct);
    }
    return "redirect:/admin/product";
  }
}
