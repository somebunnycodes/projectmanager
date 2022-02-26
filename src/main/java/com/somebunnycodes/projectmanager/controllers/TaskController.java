package com.somebunnycodes.projectmanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.somebunnycodes.projectmanager.models.Project;
import com.somebunnycodes.projectmanager.models.Task;
import com.somebunnycodes.projectmanager.models.User;
import com.somebunnycodes.projectmanager.services.ProjectService;
import com.somebunnycodes.projectmanager.services.TaskService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ProjectService projectService;

	@GetMapping("/projects/{id}/tasks")
	public String listBooks(@PathVariable Long id, HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		Project project = projectService.getProject(id);
		List<Task> tasks = taskService.getTasksByProjectId(id);
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		model.addAttribute("tasks", tasks);
		model.addAttribute("task", new Task());
		return "task.jsp";
	}
	
	@PostMapping("/projects/{id}/tasks")
	public String postTask(@PathVariable Long id, @Valid @ModelAttribute("task") Task newTask, BindingResult result, Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		Project project = projectService.getProject(id);
		newTask.setProject(project);
		Task createdTask = taskService.createTask(newTask, result);
		if (createdTask == null) {
			model.addAttribute("user", user);
			model.addAttribute("project", project);
			model.addAttribute("tasks", taskService.getTasksByProjectId(id));
			return "task.jsp";
		}
		return "redirect:/projects/{id}/tasks";
	}
	
}
