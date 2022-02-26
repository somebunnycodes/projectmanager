package com.somebunnycodes.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.somebunnycodes.projectmanager.models.Project;
import com.somebunnycodes.projectmanager.models.User;
import com.somebunnycodes.projectmanager.repositories.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;
	
    public Project createProject(Project newProject, BindingResult result) {
    	if (result.hasErrors()) {
    		return null;
    	}
    	projectRepo.save(newProject);
    	return newProject;
    }

    public void joinProject(Long projectId, User user) {
    	Project project = projectRepo.findById(projectId).get();
		project.addTeamMember(user);
		projectRepo.save(project);
    }

    public void leaveProject(Long projectId, User user) {
		Project project = projectRepo.findById(projectId).get();
		project.removeTeamMember(user);
		projectRepo.save(project);
    }

    public Project updateProject(Project updatedProject) {
    	projectRepo.save(updatedProject);
    	return updatedProject;
    }
    
    public Project updateProject(Project updatedProject, BindingResult result) {
    	if (result.hasErrors()) {
    		return null;
    	}
    	projectRepo.save(updatedProject);
    	return updatedProject;
    }
    
    public List<Project> getAllProjects() {
    	return projectRepo.findAll();
    }
    
    public List<Project> getUserProjects(User u) {
    	return projectRepo.findUserProjects(u);
    }
    
    public List<Project> getNonUserProjects(User u) {
    	return projectRepo.findNonUserProjects(u);
    }
    
    public Project getProject(Long id) {
    	Optional<Project> optProject = projectRepo.findById(id);
    	return optProject.get();
    }
}
