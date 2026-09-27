package com.elpsykongrood.projects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    String projects(Model model) {
        model.addAttribute("projects", projectService.findProjects());
        return "pages/projects";
    }
}
