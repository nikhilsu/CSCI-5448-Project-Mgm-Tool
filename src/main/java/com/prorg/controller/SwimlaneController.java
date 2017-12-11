package com.prorg.controller;


import com.prorg.helper.Constants;
import com.prorg.helper.result.Response;
import com.prorg.model.Storyboard;
import com.prorg.service.StoryboardService;
import com.prorg.service.SwimlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(Constants.Route.ADD_SWIMLANE)
public class SwimlaneController {

    private SwimlaneService swimlaneService;
    private StoryboardService storyboardService;

    @Autowired
    public SwimlaneController(SwimlaneService swimlaneService, StoryboardService storyboardService){
        this.swimlaneService = swimlaneService;
        this.storyboardService = storyboardService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addSwimlane(HttpServletRequest request, Model model) throws Exception {
        String name = request.getParameter("name");
        int storyboardId = Integer.valueOf(request.getParameter("storyboardId"));
        Response response = storyboardService.getStoryboardById(storyboardId);
        Storyboard storyboard = (Storyboard) response.data();
        Response swimlaneCreation = swimlaneService.createSwimlane(name, storyboard);
        model.addAttribute(Constants.ModelAttributes.MESSAGE, swimlaneCreation.isSuccessful() ? "Success" : "Failed");
        return Constants.RedirectPage.INDEX;
    }
}