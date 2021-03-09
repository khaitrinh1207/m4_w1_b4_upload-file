package controller;

import DAO.ProductDAO;
import model.Product;
import model.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
    private Environment environment;

    @GetMapping
    public ModelAndView showAll(){
        List<Product> productList = new ProductDAO().findAll();
        return new ModelAndView("home", "products",productList);
    }

    @GetMapping("edit")
    public ModelAndView showFormEdit(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = new ProductDAO().findByid(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @PostMapping("edit")
    public ModelAndView edit(@ModelAttribute ProductForm productForm, @RequestParam int id) throws IOException {
        MultipartFile mf = productForm.getImage();
        String image = mf.getOriginalFilename();
        String thu_muc_anh = environment.getProperty("file_upload").toString();
        FileCopyUtils.copy(mf.getBytes(), new File(thu_muc_anh + image));
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getDescription(), image);
        new ProductDAO().update(id,product);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }

    @GetMapping("delete")
    public ModelAndView del(@RequestParam int id){
        new ProductDAO().delete(id);
        return new ModelAndView("redirect:/products");
    }
}
