package com.company.controller;

import com.company.entity.Category;
import com.company.entity.Event;
import com.company.entity.Type;
import com.company.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexPageController {

    @Autowired
    IEventService iEventService;
    @Autowired
    ICategoryService iCategoryService;
    @Autowired
    ITypeService iTypeService;

    @Autowired
    IRegisterEventService registerEventService;


    @RequestMapping("/")
    public String showIndexPage (Model eventsModel, Model searchModel, Model typesModel, Model categoriesModel) {

        List<Event> eventList = iEventService.getAll();
        eventsModel.addAttribute("eventList", eventList);

        Event searchEvent = new Event();
        searchModel.addAttribute("searchEvent", searchEvent );

        List<Type> typeList = iTypeService.getAll();
        typesModel.addAttribute("typeList", typeList);

        List<Category> categoryList = iCategoryService.getAll();
        categoriesModel.addAttribute("categoryList", categoryList);

        return "index";
    }

    @RequestMapping("/search")
    public String searchCustomers(Model eventsModel, Model searchModel, Model typeModel, Model categoryModel, @ModelAttribute("event") Event searchEvent) {

        int categoryId = iCategoryService.getByName(searchEvent.getCategory().getName()).getId();
        int typeId = iTypeService.getByName(searchEvent.getType().getName()).getId();

        List<Event> eventList = iEventService.search(searchEvent.getName(), searchEvent.getPlace(), typeId, categoryId, searchEvent.getDate());

        eventsModel.addAttribute("eventList", eventList);
        searchModel.addAttribute("searchEvent", searchEvent);
        typeModel.addAttribute("typeList", searchEvent.getType());
        categoryModel.addAttribute("categoryList", searchEvent.getCategory());
        return "index";
    }

    @RequestMapping(value="/signUp/{id}", method = RequestMethod.POST)
    public String signUp(@PathVariable int id, Model model) {
        boolean signed = registerEventService.signUp(id, getUsername());
        model.addAttribute("signed", signed);

        return "/user/user-signIn-result";
    }

    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

}