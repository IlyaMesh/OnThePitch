package com.onthepitch.backend.controllers;

import com.onthepitch.backend.commands.ClubForm;
import com.onthepitch.backend.converter.ClubToClubForm;
import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ClubController {
    private ClubService clubService;

    private ClubToClubForm clubToClubForm;

    @Autowired
    public void setClubToClubForm(ClubToClubForm clubToClubForm) {
        this.clubToClubForm = clubToClubForm;
    }

    @Autowired
    public void setClubService(ClubService clubService) {
        this.clubService = clubService;
    }

    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/club/list";
    }

    @RequestMapping({"/club/list", "/club"})
    public String listProducts(Model model) {
        model.addAttribute("clubs", clubService.listAll());
        return "club/list";
    }

    @RequestMapping("/club/show/{id}")
    public String getProduct(@PathVariable String id, Model model) {
        model.addAttribute("club", clubService.getById(Long.valueOf(id)));
        return "club/show";
    }

    @RequestMapping("club/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Club club = clubService.getById(Long.valueOf(id));
        ClubForm clubForm = clubToClubForm.convert(club);

        model.addAttribute("clubForm", clubForm);
        return "club/clubform";
    }

    @RequestMapping("/club/new")
    public String newProduct(Model model) {
        model.addAttribute("clubForm", new ClubForm());
        return "club/clubform";
    }

    @RequestMapping(value = "/club", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ClubForm clubForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "club/clubform";
        }

        Club savedClub = clubService.saveOrUpdatePostForm(clubForm);

        return "redirect:/club/show/" + savedClub.getClub_id();
    }

    @RequestMapping("/club/delete/{id}")
    public String delete(@PathVariable String id) {
        clubService.delete(Long.valueOf(id));
        return "redirect:/club/list";
    }
}
