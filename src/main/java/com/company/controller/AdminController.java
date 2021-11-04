package com.company.controller;

import com.company.entity.Category;
import com.company.entity.Type;
import com.company.service.ICategoryService;
import com.company.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    ITypeService iTypeService;
    @Autowired
    ICategoryService iCategoryService;

    @RequestMapping("/")
    public String showAdminMainPage (Model typesModel, Model typeModel, Model categoriesModel, Model categoryModel){

        List<Type> typeList = iTypeService.getAll();
        typesModel.addAttribute("typeList", typeList);

        Type type = new Type();
        typeModel.addAttribute("type", type);

        List<Category> categoryList = iCategoryService.getAll();
        categoriesModel.addAttribute("categoryList", categoryList);

        Category category = new Category();
        categoryModel.addAttribute("category", category);

        return "/admin/admin";
    }

    @RequestMapping ("/saveType")
    public String saveType (@ModelAttribute("type") Type type) {

        iTypeService.save(type);

        return "redirect:/admin/";
    }

    @RequestMapping ("/saveCategory")
    public String saveCategory (@ModelAttribute("category") Category category){

        iCategoryService.save(category);

        return "redirect:/admin/";
    }

}
