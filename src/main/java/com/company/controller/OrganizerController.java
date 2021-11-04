package com.company.controller;

import com.company.entity.Category;
import com.company.entity.Event;
import com.company.entity.Ticket;
import com.company.entity.Type;
import com.company.service.ICategoryService;
import com.company.service.IEventService;
import com.company.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/organizator")
@Controller
public class OrganizerController {

    @Autowired
    IEventService eventService;
    @Autowired
    ITypeService iTypeService;
    @Autowired
    ICategoryService iCategoryService;

    @RequestMapping("/")
    public String showOrganizerMainPage (){
        return "/organizer/organizer";
    }

    @RequestMapping("/newEvent")
    public String showNewEventPage (Model modelEvent, Model modelType, Model modelCategory, Model modelStatus){
        Event event = new Event();
        modelEvent.addAttribute("event", event);
        
        List<Type> types = iTypeService.getAll();
        modelType.addAttribute("types", types);

        List<Category> categories = iCategoryService.getAll();
        modelCategory.addAttribute("categories", categories);

        Ticket.Status[] statuses = Ticket.Status.values();
        modelStatus.addAttribute("statuses", statuses);

        return "/organizer/organizer-event-new";
    }

    @RequestMapping ("/newEvent/saveEvent")
    public String saveEvent (@ModelAttribute("event") Event event){

        Type userType = iTypeService.getByName(event.getType().getName());
        event.setType(userType);

        Category userCategory = iCategoryService.getByName(event.getCategory().getName());
        event.setCategory(userCategory);

        event.setOwner(getUsername());

        eventService.save(event);

        return "redirect:/organizator/";
    }

    @RequestMapping("/listOfEvent")
    public String showEventsList(Model model){
        List<Event> events = eventService.getUserEvents (getUsername());
        model.addAttribute("events", events);

        return "organizer/organizer-event-list";
    }

    @RequestMapping("removeEvent")
    public String showRemove (Model model){
        List<Event> events = eventService.getUserOldEvents (getUsername());
        model.addAttribute("events", events);

        return "organizer/organizer-event-remove-old";
    }

    @RequestMapping("/removeEvent/remove")
    public String remove (Model model){
        eventService.deleteUserOldEvent (getUsername());
        return "organizer/organizer-event-remove-old";
    }

    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
