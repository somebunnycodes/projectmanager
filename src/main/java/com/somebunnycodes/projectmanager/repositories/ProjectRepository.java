package com.somebunnycodes.projectmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.somebunnycodes.projectmanager.models.Project;
import com.somebunnycodes.projectmanager.models.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	List<Project> findAll();
	
	@Query("SELECT p FROM Project p LEFT JOIN p.teamMembers u WHERE u = ?1 OR p.teamLeader = ?1")
	List<Project> findUserProjects(User u);

	@Query("SELECT p FROM Project p LEFT JOIN p.teamMembers u WHERE u != ?1 OR u = null AND p.teamLeader != ?1")
	List<Project> findNonUserProjects(User u);
}
