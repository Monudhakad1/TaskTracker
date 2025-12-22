package com.tracker.newtracker.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="task_list")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "taskList" , cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Task> taskList;

    @Column(name="created", nullable=false, updatable=false)
    private LocalDateTime created;

    @Column(name="updated", nullable=false)
    private LocalDateTime updated;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList1 = (TaskList) o;
        return Objects.equals(id, taskList1.id) && Objects.equals(title, taskList1.title) && Objects.equals(description, taskList1.description) && Objects.equals(taskList, taskList1.taskList) && Objects.equals(created, taskList1.created) && Objects.equals(updated, taskList1.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, taskList, created, updated);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskList=" + taskList +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
