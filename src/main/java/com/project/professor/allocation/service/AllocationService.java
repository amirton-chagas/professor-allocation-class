package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.CourseRepository;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class AllocationService {

	private final AllocationRepository repository;
	private final ProfessorRepository professorRepository;
	private final CourseRepository courseRepository;

	public AllocationService(AllocationRepository repository, ProfessorRepository professorRepository, CourseRepository courseRepository) {
		super();
		this.repository = repository;
		this.professorRepository = professorRepository;
		this.courseRepository = courseRepository;
	}

	public List<Allocation> findAll() {
		return repository.findAll();
	}

	public Allocation findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Allocation create(Allocation allocation) {
		allocation.setId(null);

		return saveInternal(allocation);
	}

	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		if (!existsId(id)) {
			return null;
		}

		return saveInternal(allocation);
	}

	private Allocation saveInternal(Allocation allocation) {
		if (hasCollision(allocation)) {
			throw new RuntimeException();
		} else {
			Allocation updated = repository.save(allocation);
			
			updated.setProfessor(professorRepository.findById(updated.getProfessorId()).orElse(null));
			updated.setCourse(courseRepository.findById(updated.getCourseId()).orElse(null));
			
			return updated;
		}
	}

	public void delete(Long id) {
		if (!existsId(id)) {
			return;
		}

		repository.deleteById(id);
	}

	public void deleteAll() {
		repository.deleteAllInBatch();
	}

	private boolean existsId(Long id) {
		return id != null && repository.existsById(id);
	}

	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = repository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}
}
