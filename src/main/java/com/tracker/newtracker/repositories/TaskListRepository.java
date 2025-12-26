package com.tracker.newtracker.repositories;

import com.tracker.newtracker.models.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

    //jpa contains all the things

}
