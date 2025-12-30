package com.tracker.newtracker.repositories;

import com.tracker.newtracker.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository // automate the object creation
public interface TaskRepository extends JpaRepository<Task, UUID> {

    // jpa contains all the things
    List<Task> findByTaskList_Id(UUID taskListId);

    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID taskId);


    void deleteAllByTaskListIdAndId(UUID taskListId,UUID id);

}
