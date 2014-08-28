package org.vietngumn.schoolapp.repository;

import java.util.List;

import org.vietngumn.schoolapp.domain.School;

public interface SchoolRepository {

	School save(School School);

	void delete(String schoolId);

	School findById(String schoolId);

	List<School> findAll();
}
