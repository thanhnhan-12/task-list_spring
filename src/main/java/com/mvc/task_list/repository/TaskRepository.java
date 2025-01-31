package com.mvc.task_list.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mvc.task_list.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByName(String name);

    @Query("SELECT T FROM Task T WHERE T.user.id = :userId AND (:name IS NULL OR LOWER(T.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Task> findTaskByUserId(@Param("userId") int userId, @Param("name") String name);
}