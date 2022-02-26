package com.somebunnycodes.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.somebunnycodes.projectmanager.models.Task;
import com.somebunnycodes.projectmanager.repositories.TaskRepository;

@Service
public class TaskService {

	Logger logger = LoggerFactory.getLogger(TaskService.class);
	
    @Autowired
    private TaskRepository taskRepository;
	
    public Task createTask(Task newTask, BindingResult result) {
    	if (result.hasErrors()) {
    		return null;
    	}
    	taskRepository.save(newTask);
    	return newTask;
    }

    public Task updateTask(Task updatedTask, BindingResult result) {
    	if (result.hasErrors()) {
    		return null;
    	}
    	taskRepository.save(updatedTask);
    	return updatedTask;
    }
    
    public List<Task> getTasksByProjectId(Long id) {
    	return taskRepository.findByProjectId(id);
    }
    
    public Optional<Task> getTask(Long id) {
    	return taskRepository.findById(id);
    }
}
