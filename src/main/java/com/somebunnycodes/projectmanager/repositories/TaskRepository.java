package com.somebunnycodes.projectmanager.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.somebunnycodes.projectmanager.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> findByProjectId(Long projectId);

}
