package io.github.tuliochamorra.model.repository;

import io.github.tuliochamorra.model.entity.Teacher;
import io.github.tuliochamorra.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {



}
