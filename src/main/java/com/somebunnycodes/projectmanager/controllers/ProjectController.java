package com.somebunnycodes.projectmanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.somebunnycodes.projectmanager.models.Project;
import com.somebunnycodes.projectmanager.models.User;
import com.somebunnycodes.projectmanager.services.ProjectService;

@Controller
public class ProjectController {

	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectService projectService;

	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		List<Project> userProjects = projectService.getUserProjects(user);
		List<Project> nonUserProjects = projectService.getNonUserProjects(user);
		model.addAttribute("user", user);
		model.addAttribute("userProjects", userProjects);
		model.addAttribute("nonUserProjects", nonUserProjects);
		return "dashboard.jsp";
	}
	
	@GetMapping("/projects/new")
	public String createProject(HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/dashboard";
		}
		model.addAttribute("project", new Project());
		model.addAttribute("user", user);
		return "addProject.jsp";
	}
	
	@GetMapping("/projects/{id}")
	public String showProject(HttpSession session, @PathVariable Long id, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		Project project = projectService.getProject(id);
		model.addAttribute("project", project);
		return "project.jsp";
	}
	
	@GetMapping("/projects/edit/{id}")
	public String editProject(HttpSession session, @PathVariable Long id, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		Project project = projectService.getProject(id);
		model.addAttribute("project", project);
		model.addAttribute("user", user);
		return "editProject.jsp";
	}
	
	@GetMapping("/projects/{id}/join")
	public String joinProject(HttpSession session, @PathVariable Long id) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		projectService.joinProject(id,  user);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/projects/{id}/leave")
	public String leaveProject(HttpSession session, @PathVariable Long id) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		projectService.leaveProject(id, user);
		return "redirect:/dashboard";
	}

	@PostMapping("/projects")
	public String postProject(@Valid @ModelAttribute("project") Project newProject, BindingResult result, Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		Project project = projectService.createProject(newProject, result);
		if (project == null) {
			model.addAttribute("project", newProject);
			return "addProject.jsp";
		}
		return "redirect:/dashboard";
	}
	
	@PutMapping("/projects/{id}")
	public String putProject(@PathVariable Long id, @Valid @ModelAttribute("project") Project updatedProject, BindingResult result, Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/dashboard";
		}
		
		// Set book ID to the one in the path so right book is updated
		updatedProject.setId(id);
		
		Project project = projectService.updateProject(updatedProject, result);
		if (project == null) {
			model.addAttribute("project", updatedProject);
			return "editProject.jsp";
		}
		return "redirect:/dashboard";
	}
}
