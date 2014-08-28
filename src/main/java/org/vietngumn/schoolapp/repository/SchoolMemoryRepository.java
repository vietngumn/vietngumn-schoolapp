package org.vietngumn.schoolapp.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vietngumn.schoolapp.domain.School;

public class SchoolMemoryRepository implements SchoolRepository {

	private Map<String, School> schools;

	public SchoolMemoryRepository(final Map<String, School> schools) {
		this.schools = Collections.unmodifiableMap(schools);
	}

	@Override
	public synchronized School save(School school) {

		Map<String, School> modifiableSchools = new HashMap<String, School>(schools);
		modifiableSchools.put(school.getSchoolId(), school);
		this.schools = Collections.unmodifiableMap(modifiableSchools);

		return school;
	}

	@Override
	public synchronized void delete(String schoolId) {
		if (schools.containsKey(schoolId)) {
			Map<String, School> modifiableSchools = new HashMap<String, School>(schools);
			modifiableSchools.remove(schoolId);
			this.schools = Collections.unmodifiableMap(modifiableSchools);
		}
	}

	@Override
	public School findById(String schoolId) {
		return schools.get(schoolId);
	}

	@Override
	public List<School> findAll() {
		return Collections.unmodifiableList(new ArrayList<School>(schools.values()));
	}
}
