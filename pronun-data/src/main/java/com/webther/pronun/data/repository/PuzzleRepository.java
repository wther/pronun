package com.webther.pronun.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webther.pronun.data.entity.PuzzleEntity;

/**
 * Repository for accessing puzzles
 * 
 * @author Barnabas
 */
public interface PuzzleRepository extends JpaRepository<PuzzleEntity, Long>{
    
	/**
	 * Find puzzle by puzzle code
	 */
	public List<PuzzleEntity> findByPuzzleCode(String puzzleCode);
}
